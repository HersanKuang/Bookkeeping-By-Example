package com.helloworld.administrator.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MoneyDAO {
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public MoneyDAO(Context context) {
        this.context = context;
    }

    //打开数据库
    public void open() throws SQLiteException {
        dbHelper = new DBHelper(context, "MoneyData.db", null, 1);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }
    //关闭数据库
    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }
    //添加收入
    public long addIncome(Money o) {
        // 创建ContentValues对象
        ContentValues values = new ContentValues();
        // 向该对象中插入键值对
        values.put("id", o.id);
        values.put("money", o.money);
        values.put("date", o.date);
        values.put("type", o.type);
        values.put("note", o.note);
        // 调用insert()方法将数据插入到数据库当中
        return db.insert("tb_income", null, values);
    }
    //查找所有收入
    public ArrayList<Map<String, Object>> getAllIncome() {
        ArrayList<Map<String, Object>> listOrders = new ArrayList<Map<String, Object>>();
        Cursor cursor = db.query("tb_income", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();  //记录数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", cursor.getString(cursor.getColumnIndex("id")));
                map.put("money", cursor.getString(cursor.getColumnIndex("money")));
                map.put("date", cursor.getString(cursor.getColumnIndex("date")));
                map.put("type", cursor.getString(cursor.getColumnIndex("type")));
                map.put("note", cursor.getString(cursor.getColumnIndex("note")));
                listOrders.add(map);
            }
            return listOrders;
        }
    }
    //根据日期查找收入
    public Money getOrders(String orderid) {
        //查询收入
        Cursor cursor = db.query("tb_income", null, "date=?", new String[]{orderid}, null, null, null);
        Money o = new Money();
        while (cursor.moveToNext()) {
            o.id = cursor.getString(cursor.getColumnIndex("id"));
            o.money= cursor.getString(cursor.getColumnIndex("money"));
            o.date = cursor.getString(cursor.getColumnIndex("date"));
            o.type = cursor.getString(cursor.getColumnIndex("type"));
            o.note = cursor.getString(cursor.getColumnIndex("note"));
        }
        return o;
    }
    //删除指定收入
    public int deletOrders(Money o) {
        return db.delete("tb_income", "date=?", new String[]{String.valueOf(o.date)});
    }

    //修改指定收入
    public int updateOrders(Money o) {
        ContentValues value = new ContentValues();
        value.put("money", o.money);
        value.put("id", o.id);
        value.put("type", o.type);
        value.put("note", o.note);
        return db.update("tb_income", value, "date=?", new String[]{String.valueOf(o.date)});
    }

    //添加支出
    public long addPay(Money o) {
        // 创建ContentValues对象
        ContentValues values = new ContentValues();
        // 向该对象中插入键值对
        values.put("id", o.id2);
        values.put("money", o.money2);
        values.put("date", o.date2);
        values.put("type", o.type2);
        values.put("note", o.note2);
        // 调用insert()方法将数据插入到数据库当中
        return db.insert("tb_pay", null, values);
    }

    //查找所有支出
    public ArrayList<Map<String, Object>> getAllPay() {
        ArrayList<Map<String, Object>> listOrders = new ArrayList<Map<String, Object>>();
        Cursor cursor = db.query("tb_pay", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();  //记录数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", cursor.getString(cursor.getColumnIndex("id")));
                map.put("money", cursor.getString(cursor.getColumnIndex("money")));
                map.put("date", cursor.getString(cursor.getColumnIndex("date")));
                map.put("type", cursor.getString(cursor.getColumnIndex("type")));
                map.put("note", cursor.getString(cursor.getColumnIndex("note")));
                listOrders.add(map);
            }
            return listOrders;
        }
    }
    //根据id查找支出
    public Money getPay(String orderid) {
        //查询支出
        Cursor cursor = db.query("tb_pay", null, "date=?", new String[]{orderid}, null, null, null);
        Money o = new Money();
        while (cursor.moveToNext()) {
            o.id2 = cursor.getString(cursor.getColumnIndex("id"));
            o.money2= cursor.getString(cursor.getColumnIndex("money"));
            o.date2 = cursor.getString(cursor.getColumnIndex("date"));
            o.type2 = cursor.getString(cursor.getColumnIndex("type"));
            o.note2 = cursor.getString(cursor.getColumnIndex("note"));
        }
        return o;
    }

    //修改指定支出
    public int updatePay(Money o) {
        ContentValues value = new ContentValues();
        value.put("money", o.money2);
        value.put("date", o.date2);
        value.put("type", o.type2);
        value.put("note", o.note2);
        return db.update("tb_pay", value, "date=?", new String[]{String.valueOf(o.date2)});
    }

    //删除指定支出
    public int deletPay(Money o) {
        return db.delete("tb_pay", "date=?", new String[]{String.valueOf(o.date2)});
    }


}
