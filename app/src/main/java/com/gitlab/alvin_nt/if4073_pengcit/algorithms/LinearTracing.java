package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.gitlab.alvin_nt.if4073_pengcit.structures.Point2D;

import java.util.ArrayList;

/**
 * Created by fahziar on 13/09/2015.
 */
public class LinearTracing {
    private Bitmap image;
    private String chainCode;
    private static final int FOREGROUND = 0xff000000;
    private static final int BACKGROUND = 0xffffffff;

    /**
     * set image
     * @param image
     */
    public void setImage(Bitmap image){
        this.image = image.copy(Bitmap.Config.ARGB_8888, true);
        chainCode = "";
        process();
    }

    /**
     * get chain code created
     * will return empty string if image has not been set
     */
    public String getCode(){
        //only [0..n-2] that become part of the chain code
        return chainCode.substring(0, chainCode.length() - 2);
    }

    /**
     * start creating chain code
     */
    private void process() {
        int x,y;
        for (y=0; y<image.getHeight(); y++){
            for (x=0; x<image.getWidth();  x++){
                if (image.getPixel(x, y) == FOREGROUND){
                    processBlob(x, y);
                    remove(x, y);
                }
            }
        }

    }

    private void processBlob(int x, int y){
        int direction = 7;
        Point2D point;
        ArrayList<Point2D> points = new ArrayList<>();

        //Get the first and second pixel. Those are required to check when to stop the loop
        //Get first pixel
        direction = selectDirection(direction, x, y);
        chainCode = chainCode.concat(String.valueOf(direction));
        point = generateNewPoint(x, y, direction);
        points.add(new Point2D(point));

        //Get the second pixel
        direction = selectDirection(direction, point.getX(), point.getY());
        chainCode = chainCode.concat(String.valueOf(direction));
        point = generateNewPoint(point.getX(), point.getY(), direction);
        points.add(new Point2D(point));

        do{
            direction = selectDirection(direction, point.getX(), point.getY());
            chainCode = chainCode.concat(String.valueOf(direction));
            point = generateNewPoint(point.getX(), point.getY(), direction);
            points.add(new Point2D(point));

        } while ((!points.get(0).isEqual(points.get(points.size() - 2)) &&
                (!points.get(1).isEqual(points.get(points.size() - 1)))));
    }

    /**
     * Remove blob from image
     * @param x x Firepoint. X must be part of blob.
     * @param y y Firepoint. Y must be part of blob.
     */
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

    /**
     * Chose where to go
     * @param currentDir current direction
     * @param x current position
     * @param y current position
     * @return next direction
     */
    private int selectDirection(int currentDir, int x, int y){
        int dir;
        if ((currentDir % 2) == 0){
            dir = (currentDir + 7) % 8;
        } else {
            dir = (currentDir + 6) % 8;
        }

        //Find the first element
        int stop = dir;
        int found = -1;
        do{
            if (checkPixel(dir, x, y)) {
                found = dir;
            } else {
                dir = (dir + 1) % 8;
            }
        } while ((dir != stop) && (found == -1));
        return found;
    }

    /**
     * Move to new position
     * @param x original position
     * @param y original position
     * @param dir direction of the move
     * @return new poistion
     */
    private Point2D generateNewPoint(int x, int y, int dir){
        Point2D out = new Point2D();
        switch (dir){
            case 0:
                out.setX(x + 1);
                out.setY(y);
                break;
            case 1:
                out.setX(x + 1);
                out.setY(y - 1);
                break;
            case 2:
                out.setX(x);
                out.setY(y -1);
                break;
            case 3:
                out.setX(x - 1);
                out.setY(y - 1);
                break;
            case 4:
                out.setX(x - 1);
                out.setY(y);
                break;
            case 5:
                out.setX(x - 1);
                out.setY(y + 1);
                break;
            case 6:
                out.setX(x);
                out.setY(y + 1);
                break;
            case 7:
                out.setX(x + 1);
                out.setY(y + 1);
                break;
        }

        return out;
    }

    /**
     * Check pixel if it is part of the blob
     * @param dir Direction
     * @param x x
     * @param y y
     * @return true if the pixel part of the blob and vice versa
     */
    private boolean checkPixel(int dir, int x, int y){
        int pixel;
        switch (dir){
            case 0:
                pixel = image.getPixel(x + 1, y);
                break;
            case 1:
                pixel = image.getPixel(x + 1, y-1);
                break;
            case 2:
                pixel = image.getPixel(x, y-1);
                break;
            case 3:
                pixel = image.getPixel(x-1, y-1);
                break;
            case 4:
                pixel = image.getPixel(x-1, y);
                break;
            case 5:
                pixel = image.getPixel(x-1, y+1);
                break;
            case 6:
                pixel = image.getPixel(x, y+1);
                break;
            case 7:
                pixel = image.getPixel(x+1, y+1);
                break;
            default:
                throw new RuntimeException("Invalid direction");
        }

        return pixel == FOREGROUND;
    }

}
