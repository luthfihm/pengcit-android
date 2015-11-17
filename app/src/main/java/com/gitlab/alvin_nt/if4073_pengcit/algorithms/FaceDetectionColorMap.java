package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by Fahmi on 17/11/2015.
 */

public class FaceDetectionColorMap {
    private Bitmap oldImage;
    private Bitmap newImage;
    private int matrixColorMap[][];
    private RGB currentPixel;
    private ColorMap colorMap;

    public FaceDetectionColorMap(Bitmap image){
        oldImage= image;
        newImage= image;
        matrixColorMap= new int[oldImage.getWidth()][oldImage.getHeight()];
        currentPixel = new RGB();
        colorMap = new ColorMap();
    }
    public Bitmap getFaceDetectedImage(){
        for(int i=0;i<oldImage.getWidth();i++){
            for(int j=0;j<oldImage.getHeight();j++){
                currentPixel.setRGB(oldImage.getPixel(i, j));
                if(colorMap.isOnColorMap(currentPixel)){
                    newImage.setPixel(i,j, Color.rgb(100,204,0));
                }
            }
        }
        return newImage;
    }
}
