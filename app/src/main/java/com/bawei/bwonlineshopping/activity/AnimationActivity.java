package com.bawei.bwonlineshopping.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.bwonlineshopping.R;
import com.bawei.bwonlineshopping.base.BaseActivity;
import com.bawei.bwonlineshopping.base.BasePresenter;

public class AnimationActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv;
    private TextView tv;
    private Button group;
    private Button suo;
    private Button ping;
    private Button jian;
    private Button zhuan;
    private Button normal;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_animation;
    }

    @Override
    protected void initView() {
        iv = findViewById(R.id.anim_iv);
        tv = findViewById(R.id.anim_tv);
        group = findViewById(R.id.group);
        ping = findViewById(R.id.ping);
        suo = findViewById(R.id.suofang);
        jian = findViewById(R.id.jian);
        zhuan = findViewById(R.id.zhuan);
        normal = findViewById(R.id.bt_normal);

        iv.setOnClickListener(this);
        tv.setOnClickListener(this);
        group.setOnClickListener(this);
        ping.setOnClickListener(this);
        suo.setOnClickListener(this);
        jian.setOnClickListener(this);
        zhuan.setOnClickListener(this);
        normal.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
//            //普通动画
//            case R.id.bt_normal:
//                TranslateAnimation animation = new TranslateAnimation(0, 600, 300, 300);
//                animation.setDuration(2000);
//                animation.setFillAfter(false);
//                iv.startAnimation(animation);
//                break;
                //平移动画
            case R.id.ping:
                 ObjectAnimator transX = ObjectAnimator.ofFloat(iv, "translationX", 0, 500f);
                transX.setDuration(2000);
                //设置动画先后执行速度
                transX.setInterpolator(new AccelerateInterpolator());
                // 通过 setRepeatMode 来设置重复的模式，ValueAnimator.RESTART 代表重新来
                // 通过 setRepeatCount 设置重复的次数，次数中不包括第一次执行，
                //两者必须配合使用
                transX.setRepeatMode(ValueAnimator.RESTART);
                transX.setRepeatCount(2);
                //开始动画
                transX.start();
                break;
            case R.id.zhuan:
                ObjectAnimator animator = ObjectAnimator.ofFloat(iv, "rotation", 0, 500f);
                animator.setDuration(2000);
                //设置重复模式
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.setRepeatCount(2);
                animator.start();
                break;
            case R.id.suofang:
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(iv, "scaleX", 1f, 0.3f);
                scaleX.setDuration(2000);
                scaleX.start();
                break;
            case R.id.jian:
                ObjectAnimator alpha = ObjectAnimator.ofFloat(iv, "alpha", 1f, 0.3f);
                alpha.setDuration(2000);
                alpha.start();
                break;
                //实现动画组
            // 首先，要单独实现每一个动画
            // 我们需要 new 一个 AnimatorSet对象
            // 将我们刚才实现的动画，按照我们想播放的顺序，放入AnimatorSet对象中
            // .play 代表我们要播放哪个动画
            // .with 代表一起播放
            // .before 代表在 XXXXX 之前 也就是.before之前的优先执行
            // .after 代表在 XXXXX 之后 也就是.after包裹的优先执行
            case R.id.group:
                ObjectAnimator trans = ObjectAnimator.ofFloat(iv, "translationX", 0, 500f);
                trans.setDuration(2000);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv, "rotation", 0, 500f);
                animator2.setDuration(2000);
                ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(iv, "scaleX", 1f, 0.1f);
                scaleX2.setDuration(2000);
                ObjectAnimator alpha2 = ObjectAnimator.ofFloat(iv, "alpha", 1f, 0.3f);
                alpha2.setDuration(2000);
                //创建动画集合
                AnimatorSet set = new AnimatorSet();
                // 执行平移动画的同时执行旋转和缩放，在渐变动画之前
                set.play(trans).with(animator2).with(scaleX2).before(alpha2);
                set.start();
                break;
            case R.id.anim_iv:
                doValueAnimator();
                ObjectAnimator scale = ObjectAnimator.ofFloat(tv, "scaleX", 0.2f, 1f);
                scale.setDuration(5000);
                scale.start();
                break;
            default:
                break;
        }
    }

    private void doValueAnimator(){
        //传入起始值和中间值
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 在回调中，我们需要 valueAnimator.getAnimatedValue() 通过把值取出，
                // 并强转成我们的 ofXXXX 中，我们输入设置的类型，
                // 如下面，我们用的是 ofFloat，所以强转成 float 类型
                // 拿着具体的值，具体操作
                float current= (float) animation.getAnimatedValue();
                tv.setText(current*100+"%");
            }
        });
        // 这里是改变字体颜色的地方，使用了 ofArgb 来修改颜色
        // 这里面我输入的是色值 0xffff0000
        // 前两位 0x 代表偏移，跟我们具体的色值没什么大关系，但是必须有
        // 接下两位 ff 代表 alpha 也就是透明度
        // 再往后两位 代表 RED 红色
        // 再往后两位 代表 GREEN 绿色
        // 再往后两位 代表 BLUE 蓝色
        ValueAnimator argb = ValueAnimator.ofArgb(0xffff0000, 0xff00ff00);
        argb.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue= (int) animation.getAnimatedValue();
                tv.setTextColor(currentValue);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator).with(argb);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }
}
