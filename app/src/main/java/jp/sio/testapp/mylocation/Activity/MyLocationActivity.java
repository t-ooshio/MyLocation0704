package jp.sio.testapp.mylocation.Activity;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.widget.Toast;

import jp.sio.testapp.mylocation.Presenter.MyLocationPresenter;
import jp.sio.testapp.mylocation.R;


public class MyLocationActivity extends AppCompatActivity {

    private Button buttonStart;
    private Button buttonStop;
    private Button buttonSetting;
    private TextView tvResult;
    private TextView tvState;
    private TextView tvSetting;

    private Context context = this;
    private MyLocationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MyLocationPresenter(this);

        buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonStop = (Button)findViewById(R.id.buttonStop);
        buttonSetting = (Button)findViewById(R.id.buttonSetting);
        tvResult = (TextView)findViewById(R.id.textViewResult);
        tvState = (TextView)findViewById(R.id.textViewState);
        tvSetting = (TextView)findViewById(R.id.textViewSetting);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offBtnStart();
                onBtnStop();
                offBtnSetting();
                pushBtnStart();
            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnStart();
                offBtnStop();
                onBtnSetting();
                pushBtnStop();
            }
        });
        buttonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushBtnSetting();
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        presenter.checkPermission();
        presenter.mStart();
    }
    public void showTextViewResult(String str){
        tvResult.setText(str);
    }
    public void showTextViewState(String str){tvState.setText(str + "\n");}
    public void showTextViewSetting(String str){
        tvSetting.setText(str);
    }

    public void pushBtnStart(){
        showTextViewState("測位中");
        presenter.locationStart();

    }
    public void pushBtnStop(){
        showTextViewState("停止");
        presenter.locationStop();
    }
    public void pushBtnSetting(){
        presenter.settingStart();
    }

    public void onBtnStart(){
        buttonStart.setEnabled(true);
    }
    public void offBtnStart(){
        buttonStart.setEnabled(false);
    }

    public void onBtnStop(){
        buttonStop.setEnabled(true);
    }
    public void offBtnStop(){
        buttonStop.setEnabled(false);
    }

    public void onBtnSetting(){
        buttonSetting.setEnabled(true);
    }
    public void offBtnSetting(){
        buttonSetting.setEnabled(false);
    }

    public void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}