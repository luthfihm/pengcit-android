package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

import android.graphics.Bitmap;

/**
 * Created by Luthfi on 9/6/2015.
 */
public class Histogram {
    private Bitmap image;
    private RgbFreq rgbFreq;
    private CumulativeTable cumulativeTable;


    public Histogram () {
        rgbFreq = new RgbFreq();
    }

    public Histogram (Bitmap image) {
        this.image = image;
        rgbFreq = new RgbFreq();
    }

    /*
    public Histogram (File imageFile) throws IOException {
        this.image = ImageIO.read(imageFile);
        rgbFreq = new RgbFreq();
    }
    */

    public void generateHistogram () {
        for (int y = 0;y < image.getHeight();y++)
        {
            for (int x = 0;x < image.getWidth();x++)
            {
                int rgb = image.getPixel(x, y);
                rgbFreq.addColor(rgb);
            }
        }
    }

    public void generateCumulativeTable (float parameter) throws Exception {
        cumulativeTable = new CumulativeTable(parameter);
        cumulativeTable.generateTable(rgbFreq);
    }

    public Bitmap getGeneratedImage ()
    {
        Bitmap generatedImage = Bitmap.createBitmap(image.getWidth(),image.getHeight(), Bitmap.Config.ARGB_8888);
        for (int y = 0;y < image.getHeight();y++)
        {
            for (int x = 0;x < image.getWidth();x++)
            {
                int rgb = cumulativeTable.getColorFromTable(image.getPixel(x, y));
                generatedImage.setPixel(x,y,rgb);
            }
        }
        return generatedImage;
    }
}
