package com.my.parallaxrecyclerview;


import android.view.View;
import android.widget.TextView;

public class MyViewHolder extends ParallaxViewHolder {
    public ParallaxImageView img;
    public  TextView tv_name;
    public  TextView tv_name_en;

    @Override
    public int getParallaxImageId() {
        return R.id.iv;
    }

    public MyViewHolder(View itemView) {
        super(itemView);
        img = (ParallaxImageView) itemView.findViewById(R.id.iv);
        tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        tv_name_en = (TextView) itemView.findViewById(R.id.tv_name_en);
    }
}
