package com.gitlab.alvin_nt.if4073_pengcit.numberrecognizer;

/**
 * Created by luthfi on 10/26/2015.
 */
public class Segment {
    private short[][] data;
	
	/*
	x = horizontal;
	y = vertical;
	start at top left
	*/

    public Segment() {
        data = new short[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                data[i][j] = 0;
            }
        }
    }

    public Segment (boolean[][] block, int width, int height) {
        //first divide the macroblock to 25 microblocks
        //System.out.println("====== " + width + " by " + height);
        data = new short[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int sum = 0;
                //get the sum of all trues in the microblock
                //System.out.println("h " + Math.ceil(height/5.0f*i) + " until " + Math.ceil(height/5.0f*(i+1)));
                //System.out.println("w " + Math.ceil(width/5.0f*j) + " until " + Math.ceil(width/5.0f*(j+1)));
                for (int y = (int)Math.ceil(height / 5.0f * i); y < (int)Math.ceil(height / 5.0f * (i + 1)); y++) {
                    for (int x = (int)Math.ceil(width / 5.0f * j); x < (int)Math.ceil(width / 5.0f * (j + 1)); x++) {
                        if (block[x][y] == true) {
                            sum = (short) (sum + 1);
                        }
                    }
                }
                //now normalize the values in the range 0..255
                int curHeight = (int) (Math.ceil(height / 5.0f * (i + 1))-Math.ceil(height / 5.0f * i));
                int curWidth = (int) (Math.ceil(width / 5.0f * (j + 1))-Math.ceil(width / 5.0f * j));
                float divisor = curHeight*curWidth;
                //and finally enter the value to the microblock array
                data[j][i] = (short) (Math.floor(sum / divisor * 255f));
                //data[j][i] = (short) sum;
            }
        }
    }

    public void setValue(int x, int y, short value) {
        if (x<5 && y<5) {
            data[x][y] = value;
        }
    }

    public int getValue(int x, int y) {
        if (x<5 && y<5) {
            return data[x][y];
        } else {
            return 0;
        }
    }

    public void print() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String formatted = String.format("%03d", data[j][i]);
                //System.out.print("[" + formatted + "]");
            }
            //System.out.println();
        }
        //System.out.println("===============");
    }
}
