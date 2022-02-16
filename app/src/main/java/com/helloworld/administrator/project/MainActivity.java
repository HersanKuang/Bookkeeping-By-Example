package com.helloworld.administrator.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button btn_income= (Button) findViewById(R.id.btn_income);
        btn_income.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,InsertActivity.class);
               startActivity(intent);
           }
        });

        Button btn_pay= (Button) findViewById(R.id.btn_pay);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Insert2Activity.class);
                startActivity(intent);
            }
        });

        Button btn_myincome= (Button) findViewById(R.id.btn_myincome);
        btn_myincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,QueryActivity.class);
                startActivity(intent);
            }
        });
        Button btn_mypay= (Button) findViewById(R.id.btn_mypay);
        btn_mypay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Query2Activity.class);
                startActivity(intent);
            }
        });


        Button btn_update= (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
                startActivity(intent);
            }
        });
        Button btn_update2= (Button) findViewById(R.id.btn_update2);
        btn_update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Update2Activity.class);
                startActivity(intent);
            }
        });

        Button btn_del= (Button) findViewById(R.id.btn_del);
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DeleteActivity.class);
                startActivity(intent);
            }
        });

        Button btn_del2= (Button) findViewById(R.id.btn_del2);
        btn_del2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Delete2Activity.class);
                startActivity(intent);
            }
        });

        Button btn_esc= (Button) findViewById(R.id.btn_esc);
        btn_esc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.exit(0);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,LoginActivity.class);
                startActivity(intent);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


            }
        });

    }
}
