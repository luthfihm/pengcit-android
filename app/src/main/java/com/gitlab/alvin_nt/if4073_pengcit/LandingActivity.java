package com.gitlab.alvin_nt.if4073_pengcit;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.gitlab.alvin_nt.if4073_pengcit.algorithms.FaceDetectionKMeans;

public class LandingActivity extends ActionBarActivity implements View.OnClickListener{
    private Button btnHistogram;
    private Button btnDetectNumber;
    private Button btnZhangSuen;
    private Button btnOtsu;
    private Button btnChainCode;
    private Button btnEdgeDetection;
    private Button btnEdgeDetectionFirstOrder;
    private Button btnEdgeDetectionSecondOrder;
    private Button btnFaceDetectionColorMap;
    private Button btnFaceDetectionKMeans;
    private Button btnGaussianBlur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        btnHistogram = (Button) findViewById(R.id.button_HistogramActivity);
        btnHistogram.setOnClickListener(this);

        btnOtsu = (Button) findViewById(R.id.button_OtsuActivity);
        btnOtsu.setOnClickListener(this);

        btnEdgeDetection = (Button) findViewById(R.id.button_EdgeDetection);
        btnEdgeDetection.setOnClickListener(this);

        btnEdgeDetectionFirstOrder = (Button) findViewById(R.id.button_EdgeDetectionFirstOrder);
        btnEdgeDetectionFirstOrder.setOnClickListener(this);

        btnEdgeDetectionSecondOrder = (Button) findViewById(R.id.button_EdgeDetectionSecondOrder);
        btnEdgeDetectionSecondOrder.setOnClickListener(this);

        btnFaceDetectionColorMap = (Button) findViewById(R.id.button_FaceDetectionColorMap);
        btnFaceDetectionColorMap.setOnClickListener(this);

        btnFaceDetectionKMeans = (Button) findViewById(R.id.button_FaceDetectionKMeans);
        btnFaceDetectionKMeans.setOnClickListener(this);

        btnGaussianBlur = (Button) findViewById(R.id.button_gauss);
        btnGaussianBlur.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // later on, we need to define the button interaction with the menus.
    // define those here

    public void launchColorView() {
        Intent intent = new Intent(this, ColorViewActivity.class);

        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.button_HistogramActivity:
                HistogramActivity.startThisActivity(this);
                break;
            case R.id.button_OtsuActivity:
                OtsuActivity.startThisActivity(this);
                break;
            case R.id.button_EdgeDetection:
                EdgeDetectionActivity.startThisActivity(this);
                break;
            case R.id.button_EdgeDetectionFirstOrder:
                EdgeDetectionFirstOrderActivity.startThisActivity(this);
                break;
            case R.id.button_EdgeDetectionSecondOrder:
                EdgeDetectionSecondOrderActivity.startThisActivity(this);
                break;
            case R.id.button_FaceDetectionColorMap:
                FaceDetectionColorMapActivity.startThisActivity(this);
                break;
            case R.id.button_FaceDetectionKMeans:
                FaceDetectionKMeansActivity.startThisActivity(this);
                break;
            case R.id.button_gauss:
                BasicImageFilterActivity.startThisActivity(this);
                break;
        }
    }
}
