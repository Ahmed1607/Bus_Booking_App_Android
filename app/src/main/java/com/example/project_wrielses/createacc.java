package com.example.project_wrielses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_wrielses.room.entities.appdatabase.AppDatabase;
import com.example.project_wrielses.room.entities.dao.MyDao;
import com.example.project_wrielses.room.entities.entities.Driver;
import com.example.project_wrielses.room.entities.entities.User;

import java.util.List;

public class createacc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createacc);
    }
    public void create_onclick(View v){
        CheckBox pass=findViewById(R.id.passenger);
        CheckBox driver=findViewById(R.id.driver);
        EditText username=findViewById(R.id.create_username);
        EditText password=findViewById(R.id.create_password);

        if(pass.isChecked() && driver.isChecked()){
            Toast.makeText(this, "choose only one", Toast.LENGTH_SHORT).show();
        }

        else  if (pass.isChecked()){
            AppDatabase db=AppDatabase.getDatabase(getApplicationContext());
            MyDao dao = db.dao();
            User newUser= new User();
            newUser.username=username.getText().toString();
            newUser.password=password.getText().toString();

            List<User> users=dao.getAllUsers();
            boolean found = false;
            for(int i=0;i<users.size();i++){
                if(users.get(i).username.equals(username.getText().toString())){
                    found=true;
                }
            }
            if(found){
                Toast.makeText(this,"username taken",Toast.LENGTH_SHORT).show();
            }
            else{
                dao.insertUser(newUser);
                Toast.makeText(this,"Account Created  ",Toast.LENGTH_SHORT).show();

                Intent f= new Intent(this,passenger.class);
                finish();
//                f.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(f);
            }

        }
        else if (driver.isChecked()){
            AppDatabase db=AppDatabase.getDatabase(getApplicationContext());
            MyDao dao = db.dao();
            Driver newDriver= new Driver();
            newDriver.username=username.getText().toString();
            newDriver.password=password.getText().toString();

            List<Driver>drivers=dao.getAllDrivers();
            boolean found = false;
            for(int i=0;i<drivers.size();i++){
                if(drivers.get(i).username.equals(username.getText().toString())){
                    found=true;
                }
            }
            if(found){
                Toast.makeText(this,"username taken",Toast.LENGTH_SHORT).show();
            }
            else{
                dao.insertDriver(newDriver);
                Toast.makeText(this,"Account Created  ",Toast.LENGTH_SHORT).show();

                Intent f= new Intent(this,driver.class);
                finish();

//                f.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                f.putExtra("username",username.getText().toString());
                startActivity(f);
            }

        }
        else{
            Toast.makeText(this,"Choose passenger or driver  ",Toast.LENGTH_SHORT).show();
        }

    }
}