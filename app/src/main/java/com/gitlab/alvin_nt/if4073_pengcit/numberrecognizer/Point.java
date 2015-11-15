package com.gitlab.alvin_nt.if4073_pengcit.numberrecognizer;

/**
 * Created by luthfi on 10/26/2015.
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void makeGreater(Point pt) {
        if (pt.getX() > x) {
            x = pt.getX();
        }
        if (pt.getY() > y) {
            y = pt.getY();
        }
    }

    public void makeLesser(Point pt) {
        if (pt.getX() < x) {
            x = pt.getX();
        }
        if (pt.getY() < y) {
            y = pt.getY();
        }
    }
}
