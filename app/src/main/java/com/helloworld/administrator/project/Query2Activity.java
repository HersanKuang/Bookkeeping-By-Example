package com.helloworld.administrator.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class Query2Activity extends AppCompatActivity {
    ListView listView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query2);

        setTitle("查询收支");
        //初始化界面
        initView();
    }

    private void initView() {
        //建立数据库访问对象
        MoneyDAO dao=new MoneyDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //调用数据库访问方法
        List<Map<String,Object>> mOrderData=dao.getAllPay();
        //获取组件
        listView=(ListView)findViewById(R.id.list_income);
        //定义数据源
        String[] from={"id","money","date","type","note"};
        //定义布局控件ID
        int[] to={R.id.tv_lst_orderid,R.id.tv_lst_username,R.id.tv_lst_name,R.id.tv_lst_price,R.id.tv_lst_amount};
        SimpleAdapter listItemAdapter=new SimpleAdapter(Query2Activity.this,mOrderData,R.layout.item_list,from,to);
        //添加并显示
        listView.setAdapter(listItemAdapter);
        //关闭数据库
        dao.close();
    }
}
