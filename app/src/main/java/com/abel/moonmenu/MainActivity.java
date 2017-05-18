package com.abel.moonmenu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private final int radius1 = 500;
    private final String TAG = "CircleMenu";
    @InjectView(R.id.iv1)
    ImageView iv1;
    @InjectView(R.id.iv2)
    ImageView iv2;
    @InjectView(R.id.iv3)
    ImageView iv3;
    @InjectView(R.id.iv4)
    ImageView iv4;
    @InjectView(R.id.iv5)
    ImageView iv5;
    @InjectView(R.id.iv)
    ImageView iv;
    private List<ImageView> imageViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        imageVIewArray();
    }

    private void imageVIewArray() {
        imageViews.add(iv1);
        imageViews.add(iv2);
        imageViews.add(iv3);
        imageViews.add(iv4);
        imageViews.add(iv5);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.iv)
    public void onClick() {
        Boolean isShowing = (Boolean) iv.getTag();
        if (null == isShowing || isShowing == false) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv, "rotation", 0, 45);
            objectAnimator.setDuration(500);
            objectAnimator.start();
            iv.setTag(true);
            showSectorMenu();
            /** 打开下面的注释可以看淡垂直显示的菜单效果*/
//            showVerticalMenu();
        } else {
            iv.setTag(false);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv, "rotation", 45, 0);
            objectAnimator.setDuration(500);
            objectAnimator.start();
            closeSectorMenu();
            /** 关闭淡垂直显示的菜单效果*/
//            closeVerticalMenu();
        }

    }

    private void closeSectorMenu() {
        int avgAngle = (90 / imageViews.size() - 1);
        for (int i = 0; i < imageViews.size(); i++) {
            int angle = avgAngle * i;
            /*** 此处使用PointF*/
            PointF point = new PointF();
            point.x = (float) Math.cos(angle * (Math.PI / 180)) * radius1;
            /*** 此处y坐标是负的，因为纵坐标向下为正，而我们要在上面显示，大家可以更改符号看效果*/
            point.y = (float) -Math.sin(angle * (Math.PI / 180)) * radius1;
            Log.i(TAG, point.toString());
            ObjectAnimator oax = ObjectAnimator.ofFloat(imageViews.get(i), "translationX", point.x, 0);
            ObjectAnimator oay = ObjectAnimator.ofFloat(imageViews.get(i), "translationY", point.y, 0);
            /*** 此处在平移的基础上加了旋转动画*/
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageViews.get(i), "rotation", 0, 360);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(500);
            /** 使用with连接多个动画*/
            animatorSet.play(oax).with(oay).with(objectAnimator);
            animatorSet.start();
        }

    }

    /**
     * 显示扇形菜单
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void showSectorMenu() {
        int avgAngle = (90 / imageViews.size() - 1);
        for (int i = 0; i < imageViews.size(); i++) {
            int angle = avgAngle * i;
            /*** 此处使用PointF*/
            PointF point = new PointF();
            point.x = (float) Math.cos(angle * (Math.PI / 180)) * radius1;
            /*** 此处y坐标是负的，因为纵坐标向下为正，而我们要在上面显示，大家可以更改符号看效果*/
            point.y = (float) -Math.sin(angle * (Math.PI / 180)) * radius1;
            Log.i(TAG, point.toString());
            ObjectAnimator oax = ObjectAnimator.ofFloat(imageViews.get(i), "translationX", 0, point.x);
            ObjectAnimator oay = ObjectAnimator.ofFloat(imageViews.get(i), "translationY", 0, point.y);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageViews.get(i), "rotation", 0, 360);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(500);
            animatorSet.play(oax).with(oay).with(objectAnimator);
            animatorSet.start();
        }
    }

    /**
     * 显示垂直菜单
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void showVerticalMenu() {
        for (int i = 0; i < imageViews.size(); i++) {
            /*** 此处使用PointF*/
            PointF point = new PointF();
            point.x = 0;
            /*** 此处y坐标是负的，因为纵坐标向下为正，而我们要在上面显示，大家可以更改符号看效果*/
            point.y = -(150 * (i + 1));
            Log.i(TAG, point.toString());
            ObjectAnimator oax = ObjectAnimator.ofFloat(imageViews.get(i), "translationX", 0, point.x);
            ObjectAnimator oay = ObjectAnimator.ofFloat(imageViews.get(i), "translationY", 0, point.y);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageViews.get(i), "rotation", 0, 360);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(500);
            animatorSet.play(oax).with(oay).with(objectAnimator);
            animatorSet.start();
        }
    }

    /**
     * 关闭垂直菜单
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void closeVerticalMenu() {
        for (int i = 0; i < imageViews.size(); i++) {
            /*** 此处使用PointF*/
            PointF point = new PointF();
            point.x = 0;
            /*** 此处y坐标是负的，因为纵坐标向下为正，而我们要在上面显示，大家可以更改符号看效果*/
            point.y = -(150 * (i + 1));
            Log.i(TAG, point.toString());
            ObjectAnimator oax = ObjectAnimator.ofFloat(imageViews.get(i), "translationX", 0, point.x);
            ObjectAnimator oay = ObjectAnimator.ofFloat(imageViews.get(i), "translationY", point.y, 0);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageViews.get(i), "rotation", 0, 360);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(500);
            animatorSet.play(oax).with(oay).with(objectAnimator);
            animatorSet.start();
        }
    }
}
