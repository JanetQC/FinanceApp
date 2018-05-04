package com.example.janetdo.financeapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by janetdo on 04.05.18.
 */

public class PieChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_chart);
        initPieChart();
    }

    private void initPieChart() {
        String[] mParties = new String[]{
                "Party A", "Party B", "Party C"
        };
        float mult = 5;
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        PieChart pieChart = (PieChart) findViewById(R.id.pieChart);
        for (int i = 0; i < 3; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
                    mParties[i % mParties.length],
                    null));
        }
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(R.color.springOrange);
        colors.add(R.color.darkBlue);
        colors.add(R.color.middleBlue);
        colors.add(R.color.pigPink);


        PieDataSet dataSet = new PieDataSet(entries, "Ausgaben");
        dataSet.setColors(colors);
        Typeface mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        Typeface mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setCenterTextTypeface(mTfRegular);
        // pieChart.setCenterText(generateCenterSpannableText());

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
    }
}
