package com.chenyuan.sentence.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chenyuan.sentence.R;

import butterknife.ButterKnife;

/**
 * 句集详情
 */
public class JujiDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juji_detail);

        ButterKnife.bind(this);

    }
}
