package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

/**
 * Created by Luthfi on 9/6/2015.
 */
public class RgbFreq {
    private int[] redFreq;
    private int[] greenFreq;
    private int[] blueFreq;

    public RgbFreq () {
        redFreq = new int[256];
        greenFreq = new int[256];
        blueFreq = new int[256];
    }

    public void addColor (int r,int g,int b)
    {
        redFreq[r]++;
        greenFreq[g]++;
        blueFreq[b]++;
    }

    public void addColor (int rgb)
    {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb >> 0) & 0xff;

        redFreq[r]++;
        greenFreq[g]++;
        blueFreq[b]++;
    }

    public int[] getRedFreq() {
        return redFreq;
    }

    public int[] getGreenFreq() {
        return greenFreq;
    }

    public int[] getBlueFreq() {
        return blueFreq;
    }
}
