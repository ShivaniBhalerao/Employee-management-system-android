package com.shivanigb.employeedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by DELL on 9/30/2017.
 */

public class DbHandler extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase db;

    DbHandler(Context context)
    {
        super(context,"empdb",null,1);
        this.context=context;
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table emp(id integer primary key,name text)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void  addEmployee(Employee e)
    {
        ContentValues cv=new ContentValues();
        cv.put("id",e.getId());
        cv.put("name",e.getName());
        long rid=db.insert("emp",null,cv);
        if(rid<0)
            Toast.makeText(context, "insert issue", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "1 record inserted", Toast.LENGTH_SHORT).show();

    }

    public void updateEmployee(Employee e)
    {
        ContentValues cv=new ContentValues();
        cv.put("id",e.getId());
        cv.put("name",e.getName());
        long nor=db.update("emp",cv,"id="+e.getId(),null); //number of records
        Toast.makeText(context, nor+"records updated", Toast.LENGTH_SHORT).show();

    }

    public void deleteEmployee(Employee e)
    {
        long nor=db.delete("emp","id="+e.getId(),null);
        Toast.makeText(context, nor+"records deleted", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<Employee> viewEmployee()
    {
        Cursor c=db.query("emp",null,null,null,null,null,null);
        c.moveToFirst();
        ArrayList<Employee> a=new ArrayList<>();

        if(c.getCount()>0)
        {
            do {
                String id=c.getString(0);
                String name=c.getString(1);
                Employee b=new Employee(Integer.parseInt(id),name);
                a.add(b);

            }while (c.moveToNext());

        }
        return a;

    }
}
