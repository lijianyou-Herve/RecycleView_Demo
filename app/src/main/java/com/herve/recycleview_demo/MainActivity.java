package com.herve.recycleview_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private LinearLayout line_Addmore;
    private float beforeHeight = 1f;
    private int height = 100;
    private String TAG = "MainActivity_Herve";
    private int itemHeign = 140;
    //
    private int cloumnNum = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initData();
        initListener();

    }


    private void initData() {
        GridLayoutManager girdLayoutManager = new GridLayoutManager(this, cloumnNum);
        recyclerView.setLayoutManager(girdLayoutManager);
        adapter = new MyAdapter(this);
        recyclerView.setAdapter(adapter);

        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeign);
        recyclerView.setLayoutParams(layoutParams);


        updateRecycleView(1);

        recyclerView.setLayoutParams(layoutParams);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    private void initUI() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        line_Addmore = (LinearLayout) findViewById(R.id.line_Addmore);

    }

    private void initListener() {


        adapter.setOnItemClicklistener(new MyAdapter.OnItemClicklistener() {
            @Override
            public void onItemClickListener(final View view, int position) {
                Log.i(TAG, "onItemClickListener: position=BBB" + position);
                if (position == 3) {
                    if (line_Addmore.getVisibility() == View.GONE) {

                        Log.i(TAG, "onItemClickListener: position=AAAAA" + position);

                        line_Addmore.setVisibility(View.VISIBLE);
                        line_Addmore.setOnClickListener(MainActivity.this);
                        Glide.with(MainActivity.this).load(R.mipmap.community_details_more_selected).into((ImageView) view);

                        updateRecycleView(2);
                    } else {
                        line_Addmore.setVisibility(View.GONE);
                        line_Addmore.setOnClickListener(MainActivity.this);
                        adapter.removeAll();
                        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeign);
                        recyclerView.setLayoutParams(layoutParams);

                        updateRecycleView(1);
                        Glide.with(MainActivity.this).load(R.mipmap.community_details_more_selected_touch).into((ImageView) view);
                    }

                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.line_Addmore:
                updateRecycleView(2);
                Log.i(TAG, "onClick: recyclerView.getHeight=" + height);
                Log.i(TAG, "onClick: getHeight=" + beforeHeight);
                float scale = (beforeHeight / height);
                break;

        }


    }


    //更新RecycleView
    public void updateRecycleView(int column) {
        beforeHeight = recyclerView.getHeight();
        int num = recyclerView.getChildCount() / cloumnNum;
        height = itemHeign * num + itemHeign * column;
        Log.i(TAG, "onClick:height= " + height);
        if (column == 1) {
            height = itemHeign;
        }
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        recyclerView.setLayoutParams(layoutParams);
        for (int i = 0; i < cloumnNum * column; i++) {
            adapter.insertData(1);
        }
    }

    public AnimationSet animation(float start) {
        AnimationSet set = new AnimationSet(true);
        Log.i(TAG, "animation: start=" + start);
        //初始化
        Animation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, start, 1.0f);
        //设置动画时间
        scaleAnimation.setDuration(500);
        set.addAnimation(scaleAnimation);
        return set;
    }
}
