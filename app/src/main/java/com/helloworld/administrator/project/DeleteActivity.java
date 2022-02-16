package com.helloworld.administrator.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity   implements View.OnClickListener{

    private Button btn_delSearch;
    private EditText et_delid;
    private EditText et_delmoney;
    private EditText et_deldate;
    private EditText et_deltype;
    private EditText et_delnote;
    private Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        initView();
    }

    private void initView() {
        et_delid= (EditText) findViewById(R.id.et_delid);
        et_delmoney= (EditText) findViewById(R.id.et_delmoney);
        et_deldate= (EditText) findViewById(R.id.et_deldate);
        et_deltype= (EditText) findViewById(R.id.et_deltype);
        et_delnote= (EditText) findViewById(R.id.et_delnote);
        btn_delSearch= (Button) findViewById(R.id.btn_search);
        btn_delete= (Button) findViewById(R.id.btn_delete);

        btn_delSearch.setOnClickListener((View.OnClickListener) this);
        btn_delete.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.btn_search:   //查询操作
                searchOrder();
                break;
            case R.id.btn_delete:    //删除操作
                deleteOrder();
                break;
        }

    }

    private void searchOrder() {
        String orderid=et_deldate.getText().toString().trim();  //获取用户输入
        //建立数据库访问对象
        MoneyDAO dao=new MoneyDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //调用数据库访问方法
        Money o=dao.getOrders(orderid);
        //将数据填入控件
        et_delmoney.setText(o.money);
        et_delid.setText(o.id);
        et_deltype.setText(o.type);
        et_delnote.setText(o.note);
        //关闭数据库
        dao.close();
    }

    private void deleteOrder() {
        Money o=new Money();
        o.date=et_deldate.getText().toString().trim();
        //创建数据库访问对象
        MoneyDAO dao=new MoneyDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //执行数据库访问方法
        int result= dao.deletOrders(o);
        if(result>0) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
        }
        //关闭数据库
        dao.close();
    }
}
