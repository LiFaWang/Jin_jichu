package com.it.cn.jin_jichu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText et_number;
    EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1找到我们关心的控件

        et_content = (EditText) findViewById(R.id.et_content);

        et_number = (EditText) findViewById(R.id.et_number);
    }
    public void insertsms(View v){

        Intent intent = new Intent(this,SmsTemplateActivity.class);
        startActivityForResult(intent,2);
    }
    public void add(View v){
        Intent intent = new Intent(this,ContactActivity.class);
      //  startActivity(intent);
        startActivityForResult(intent,1);

    }
//当开启的activity页面关闭的时候调用
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==10){
            //说明数据是从ContactActiviy来的
            String phone = data.getStringExtra("phone");
            et_number.setText(phone);
        }else if(resultCode==20){
            //说明数据是从SmsTemplateActivity来的
            String smsContent = data.getStringExtra("smsContent");
            et_content.setText(smsContent);
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    public void send(View v){
    //1获取发送短信的号码和发送内容
        String  number = et_number.getText().toString().trim();
        String content = et_content.getText().toString().trim();
        //2和获取到smsManager的实例
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> divideMessage = smsManager.divideMessage(content);
        for (String str  :divideMessage ) {

            smsManager.sendTextMessage(number,null,str,null,null);

        }


    }

}
