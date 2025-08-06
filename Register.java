package com.example.webtoon;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText username,password,repwd;
    Button btnreg,btnclog;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.edunm);
        password=findViewById(R.id.regpass);
        repwd=findViewById(R.id.confirmbtn);
        btnclog=findViewById(R.id.btnclog);
        btnreg=findViewById(R.id.btnreg);
        DB=new DBHelper(this);



        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repwd.getText().toString();

                if (TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(repass))
                    Toast.makeText(Register.this,"ALL field Required",Toast.LENGTH_LONG).show();
                else {
                    if (pass.equals(repass)){
                        Boolean checkuser=DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert=DB.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(Register.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                                Intent i =new Intent(getApplicationContext(),Home.class);
                                startActivity(i);
                            }else {
                                Toast.makeText(Register.this,"Registration Failed",Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(Register.this,"User Already Exists",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(Register.this, "Passwords are not matching", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnclog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }
}