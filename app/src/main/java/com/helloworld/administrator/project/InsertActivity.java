package com.helloworld.administrator.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity implements View.OnClickListener {
    //private Spinner mSpinner;
    //private ArrayAdapter<String> mAdapter ;
    //private static String [] mStringArray;
    private EditText et_incomeid;
    private EditText et_money;
    private EditText et_date;
    private EditText et_type;
    private EditText et_note;
    private Button btn_sureincome;
    //static String type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        initView();
    }

    private void initView() {
        et_incomeid= (EditText) findViewById(R.id.et_incomeid);
        et_money= (EditText) findViewById(R.id.et_money);
        et_date= (EditText) findViewById(R.id.et_date);
        et_type= (EditText) findViewById(R.id.et_type);
        et_note= (EditText) findViewById(R.id.et_note);
        btn_sureincome= (Button) findViewById(R.id.btn_sureincome);
        btn_sureincome.setOnClickListener(this);
    }
    public void onClick(View v){
        //当单击“添加”按钮时，获取信息
        String id=et_incomeid.getText().toString().trim();
        String money = et_money.getText().toString().trim();
        String date = et_date.getText().toString().trim();
        String type = et_type.getText().toString().trim();
        String note = et_note.getText().toString();
        //添加
        Money o =new Money();
        o.id=id;
        o.money=money;
        o.date = date;
        o.type = type;
        o.note = note;
        //创建数据库访问对象
        MoneyDAO dao = new MoneyDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //执行数据库访问方法
        long result = dao.addIncome(o);
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






   /* private void init(){
        mSpinner=(Spinner) findViewById(R.id.spinner);
        mStringArray=getResources().getStringArray(R.array.test_string_array);

        //使用自定义的ArrayAdapter
        mAdapter = new TestArrayAdapter(InsertActivity.this,mStringArray);

        mSpinner.setAdapter(mAdapter);
        //监听Item选中事件
        mSpinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());
    }
    static class ItemSelectedListenerImpl implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3) {
            type =mStringArray[position];
            //  System.out.println("选中了:"+mStringArray[position]);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }*/
}
