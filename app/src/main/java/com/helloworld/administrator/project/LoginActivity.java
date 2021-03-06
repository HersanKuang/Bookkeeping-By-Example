package com.helloworld.administrator.project;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    //    private SharedPreferences preferences;
    private EditText et_username;
    private EditText et_userpwd;
    private Button btn_login;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberpass;
    private Button btn_register;
    private static final int ID = 1;
    private static final String CHANNELID ="1";
    private static final String CHANNELNAME = "channel1";



//    private NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        et_username = (EditText) findViewById(R.id.et_username);
        et_userpwd = (EditText) findViewById(R.id.et_userpwd);
        Uri uri= Uri.parse("http://www.baidu.com");
        btn_login = (Button) findViewById(R.id.btn_login);
        rememberpass = (CheckBox) findViewById(R.id.remember);
        btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //??????????????????
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            //??????????????????????????????????????????
            String account = pref.getString("Name", "");
            String password = pref.getString("Password", "");
            et_username.setText(account);
            et_userpwd.setText(password);
            rememberpass.setChecked(true);
        }
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //??????????????????
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // manager.cancel(1);
                //??????8.0????????????????????????????????????NotificationChannel
                //API26
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    //????????????api????????????
                    //????????????
                    NotificationChannel channel = new NotificationChannel(CHANNELID,CHANNELNAME,NotificationManager.IMPORTANCE_HIGH);
                    manager.createNotificationChannel(channel);//????????????
                    //??????url
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    PendingIntent pendingIntent = PendingIntent.getActivity(LoginActivity.this,0,intent,0);
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(LoginActivity.this,CHANNELID);
                    builder .setContentTitle("Hi! "+et_username.getText().toString().trim()+", ?????????????????????")//????????????
                            .setContentText("??????????????????")//????????????
                            .setWhen(System.currentTimeMillis())//??????????????????
                            .setContentIntent(pendingIntent)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setAutoCancel(true)//??????????????????
                            //.setSound()
                            //??????????????????????????????????????????????????????????????????????????????????????????
                            .setVibrate(new long[] {0,1000,1000,1000})//????????????
                            //??????????????????LED???????????????????????????????????????????????????????????????????????????
                            .setLights(Color.BLUE,1000,1000)
                            /**???????????????????????????
                             * RIORITY_DEFAULT
                             * RIORITY_MIN
                             * RIORITY_LOW
                             * RIORITY_HIGE
                             * RIORITY_MAX
                             **/
                            .setPriority(NotificationCompat.PRIORITY_MAX)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher)).build();
                    manager.notify(1,builder.build());
                } else{
                    //????????????api????????????
                    Notification notification = new NotificationCompat.Builder(LoginActivity.this)
                            .setContentTitle("Hi! "+et_username.getText().toString().trim()+", ?????????????????????")
                            .setContentText("??????????????????")
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher))
                            .build();
                    manager.notify(1,notification);
                }


                // TODO Auto-generated method stub
                String account = et_username.getText().toString();
                String password = et_userpwd.getText().toString();
                SharedPreferences pre = getSharedPreferences("MyShare", MODE_PRIVATE);
                if (account.equals(pre.getString("userName", "")) && password.equals(pre.getString("userPass", ""))) {
                    editor = pref.edit();
                    //????????????
                    if (rememberpass.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("Name", account);
                        editor.putString("Password", password);
                        Toast.makeText(LoginActivity.this, "????????????", Toast.LENGTH_LONG).show();
                    } else {
                        editor.clear();
                    }
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "???????????????????????????", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
