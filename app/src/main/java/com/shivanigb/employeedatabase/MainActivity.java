package com.shivanigb.employeedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etEmpid,etEmpname;
    Button btnAdd,btnView,btnDelete,btnUpdate;
    TextView tvData;
    DbHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etEmpid=(EditText)findViewById(R.id.etEmpid);
        etEmpname=(EditText)findViewById(R.id.etEmpname);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnView=(Button)findViewById(R.id.btnView);
        tvData=(TextView)findViewById(R.id.tvData);
        db=new DbHandler(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=etEmpid.getText().toString();
                String name=etEmpname.getText().toString();
                if(id.length()==0)
                {
                    etEmpid.setError("id is empty");
                    etEmpid.requestFocus();
                    return;
                }
                if(name.length()==0)
                {
                    etEmpname.setError("name is empty");
                    etEmpname.requestFocus();
                    return;
                }
                Employee e=new Employee(Integer.parseInt(id),name);
                db.addEmployee(e);
                etEmpid.setText("");
                etEmpname.setText("");
                etEmpid.requestFocus();


            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=etEmpid.getText().toString();
                String name=etEmpname.getText().toString();
                if(id.length()==0)
                {
                    etEmpid.setError("id is empty");
                    etEmpid.requestFocus();
                    return;
                }
                if(name.length()==0)
                {
                    etEmpname.setError("name is empty");
                    etEmpname.requestFocus();
                    return;
                }
                Employee e=new Employee(Integer.parseInt(id),name);
                db.updateEmployee(e);
                etEmpid.setText("");
                etEmpname.setText("");
                etEmpid.requestFocus();

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=etEmpid.getText().toString();
                if(id.length()==0)
                {
                    etEmpid.setError("id is empty");
                    etEmpid.requestFocus();
                    return;
                }

                Employee e=new Employee(Integer.parseInt(id),"");
                db.deleteEmployee(e);
                etEmpid.setText("");
                etEmpid.requestFocus();


            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvData.setText("");
                ArrayList<Employee> a=new ArrayList<Employee>();
                a=db.viewEmployee();
                if(a.size()==0)
                {
                    tvData.setText("no records");
                }
                else
                {
                    for( Employee m: a)
                    {
                        tvData.setText(tvData.getText()+"\n"+m.getId()+":"+m.getName());
                    }
                }
            }
        });

    }
}
