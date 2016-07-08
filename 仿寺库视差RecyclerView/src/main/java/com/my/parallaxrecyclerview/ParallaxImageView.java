package com.my.parallaxrecyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by mohamedzakaria on 6/16/16.
 */
public class ParallaxImageView extends ImageView {
    private final float DEFAULT_PARALLAX_RATIO = 2f;

    private final boolean DEFAULT_CENTER_CROP = true;

    private boolean needToTranslate = true;
    private ParallaxImageListener listener;

    private int rowHeight = -1;
    private int rowYPos = -1;
    private int recyclerViewHeight = -1;
    private int recyclerViewYPos = -1;
    private boolean shouldCenterCrop = DEFAULT_CENTER_CROP;
    private float parallaxValue = DEFAULT_PARALLAX_RATIO;

    public interface ParallaxImageListener {
        int[] requireValuesForTranslate();
    }

    public ParallaxImageView(Context context) {
        super(context);
        init(context, null);
    }

    public ParallaxImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ParallaxImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setScaleType(ScaleType.MATRIX);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ensureTranslate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        ensureTranslate();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        ensureTranslate();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        ensureTranslate();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        ensureTranslate();
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        ensureTranslate();
    }
    public void reuse() {
        this.needToTranslate = true;
    }

    public void centerCrop(boolean enable) {
        this.shouldCenterCrop = enable;
    }

    public void setParallaxValue(float parallaxValue) {
        this.parallaxValue = parallaxValue;
    }

    public void setListener(ParallaxImageListener listener) {
        this.listener = listener;
    }

    public ParallaxImageListener getListener() {
        return listener;
    }

    public synchronized boolean doTranslate() {
        if (getDrawable() == null) {
            return false;
        }

        if (getListener() != null && getValues()) {
            calculateAndMove();
            return true;
        } else {
            return false;
        }
    }
    private boolean ensureTranslate() {
        if (needToTranslate) {
            needToTranslate = !doTranslate();
        }
        return !needToTranslate;
    }

    private boolean getValues() {
        int[] values = getListener().requireValuesForTranslate();
        if (values == null)
            return false;

        this.rowHeight = values[0];
        this.rowYPos = values[1];
        this.recyclerViewHeight = values[2];
        this.recyclerViewYPos = values[3];
        return true;
    }

    private void calculateAndMove() {
        float distanceFromCenter = (recyclerViewYPos + recyclerViewHeight) / 2 - rowYPos;
        int imageHeight = getDrawable().getIntrinsicHeight();
        float scale = 1;
        if (shouldCenterCrop) {
            scale = recomputeImageMatrix();
            imageHeight *= scale;
        }
        float difference = imageHeight - rowHeight;
        float move = (distanceFromCenter / recyclerViewHeight) * difference * DEFAULT_PARALLAX_RATIO;
        moveTo((move / 2) - (difference / 2), scale);
    }

    private float recomputeImageMatrix() {
        float scale;
        final int viewWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        final int viewHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        final int drawableWidth = getDrawable().getIntrinsicWidth();
        final int drawableHeight = getDrawable().getIntrinsicHeight();

        if (drawableWidth * viewHeight > drawableHeight * viewWidth) {
            scale = (float) viewHeight / (float) drawableHeight;
        } else {
            scale = (float) viewWidth / (float) drawableWidth;
        }

        return scale;
    }

    private void moveTo(float move, float scale) {
        Matrix imageMatrix = getImageMatrix();
        if (scale != 1) {
            imageMatrix.setScale(scale, scale);
        }

        float[] matrixValues = new float[9];
        imageMatrix.getValues(matrixValues);
        float current = matrixValues[Matrix.MTRANS_Y];
        imageMatrix.postTranslate(0, move - current);

        setImageMatrix(imageMatrix);
        invalidate();
    }
}
