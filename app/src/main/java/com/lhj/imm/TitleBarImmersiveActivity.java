package com.lhj.imm;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lhj.imm.util.StatusBarUtil;
import com.lhj.imm.view.MyScrollView;

/**
 * ┴┬┴┬／￣＼＿／￣＼
 * ┬┴┬┴▏　　▏▔▔▔▔＼ 这
 * ┴┬┴／＼　／　　　　　　﹨ 地
 * ┬┴∕　　　　　　　／　　　） 方
 * ┴┬▏　　　　　　　　●　　▏ 不
 * ┬┴▏　　　　　　　　　　　▔█◤ 错，
 * ┴◢██◣　　　　　　 ＼＿＿／
 * ┬█████◣　　　　　　　／　 让我用PP踩踩！
 * ┴█████████████◣
 * ◢██████████████▆▄
 * █◤◢██◣◥█████████◤＼
 * ◥◢████　████████◤　　 ＼
 * ┴█████　██████◤　　　　　 ﹨
 * ┬│　　　│█████◤　　　　　　　　▏
 * ┴│　　　│ PP熊在此！借贵宝地一踩！ 　▏
 * ┬∕　　　∕　　　　／▔▔▔＼　　　　 ∕
 * *∕＿＿_／﹨　　　∕　　　　　 ＼　　／＼
 * ┴┬┴┬┴┬┴ ＼＿＿＿＼　　　　 ﹨／▔＼﹨／▔＼ ╃天天开心╃
 * ▲△▲▲╓╥╥╥╥╥╥╥╥＼　　 ∕　 ／▔﹨　／▔
 * 　＊＊＊╠╬╬╬╬╬╬╬╬＊﹨　　／　　／／ ╃事事顺心╃整和不错
 * <p>
 * 作者：linhongjie
 * 时间：2016/12/21 14:48
 * 描述：ScrollView标题栏+状态栏/导航栏渐变悬浮效果
 */
