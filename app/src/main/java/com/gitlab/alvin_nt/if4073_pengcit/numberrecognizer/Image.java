package com.gitlab.alvin_nt.if4073_pengcit.numberrecognizer;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luthfi on 10/26/2015.
 */
public class Image {
    private int width;
    private int height;
    private boolean[][] data;
    Bitmap img = null;

    //default CTOR. load arbitrary image.
    public Image(Bitmap path, boolean whiteHot) {
        ////System.out.println("reading " + path);
        Bitmap imgFile = path;
        width = imgFile.getWidth();
        height = imgFile.getHeight();
        data = new boolean[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (whiteHot) {
                    data[x][y] = imgFile.getPixel(x, y) == Color.WHITE; // if current pixel is white
                } else {
                    data[x][y] = imgFile.getPixel(x, y) == Color.BLACK; // if current pixel is black
                }
            }
            ////System.out.print("\n");
        }
    }

    //load image from file
    /*public Image(String path, boolean whiteHot) {
        try {
            //System.out.println("reading " + path);
            BufferedImage imgFile = ImageIO.read(new File(path));
            width = imgFile.getWidth();
            height = imgFile.getHeight();
            data = new boolean[width][height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (whiteHot) {
                        data[x][y] = imgFile.getRGB(x, y) != -16777216; // if current pixel is white
                    } else {
                        data[x][y] = imgFile.getRGB(x, y) == -16777216; // if current pixel is black
                    }
                }
                ////System.out.print("\n");
            }
        } catch (IOException e) {
            //System.out.println("Unable to load image: " + e.getMessage());
        }
    }*/

    //recognize the pattern towards a pre-set rules.
    public int recognize() throws Exception {
        int retval = 0;
        //System.out.println("recognizing...");
        String pattern = analyze(); // THIS IS THE CHAIN CODE!
        //System.out.println("pattern is " + pattern);

        //TODO: do the recognition here. add your own rules and respective retvals

        return retval;
    }

    //analyze the image for pattern
    public String analyze() throws Exception {
        String pattern = "";
        //begin scan...
        int prevY = 0;
        int yThreshold = height/4;
        for (int i = 0; i < 2; i++) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < yThreshold; y++) {
                    if (data[x][y] == true) { // if current pixel is pixel of interest
                        //found first hot pixel of interest. store current position.
                        int startPos[] = {x,y};
                        boolean keepSearching = true;
                        int prev = 5;
                        while (keepSearching) {
                            //begin trace...
                            boolean d[] = scanSurroundings(x, y);
                            int p[] = genScanPath(prev);
                            if (d[p[0]]) {
                                //wow we're dealing with concave vertex here. shit.
                                if (!d[p[1]]) {
                                    prev = p[0];
                                    x = getNextX(x, p[0]);
                                    y = getNextY(y, p[0]);
                                    pattern = pattern + Integer.toString(p[0]);
                                } else if (!d[p[2]]) {
                                    prev = p[1];
                                    x = getNextX(x, p[1]);
                                    y = getNextY(y, p[1]);
                                    pattern = pattern + Integer.toString(p[1]);
                                } else if (!d[p[3]]) {
                                    prev = p[2];
                                    x = getNextX(x, p[2]);
                                    y = getNextY(y, p[2]);
                                    pattern = pattern + Integer.toString(p[2]);
                                } else if (!d[p[4]]) {
                                    prev = p[0];
                                    x = getNextX(x, p[0]);
                                    y = getNextY(y, p[0]);
                                    pattern = pattern + Integer.toString(p[0]);
                                } else if (!d[p[5]]) {
                                    prev = p[4];
                                    x = getNextX(x, p[4]);
                                    y = getNextY(y, p[4]);
                                    pattern = pattern + Integer.toString(p[4]);
                                } else if (!d[p[6]]) {
                                    prev = p[5];
                                    x = getNextX(x, p[5]);
                                    y = getNextY(y, p[5]);
                                    pattern = pattern + Integer.toString(p[5]);
                                } else if (!d[p[7]]) {
                                    keepSearching = false;
                                    throw(new Exception("Tracer encountered a strange situation. He's Giving up." + d[p[7]]));
                                }
                            } else if (d[p[1]]) {
                                prev = p[1];
                                x = getNextX(x, p[1]);
                                y = getNextY(y, p[1]);
                                pattern = pattern + Integer.toString(p[1]);
                            } else if (d[p[2]]) {
                                prev = p[2];
                                x = getNextX(x, p[2]);
                                y = getNextY(y, p[2]);
                                pattern = pattern + Integer.toString(p[2]);
                            } else if (d[p[3]]) {
                                prev = p[3];
                                x = getNextX(x, p[3]);
                                y = getNextY(y, p[3]);
                                pattern = pattern + Integer.toString(p[3]);
                            } else if (d[p[4]]) {
                                prev = p[4];
                                x = getNextX(x, p[4]);
                                y = getNextY(y, p[4]);
                                pattern = pattern + Integer.toString(p[4]);
                            } else if (d[p[5]]) {
                                prev = p[5];
                                x = getNextX(x, p[5]);
                                y = getNextY(y, p[5]);
                                pattern = pattern + Integer.toString(p[5]);
                            } else if (d[p[6]]) {
                                prev = p[6];
                                x = getNextX(x, p[6]);
                                y = getNextY(y, p[6]);
                                pattern = pattern + Integer.toString(p[6]);
                            } else if (d[p[7]]) {
                                //System.out.println("dead end. filling to black and resetting scan.");
                                data[x][y] = false;
                                x = startPos[0];
                                y = startPos[1];
                                prev = 5;
                            }
                            if ((startPos[0] == x) && (startPos[1] == y)) {
                                // already back to origin
                                keepSearching = false;
                            }
                            ////System.out.println("scanning... now at " + x + "," + y);
                        }
                        //now begin second iteration, eliminating the black.
                        //this object is scanned. 
                        y = startPos[1];
                        x = startPos[0];
                        floodFill(x, y);
                        //printDebugImage(x+"-"+y);
                        pattern = pattern + "\n";
                    }
                }
            }
            yThreshold = height;
        }
        return pattern;
    }

    //analyze using 5x5 segment
    public List<Segment> analyzeSegment() throws Exception {
        List<Segment> segmentList = new ArrayList<>();
        //begin scan...
        int prevY = 0;
        int yThreshold = height/4;
        for (int i = 0; i < 2; i++) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < yThreshold; y++) {
                    if (data[x][y] == true) { // if current pixel is pixel of interest
                        //found first hot pixel of interest. copy data to temp array so we can analyze the extremes
                        boolean dataCopy[][] = new boolean[width][height];
                        for (int k = 0; k < width; k++) {
                            for (int l = 0; l < height; l++) {
                                dataCopy[k][l] = data[k][l];
                            }
                        }
                        PointExtremes xtremes = getExtremes(x,y,dataCopy);
                        //xtremes.debugPrint();
                        //extremes analyzed, now copy only the character segment block (macroblock) so we can segmentalize it.
                        boolean block[][] = new boolean [xtremes.getWidth()][xtremes.getHeight()];
                        for (int k = 0; k < xtremes.getWidth(); k++) {
                            for (int l = 0; l < xtremes.getHeight(); l++) {
                                block[k][l] = data[k+xtremes.getMin().getX()][l+xtremes.getMin().getY()];
                            }
                        }
                        Segment curSeg = new Segment(block, xtremes.getWidth(), xtremes.getHeight());
                        //curSeg.print();
                        //aaand done. add the segment to the char segment list and nuke the char
                        segmentList.add(curSeg);
                        floodFill(x, y);
                        //printDebugImage(x+"-"+y);
                    }
                }
            }
            yThreshold = height;
        }
        return segmentList;
    }

    //print debug image
    /*private void printDebugImage(String name) {
        try (PrintWriter writer = new PrintWriter("debug/flooded " + name + ".txt", "UTF-8")) {
            for (int a = 0; a < height; a++) {
                for (int b = 0; b < width; b++) {
                    if (data[b][a] == true) {
                        writer.print("#");
                    } else {
                        writer.print(" ");
                    }
                }
                writer.println("");
            }
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }*/

    //AKA Nuker. removes a blob of trues from data[][]
    private void floodFill(int x, int y) {
        if (
                data[x][y] == false ||
                        x < 0 ||
                        y < 0 ||
                        x > width ||
                        y > height) {
            return;
        }
        data[x][y] = false;
        floodFill(x-1,y);
        floodFill(x+1,y);
        floodFill(x,y+1);
        floodFill(x,y-1);
    }

    //get extreme points, similiar style to floodFill
    //EPIC RECURSIVE ACTION! been a while since I made something like this
    private PointExtremes getExtremes(int x, int y, boolean dataCopy[][]) {

        if (
                dataCopy[x][y] == false ||
                        x < 0 ||
                        y < 0 ||
                        x > width ||
                        y > height) {
            return new PointExtremes(new Point(0,0), new Point(0,0), false);
        } else {
            PointExtremes px = new PointExtremes(new Point(x,y), new Point(x,y), true);
            dataCopy[x][y] = false;
            PointExtremes px1 = getExtremes(x-1,y,dataCopy);
            PointExtremes px2 = getExtremes(x+1,y,dataCopy);
            PointExtremes px3 = getExtremes(x,y+1,dataCopy);
            PointExtremes px4 = getExtremes(x,y-1,dataCopy);
            px.combine(px1);
            px.combine(px2);
            px.combine(px3);
            px.combine(px4);
            return px;
        }
    }

    //some magic... I forgot what it does now.
    private int getNextY(int x, int dir) throws Exception {
        switch (dir){
            case 0: return x-1;
            case 1: return x-1;
            case 2: return x-1;
            case 3: return x;
            case 4: return x+1;
            case 5: return x+1;
            case 6: return x+1;
            case 7: return x;
        }
        throw(new Exception("strange getNextX direction."));
    }

    //some magic... I forgot what it does now.
    private int getNextX(int y, int dir) throws Exception {
        switch (dir){
            case 0: return y-1;
            case 1: return y;
            case 2: return y+1;
            case 3: return y+1;
            case 4: return y+1;
            case 5: return y;
            case 6: return y-1;
            case 7: return y-1;
        }
        throw(new Exception("strange getNextY direction."));
    }

    //the pixels around the current pos. TRUE is BLACK.
    private boolean[] scanSurroundings(int x, int y) {
        boolean d[] = {false, false, false, false, false, false, false, false};
        for (int a = 0; a<3; a++){ //y
            for (int b = 0; b<3; b++){ //x
                if(data[x-1+b][y-1+a]) {
                    if (a==0){
                        if (b==0) {
                            d[0]=true;
                        } else if (b==1) {
                            d[1]=true;
                        } else if (b==2) {
                            d[2]=true;
                        }
                    } else if (a==1) {
                        if (b==0) {
                            d[7]=true;
                        } else if (b==2) {
                            d[3]=true;
                        }
                    } else if (a==2){
                        if (b==0) {
                            d[6]=true;
                        } else if (b==1) {
                            d[5]=true;
                        } else if (b==2) {
                            d[4]=true;
                        }
                    }
                }
            }
        }
        return d;
    }

    //generate scan path. radar style! yeah.
    private int[] genScanPath(int prev){
        int ret[] = {0,0,0,0,0,0,0,0};

        ret[0] = prev;
        ret[1] = plusDir(prev, 1);
        ret[2] = plusDir(prev, 2);
        ret[3] = plusDir(prev, 3);
        ret[4] = negaDir(prev, 1);
        ret[5] = negaDir(prev, 2);
        ret[6] = negaDir(prev, 3);
        ret[7] = negaDir(prev, 4);

        return ret;
    }

    //rotational addition. Can be improved.
    private int plusDir(int a, int b){
        for(int i=0; i<b; i++) {
            a++;
            if(a==8) {
                a=0;
            }
        }
        return a;
    }

    //rotational negation. Can be improved.
    private int negaDir(int a, int b){
        for(int i=0; i<b; i++) {
            a--;
            if(a==-1) {
                a=7;
            }
        }
        return a;
    }

    //convert standards
    public String convert(String in) {
        String retval = "";
        for (char c : in.toCharArray()) {
            if (c=='0') {
                retval = retval + "7";
            } else if (c=='1') {
                retval = retval + "0";
            } else if (c=='2') {
                retval = retval + "1";
            } else if (c=='3') {
                retval = retval + "2";
            } else if (c=='4') {
                retval = retval + "3";
            } else if (c=='5') {
                retval = retval + "4";
            } else if (c=='6') {
                retval = retval + "5";
            } else if (c=='7') {
                retval = retval + "6";
            }
        }
        int len = retval.length();
        String retvalR = "";
        for (int i=len-1; i>=0; i--) {
            retvalR = retvalR + retval.charAt(i);
        }
        return retvalR;
    }
}
