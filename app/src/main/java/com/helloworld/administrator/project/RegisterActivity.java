package com.helloworld.administrator.project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

    private Button btn_register1;
    private Button btn_exit1;
    private EditText edit_register;
    private EditText edit_setpassword;
    private EditText edit_resetpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_exit1 = (Button) findViewById(R.id.btn_exit1);
        btn_register1 = (Button) findViewById(R.id.btn_register1);
        edit_register = (EditText) findViewById(R.id.edit_register);
        edit_setpassword = (EditText) findViewById(R.id.edit_setpassword);
        edit_resetpassword = (EditText) findViewById(R.id.edit_resetpassword);
        btn_register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取账号输入框的内容
                String ed_Username = edit_register.getText().toString();
                //获取密码输入框内容
                String ed_UserPass = edit_setpassword.getText().toString();
                //获取重复密码输入框的内容
                String ed_UserPass_2=edit_resetpassword.getText().toString();
                //注册账号合法性判断
                if (ed_Username.equals("")) {
                    Snackbar.make(v, "账号不能为空", BaseTransientBottomBar.LENGTH_SHORT).show();
                }else if (ed_UserPass.equals("")||ed_UserPass_2.equals("")){
                    Snackbar.make(v, "密码不能为空", BaseTransientBottomBar.LENGTH_SHORT).show();
                } else if(!(ed_UserPass.equals(ed_UserPass_2))){
                    Snackbar.make(v, "两次输入的密码不相等，请重新输入!", BaseTransientBottomBar.LENGTH_SHORT).show();
                }else{
                    //参数1：文件名 参数2：模式（私有模式—MODE_PRIVATE)
                    //如果名为MyShare已经存在，putString会替换内容，不存在的话会直接新建
                    SharedPreferences sharedPreferences=getSharedPreferences("MyShare",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    //参数1：keyName，参数2：内容
                    editor.putString("userName",ed_Username);
                    editor.putString("userPass",ed_UserPass);
                    //完成创建
                    editor.commit();
                    //注册成功提示
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                    //跳转并结束注册页面
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();
                }
            }
        });

        btn_exit1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}

