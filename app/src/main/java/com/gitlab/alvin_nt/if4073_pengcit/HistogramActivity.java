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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gitlab.alvin_nt.if4073_pengcit.algorithms.Histogram;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by fahziar on 07/09/2015.
 */
public class HistogramActivity extends Activity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{
    private static final int SELECT_PICTURE = 1;
    private static final String TAG = "Histogram Activity";
    private static final float SEEKBAR_MIN = 0f;
    private static final float SEEKBAR_MAX = 1.f;
    private static final float SEEKBAR_STEP = 0.1f;

    private Button btnSelectImage;
    private ImageView previewImage;
    private ImageView editedImage;
    private SeekBar seekBar;
    private TextView txtValue;

    private String selectedImagePath;
    private Bitmap bitmap;
    private Histogram histogram;

    public static void startThisActivity(Context ctx){
        Intent intent = new Intent(ctx, HistogramActivity.class);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histogram);

        btnSelectImage = (Button) findViewById(R.id.btnSelectImage);
        btnSelectImage.setOnClickListener(this);

        previewImage = (ImageView) findViewById(R.id.ivOriginalImage);
        editedImage = (ImageView) findViewById(R.id.ivEditedImage);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax((int) Math.ceil((SEEKBAR_MAX - SEEKBAR_MIN) / SEEKBAR_STEP));
        seekBar.setOnSeekBarChangeListener(this);

        txtValue = (TextView) findViewById(R.id.txtValue);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                Log.d(TAG, "Selected image path: " + selectedImagePath);
                File imageFile;
                InputStream stream = null;
                try {
//                    imageFile = new File(selectedImagePath);
                    stream = this.getContentResolver().openInputStream(data.getData());
                    bitmap = BitmapFactory.decodeStream(stream);
                    previewImage.setImageBitmap(bitmap);
                    updateImage(getSeekbarValue(seekBar.getProgress()));
                    stream.close();
                } catch (FileNotFoundException e) {
                    Toast.makeText(this, "File not found", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(this, "Error opening file", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void updateImage(float f){
        if (bitmap != null){
            histogram = new Histogram(bitmap);
            histogram.generateHistogram();
            try {
                histogram.generateCumulativeTable(f);
            } catch (Exception e) {
                Toast.makeText(this, "Failed create image", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            editedImage.setImageBitmap(histogram.getGeneratedImage());
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
    private String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        float value = getSeekbarValue(i);
        updateImage(value);
        txtValue.setText(String.valueOf(value));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private float getSeekbarValue(int i){
        return SEEKBAR_MIN + (i * SEEKBAR_STEP);
    }
}