public class TitleBarImmersiveActivity extends AppCompatActivity implements MyScrollView.OnScrollChangedListener {
    final static String TAG = "TitleBarImmersiveActivity";
    MyScrollView scrollView;
    RelativeLayout base_rl;//标题栏
    RelativeLayout container;//内容2
    int height;
    TextView base_title;//topbar的标题
    LinearLayout ll_content;//scrollview里的唯一一个布局，在这里可以include
    RelativeLayout base_left_rl;//左边圆圈
    ImageView base_left_iv;//左边箭头图片
    RelativeLayout base_right_rl;//右边第一个圆圈
    ImageView base_right_iv;//右边收藏图片
    RelativeLayout base_right_rl2;//右边第二个圆圈
    ImageView base_right_iv2;//右边分享图片

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersive);
        container = (RelativeLayout) findViewById(R.id.container);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        base_title = (TextView) findViewById(R.id.base_title);
        scrollView = (MyScrollView) findViewById(R.id.scrollView);
        base_rl = (RelativeLayout) findViewById(R.id.base_rl);
        base_left_rl = (RelativeLayout) findViewById(R.id.base_left_rl);
        base_left_iv = (ImageView) findViewById(R.id.base_left_iv);
        base_right_rl = (RelativeLayout) findViewById(R.id.base_right_rl);
        base_right_iv = (ImageView) findViewById(R.id.base_right_iv);
        base_right_rl2 = (RelativeLayout) findViewById(R.id.base_right_rl2);
        base_right_iv2 = (ImageView) findViewById(R.id.base_right_iv2);

        /**
         * 设置标题栏背景一开始就是透明
         */
        base_rl.setBackground(ContextCompat.getDrawable(this, R.color.transparent));
        scrollView.setOnScrollListener(this);
        /**
         * 设置状态栏一开始就是透明
         */
        StatusBarUtil.setTranslucentForImageView(this, 0, base_rl);

        /**
         * 获取高度
         */
        ViewTreeObserver vto = ll_content.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                //可滑动值=height-scrollView.getHeight()
                TitleBarImmersiveActivity.this.height = ll_content.getHeight() - scrollView.getHeight();
                Log.e("sh", height + "");
                //注意要移除 removeOnGlobalLayoutListener
                ll_content.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                ll_content.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (t <= 0) {//顶
            base_rl.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
            base_left_iv.setBackground(ContextCompat.getDrawable(this, R.mipmap.white_left));
            base_right_iv.setBackground(ContextCompat.getDrawable(this, R.mipmap.white_collection));
            base_right_iv2.setBackground(ContextCompat.getDrawable(this, R.mipmap.white_share));
            ((GradientDrawable) base_left_rl.getBackground()).setColor(Color.argb((int) 255, 90, 90, 90));
            ((GradientDrawable) base_right_rl.getBackground()).setColor(Color.argb((int) 255, 90, 90, 90));
            ((GradientDrawable) base_right_rl2.getBackground()).setColor(Color.argb((int) 255, 90, 90, 90));
        } else if (t >= height) {//底
            base_left_iv.setBackground(ContextCompat.getDrawable(this, R.mipmap.left));
            base_right_iv.setBackground(ContextCompat.getDrawable(this, R.mipmap.icon_mid2_03));
            base_right_iv2.setBackground(ContextCompat.getDrawable(this, R.mipmap.icon_mid2_02));
            base_left_iv.setImageAlpha(255);
            base_right_iv.setImageAlpha(255);
            base_right_iv2.setImageAlpha(255);
            base_rl.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
//            base_rl.setBackgroundColor(Color.argb((int) 255, 255, 64, 129));
        } else {//滑动过程
            float scale = (float) t / height;
            int alpha = (int) (255 * scale);
            if (t <= height / 2) {
                base_left_iv.setBackground(ContextCompat.getDrawable(this, R.mipmap.white_left));
                base_right_iv.setBackground(ContextCompat.getDrawable(this, R.mipmap.white_collection));
                base_right_iv2.setBackground(ContextCompat.getDrawable(this, R.mipmap.white_share));
                base_left_iv.setImageAlpha(alpha);
                base_right_iv.setImageAlpha(alpha);
                base_right_iv2.setImageAlpha(alpha);
            } else {
                base_left_iv.setBackground(ContextCompat.getDrawable(this, R.mipmap.left));
                base_right_iv.setBackground(ContextCompat.getDrawable(this, R.mipmap.icon_mid2_03));
                base_right_iv2.setBackground(ContextCompat.getDrawable(this, R.mipmap.icon_mid2_02));
                base_left_iv.setImageAlpha(255 - alpha);
                base_right_iv.setImageAlpha(255 - alpha);
                base_right_iv2.setImageAlpha(255 - alpha);
            }
            if (alpha <= 191) {//状态栏逐渐显示,230标题栏逐渐显示
                StatusBarUtil.setTranslucentForImageView(this, alpha, base_rl);
                base_rl.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                ((GradientDrawable) base_left_rl.getBackground()).setColor(Color.argb((int) 255 - alpha, 90, 90, 90));
                ((GradientDrawable) base_right_rl.getBackground()).setColor(Color.argb((int) 255 - alpha, 90, 90, 90));
                ((GradientDrawable) base_right_rl2.getBackground()).setColor(Color.argb((int) 255 - alpha, 90, 90, 90));
            } else if (alpha > 191 && alpha <= 230) {
                StatusBarUtil.setTranslucentForImageView(this, alpha, base_rl);
                base_rl.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                ((GradientDrawable) base_left_rl.getBackground()).setColor(Color.argb((int) 255 - alpha, 90, 90, 90));
                ((GradientDrawable) base_right_rl.getBackground()).setColor(Color.argb((int) 255 - alpha, 90, 90, 90));
                ((GradientDrawable) base_right_rl2.getBackground()).setColor(Color.argb((int) 255 - alpha, 90, 90, 90));
            } else {
                ((GradientDrawable) base_left_rl.getBackground()).setColor(Color.argb((int) 1, 90, 90, 90));
                ((GradientDrawable) base_right_rl.getBackground()).setColor(Color.argb((int) 1, 90, 90, 90));
                ((GradientDrawable) base_right_rl2.getBackground()).setColor(Color.argb((int) 1, 90, 90, 90));
                base_rl.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
            }
//            base_rl.setBackgroundColor(Color.argb((int) 255, 255, 64, 129));
            Log.e(TAG, "ah=" + height + "," + t + "," + scale + "," + alpha);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((GradientDrawable) base_left_rl.getBackground()).setColor(Color.argb((int) 255, 90, 90, 90));
        ((GradientDrawable) base_right_rl.getBackground()).setColor(Color.argb((int) 255, 90, 90, 90));
        ((GradientDrawable) base_right_rl2.getBackground()).setColor(Color.argb((int) 255, 90, 90, 90));
    }
}
