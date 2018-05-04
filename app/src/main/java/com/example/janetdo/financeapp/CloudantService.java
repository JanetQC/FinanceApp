package com.example.janetdo.financeapp;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.example.janetdo.financeapp.Credential.Credentials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by janetdo on 02.01.18.
 */

public class CloudantService {
    private CloudantClient client;
    private String selectorAll = "{\"selector\":{}}";
    private List<Expense> allExpenses;
    private List<Expense> selectedExpenses;
    private Database expenses;

    private static CloudantService serviceInstance;

    public static CloudantService getInstance() {
        if( serviceInstance == null ) {
            serviceInstance = new CloudantService();
        }
        return serviceInstance;
    }
    public CloudantService() {
        client = ClientBuilder.account(Credentials.CLOUDANT_NAME)
                .username(Credentials.CLOUDANT_NAME)
                .password(Credentials.CLOUDANT_PW)
                .build();
        expenses = client.database("expenses", false);
    }


    public List<Expense> getAllExpenses() {
        allExpenses = new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    allExpenses = expenses.findByIndex(selectorAll, Expense.class);

                } catch (Exception e) {
                    System.out.println("Db could not fetch all elements of problem" + e);
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {

        }
        return allExpenses;
    }

    public List<Expense> selectCategory(Category category) {
        selectedExpenses = new ArrayList<Expense>();
        final String selector = "{\"selector\":{category:" + category.toString() + "}}";
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    selectedExpenses = expenses.findByIndex(selector, Expense.class);

                } catch (Exception e) {
                    System.out.println("Db could not fetch all elelments of problem" + e);
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {

        }
        return selectedExpenses;
    }

    public List<Expense> selectSubcategory(Category subcategory) {
        selectedExpenses = new ArrayList<Expense>();
        final String selector = "{\"selector\":{subcategory:" + subcategory.toString() + "}}";
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    selectedExpenses = expenses.findByIndex(selector, Expense.class);

                } catch (Exception e) {
                    System.out.println("Db could not fetch all elements of problem" + e);
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {

        }
        return selectedExpenses;
    }

    public void writeToTable(Object expense) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    expenses.save(expense);
                } catch (Exception e) {
                    System.out.println("This didn't work so well, huh? Db could not save element:" + expense + "in expenses" + e);
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {

        }
    }

    public Map<String, Double> getMonthlyExpenses(){
        Map<String, Double> monthlyExpenses = new HashMap<>();

        return monthlyExpenses;
    }

}
