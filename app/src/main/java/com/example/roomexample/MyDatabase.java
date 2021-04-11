package com.example.roomexample;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Customer.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    // Since we named our DAO class as dao
    public abstract Dao dao();

}
