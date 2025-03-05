package com.example.sqliteapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name, contact, dob;
    Button insert, delete, update, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        insert = findViewById(R.id.insertBtn);
        update = findViewById(R.id.updateBtn);
        delete = findViewById(R.id.deleteBtn);
        view = findViewById(R.id.viewData);

        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String nameText = name.getText().toString();
                String contectText = contact.getText().toString();
                String dobText = dob.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameText,contectText,dobText);
                if (checkinsertdata == true){
                    Toast.makeText(MainActivity.this,"New Entry inserted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"New Entry not inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });



        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String nameText = name.getText().toString();
                String contectText = contact.getText().toString();
                String dobText = dob.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameText,contectText,dobText);
                if (checkupdatedata == true){
                    Toast.makeText(MainActivity.this,"Entry updated",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Entry not updated",Toast.LENGTH_SHORT).show();
                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String nameText = name.getText().toString();
                Boolean checkdeletedata = DB.deleteuserdata(nameText);
                if (checkdeletedata == true){
                    Toast.makeText(MainActivity.this,"Entry deleted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Entry not deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });


        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Cursor res = DB.getuserdata();
                if (res.getCount() ==0){
                    Toast.makeText(MainActivity.this,"No data exists",Toast.LENGTH_SHORT).show();
                }
                StringBuffer stringBuffer = new StringBuffer();
                while (res.moveToNext()){
                    stringBuffer.append("Name : "+ res.getString(0) + "\n");
                    stringBuffer.append("Contact : "+ res.getString(1) + "\n");
                    stringBuffer.append("DOB : "+ res.getString(2) + "\n\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(stringBuffer.toString());
                builder.show();
            }
        });
    }
}