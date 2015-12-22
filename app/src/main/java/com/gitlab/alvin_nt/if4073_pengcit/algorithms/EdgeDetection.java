package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by luthfi on 03/11/15.
 */
public class EdgeDetection {
    private Bitmap original, gray;

    public EdgeDetection (Bitmap image) {
        this.original = image;
        gray = null;
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

                red = (int) (red + green + blue)/3;
                // Return back to original format
                newPixel = Color.argb(alpha, red, red, red);

                // Write pixels into image
                gray.setPixel(i, j, newPixel);

            }
        }
    }

    public Bitmap getByHomogen() {
        this.setGray();
        Bitmap imageOutput = gray.copy(gray.getConfig(),true);
        if (imageOutput.getWidth() > 2 && imageOutput.getHeight() > 2) {
            for (int y=1;y<imageOutput.getHeight()-1;y++) {
                for (int x=1;x<imageOutput.getWidth()-1;x++) {
                    int p1 = Color.red(gray.getPixel(x-1,y-1));
                    int p2 = Color.red(gray.getPixel(x,y-1));
                    int p3 = Color.red(gray.getPixel(x+1,y-1));
                    int p4 = Color.red(gray.getPixel(x-1,y));
                    int p5 = Color.red(gray.getPixel(x,y));
                    int p6 = Color.red(gray.getPixel(x+1,y));
                    int p7 = Color.red(gray.getPixel(x-1,y+1));
                    int p8 = Color.red(gray.getPixel(x,y+1));
                    int p9 = Color.red(gray.getPixel(x+1,y+1));

                    ArrayList<Integer> gridHomogen = new ArrayList<Integer>();

                    gridHomogen.add(Math.abs(p1-p5));
                    gridHomogen.add(Math.abs(p2-p5));
                    gridHomogen.add(Math.abs(p3-p5));
                    gridHomogen.add(Math.abs(p4-p5));
                    gridHomogen.add(Math.abs(p6-p5));
                    gridHomogen.add(Math.abs(p7-p5));
                    gridHomogen.add(Math.abs(p8-p5));
                    gridHomogen.add(Math.abs(p9-p5));

                    int color = Collections.max(gridHomogen);

                    int alpha = Color.alpha(imageOutput.getPixel(x,y));

                    int newPixel = Color.argb(alpha, color, color, color);

                    imageOutput.setPixel(x,y,newPixel);
                }
            }
        }
        return imageOutput;
    }

    public Bitmap getByDifference() {
        this.setGray();
        Bitmap imageOutput = gray.copy(gray.getConfig(),true);
        if (imageOutput.getWidth() > 2 && imageOutput.getHeight() > 2) {
            for (int y=1;y<imageOutput.getHeight()-1;y++) {
                for (int x=1;x<imageOutput.getWidth()-1;x++) {
                    int p1 = Color.red(gray.getPixel(x-1,y-1));
                    int p2 = Color.red(gray.getPixel(x,y-1));
                    int p3 = Color.red(gray.getPixel(x+1,y-1));
                    int p4 = Color.red(gray.getPixel(x-1,y));
                    int p5 = Color.red(gray.getPixel(x,y));
                    int p6 = Color.red(gray.getPixel(x+1,y));
                    int p7 = Color.red(gray.getPixel(x-1,y+1));
                    int p8 = Color.red(gray.getPixel(x,y+1));
                    int p9 = Color.red(gray.getPixel(x+1,y+1));

                    ArrayList<Integer> grid = new ArrayList<Integer>();

                    grid.add(Math.abs(p1-p9));
                    grid.add(Math.abs(p2-p8));
                    grid.add(Math.abs(p3-p7));
                    grid.add(Math.abs(p4-p6));

                    int color = Collections.max(grid);

                    int alpha = Color.alpha(imageOutput.getPixel(x,y));

                    int newPixel = Color.argb(alpha, color, color, color);

                    imageOutput.setPixel(x,y,newPixel);
                }
            }
        }
        return imageOutput;
    }
}
