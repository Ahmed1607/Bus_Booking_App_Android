package com.example.project_wrielses.room.entities.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.project_wrielses.room.entities.entities.Bus;
import com.example.project_wrielses.room.entities.entities.Driver;
import com.example.project_wrielses.room.entities.entities.User;

import java.util.List;

@Dao
public interface MyDao {


    //OPERATIONS FOR USER
    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    //OPERATIONS FOR DRIVER

    @Query("SELECT * FROM driver")
    List<Driver> getAllDrivers();



    @Insert
    void insertDriver(Driver driver);

    @Delete
    void deleteDriver(Driver driver);
    //OPERATIONS FOR BUS

    @Query("SELECT * FROM bus")
    List<Bus> getAllBusses();



    @Insert
    void insertBus(Bus bus);

    @Delete
    void deleteBus(Bus bus);

}
