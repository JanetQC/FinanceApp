package com.example.janetdo.financeapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void addEntry(View view) {
        Intent intent = new Intent(this, EntryActivity.class);
        startActivity(intent);
    }

    public void startPieChartActivity(View view) {
        Intent intent = new Intent(this, PieChartActivity.class);
        startActivity(intent);
    }

    public void startBarChartActivity(View view) {
        Intent intent = new Intent(this, BarChartActivity.class);
        startActivity(intent);
    }
}
