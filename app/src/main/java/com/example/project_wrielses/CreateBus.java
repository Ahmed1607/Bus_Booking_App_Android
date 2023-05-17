package com.example.project_wrielses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_wrielses.room.entities.appdatabase.AppDatabase;
import com.example.project_wrielses.room.entities.dao.MyDao;
import com.example.project_wrielses.room.entities.entities.Bus;

public class CreateBus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bus);
    }

    public void addBusOnClick(View v){

        EditText busName = findViewById(R.id.driverName);
        EditText busModel = findViewById(R.id.busModel);
        EditText from = findViewById(R.id.fromDes);
        EditText to = findViewById(R.id.toDes);
        EditText takeOff = findViewById(R.id.takeOffTime);
        EditText arrival = findViewById(R.id.arrivalTime);
        CheckBox twelve = findViewById(R.id.twelveSeatsCheckBox);
        CheckBox fifteen = findViewById(R.id.fifteenSeatsCheckBox);
        CheckBox thirty = findViewById(R.id.thirtySeatsCheckBox);
        Bus bus = new Bus();
        bus.driverName = busName.getText().toString();
        bus.model=busModel.getText().toString();
        bus.fromDes=from.getText().toString();
        bus.toDes=to.getText().toString();
        bus.startTime=takeOff.getText().toString();
        bus.endTime=arrival.getText().toString();
        if(twelve.isChecked()){
            bus.avaSeats=12;
        }
        else if(fifteen.isChecked()){
            bus.avaSeats=15;
        }
        else if(thirty.isChecked()){
            bus.avaSeats=30;
        }

        AppDatabase db=AppDatabase.getDatabase(getApplicationContext());
        MyDao dao = db.dao();

        dao.insertBus(bus);
        Toast.makeText(this,"Bus added", Toast.LENGTH_SHORT).show();
        Intent f= new Intent(this,driver.class);
        f.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        f.putExtra("username",getIntent().getExtras().getString("username"));
        startActivity(f);




    }

}