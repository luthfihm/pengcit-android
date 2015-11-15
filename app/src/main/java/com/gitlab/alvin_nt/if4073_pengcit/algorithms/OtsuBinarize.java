package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by luthfi on 10/26/2015.
 */
public class OtsuBinarize {
    private Bitmap original, gray;

    private int[] histogram;

    private int threshold;

    public OtsuBinarize(Bitmap original) {
        this.original = original;
        gray = null;
        histogram = new int[256];
    }

    // Set histogram of grayscale image
    private void setHistogram() {
        for(int i=0; i<histogram.length; i++) histogram[i] = 0;

        for(int i=0; i<gray.getWidth(); i++) {
            for(int j=0; j<gray.getHeight(); j++) {
                int pixel = gray.getPixel(i, j);
                int red = Color.red(pixel);
                histogram[red]++;
            }
        }
    }

    private void setGray() {
        int alpha, red, green, blue;
        int newPixel;

        gray = original.copy(original.getConfig(),true);

        for(int i=0; i<original.getWidth(); i++) {
            for(int j=0; j<original.getHeight(); j++) {

                // Get pixels by R, G, B
                int pixel = original.getPixel(i,j);
                alpha = Color.alpha(pixel);
                red = Color.red(pixel);
                green = Color.green(pixel);
                blue = Color.blue(pixel);

                red = (int) (0.21 * red + 0.71 * green + 0.07 * blue);
                // Return back to original format
                newPixel = Color.argb(alpha, red, red, red);

                // Write pixels into image
                gray.setPixel(i, j, newPixel);

            }
        }
    }

    // Get binary treshold using Otsu's method
    private void setOtsuTreshold() {

        this.setGray();

        this.setHistogram();


        int total = gray.getHeight() * gray.getWidth();

        float sum = 0;
        for(int i=0; i<256; i++) sum += i * histogram[i];

        float sumB = 0;
        int wB = 0;
        int wF = 0;

        float varMax = 0;
        threshold = 0;

        for(int i=0 ; i<256 ; i++) {
            wB += histogram[i];
            if(wB == 0) continue;
            wF = total - wB;

            if(wF == 0) break;

            sumB += (float) (i * histogram[i]);
            float mB = sumB / wB;
            float mF = (sum - sumB) / wF;

            float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);

            if(varBetween > varMax) {
                varMax = varBetween;
                threshold = i;
            }
        }
    }

    public Bitmap getBinarize() {

        int red;
        int newPixel;

        this.setOtsuTreshold();

        Bitmap binarized = gray.copy(gray.getConfig(),true);

        for(int i=0; i<gray.getWidth(); i++) {
            for(int j=0; j<gray.getHeight(); j++) {

                // Get pixels
                int pixel = gray.getPixel(i,j);
                red = Color.red(pixel);
                int alpha = Color.alpha(pixel);
                if(red > threshold) {
                    newPixel = 255;
                }
                else {
                    newPixel = 0;
                }
                newPixel = Color.argb(alpha, newPixel, newPixel, newPixel);
                binarized.setPixel(i, j, newPixel);

            }
        }

        return binarized;

    }
}
