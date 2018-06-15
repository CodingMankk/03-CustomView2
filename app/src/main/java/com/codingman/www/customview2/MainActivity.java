package com.codingman.www.customview2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnChangeDx;
    private Button mBtnChangeDy;
    private Button mBtnChangeRadius;
    private Button mBtnAddShadow;
    private Button mBtnClearShadow1;
    private Button mBtnClearShadow2;

    private MyViewPaintShower myviewpaintshower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnChangeDx = (Button) findViewById(R.id.bt_changeDx);
        mBtnChangeDy = (Button) findViewById(R.id.bt_changeDy);
        mBtnChangeRadius = (Button) findViewById(R.id.bt_changeRadius);
        myviewpaintshower = (MyViewPaintShower) findViewById(R.id.myView);
        mBtnAddShadow = (Button)findViewById(R.id.bt_addShadow);
        mBtnClearShadow1 = (Button)findViewById(R.id.bt_clearShadow1);
        mBtnClearShadow2 = (Button)findViewById(R.id.bt_clearShadow2);
        TextView textView = (TextView) findViewById(R.id.tv_shadow);

//        textView.setShadowLayer(1,10,10, Color.CYAN);

        mBtnChangeDx.setOnClickListener(this);
        mBtnChangeDy.setOnClickListener(this);
        mBtnChangeRadius.setOnClickListener(this);
        mBtnAddShadow.setOnClickListener(this);
        mBtnClearShadow1.setOnClickListener(this);
        mBtnClearShadow2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
             case R.id.bt_changeDx:
                 myviewpaintshower.ChangeDx();
                  break;
            case R.id.bt_changeDy:
                myviewpaintshower.ChangeDy();
                  break;
            case R.id.bt_changeRadius:
                myviewpaintshower.changeRadius();
                  break;
            case R.id.bt_addShadow:
                myviewpaintshower.addShadow();
                break;
            case R.id.bt_clearShadow1:
                myviewpaintshower.clearShadow1();
                break;
            case R.id.bt_clearShadow2:
                myviewpaintshower.clearShadow2();
                break;

             default:
                  break;
        }
    }
}
