package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

import android.graphics.Color;

/**
 * Created by Fahmi on 18/11/2015.
 */
public class RGB {
    private int red;
    private int green;
    private int blue;
    public RGB(){
        red = 0;
        green = 0;
        blue = 0;
    }
    public RGB(int r, int g, int b){
        red = r;
        green = g;
        blue = b;
    }
    public void setRGB(int r, int g, int b){
        red = r;
        green = g;
        blue = b;
    }
    public void setRGB(int pixel){
        red = Color.red(pixel);
        green = Color.green(pixel);
        blue = Color.blue(pixel);
    }

    public int getRed() {
        return red;
    }

    public int getBlue() {
        return blue;
    }

    public int getGreen() {
        return green;
    }
}
