package com.gitlab.alvin_nt.if4073_pengcit.numberrecognizer;

/**
 * Created by luthfi on 10/26/2015.
 */
public class PointExtremes {
    private Point max;
    private Point min;
    private boolean valid;

    public PointExtremes(Point max, Point min, boolean valid) {
        this.max = max;
        this.min = min;
        this.valid = valid;
    }

    public void setMax(Point mx) {
        max = mx;
    }

    public void setMin(Point mn) {
        min = mn;
    }

    public void setValid() {
        valid = true;
    }

    public Point getMax() {
        return max;
    }

    public Point getMin() {
        return min;
    }

    public boolean getValidity() {
        return valid;
    }

    public void combine(PointExtremes ptX) {
        if (ptX.getValidity()) {
            max.makeGreater(ptX.getMax());
            min.makeLesser(ptX.getMin());
        }
    }

    public int getHeight() {
        return getMax().getY()-getMin().getY();
    }

    public int getWidth() {
        return getMax().getX()-getMin().getX();
    }

    public void debugPrint() {
        /*System.out.print(getMax().getX());
        System.out.print(", ");
        System.out.print(getMax().getY());
        System.out.print(" - ");
        System.out.print(getMin().getX());
        System.out.print(", ");
        System.out.print(getMin().getY());
        System.out.println();*/
    }
}
