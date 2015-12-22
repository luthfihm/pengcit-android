package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by luthfi on 22/12/15.
 */
public class EdgeDetectionSecondOrder {
    public static final int SOBEL_OPR[] = {1,2,1};
    public static final int SCHARR_OPR[] = {3,10,3};
    public static final int PREWIT_OPR[] = {1,1,1};

    private Bitmap image;
    private int matrix1[][];
    private int matrix2[][];
    private int matrix3[][];
    private int matrix4[][];
    private int matrix5[][];
    private int matrix6[][];
    private int matrix7[][];
    private int matrix8[][];

    public EdgeDetectionSecondOrder(Bitmap image) {
        this.image = image;
    }

    public void setOperator(int opr[]) {
        matrix1 = new int[3][3];
        matrix2 = new int[3][3];
        matrix3 = new int[3][3];
        matrix4 = new int[3][3];
        matrix5 = new int[3][3];
        matrix6 = new int[3][3];
        matrix7 = new int[3][3];
        matrix8 = new int[3][3];

        for (int i=0;i<3;i++) {
            matrix1[i][0] = -opr[i]; matrix1[i][1] = 0; matrix1[i][2] = opr[i];
        }

        for (int j=0;j<3;j++) {
            matrix2[0][j] = -opr[j];
            matrix2[1][j] = 0;
            matrix2[2][j] = opr[j];
        }

        for (int i=0;i<3;i++) {
            matrix3[i][0] = opr[i]; matrix3[i][1] = 0; matrix3[i][2] = -opr[i];
        }

        for (int j=0;j<3;j++) {
            matrix4[0][j] = opr[j];
            matrix4[1][j] = 0;
            matrix4[2][j] = -opr[j];
        }

        matrix5[0][1] = -opr[0];
        matrix5[0][0] = -opr[1];
        matrix5[1][0] = -opr[2];

        matrix5[0][2] = 0;
        matrix5[1][1] = 0;
        matrix5[2][0] = 0;

        matrix5[1][2] = opr[0];
        matrix5[2][2] = opr[1];
        matrix5[2][1] = opr[2];



        matrix6[0][1] = opr[0];
        matrix6[0][0] = opr[1];
        matrix6[1][0] = opr[2];

        matrix6[0][2] = 0;
        matrix6[1][1] = 0;
        matrix6[2][0] = 0;

        matrix6[1][2] = -opr[0];
        matrix6[2][2] = -opr[1];
        matrix6[2][1] = -opr[2];



        matrix7[1][0] = -opr[0];
        matrix7[2][0] = -opr[1];
        matrix7[2][1] = -opr[2];

        matrix7[0][0] = 0;
        matrix7[1][1] = 0;
        matrix7[2][2] = 0;

        matrix7[0][1] = opr[0];
        matrix7[0][2] = opr[1];
        matrix7[1][2] = opr[2];



        matrix8[1][0] = opr[0];
        matrix8[2][0] = opr[1];
        matrix8[2][1] = opr[2];

        matrix8[0][0] = 0;
        matrix8[1][1] = 0;
        matrix8[2][2] = 0;

        matrix8[0][1] = -opr[0];
        matrix8[0][2] = -opr[1];
        matrix8[1][2] = -opr[2];
    }

    public Bitmap getSharpenedImage() {
        GrayScale grayScale = new GrayScale(image);
        Bitmap gray = grayScale.getGray();
        Bitmap imageOutput = gray.copy(gray.getConfig(),true);

        if (imageOutput.getWidth() > 2 && imageOutput.getHeight() > 2) {
            for (int y = 1; y < imageOutput.getHeight() - 1; y++) {
                for (int x = 1; x < imageOutput.getWidth() - 1; x++) {
                    int m[][] = {
                            {Color.red(gray.getPixel(x - 1, y - 1)),Color.red(gray.getPixel(x,y-1)),Color.red(gray.getPixel(x+1,y-1))},
                            {Color.red(gray.getPixel(x-1,y)),Color.red(gray.getPixel(x,y)),Color.red(gray.getPixel(x+1,y))},
                            {Color.red(gray.getPixel(x-1,y+1)),Color.red(gray.getPixel(x,y+1)),Color.red(gray.getPixel(x+1,y+1))}
                    };
                    List<Integer> g = new ArrayList<Integer>();

                    g.add(getMatrixOperation(m,matrix1));
                    g.add(getMatrixOperation(m,matrix2));
                    g.add(getMatrixOperation(m,matrix3));
                    g.add(getMatrixOperation(m,matrix4));
                    g.add(getMatrixOperation(m,matrix5));
                    g.add(getMatrixOperation(m,matrix6));
                    g.add(getMatrixOperation(m,matrix7));
                    g.add(getMatrixOperation(m,matrix8));


                    int color = Collections.max(g);

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
