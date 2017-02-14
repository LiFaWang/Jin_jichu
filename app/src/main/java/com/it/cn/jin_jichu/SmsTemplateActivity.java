package com.it.cn.jin_jichu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/1/19.
 */

public class SmsTemplateActivity extends Activity {
    String object[] = {"我在吃饭，稍后联系", "我在开会，稍后联系", "我在泡妞，稍后联系", "我在发呆，稍后联系"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_template);
        //1找到lv
        ListView lv = (ListView) findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.smstemplate_item, object);
        //2显示数据

        lv.setAdapter(adapter);
        //3设置lv条目的点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      @Override
                                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                          //4取出点击条目的数据
                                          String smsContent = object[position];
                                          //把sms返回给调用者
                                          Intent intent = new Intent();
                                          intent.putExtra("smsContent", smsContent);
                                          setResult(20, intent);
                                          //6调用finish
                                          finish();

                                      }
                                  }
        );


    }
}

