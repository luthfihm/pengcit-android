package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by luthfi on 11/11/15.
 */
public class GrayScale {
    private Bitmap original;

    public GrayScale(Bitmap image) {
        this.original = image;
    }

    public Bitmap getGray() {
        int alpha, red, green, blue;
        int newPixel;

        Bitmap gray = original.copy(original.getConfig(),true);

        for(int i=0; i<original.getWidth(); i++) {
            for(int j=0; j<original.getHeight(); j++) {

                // Get pixels by R, G, B
                int pixel = original.getPixel(i,j);
                alpha = Color.alpha(pixel);
                red = Color.red(pixel);
                green = Color.green(pixel);
                blue = Color.blue(pixel);

                red = (int) (red + green + blue)/3;
                // Return back to original format
                newPixel = Color.argb(alpha, red, red, red);

                // Write pixels into image
                gray.setPixel(i, j, newPixel);

            }
        }
        return  gray;
    }
}
