package com.gitlab.alvin_nt.if4073_pengcit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gitlab.alvin_nt.if4073_pengcit.algorithms.EdgeDetectionFirstOrder;
import com.gitlab.alvin_nt.if4073_pengcit.algorithms.FaceDetectionColorMap;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Fahmi on 17/11/2015.
 */
public class FaceDetectionColorMapActivity extends Activity implements View.OnClickListener{
    private final int SELECT_PHOTO = 1;
    private Button btnSelectImage;
    private ImageView imageViewInput;


    public static void startThisActivity(Context ctx){
        Intent intent = new Intent(ctx, FaceDetectionColorMapActivity.class);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edge_detection_first_order);
        imageViewInput = (ImageView)findViewById(R.id.imageViewInput);

        btnSelectImage = (Button) findViewById(R.id.button_PickImage);
        btnSelectImage.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edge_detection_first_order, menu);
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

                        imageViewInput.setImageBitmap(inputImage);
                        FaceDetectionColorMap faceDetectionColorMap = new FaceDetectionColorMap(inputImage);

                        /*edgeDetectionFirstOrder.setOperator(EdgeDetectionFirstOrder.SOBEL_OPR);
                        Bitmap outputSobel = edgeDetectionFirstOrder.getSharpenedImage();
                        imageViewSobel.setImageBitmap(outputSobel);

                        edgeDetectionFirstOrder.setOperator(EdgeDetectionFirstOrder.PREWIT_OPR);
                        Bitmap outputPrewit = edgeDetectionFirstOrder.getSharpenedImage();
                        imageViewPrewit.setImageBitmap(outputPrewit);

                        edgeDetectionFirstOrder.setOperator(EdgeDetectionFirstOrder.SCHARR_OPR);
                        Bitmap outputScharr = edgeDetectionFirstOrder.getSharpenedImage();
                        imageViewScharr.setImageBitmap(outputScharr);*/

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }
}

