package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.Arrays;

/**
 * Created by fahziar on 13/09/2015.
 */
public class NumberDetection {
    private static final int FOREGROUND = 0xff000000;
    private static final int BACKGROUND = 0xffffffff;
    private Bitmap image;
    private String code;

    public void setImage(Bitmap image){
        this.image = image.copy(Bitmap.Config.ARGB_8888, true);
        process();
    }

    public String getCode(){
        return code;
    }

    private void process(){
        int row, col;
        for (row=0; row<image.getHeight(); row++){
            for (col=0; col<image.getWidth(); col++){
                if (image.getPixel(col, row) == FOREGROUND){
                    this.code = getCodeFromImage(col, row);
                    remove(col, row);
                }
            }
        }
    }

    private void remove(int x, int y){
        image.setPixel(x, y, BACKGROUND);
        if (image.getPixel(x -1, y) == FOREGROUND){
            remove(x-1, y);
        }

        if (image.getPixel(x+1, y) == FOREGROUND){
            remove(x+1, y);
        }

        if (image.getPixel(x, y-1) == FOREGROUND){
            remove(x, y-1);
        }

        if (image.getPixel(x, y+1) == FOREGROUND){
            remove(x, y+1);
        }
    }

    private String getCodeFromImage(int x, int y){
        String out = "";
        int i = x;
        int j = y;
        int direction = 0;

        do {
            int move = selectNextMove(i, j, direction);
            out = out.concat(String.valueOf(move));
            switch (move){
                case 0:
                    direction = 0;
                    j = j -1;
                    break;
                case 1:
                    direction = 0;
                    j = j -1;
                    i = i + 1;
                    break;
                case 2:
                    direction = 1;
                    i = i + 1;
                    break;
                case 3:
                    direction = 1;
                    i = i + 1;
                    j = j + 1;
                    break;
                case 4:
                    direction = 2;
                    j = j + 1;
                    break;
                case 5:
                    direction = 2;
                    i = i -1;
                    j = j + 1;
                    break;
                case 6:
                    direction = 3;
                    i = i -1;
                    break;
                case 7:
                    direction = 2;
                    i = i - 1;
                    j = j - 1;
                    break;
            }
        } while ((i != x) || (j != y));

        return out;
    }

    /**
     * select next pixel on the edge
     * @param x current position
     * @param y current position
     * @param direction preferred direction, 0 is up, 1 is left, 2 is down and 3 is right
     * @return direction
     */
    private int selectNextMove(int x, int y, int direction){
        int edge = -1;
        switch (direction){
            case 0:
                edge = findEdgeHorizontal(x, y-1);
                if (edge == -1){
                    edge = selectNextMove(x, y, 1);
                } else {
                    edge = (edge + 7) % 7;
                }
                break;
            case 1:
                edge = findEdgeVertical(x + 1, y);
                if (edge == -1){
                    edge = selectNextMove(x, y, 2);
                } else {
                    edge = edge + 1;
                }
                break;
            case 2:
                edge = findEdgeHorizontal(x, y + 1);
                if (edge == -1){
                    edge = selectNextMove(x, y, 3);
                } else {
                    if (edge == 0){
                        edge = 5;
                    } else if (edge ==1){
                        edge = 4;
                    } else {
                        edge = 3;
                    }
                }
                break;
            case 3:
                edge = findEdgeVertical(x - 1, y);
                if (edge == -1){
                    edge = selectNextMove(x, y, 0);
                } else {
                    if (edge == 0){
                        edge = 7;
                    } else if (edge ==1){
                        edge = 6;
                    } else {
                        edge = 5;
                    }
                }
                break;
        }

        return edge;
    }

    private int findEdgeHorizontal(int x, int y){
        int i = x-1;
        int out = -1;
        do {
            if(image.getPixel(i-1, y) != image.getPixel(i,y)){
                out = i + 1 - x;
            } else {
                i++;
            }

        } while ((i <= x + 1) && (out == -1));
        return out;
    }

    private int findEdgeVertical(int x, int y){
        int i = y-1;
        int out = -1;
        do {
            if(image.getPixel(x, i-1) != image.getPixel(x,i)){
                out = i + 1 - y;
            } else {
                i++;
            }

        } while ((i <= y + 1) && (out == -1));
        return out;
    }
}
