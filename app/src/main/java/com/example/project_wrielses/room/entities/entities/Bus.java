package com.example.project_wrielses.room.entities.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Bus {
    @PrimaryKey(autoGenerate = true)
    public int id;


    @ColumnInfo(name = "driver_name")
    public String driverName;
    @ColumnInfo(name = "ava_seats")
    public int avaSeats;

    @ColumnInfo(name = "start_time")
    public String startTime;

    @ColumnInfo(name = "end_time")
    public String endTime;

    @ColumnInfo(name = "from_des")
    public String fromDes;

    @ColumnInfo(name = "to_des")
    public String toDes;

    @ColumnInfo(name = "model")
    public String model;


}