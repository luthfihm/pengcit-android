package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by luthfi on 11/11/15.
 */
public class EdgeDetectionFirstOrder {

    public static final int SOBEL_OPR[] = {1,2,1};
    public static final int SCHARR_OPR[] = {3,10,3};
    public static final int PREWIT_OPR[] = {1,1,1};

    private Bitmap image;
    private int matrixX[][];
    private int matrixY[][];

    public EdgeDetectionFirstOrder(Bitmap image) {
        this.image = image;
    }

    public void setOperator(int opr[]) {
        matrixX = new int[3][3];
        matrixY = new int[3][3];

        for (int i=0;i<3;i++) {
            matrixX[i][0] = -opr[i]; matrixX[i][1] = 0; matrixX[i][2] = opr[i];
        }

        for (int j=0;j<3;j++) {
            matrixY[0][j] = -opr[j];
            matrixY[1][j] = 0;
            matrixY[2][j] = opr[j];
        }
    }

    public Bitmap getSharpenedImage() {
        GrayScale grayScale = new GrayScale(image);
        Bitmap gray = grayScale.getGray();
        Bitmap imageOutput = gray.copy(gray.getConfig(),true);

        if (imageOutput.getWidth() > 2 && imageOutput.getHeight() > 2) {
            for (int y = 1; y < imageOutput.getHeight() - 1; y++) {
                for (int x = 1; x < imageOutput.getWidth() - 1; x++) {
                    int m[][] = {
                            {Color.red(gray.getPixel(x-1,y-1)),Color.red(gray.getPixel(x,y-1)),Color.red(gray.getPixel(x+1,y-1))},
                            {Color.red(gray.getPixel(x-1,y)),Color.red(gray.getPixel(x,y)),Color.red(gray.getPixel(x+1,y))},
                            {Color.red(gray.getPixel(x-1,y+1)),Color.red(gray.getPixel(x,y+1)),Color.red(gray.getPixel(x+1,y+1))}
                    };

                    int gx = getMatrixOperation(m,matrixX);
                    int gy = getMatrixOperation(m,matrixY);

                    int color = Math.max(gx,gy);

                    int alpha = Color.alpha(imageOutput.getPixel(x,y));

                    int newPixel = Color.argb(alpha, color, color, color);

                    imageOutput.setPixel(x,y,newPixel);
                }
            }
        }
        return imageOutput;
    }

    private int getMatrixOperation(int m1[][], int m2[][]){
        int result = 0;
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                result += m1[i][j] * m2[i][j];
            }
        }
        return Math.abs(result);
    }
}
