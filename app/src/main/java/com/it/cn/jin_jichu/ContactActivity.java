package com.it.cn.jin_jichu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */

public class ContactActivity extends Activity {
    List<Person> lists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        //找到liv
        ListView lv= (ListView) findViewById(R.id.lv);
        //准备listView要显示的数据，模拟点假数据

        lists = new ArrayList<>();
        for(int i=0;i<20;i++){
            Person p = new Person();
            p.setName("张三"+i);
            p.setPhone("11"+i);
            lists.add(p);
        }
        //3展示数据
        lv.setAdapter(new MyAdapter());
        //4给listview 设置点击的事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //5获取点的条目的数据，数据在哪里存，就去那里取
                String phone = lists.get(i).getPhone();
                //5.0 把数据返回给调用者
                Intent intent=new Intent();
                intent.putExtra("phone",phone);
                setResult(10,intent );
                //5.1关闭当前页面
                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class MyAdapter  extends BaseAdapter{

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView==null){
              view=View.inflate(getApplicationContext(),R.layout.contact_item,null);
            }else {
                view=convertView;
            }
            //[1]找到我们在item  中的控件，用来显示数据
            TextView tv_name= (TextView) view.findViewById(R.id.tv_name);
            TextView tv_phone= (TextView) view.findViewById(R.id.tv_phone);
          //  [2]展示数据
            tv_name.setText(lists.get(position).getName());
            tv_phone.setText(lists.get(position).getPhone());

            return view;
        }
    }
}
