package com.example.janetdo.financeapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by janetdo on 04.05.18.
 */

public class PieChartActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private CloudantService cloudant;
    List<Category> allCategories;
    Map<Category, Double> categoryValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_chart);
        cloudant = CloudantService.getInstance();
        allCategories = Arrays.asList(Category.values());
        categoryValues =  getEachCategory();
        initPieChart();
    }

    private void initPieChart() {
        double sum = categoryValues.entrySet().stream().map(ele -> ele.getValue()).mapToDouble(Double::doubleValue).sum();

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        PieChart pieChart = (PieChart) findViewById(R.id.pieChart);
        categoryValues.entrySet().stream().filter(ele -> ele.getValue() != 0).forEach(category -> {
          float percentageValue = (float) (category.getValue()/ sum)*100;
          String categoryName =  category.getKey().toString().substring(0, 1).toUpperCase() + category.getKey().toString().substring(1);
            entries.add(new PieEntry(percentageValue, categoryName, null));
        });

        ArrayList<Integer> colors = new ArrayList<Integer>();
        // colors.add(R.color.springOrange);
        //  colors.add(R.color.darkBlue);
        // colors.add(R.color.middleBlue);
        // colors.add(R.color.pigPink);

        int[] PASTEL_COLORS = {
                Color.rgb(64, 89, 128), Color.rgb(149, 165, 124), Color.rgb(217, 184, 162),
                Color.rgb(191, 134, 134), Color.rgb(179, 48, 80)
        };

        PieDataSet dataSet = new PieDataSet(entries, "Ausgaben");
        //  dataSet.setColors(colors);
        dataSet.setColors(PASTEL_COLORS);

        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf"));
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.setOnChartValueSelectedListener(this);


    }

    public Map<Category, Double> getEachCategory() {
        Map<Category, Double> catgeoryMap = new HashMap<>();

        allCategories.stream().forEach(category -> {
            List<Expense> tempList = cloudant.selectCategory(category);
            double categorySum = tempList.stream().filter(ele -> ele.isExpense()).map(exp -> exp.getAmount()).mapToDouble(Double::doubleValue).sum();
            double reimbursementSum = tempList.stream().filter(ele -> !ele.isExpense()).map(exp -> exp.getAmount()).mapToDouble(Double::doubleValue).sum();
            double resultSum = categorySum - reimbursementSum;
            catgeoryMap.put(category, resultSum);
        });

        System.out.println(catgeoryMap.entrySet().toString());
        return catgeoryMap;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;
        System.out.println("!!!");
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {

        System.out.println("???");
        Log.i("PieChart", "nothing selected");
    }
}
