package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

import android.graphics.Bitmap;

/**
 * Created by dalva24 on 22/12/15.
 */
public class Blur {
    private Bitmap scl;
    public Blur(Bitmap original) {
        this.scl = original;
    }

    public Bitmap gauss(int r) {
        int w = scl.getWidth();
        int h = scl.getHeight();
        int rs = (int) Math.ceil(r*2.57);
        for (int i=0;i<h;i++) {
            for (int j=0;i<w;j++) {
                int val = 0;
                int wsum = 0;
                for (int iy=i-rs; iy<i+rs+1; iy++) {
                    for (int ix=j-rs; ix<j+rs+1; ix++) {
                        int x = Math.min(w-1, Math.max(0, ix));
                        int y = Math.min(h-1, Math.max(0, iy));
                        int dsq = (ix-j)*(ix-j)+(iy-i)*(iy-i);
                        double wgt = Math.exp( -dsq / (2*r*r) ) / (Math.PI*2*r*r);
                        //val += scl[y*w+x] * wgt;
                        val += scl.getPixel(x, y) * wgt;
                        wsum += wgt;
                    }
                }
                //tcl[i*w+j] = Math.round(val/wsum);
                scl.setPixel(i,j,Math.round(val/wsum));
            }
        }
        return scl;
    }
}
