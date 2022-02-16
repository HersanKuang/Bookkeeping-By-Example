package com.helloworld.administrator.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update2Activity extends AppCompatActivity   implements View.OnClickListener{
    //组件定义
    private EditText et_orderid2;
    private Button btn_search2;
    private EditText et_upmoney2;
    private EditText et_update2;
    private EditText et_uptype2;
    private EditText et_upnote2;
    private Button btn_edit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update2);
        setTitle("更新收支");
        initView();
    }

    private void initView() {
        et_orderid2= (EditText) findViewById(R.id.et_orderid2);
        btn_search2= (Button) findViewById(R.id.btn_search2);

        et_upmoney2= (EditText) findViewById(R.id.et_upmoney2);
        et_update2= (EditText) findViewById(R.id.et_update2);
        et_uptype2= (EditText) findViewById(R.id.et_uptype2);
        et_upnote2= (EditText) findViewById(R.id.et_upnote2);
        btn_edit2= (Button) findViewById(R.id.btn_edit2);

        btn_search2.setOnClickListener((View.OnClickListener) this);
        btn_edit2.setOnClickListener((View.OnClickListener) this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_search2:   //查询操作
                searchOrder();
                break;
            case R.id.btn_edit2:    //更新操作
                updateOrder();
                break;
        }

    }
    private void searchOrder() {
        String orderid=et_update2.getText().toString().trim();  //获取用户输入
        //建立数据库访问对象
        MoneyDAO dao=new MoneyDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //调用数据库访问方法
        Money o=dao.getPay(orderid);
        //将数据填入控件
        et_upmoney2.setText(o.money2);
        et_orderid2.setText(o.id2);
        et_uptype2.setText(o.type2);
        et_upnote2.setText(o.note2);
        //关闭数据库
        dao.close();
    }
    private void updateOrder() {
        Money o=new Money();
        o.id2 =et_orderid2.getText().toString().trim();
        o.money2 =et_upmoney2.getText().toString().trim();
        o.date2 =et_update2.getText().toString().trim();
        o.type2 =et_uptype2.getText().toString().trim();
        o.note2 =et_upnote2.getText().toString().trim();
        //创建数据库访问对象
        MoneyDAO dao=new MoneyDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //执行数据库访问方法
        long result= dao.updatePay(o);
        if(result>0) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }
        //关闭数据库
        dao.close();
    }
}
