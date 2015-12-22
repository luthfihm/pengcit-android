package com.gitlab.alvin_nt.if4073_pengcit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gitlab.alvin_nt.if4073_pengcit.algorithms.EdgeDetection;
import com.gitlab.alvin_nt.if4073_pengcit.algorithms.FaceDetectionKMeans;
import com.gitlab.alvin_nt.if4073_pengcit.algorithms.OtsuBinarize;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Fahmi on 21/12/2015.
 */
public class FaceDetectionKMeansActivity extends Activity implements View.OnClickListener{
    private final int SELECT_PHOTO = 1;
    private Button btnSelectImage;
    private ImageView imageViewInput;
    private ImageView imageViewOutput;

    public static void startThisActivity(Context ctx){
        Intent intent = new Intent(ctx, FaceDetectionKMeansActivity.class);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_detection_kmeans);
        imageViewInput = (ImageView)findViewById(R.id.imageViewInput);
        btnSelectImage = (Button) findViewById(R.id.button_PickImage);
        btnSelectImage.setOnClickListener(this);
        imageViewOutput = (ImageView)findViewById(R.id.imageViewOutput);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_face_detection_kmeans, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_PickImage:
                //Log.d("PICK", "TEST");
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap inputImage = BitmapFactory.decodeStream(imageStream);

                        //edge detection
                        imageViewInput.setImageBitmap(inputImage);
                        EdgeDetection edgeDetection = new EdgeDetection(inputImage);
                        Bitmap outputImage1 = edgeDetection.getByDifference();
                        //imageViewOutput.setImageBitmap(outputImage1);

                        OtsuBinarize otsuBinarize = new OtsuBinarize(outputImage1);
                        Bitmap imageBinaries = otsuBinarize.getBinarizeNotOtsu(25);
                        //Bitmap imageBinaries = otsuBinarize.getBinarize();
                        //imageViewOutput.setImageBitmap(imageBinaries);

                        //k-means
                        FaceDetectionKMeans faceDetectionKMeans = new FaceDetectionKMeans(imageBinaries);
                        Bitmap outputImage2 = faceDetectionKMeans.getImageAfterClustering();
                        imageViewOutput.setImageBitmap(outputImage2);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
