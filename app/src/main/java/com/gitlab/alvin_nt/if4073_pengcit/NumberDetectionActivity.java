package com.gitlab.alvin_nt.if4073_pengcit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitlab.alvin_nt.if4073_pengcit.algorithms.ChainCode;
import com.gitlab.alvin_nt.if4073_pengcit.algorithms.LinearTracing;
import com.gitlab.alvin_nt.if4073_pengcit.algorithms.NumberDetection;
import com.gitlab.alvin_nt.if4073_pengcit.algorithms.OtsuBinarize;
import com.gitlab.alvin_nt.if4073_pengcit.numberrecognizer.Image;
import com.gitlab.alvin_nt.if4073_pengcit.numberrecognizer.Segment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by fahziar on 13/09/2015.
 */
public class NumberDetectionActivity extends Activity implements View.OnClickListener {

    private final int SELECT_PHOTO = 1;
    private ImageView imageViewInput;
    private TextView textView;
    private Button btnSelectImage;

    public static void startThisActivity(Context ctx){
        Intent intent = new Intent(ctx, NumberDetectionActivity.class);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhang_suen);
        imageViewInput = (ImageView)findViewById(R.id.imageViewInput);
        textView = (TextView)findViewById(R.id.textView);

        btnSelectImage = (Button) findViewById(R.id.button_PickImage);
        btnSelectImage.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_zhang_suen, menu);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_PickImage:
                Log.d("PICK", "TEST");
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
                        OtsuBinarize otsuBinarize = new OtsuBinarize(inputImage);
                        Bitmap binarized = otsuBinarize.getBinarize();
                        Image image = new Image(binarized, true);
                        try {

                            //analyze as 5x5 segment
                            List<Segment> segList = image.analyzeSegment();


                        } catch (Exception x) {
                            //System.out.println("Exception Encountered!");
                            x.printStackTrace();
                        }
                        textView.setText("test");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        }
    }
}
