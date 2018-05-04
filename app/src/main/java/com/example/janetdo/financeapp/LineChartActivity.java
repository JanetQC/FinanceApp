package com.example.janetdo.financeapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janetdo on 05.05.18.
 */

public class LineChartActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    private LineChart lineChart;
    private CloudantService cloudant;
    List<Expense> allExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_chart);
        lineChart = findViewById(R.id.lineChart);
        cloudant = CloudantService.getInstance();
        setupLineChartData();

    }

    private void setupLineChartData() {
        List<Entry> yVals = new ArrayList<Entry>();
        yVals.add(new Entry(0f, 30f, "0"));
        yVals.add(new Entry(1f, 2f, "1"));
        yVals.add(new Entry(2f, 4f, "2"));
        yVals.add(new Entry(3f, 6f, "3"));
        yVals.add(new Entry(4f, 8f, "4"));

        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");
        lineChart.setOnChartValueSelectedListener(this);
        set1.setColor(R.color.darkBlue);
        set1.setCircleColor(Color.BLUE);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(0f);
        set1.setDrawFilled(false);

        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);

        // set data
        lineChart.setData(data);
        lineChart.setPinchZoom(true);
        //  lineChart.getXAxis().enableGridDashedLine(5f, 5f, 0f);
        lineChart.getAxisRight().enableGridDashedLine(5f, 5f, 0f);
        lineChart.getAxisLeft().enableGridDashedLine(5f, 5f, 0f);
        lineChart.getXAxis().setLabelCount(4);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {
    }
}
