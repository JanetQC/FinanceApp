package com.example.janetdo.financeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by janetdo on 22.04.18.
 */

public class EntryActivity extends AppCompatActivity {
    private CloudantService cloudant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cloudant = new CloudantService();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.entry);
    }

    public void removeText(View view) {
        //access clicked edit text?
        EditText text = findViewById(R.id.commentBox);
        text.setText("");
    }

    public void saveEntry(View view) {
        EditText commentBox = findViewById(R.id.commentBox);
        EditText date = findViewById(R.id.date);
        EditText amountField = findViewById(R.id.amount);
        RadioButton expenseField = findViewById(R.id.expense);

        String comment = commentBox.getText().toString().trim();
        String dateText = date.getText().toString().trim();
        //   float amount = (float) amountField.getText().toString();
        double amount = 0.0;
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
}
