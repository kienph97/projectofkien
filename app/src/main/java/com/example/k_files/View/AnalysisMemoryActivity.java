package com.example.k_files.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import com.example.k_files.Presenter.CaculatorSize;
import com.example.k_files.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class AnalysisMemoryActivity extends AppCompatActivity {
    PieChartView mPieChart;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_memory);
        mPieChart = findViewById(R.id.pieChart);
        List<SliceValue> pieData = new ArrayList<>();
        CaculatorSize caculatorSize = new CaculatorSize();
        String pathRoot = Environment.getExternalStorageDirectory()+"/";
        int sizeImage = caculatorSize.caculatorImage(this);
        int sizeAudio = caculatorSize.caculatorAudio(this);
        int sizeVideo = caculatorSize.caculatorVideo(this);
        int sizeTotal = Math.toIntExact(caculatorSize.caculatorUsaSize(pathRoot));
        int other = sizeTotal - sizeAudio - sizeImage - sizeVideo;
        pieData.add(new SliceValue(sizeImage, Color.RED).setLabel("Image: " + sizeImage + "MB"));
        pieData.add(new SliceValue(sizeVideo, Color.GREEN).setLabel("Video: "+ sizeVideo + "MB"));
        pieData.add(new SliceValue(sizeAudio, Color.BLUE).setLabel("Audio: "+ sizeAudio + "MB"));
        pieData.add(new SliceValue(other,Color.YELLOW).setLabel("Other: "+ other + "MB"));
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true);
        pieChartData.setHasCenterCircle(true).setCenterText1("Analysis Memory").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        mPieChart.setPieChartData(pieChartData);
    }
}