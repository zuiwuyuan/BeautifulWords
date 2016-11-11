package com.chenyuan.sentence.mvp.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.chenyuan.sentence.R;
import com.chenyuan.sentence.common.ActivityController;
import com.chenyuan.sentence.mvp.ui.common.BaseActivity;
import com.chenyuan.sentence.mvp.ui.fragment.FragmentAllArticle;
import com.chenyuan.sentence.mvp.ui.fragment.FragmentJuji;
import com.chenyuan.sentence.mvp.ui.fragment.FragmentMeiju;
import com.chenyuan.sentence.mvp.ui.fragment.FragmentOriginal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.bottom_navigation_bar_container)
    public BottomNavigationBar bottom_navigation_bar_container;

    private FragmentMeiju fragmentMeiju;
    private FragmentAllArticle fragmentAllArticle;
    private FragmentJuji fragmentJuji;
    private FragmentOriginal fragmentOriginal;

    private Unbinder unbinder;

    // 定义一个变量，来标识是否退出
    private static boolean enableExit = false;

    // 处理请求返回信息
    private MyHandler mHandler = new MyHandler();

    private static class MyHandler extends Handler {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    enableExit = false;
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        getSupportActionBar().hide();//隐藏掉整个ActionBar，包括下面的Tabs

        initBottomNavBar();
    }

    /*初始化底部导航栏*/
    private void initBottomNavBar() {

        bottom_navigation_bar_container.setAutoHideEnabled(true);//自动隐藏
        bottom_navigation_bar_container.setMode(BottomNavigationBar.MODE_FIXED);
        bottom_navigation_bar_container.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);


        bottom_navigation_bar_container.setBarBackgroundColor(R.color.white); //背景颜色
        bottom_navigation_bar_container.setInActiveColor(R.color.bottom_nav_normal); //未选中时的颜色
        bottom_navigation_bar_container.setActiveColor(R.color.bottom_nav_selected);//选中时的颜色


        BottomNavigationItem meijulItem = new BottomNavigationItem(R.mipmap.icon_linggan, "灵感");
        BottomNavigationItem allArticleItem = new BottomNavigationItem(R.mipmap.icon_jingdian, "经典");
        BottomNavigationItem jujiItem = new BottomNavigationItem(R.mipmap.icon_juji, "句集");
        BottomNavigationItem originalItem = new BottomNavigationItem(R.mipmap.icon_yuanchuang, "原创");

        bottom_navigation_bar_container.addItem(meijulItem).addItem(allArticleItem).addItem(jujiItem).addItem(originalItem);
        bottom_navigation_bar_container.setFirstSelectedPosition(0);
        bottom_navigation_bar_container.initialise();
        bottom_navigation_bar_container.setTabSelectedListener(this);

        setDefaultFrag();
    }

    private void setDefaultFrag() {
        if (fragmentMeiju == null) {
            fragmentMeiju = new FragmentMeiju();
        }
        addFrag(fragmentMeiju);

        getSupportFragmentManager().beginTransaction().show(fragmentMeiju).commit();
    }

    /*添加Frag*/
    private void addFrag(Fragment frag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (frag != null && !frag.isAdded()) {
            ft.add(R.id.bottom_nav_content, frag);
        }
        ft.commit();
    }

    /*隐藏所有fragment*/
    private void hideAllFrag() {
        hideFrag(fragmentMeiju);
        hideFrag(fragmentAllArticle);
        hideFrag(fragmentJuji);
        hideFrag(fragmentOriginal);
    }

    /*隐藏frag*/
    private void hideFrag(Fragment frag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (frag != null && frag.isAdded()) {
            ft.hide(frag);
        }
        ft.commit();
    }

    /*底部NaV监听*/
    @Override
    public void onTabSelected(int position) {
        hideAllFrag();//先隐藏所有frag
        switch (position) {

            case 0:
                if (fragmentMeiju == null) {
                    fragmentMeiju = new FragmentMeiju();

                }
                addFrag(fragmentMeiju);
                getSupportFragmentManager().beginTransaction().show(fragmentMeiju).commit();
                break;

            case 1:
                if (fragmentAllArticle == null) {
                    fragmentAllArticle = new FragmentAllArticle();
                }
                addFrag(fragmentAllArticle);
                getSupportFragmentManager().beginTransaction().show(fragmentAllArticle).commit();

                break;
            case 2:
                if (fragmentJuji == null) {

                    fragmentJuji = new FragmentJuji();
                }

                addFrag(fragmentJuji);
                getSupportFragmentManager().beginTransaction().show(fragmentJuji).commit();

                break;
            case 3:
                if (fragmentOriginal == null) {
                    fragmentOriginal = new FragmentOriginal();

                }
                addFrag(fragmentOriginal);
                getSupportFragmentManager().beginTransaction().show(fragmentOriginal).commit();
                break;
        }

    }

    @Override
    public void onTabUnselected(int position) {


    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!enableExit) {
                enableExit = true;
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                // 利用handler延迟发送更改状态信息
                mHandler.sendEmptyMessageDelayed(0, 3000);
            } else {
                ActivityController.exitApp();

            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
