package com.otb.designerassist.mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.otb.designerassist.R;
import com.otb.designerassist.mvp.ui.common.BaseActivity;
import com.otb.designerassist.mvp.ui.fragment.FragmentAllArticle;
import com.otb.designerassist.mvp.ui.fragment.FragmentMeiju;
import com.otb.designerassist.mvp.ui.fragment.FragmentOriginal;
import com.otb.designerassist.mvp.ui.fragment.FragmentJuji;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.bottom_navigation_bar_container)
    public BottomNavigationBar bottom_navigation_bar_container;

    private FragmentMeiju fragmentMeiju;
    private FragmentAllArticle fragmentAllArticle;
    private FragmentOriginal fragmentOriginal;
    private FragmentJuji fragmentJuji;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        initBottomNavBar();
    }


    /*初始化底部导航栏*/
    private void initBottomNavBar() {

        bottom_navigation_bar_container.setAutoHideEnabled(true);//自动隐藏

        bottom_navigation_bar_container.setMode(BottomNavigationBar.MODE_FIXED);

        bottom_navigation_bar_container.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);


        bottom_navigation_bar_container.setBarBackgroundColor(R.color.white); //背景颜色
        bottom_navigation_bar_container.setInActiveColor(R.color.nav_gray); //未选中时的颜色
        bottom_navigation_bar_container.setActiveColor(R.color.colorPrimaryDark);//选中时的颜色


        BottomNavigationItem meijulItem = new BottomNavigationItem(R.drawable.notice, "灵感");
        BottomNavigationItem allArticleItem = new BottomNavigationItem(R.drawable.msg, "名句");
        BottomNavigationItem jujiItem = new BottomNavigationItem(R.drawable.task, "句集");
        BottomNavigationItem originalItem = new BottomNavigationItem(R.drawable.notice, "原创");


        bottom_navigation_bar_container.addItem(meijulItem).addItem(allArticleItem).addItem(jujiItem).addItem(originalItem);
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
                getSupportActionBar().setTitle("美图美句");
                break;

            case 1:
                if (fragmentAllArticle == null) {
                    fragmentAllArticle = new FragmentAllArticle();
                }
                addFrag(fragmentAllArticle);
                getSupportFragmentManager().beginTransaction().show(fragmentAllArticle).commit();

                getSupportActionBar().setTitle("名人名句");

                break;
            case 2:
                if (fragmentJuji == null) {

                    fragmentJuji = new FragmentJuji();
                }

                addFrag(fragmentJuji);
                getSupportFragmentManager().beginTransaction().show(fragmentJuji).commit();
                getSupportActionBar().setTitle("原创句子");

                break;
            case 3:
               /*公告Frag*/
                if (fragmentOriginal == null) {
                    fragmentOriginal = new FragmentOriginal();

                }
                addFrag(fragmentOriginal);
                getSupportFragmentManager().beginTransaction().show(fragmentOriginal).commit();
                getSupportActionBar().setTitle("精选句集");
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


    }
}
