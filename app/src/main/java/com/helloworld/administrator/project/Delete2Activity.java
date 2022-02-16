package com.helloworld.administrator.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete2Activity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_delSearch2;
    private EditText et_delid2;
    private EditText et_delmoney2;
    private EditText et_deldate2;
    private EditText et_deltype2;
    private EditText et_delnote2;
    private Button btn_delete2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete2);
        initView();
        }

    private void initView() {
        et_delid2= (EditText) findViewById(R.id.et_delid2);
        et_delmoney2= (EditText) findViewById(R.id.et_delmoney2);
        et_deldate2= (EditText) findViewById(R.id.et_deldate2);
        et_deltype2= (EditText) findViewById(R.id.et_deltype2);
        et_delnote2= (EditText) findViewById(R.id.et_delnote2);
        btn_delSearch2= (Button) findViewById(R.id.btn_search2);
        btn_delete2= (Button) findViewById(R.id.btn_delete2);

        btn_delSearch2.setOnClickListener((View.OnClickListener) this);
        btn_delete2.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.btn_search2:   //查询操作
                searchOrder();
                break;
            case R.id.btn_delete2:    //删除操作
                deleteOrder();
                break;
        }

    }

    private void searchOrder() {
        String orderid=et_deldate2.getText().toString().trim();  //获取用户输入
        //建立数据库访问对象
        MoneyDAO dao=new MoneyDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //调用数据库访问方法
        Money o=dao.getPay(orderid);
        //将数据填入控件
        et_delmoney2.setText(o.money2);
        et_delid2.setText(o.id2);
        et_deltype2.setText(o.type2);
        et_delnote2.setText(o.note2);
        //关闭数据库
        dao.close();
    }

    private void deleteOrder() {
        Money o=new Money();
        o.date2=et_deldate2.getText().toString().trim();
        //创建数据库访问对象
        MoneyDAO dao=new MoneyDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //执行数据库访问方法
        int result= dao.deletPay(o);
        if(result>0) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
        }
        //关闭数据库
        dao.close();
    }
}
