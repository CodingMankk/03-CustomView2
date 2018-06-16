package com.codingman.www.customview2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @function:
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maskfilter_layout);
//        setContentView(R.layout.activity_extraalpha_layout);
//        setContentView(R.layout.activity_alphacustomview_layout);
        setContentView(R.layout.activity_shader_layout);
    }
}
