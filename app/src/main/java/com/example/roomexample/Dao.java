package com.example.roomexample;


import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    public void addCustomer(Customer cust);

    @Query("SELECT * FROM Customer")
    public List<Customer> getCustomers();
}
