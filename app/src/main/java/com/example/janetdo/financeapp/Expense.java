package com.example.janetdo.financeapp;

import java.sql.Timestamp;

/**
 * Created by janetdo on 16.04.18.
 */

public class Expense {
    private Category category;
    private Category subcategory;
    private String comment;
    private double amount;
    private boolean isExpense;
    private Timestamp time;

    public Expense(Category category, Category subcategory, String comment, double amount, boolean isExpense) {
        this.time = new Timestamp(System.currentTimeMillis());
        this.category = category;
        this.amount = amount;
        this.subcategory = subcategory;
        this.comment = comment;
        this.isExpense = isExpense;
    }

 

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expense)) return false;

        Expense isExpense = (Expense) o;

        return category != null ? category.equals(isExpense.category) : isExpense.category == null;
    }

    @Override
    public int hashCode() {
        return category != null ? category.hashCode() : 0;
    }

    public double getAmount() {
        return this.amount;
    }
}
