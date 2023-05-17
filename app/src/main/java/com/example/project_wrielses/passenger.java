package com.example.project_wrielses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_wrielses.room.entities.appdatabase.AppDatabase;
import com.example.project_wrielses.room.entities.dao.MyDao;
import com.example.project_wrielses.room.entities.entities.Bus;

import java.util.List;

public class passenger extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        TextView bussesDetails=findViewById(R.id.bussesDetails);
        AppDatabase db=AppDatabase.getDatabase(getApplicationContext());
        MyDao dao = db.dao();
        List<Bus> busses=dao.getAllBusses();
        String allBusses="";
        for(int i=0;i<busses.size();i++){
            allBusses+="ID:";
            allBusses+=busses.get(i).id;
            allBusses+="\n";
            allBusses+="Driver Name:";
            allBusses+=busses.get(i).driverName;
            allBusses+="\n";
            allBusses+="Bus Model:";
            allBusses+=busses.get(i).model;
            allBusses+="\n";
            allBusses+="Ava Seats:";
            allBusses+=busses.get(i).avaSeats;
            allBusses+="\n";
            allBusses+="From ";
            allBusses+=busses.get(i).fromDes;
            allBusses+="\n";
            allBusses+="to :";
            allBusses+=busses.get(i).toDes;
            allBusses+="\n";
            allBusses+="take off:";
            allBusses+=busses.get(i).startTime;
            allBusses+="\n";
            allBusses+="arrival";
            allBusses+=busses.get(i).endTime;
            allBusses+="\n";

            allBusses+="\n";
        }
        bussesDetails.setText(allBusses);

        Button book = findViewById(R.id.bookButton);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText busId=findViewById(R.id.enterBusId);
                int id=Integer.parseInt(busId.getText().toString());
                AppDatabase db=AppDatabase.getDatabase(getApplicationContext());
                MyDao dao = db.dao();
                List<Bus> busses=dao.getAllBusses();
                for(int i=0;i<busses.size();i++){
                    if(busses.get(i).id==id){
                        CheckBox single = findViewById(R.id.oneSeatCheckBox);
                        CheckBox whole = findViewById(R.id.wholeBusCheckBox);
                        dao.deleteBus(busses.get(i));
                        if(single.isChecked() && whole.isChecked()){
                            Toast.makeText(passenger.this, "choose only one", Toast.LENGTH_SHORT).show();
                        }
                        else if(single.isChecked()){
                            if(busses.get(i).avaSeats>0)
                                 busses.get(i).avaSeats--;
                        }
                        else if(whole.isChecked()){
                            busses.get(i).avaSeats=0;
                        }
                        dao.insertBus(busses.get(i));
                        String allBusses="";
                        for(int j=0;j<busses.size();j++){

                            allBusses+="ID:";
                            allBusses+=busses.get(j).id;
                            allBusses+="\n";
                            allBusses+="Driver Name:";
                            allBusses+=busses.get(j).driverName;
                            allBusses+="\n";
                            allBusses+="Bus Model:";
                            allBusses+=busses.get(j).model;
                            allBusses+="\n";
                            allBusses+="Ava Seats:";
                            allBusses+=busses.get(j).avaSeats;
                            allBusses+="\n";
                            allBusses+="From ";
                            allBusses+=busses.get(j).fromDes;
                            allBusses+="\n";
                            allBusses+="to :";
                            allBusses+=busses.get(j).toDes;
                            allBusses+="\n";
                            allBusses+="take off:";
                            allBusses+=busses.get(j).startTime;
                            allBusses+="\n";
                            allBusses+="arrival";
                            allBusses+=busses.get(j).endTime;
                            allBusses+="\n";

                            allBusses+="\n";
                        }
                        bussesDetails.setText(allBusses);



                    }
                }
            }
        });
    }
}