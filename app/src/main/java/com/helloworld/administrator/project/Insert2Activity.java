package com.helloworld.administrator.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insert2Activity extends AppCompatActivity implements View.OnClickListener {
    //private Spinner mSpinner;
    //private ArrayAdapter<String> mAdapter ;
    //private static String [] mStringArray;
    private EditText et_payid;
    private EditText et_money2;
    private EditText et_date2;
    private EditText et_type2;
    private EditText et_note2;
    private Button btn_surepay;
    //static String type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert2);
        initView();
    }

    private void initView() {
        et_payid= (EditText) findViewById(R.id.et_payid);
        et_money2= (EditText) findViewById(R.id.et_money2);
        et_date2= (EditText) findViewById(R.id.et_date2);
        et_type2= (EditText) findViewById(R.id.et_type2);
        et_note2= (EditText) findViewById(R.id.et_note2);
        btn_surepay= (Button) findViewById(R.id.btn_surepay);
        btn_surepay.setOnClickListener(this);
    }
    public void onClick(View v){
        //当单击“添加”按钮时，获取订单信息
        String id=et_payid.getText().toString().trim();
        String money = et_money2.getText().toString().trim();
        String date = et_date2.getText().toString().trim();
        String type = et_type2.getText().toString().trim();
        String note = et_note2.getText().toString();
        //添加订单
        Money o =new Money();
        o.id2 = id;
        o.money2 = money;
        o.date2 = date;
        o.type2 = type;
        o.note2 = note;
        //创建数据库访问对象
        MoneyDAO dao = new MoneyDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //执行数据库访问方法
        long result = dao.addPay(o);

        if (result > 0) {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
        }
        //关闭数据库
        dao.close();
        //关闭活动
        finish();
    }

}
