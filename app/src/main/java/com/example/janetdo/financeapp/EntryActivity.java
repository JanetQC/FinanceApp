package com.example.janetdo.financeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by janetdo on 22.04.18.
 */

public class EntryActivity extends AppCompatActivity {
    private CloudantService cloudant;
    private RadioButton expense;
    private RadioButton reimbursement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cloudant = CloudantService.getInstance();
        super.onCreate(savedInstanceState);



        setContentView(R.layout.entry2);
        EditText date = findViewById(R.id.date);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("d.MM.yy");
        date.setText(format.format(timestamp));

    }

    public void removeText(View view) {
        //access clicked edit text?
        EditText text = findViewById(R.id.commentBox);
        text.setText("");
    }

    public void setExpense(View view) {
        reimbursement = findViewById(R.id.reimbursement);
        reimbursement.setChecked(false);
    }

    public void setReimbursement(View view) {
        expense = findViewById(R.id.expense);
        expense.setChecked(false);
    }




    public void saveEntry(View view) {
        EditText commentBox = findViewById(R.id.commentBox);
        EditText amountField = findViewById(R.id.amount);
        RadioButton expenseField = findViewById(R.id.expense);

        String comment = commentBox.getText().toString().trim();
        double amount = Double.valueOf( amountField.getText().toString());
        boolean isExpense = expenseField.isChecked();
        Category category = Category.entertainment;
        Category subcategory = Category.entertainment;
        Expense expense = new Expense(category, subcategory, comment, amount, isExpense);

        Toast.makeText(getApplicationContext(), "Neuer Eintrag wurde gespeichert.",
                Toast.LENGTH_LONG).show();
        cloudant.writeToTable(expense);
        try {
            wait(1000);
        } catch (Exception e) {
        }

        finish();
    }

    private void calculatePercentageValues(){

    }
}
