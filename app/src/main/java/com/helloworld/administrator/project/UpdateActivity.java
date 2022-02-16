package com.helloworld.administrator.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UpdateActivity extends AppCompatActivity  implements View.OnClickListener{
    //组件定义
    private EditText et_orderid;
    private Button btn_search;

    private EditText et_upmoney;
    private EditText et_update;
    private EditText et_uptype;
    private EditText et_upnote;

    private Button btn_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        setTitle("更新收支");
        initView();
    }

    private void initView() {
        et_orderid= (EditText) findViewById(R.id.et_orderid);
        btn_search= (Button) findViewById(R.id.btn_search);

        et_upmoney= (EditText) findViewById(R.id.et_upmoney);
        et_update= (EditText) findViewById(R.id.et_update);
        et_uptype= (EditText) findViewById(R.id.et_uptype);
        et_upnote= (EditText) findViewById(R.id.et_upnote);
        btn_edit= (Button) findViewById(R.id.btn_edit);

        btn_search.setOnClickListener((View.OnClickListener) this);
        btn_edit.setOnClickListener((View.OnClickListener) this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_search:   //查询操作
                searchOrder();
                break;
            case R.id.btn_edit:    //更新操作
                updateOrder();
                break;
        }

    }
    private void searchOrder() {
        String orderid=et_update.getText().toString().trim();  //获取用户输入
        //建立数据库访问对象
        MoneyDAO dao=new MoneyDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //调用数据库访问方法
        Money o=dao.getOrders(orderid);
        //将数据填入控件
        et_upmoney.setText(o.money);
        et_orderid.setText(o.id);
        et_uptype.setText(o.type);
        et_upnote.setText(o.note);
        //关闭数据库
        dao.close();
    }
    private void updateOrder() {
        Money o=new Money();
        o.id=et_orderid.getText().toString().trim();
        o.money=et_upmoney.getText().toString().trim();
        o.date=et_update.getText().toString().trim();
        o.type=et_uptype.getText().toString().trim();
        o.note=et_upnote.getText().toString().trim();
        //创建数据库访问对象
        MoneyDAO dao=new MoneyDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //执行数据库访问方法
        long result= dao.updateOrders(o);
        if(result>0) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }
        //关闭数据库
        dao.close();
    }
}
