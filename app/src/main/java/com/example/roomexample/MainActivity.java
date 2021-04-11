package com.example.roomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static MyDatabase myDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "customerdb").allowMainThreadQueries().build();

        Button buttonAddCustomer = findViewById(R.id.buttonAddCustomer);
        Button buttonRefresh = findViewById(R.id.buttonRefresh);
        TextView textOutput = findViewById(R.id.textOutput);
        EditText editFirstName = findViewById(R.id.editFirstName);
        EditText editLastName = findViewById(R.id.editLastName);
        EditText editEmail = findViewById(R.id.editEmail);

        buttonAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtain data from the interface
                String firstName = editFirstName.getText().toString();
                String lastName = editLastName.getText().toString();
                String email = editEmail.getText().toString();

                // Create noew customer object
                Customer cust = new Customer();
                cust.setFirstName(firstName);
                cust.setLastName(lastName);
                cust.setEmail(email);

                // Insert customer into database
                myDatabase.dao().addCustomer(cust);

                // Display message
                Toast.makeText(getBaseContext(), "Customer added successfully!", Toast.LENGTH_SHORT).show();

                // Clear text
                editFirstName.setText("");
                editLastName.setText("");
                editEmail.setText("");


            }
        });

        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            List<Customer> customers = myDatabase.dao().getCustomers();
            String customerListOutput = "";
            for(Customer cust : customers)
            {
                customerListOutput += "ID: " + cust.getId() + " Name: " + cust.getFirstName() + " " + cust.getLastName() + " Email: " + cust.getEmail() + "\n";
            }

            textOutput.setText(customerListOutput);

            }
        });
    }

}