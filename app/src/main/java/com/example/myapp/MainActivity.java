package com.example.myapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText E_name,E_surname,E_marks,E_contact,E_email,E_department,E_address;
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(this);
        E_name=(EditText) findViewById(R.id.person);
        E_surname=(EditText) findViewById(R.id.surname);
        E_marks=(EditText) findViewById(R.id.marks);
        E_contact=(EditText) findViewById(R.id.contact);
        E_email=(EditText) findViewById(R.id.email);
        E_department=(EditText) findViewById(R.id.department);
        E_address=(EditText) findViewById(R.id.address);
        btn1=(Button) findViewById(R.id.button1);
        btn2=(Button) findViewById(R.id.button2);

    }

    public void demo1(View view) {
        boolean inserted = mydb.insertData(E_name.getText().toString(),E_surname.getText().toString(), E_marks.getText().toString(),E_contact.getText().toString(), E_email.getText().toString(),E_department.getText().toString(),E_address.getText().toString());

        if(inserted==true)
            Toast.makeText(this,"Data inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Data not inserted",Toast.LENGTH_LONG).show();

    }

    public void demo2(View view) {
        Cursor res=mydb.getAllData();
        if(res.getCount() == 0)
        {
            showMessage("Error", "Nothing found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext()){
            buffer.append("ID : "+res.getString(0)+"\n");
            buffer.append("Name : "+res.getString(1)+"\n");
            buffer.append("Surname : "+res.getString(2)+"\n");
            buffer.append("MARKS : "+res.getString(3)+"\n");
            buffer.append("CONTACT : "+res.getString(4)+"\n");
            buffer.append("EMAIL : "+res.getString(5)+"\n");
            buffer.append("DEPARTMENT : "+res.getString(6)+"\n\n");
            buffer.append("ADDRESS : "+res.getString(7)+"\n\n");
        }
        showMessage("Data", buffer.toString());
    }

    private  void showMessage(String m,String message)
    {
        AlertDialog dig=new AlertDialog.Builder(MainActivity.this)
                .setTitle(m)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        dig.show();
    }
}
