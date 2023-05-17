package com.example.project_wrielses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_wrielses.room.entities.appdatabase.AppDatabase;
import com.example.project_wrielses.room.entities.dao.MyDao;
import com.example.project_wrielses.room.entities.entities.Bus;
import com.example.project_wrielses.room.entities.entities.Driver;
import com.example.project_wrielses.room.entities.entities.User;

import java.sql.SQLOutput;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db=AppDatabase.getDatabase(getApplicationContext());
        MyDao dao = db.dao();


    }
    public void sginup_onclick(View v){
        Intent f= new Intent(this,createacc.class);
        startActivity(f);
    }
    public void sginin_onclick(View v){
        AppDatabase db=AppDatabase.getDatabase(getApplicationContext());
        MyDao dao = db.dao();

        CheckBox pass=findViewById(R.id.passenger_check);
        CheckBox driver=findViewById(R.id.driver_check);
        EditText username= findViewById(R.id.username_login);
        EditText password= findViewById(R.id.passwrod_login);
        if(pass.isChecked() && driver.isChecked()){
            Toast.makeText(this,"Choose passenger or driver not both  ",Toast.LENGTH_SHORT).show();
        }
        // anotation

        else if (pass.isChecked()){
            boolean flag =false;

            List<User>users=dao.getAllUsers();
            for(int i=0;i<users.size();i++){
                if(users.get(i).username.equals(username.getText().toString()) && users.get(i).password.equals(password.getText().toString())){
                    Toast.makeText(this,"Successful Login",Toast.LENGTH_SHORT).show();
                    flag=true;

                    Intent f= new Intent(this,passenger.class);
                    startActivity(f);
                }
            }

            if(flag==false){
                Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show();
            }



        }
        else if (driver.isChecked()){
            boolean flag =false;
            List<Driver>drivers=dao.getAllDrivers();

            for(int i=0;i<drivers.size();i++){

                if(drivers.get(i).username.equals(username.getText().toString()) && drivers.get(i).password.equals(password.getText().toString())){
                    Toast.makeText(this,"Successful Login",Toast.LENGTH_SHORT).show();
                    flag=true;

                    Intent f= new Intent(this,driver.class);
                    f.putExtra("username",username.getText().toString());

                    startActivity(f);
                }
            }

            if(flag==false){
                Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show();

            }

        }
        else{
            Toast.makeText(this,"Choose passenger or driver  ",Toast.LENGTH_SHORT).show();
        }

    }
}