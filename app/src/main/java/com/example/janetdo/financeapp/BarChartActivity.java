package com.example.janetdo.financeapp;

import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janetdo on 04.05.18.
 */

public class BarChartActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private BarChart barChart;
    private CloudantService cloudant;
    List<Expense> allExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_chart);
        barChart = findViewById(R.id.barChart);
        cloudant = CloudantService.getInstance();
        doStuff();

    }

    private void doStuff() {
        List<Expense> allExpenses = cloudant.getAllExpenses();
        /**
         * 1. select after date, merge all of same month
         * 2. add months to bargroup
         * 3.
         */
        List<BarEntry> bargroup = new ArrayList<BarEntry>();
        bargroup.add(new BarEntry(0f, 30f, "January"));
        bargroup.add(new BarEntry(1f, 2f, "February"));
        bargroup.add(new BarEntry(2f, 4f, "March"));
        bargroup.add(new BarEntry(3f, 6f, "April"));



        // creating dataset for Bar Group
        BarDataSet barDataSet = new BarDataSet(bargroup, "");

        barDataSet.setColor(ContextCompat.getColor(this, R.color.middleBlue));

        XAxis xAxis = barChart.getXAxis();

        xAxis.setLabelCount(3, false);
        BarData data = new BarData(barDataSet);
        barChart.setData(data);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.enableGridDashedLine(5f, 5f, 0f);
        barChart.getAxisRight().enableGridDashedLine(5f, 5f, 0f);
        barChart.getAxisLeft().enableGridDashedLine(5f, 5f, 0f);
        barChart.getDescription().setEnabled(false);
        barChart.animateY(1000);
        barChart.getLegend().setEnabled(true);
        barChart.setPinchZoom(true);
        barChart.getData().setDrawValues(false);


    }

    private void initAxis(YAxis axis) {
        IAxisValueFormatter custom = new MyAxisValueFormatter();
        axis.setDrawGridLines(false);
        axis.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf"));
        axis.setLabelCount(8, false);
        axis.setValueFormatter(custom);
        axis.setSpaceTop(15f);
        axis.setAxisMinimum(0f);

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {
    }
}
