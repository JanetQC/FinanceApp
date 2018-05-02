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
    private CloudantService cloudant = new CloudantService();
    List<Category> allCategories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allCategories= Arrays.asList(Category.values());
        //getEachCategory();
        initPieChart();
    }

    private void initPieChart(){
         String[] mParties = new String[] {
                "Party A", "Party B", "Party C"
        };
        float mult = 5;
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        PieChart pieChart = (PieChart) findViewById(R.id.pieChart);
        for (int i = 0; i < 3 ; i++) {
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

    public Map<Category, Integer> getEachCategory() {
        Map<Category, Integer> catgeoryMap = new HashMap<>();

        allCategories.stream().forEach(category -> {
            List<Expense> tempList = cloudant.selectCategory(category);
            int categorySum = tempList.stream().map(exp -> exp.getAmount()).mapToInt(Integer::intValue).sum();
            catgeoryMap.put(category, categorySum);
        });
        System.out.println(catgeoryMap.entrySet().toString());
        return catgeoryMap;
    }

    public void addEntry(View view){
        Intent intent = new Intent(this, EntryActivity.class);
        startActivity(intent);
    }
}
