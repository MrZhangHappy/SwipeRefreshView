package com.zkk.swiperefreshview;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zkk.mylibrary.view.SwipeRefreshView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshView swipeRefreshView;
    
    private ListView listView;

    private ArrayAdapter<String> mAdapter;

    private ArrayList<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshView = (SwipeRefreshView) findViewById(R.id.swipeRefreshView);


       
//        swipeRefreshView.setColorSchemeResources(Color.parseColor("#FFFFFF"));
        //设置进度条的颜色主题，最多能设置四种 加载颜色是循环播放的，只要没有完成刷新就会一直循环，
        // holo_blue_bright>holo_green_light>holo_orange_light>holo_red_light
        swipeRefreshView.setColorSchemeColors(Color.GREEN);
        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        swipeRefreshView.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        swipeRefreshView.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置圆圈的大小
        swipeRefreshView.setSize(SwipeRefreshLayout.DEFAULT);
        //设置下拉刷新的监听
        swipeRefreshView.setOnRefreshListener(this);
        listView = (ListView) findViewById(R.id.listView);

        for (int i = 0; i < 20; i++) {
            mData.add("这是第"+i+"条数据");
        }

        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mData);

        listView.setAdapter(mAdapter);


        swipeRefreshView.setOnLoadListener(new SwipeRefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mData.add("这是加载数据");
                        mAdapter.notifyDataSetChanged();
                        swipeRefreshView.setLoading(false);
                    }
                },2000);
            }
        });


    }


    @Override
    public void onRefresh() {

        if(swipeRefreshView.isRefreshing()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    mData.add("这是刷新数据");
                    mAdapter.notifyDataSetChanged();
                    swipeRefreshView.setRefreshing(false);
                }
            },2000);
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
