package com.example.project_wrielses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.project_wrielses.room.entities.appdatabase.AppDatabase;
import com.example.project_wrielses.room.entities.dao.MyDao;
import com.example.project_wrielses.room.entities.entities.Bus;

import java.util.List;

public class driver extends AppCompatActivity {
    String userName=null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userName= extras.getString("username");
        }
        getBusDetails(userName);

    }

    private void getBusDetails(String userName) {
        TextView busDetails = findViewById(R.id.myBus);
        Bus myBus = new Bus();
        AppDatabase db=AppDatabase.getDatabase(getApplicationContext());
        MyDao dao = db.dao();
        List<Bus> busses= dao.getAllBusses();
        boolean flag=false;
        for(int i=0;i<busses.size();i++){
            if(busses.get(i).driverName.equals(userName)){
                myBus=busses.get(i);
                flag=true;
            }
        }
        if(flag){
            busDetails.setText("From: "+myBus.fromDes+"\nTo: "+myBus.toDes+"\n"+"TakeOff: "+myBus.startTime+"\narrival: "+myBus.endTime+"\n"+"Ava Seats. :"+myBus.avaSeats);

        }
        else{
            busDetails.setText("You Don't Have a bus\nCreate a Bus");
        }
    }



    public void addbus_onclick(View V){
        Intent f=new Intent(this ,CreateBus.class);
        f.putExtra("username",userName);
        f.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(f);
    }
    public void removebus_onclick(View V){
        TextView busDetails = findViewById(R.id.myBus);
        AppDatabase db=AppDatabase.getDatabase(getApplicationContext());
        MyDao dao = db.dao();
        List<Bus> busses= dao.getAllBusses();
        for(int i=0;i<busses.size();i++){
            if(busses.get(i).driverName.equals(userName)){
               dao.deleteBus(busses.get(i));
               busses.remove(i);
            }
        }
        busDetails.setText("You Don't Have a bus\nCreate a Bus");
    }
}