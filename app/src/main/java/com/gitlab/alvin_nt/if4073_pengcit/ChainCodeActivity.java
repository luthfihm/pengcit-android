package com.gitlab.alvin_nt.if4073_pengcit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.gitlab.alvin_nt.if4073_pengcit.algorithms.ChainCode;
import com.gitlab.alvin_nt.if4073_pengcit.algorithms.OtsuBinarize;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class ChainCodeActivity extends Activity implements View.OnClickListener {

    private final int SELECT_PHOTO = 1;
    private ImageView imageViewInput;
    private TextView textView;
    private Button btnSelectImage;

    public static void startThisActivity(Context ctx){
        Intent intent = new Intent(ctx, ChainCodeActivity.class);
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
                        ChainCode chainCodeGetter = new ChainCode(binarized, false);
                        String chainCode = chainCodeGetter.analyze();
                        Log.d("Chain : ",chainCode);
                        textView.setText(chainCode);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        }
    }
}
