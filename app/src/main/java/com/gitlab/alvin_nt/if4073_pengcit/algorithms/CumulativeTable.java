package com.gitlab.alvin_nt.if4073_pengcit.algorithms;


import android.graphics.Color;

/**
 * Created by Luthfi on 9/6/2015.
 */
public class CumulativeTable {
    int[] red;
    int[] green;
    int[] blue;
    float parameter;

    public CumulativeTable(float parameter) throws Exception {
        red = new int[256];
        green = new int[256];
        blue = new int[256];
        if (parameter > 1f)
        {
            throw new Exception("Parameter can not be over than 1");
        }
        else
        {
            this.parameter = parameter;
        }
    }

    public void generateTable (RgbFreq rgbFreq) {
        generateRedTable(rgbFreq.getRedFreq());
        generateGreenTable(rgbFreq.getGreenFreq());
        generateBlueTable(rgbFreq.getBlueFreq());
    }

    public int getColorFromTable (int pixel) {
        int r = Color.red(pixel);
        int g = Color.green(pixel);
        int b = Color.blue(pixel);

        int gray = (red[r]+green[g]+blue[b]) / 3;

        int color = Color.rgb(gray,gray,gray);

        return color;
    }

    public int getColorFromTable (int r,int g,int b) {
        int color = Color.rgb(red[r],green[g],blue[b]);

        return color;
    }

    private void generateRedTable (int[] redFreq) {
        int series = 0;
        int size = redFreq.length;
        for (int i = 0;i < size;i++) {
            series += redFreq[i];
            red[i] = series;
        }
        if (red[255] > 255) {
            for (int i = 0;i < size;i++) {
                red[i] = (red[i] * 255) / series;
            }
        }
        for (int i = 0;i < size;i++) {
            float f = red[i] * parameter;
            red[i] = Math.round(f);
        }
    }

    private void generateGreenTable (int[] greenFreq) {
        int series = 0;
        int size = greenFreq.length;
        for (int i = 0;i < size;i++) {
            series += greenFreq[i];
            green[i] = series;
        }
        if (green[255] > 255) {
            for (int i = 0;i < size;i++) {
                green[i] = (green[i] * 255) / series;
            }
        }
        for (int i = 0;i < size;i++) {
            float f = green[i] * parameter;
            green[i] = Math.round(f);
        }
    }

    private void generateBlueTable (int[] blueFreq) {
        int series = 0;
        int size = blueFreq.length;
        for (int i = 0;i < size;i++) {
            series += blueFreq[i];
            blue[i] = series;
        }
        if (blue[255] > 255) {
            for (int i = 0;i < size;i++) {
                blue[i] = (blue[i] * 255) / series;
            }
        }
        for (int i = 0;i < size;i++) {
            float f = blue[i] * parameter;
            blue[i] = Math.round(f);
        }
    }
}
