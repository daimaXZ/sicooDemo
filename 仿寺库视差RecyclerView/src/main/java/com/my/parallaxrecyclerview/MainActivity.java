package com.my.parallaxrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    private ParallaxRecyclerView rcv;
    private ArrayList<ItemObj> list;
    private MyAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv = (ParallaxRecyclerView) findViewById(R.id.rcv);
        linearLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        ItemObj itemObj = new ItemObj();
        itemObj.enName = "Luffy";
        itemObj.name =  "蒙奇D.路飞";
        itemObj.iv = R.mipmap.luffy;
        list.add(itemObj);

        ItemObj itemObj1 = new ItemObj();
        itemObj1.enName = "Nami";
        itemObj1.name =  "娜美";
        itemObj1.iv = R.mipmap.nami;
        list.add(itemObj1);

        ItemObj itemObj2 = new ItemObj();
        itemObj2.enName = "Roronoa Zoro";
        itemObj2.name =  "罗罗诺亚•索隆";
        itemObj2.iv = R.mipmap.suro;
        list.add(itemObj2);

        ItemObj itemObj3 = new ItemObj();
        itemObj3.enName = "Sanji";
        itemObj3.name =  "山治";
        itemObj3.iv = R.mipmap.sanji;
        list.add(itemObj3);

        ItemObj itemObj4 = new ItemObj();
        itemObj4.enName = "Usopp";
        itemObj4.name =  "乌索普";
        itemObj4.iv = R.mipmap.usupu;
        list.add(itemObj4);

        ItemObj itemObj5 = new ItemObj();
        itemObj5.enName = "Tony Tony Choppe";
        itemObj5.name =  "托尼托尼—乔巴";
        itemObj5.iv = R.mipmap.choba;
        list.add(itemObj5);

        ItemObj itemObj6 = new ItemObj();
        itemObj6.enName = "NicoRobin";
        itemObj6.name =  "妮可·罗宾";
        itemObj6.iv = R.mipmap.robi;
        list.add(itemObj6);

        ItemObj itemObj7 = new ItemObj();
        itemObj7.enName = "Franky";
        itemObj7.name =  "弗兰奇";
        itemObj7.iv = R.mipmap.fragke;
        list.add(itemObj7);

        ItemObj itemObj8 = new ItemObj();
        itemObj8.enName = "Brook";
        itemObj8.name =  "布鲁克";
        itemObj8.iv = R.mipmap.brooker;
        list.add(itemObj8);

        myAdapter = new MyAdapter(this, list);
        rcv.setAdapter(myAdapter);

    }


    public class ItemObj{
        public int iv;
        public String name;
        public String enName;
    }
}
