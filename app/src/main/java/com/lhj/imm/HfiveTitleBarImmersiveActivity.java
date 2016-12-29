package com.lhj.imm;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lhj.imm.util.StatusBarUtil;
import com.lhj.imm.view.MyWebView;

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
 * <p/>
 * 作者：linhongjie
 * 时间：2016/12/21 14:52
 * 描述：webview加载h5标题栏+状态栏/导航栏渐变悬浮效果
 */
public class HfiveTitleBarImmersiveActivity extends AppCompatActivity implements MyWebView.OnScrollChangeListener {
    private final static String TAG = "HfiveTitleBarImmersiveActivity";
    RelativeLayout base_rl;//标题栏
    RelativeLayout base_left_rl;//左边圆圈
    ImageView base_left_iv;//左边箭头图片
    RelativeLayout base_right_rl;//右边第一个圆圈
    ImageView base_right_iv;//右边收藏图片
    RelativeLayout base_right_rl2;//右边第二个圆圈
    ImageView base_right_iv2;//右边分享图片
    private float height;
    TextView base_title;//topbar的标题
    LinearLayout ll_content;//scrollview里的唯一一个布局，在这里可以include
    MyWebView base_webView;//加载h5的控件
        String url = "http://viva.vip.com/act/m/staic-page-about?wapid=vivac_314&width=1080&height=1920&area_id=103105&oxo_province_id=103105&oxo_city_id=103105102&oxo_district_id=103105102102&net=WIFI&vipruid=189097382&app_name=shop_android&source=app&warehouse=VIP_NH&app_version=5.30.3&client=android&mars_cid=50930c41-fb28-3ff7-8904-c58159c4355a&mobile_platform=3&mobile_channel=lhp000kl%3Ats5x38dl%3A7n91zmr2%3Alhp000kf&deeplink_cps=&other_cps=&protocol_version=1.1";
//    String url = "http://i.mjbang.cn/anli/new/594911.html?channel=app_mjb";
//    String url = "https://www.baidu.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5_immersive);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        base_webView = (MyWebView) findViewById(R.id.wv);
        base_title = (TextView) findViewById(R.id.base_title);
        base_rl = (RelativeLayout) findViewById(R.id.base_rl);
        base_left_rl = (RelativeLayout) findViewById(R.id.base_left_rl);
        base_left_iv = (ImageView) findViewById(R.id.base_left_iv);
        base_right_rl = (RelativeLayout) findViewById(R.id.base_right_rl);
        base_right_iv = (ImageView) findViewById(R.id.base_right_iv);
        base_right_rl2 = (RelativeLayout) findViewById(R.id.base_right_rl2);
        base_right_iv2 = (ImageView) findViewById(R.id.base_right_iv2);
        init();
        base_webView.setOnScrollChangeListener(this);
    }

    /**
     * WebView滑动过程渐变标题栏效果
     *
     * @param scrollX
     * @param scrollY
     * @param oldScrollX
     * @param oldScrollY
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onScrollChanged(MyWebView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//        float webcontent = v.getContentHeight() * v.getScale();// webview的高度
//        float webnow = v.getHeight() +scrollY;// 当前webview的高度
//        Log.e(TAG, "h=" + height + "," + scrollY+","+v.getContentHeight()+"***"+v.getScale()+"==="+webcontent+","+webnow);
        if (scrollY <= 0) {//置顶
            StatusBarUtil.setTranslucentForImageView(HfiveTitleBarImmersiveActivity.this, 0, base_rl);//顶置时状态栏透明
            base_rl.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
            ((GradientDrawable) base_left_rl.getBackground()).setColor(Color.argb((int) 255, 90, 90, 90));
            ((GradientDrawable) base_right_rl.getBackground()).setColor(Color.argb((int) 255, 90, 90, 90));
            ((GradientDrawable) base_right_rl2.getBackground()).setColor(Color.argb((int) 255, 90, 90, 90));

            base_left_iv.setBackground(ContextCompat.getDrawable(HfiveTitleBarImmersiveActivity.this, R.mipmap.white_left));
            base_right_iv.setBackground(ContextCompat.getDrawable(HfiveTitleBarImmersiveActivity.this, R.mipmap.white_collection));
            base_right_iv2.setBackground(ContextCompat.getDrawable(HfiveTitleBarImmersiveActivity.this, R.mipmap.white_share));
            Log.e(TAG, "已经处于顶端h=" + height + "," + scrollY);
            Toast.makeText(this, "已经处于顶端", Toast.LENGTH_SHORT).show();
        } else if (scrollY == height) {// 已经处于底端
            base_left_iv.setBackground(ContextCompat.getDrawable(HfiveTitleBarImmersiveActivity.this, R.mipmap.left));
            base_right_iv.setBackground(ContextCompat.getDrawable(HfiveTitleBarImmersiveActivity.this, R.mipmap.icon_mid2_03));
            base_right_iv2.setBackground(ContextCompat.getDrawable(HfiveTitleBarImmersiveActivity.this, R.mipmap.icon_mid2_02));
            base_left_iv.setImageAlpha(255);
            base_right_iv.setImageAlpha(255);
            base_right_iv2.setImageAlpha(255);
            Log.e(TAG, "已经处于底端h=" + height + "," + scrollY);
            Toast.makeText(this, "已经处于底端", Toast.LENGTH_SHORT).show();
        } else {//滑动过程
            float scale = (float) scrollY / height;
            int alpha = (int) (255 * scale);
            if (alpha <= 200) {//状态栏逐渐显示
                StatusBarUtil.setTranslucentForImageView(HfiveTitleBarImmersiveActivity.this, alpha, base_rl);
            } else if (alpha <= 230) {//标题栏逐渐显示
                base_rl.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            }
            if (scale < 0.5) {
                base_left_iv.setBackground(ContextCompat.getDrawable(HfiveTitleBarImmersiveActivity.this, R.mipmap.white_left));
                base_right_iv.setBackground(ContextCompat.getDrawable(HfiveTitleBarImmersiveActivity.this, R.mipmap.white_collection));
                base_right_iv2.setBackground(ContextCompat.getDrawable(HfiveTitleBarImmersiveActivity.this, R.mipmap.white_share));
                base_left_iv.setImageAlpha(alpha);
                base_right_iv.setImageAlpha(alpha);
                base_right_iv2.setImageAlpha(alpha);
            } else {
                base_left_iv.setBackground(ContextCompat.getDrawable(HfiveTitleBarImmersiveActivity.this, R.mipmap.left));
                base_right_iv.setBackground(ContextCompat.getDrawable(HfiveTitleBarImmersiveActivity.this, R.mipmap.icon_mid2_03));
                base_right_iv2.setBackground(ContextCompat.getDrawable(HfiveTitleBarImmersiveActivity.this, R.mipmap.icon_mid2_02));
                base_left_iv.setImageAlpha(255 - alpha);
                base_right_iv.setImageAlpha(255 - alpha);
                base_right_iv2.setImageAlpha(255 - alpha);
            }
            //图标圈圈逐渐透明
            ((GradientDrawable) base_left_rl.getBackground()).setColor(Color.argb((int) 255 - alpha, 90, 90, 90));
            ((GradientDrawable) base_right_rl.getBackground()).setColor(Color.argb((int) 255 - alpha, 90, 90, 90));
            ((GradientDrawable) base_right_rl2.getBackground()).setColor(Color.argb((int) 255 - alpha, 90, 90, 90));

        }

    }


    /**
     * JS计算h5高度
     */
    class HeightGetter {
        @JavascriptInterface
        public void run(final String height) {
            runOnUiThread(new Runnable() {
                public void run() {

                    if (height.length() > 0 && StatusBarUtil.isNumeric(height)) {
                        HfiveTitleBarImmersiveActivity.this.height = Float.valueOf(height) * base_webView.getScale() - base_webView.getHeight();
                    }
                    Log.e(TAG, "h5高度为：" + height + "系数：" + base_webView.getScale() + "屏幕分辨率高度" + base_webView.getHeight());

                }
            });
        }
    }


    /**
     * 初始化标题栏与状态栏
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    void init() {
        //设置标题栏背景一开始就是透明
        base_rl.setBackground(ContextCompat.getDrawable(this, R.color.transparent));
        //设置状态栏一开始就是透明
        StatusBarUtil.setTranslucentForImageView(this, 0, base_rl);
        //设置webView的相关参数，更多参数自行设置
        WebSettings webSettings = base_webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        base_webView.addJavascriptInterface(new HeightGetter(), "lhj");
        base_webView.clearCache(true);
        base_webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                base_title.setText(view.getTitle());
                //加载js的h5高度
                base_webView.loadUrl("javascript:window.lhj.run(document.documentElement.scrollHeight+'');");
            }
        });
        base_webView.loadUrl(url);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (base_webView != null) {
            if ((keyCode == KeyEvent.KEYCODE_BACK) && base_webView.canGoBack()) {
                base_webView.goBack();
                return true;
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((GradientDrawable) base_left_rl.getBackground()).setColor(Color.argb((int) 255, 90, 90, 90));
        ((GradientDrawable) base_right_rl.getBackground()).setColor(Color.argb((int) 255, 90, 90, 90));
        ((GradientDrawable) base_right_rl2.getBackground()).setColor(Color.argb((int) 255, 90, 90, 90));
    }
}
