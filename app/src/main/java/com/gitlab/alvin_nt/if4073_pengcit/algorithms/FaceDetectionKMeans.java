package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Fahmi on 21/12/2015.
 */
public class FaceDetectionKMeans {
    private Bitmap oldImage;
    private Bitmap newImage;
    private int imageWidth;
    private int imageHeight;
    private Point rightEyeCentroid;
    private Point leftEyeCentroid;
    private Point noseCentroid;
    private Point mouthCentroid;
    private ArrayList<Point> rightEyeCluster;
    private ArrayList<Point> leftEyeCluster;
    private ArrayList<Point> noseCluster;
    private ArrayList<Point> mouthCluster;
    private ArrayList<Point> allPoints;
    public FaceDetectionKMeans(Bitmap image){
        oldImage = image;
        newImage = image;
        imageWidth = image.getWidth();
        imageHeight = image.getHeight();
        rightEyeCentroid = new Point(imageWidth * 3 / 10, imageHeight * 3 / 10);
        leftEyeCentroid = new Point(imageWidth * 7 / 10, imageHeight * 3 / 10);
        noseCentroid = new Point(imageWidth * 5 / 10, imageHeight * 6 / 10);
        mouthCentroid = new Point(imageWidth * 5 / 10, imageHeight * 8 / 10);
        rightEyeCluster = new ArrayList<>();
        leftEyeCluster = new ArrayList<>();
        noseCluster= new ArrayList<>();
        mouthCluster = new ArrayList<>();
        allPoints = new ArrayList<>();
        setAllPoints();
        Log.d("CONSTRUCTOR", "Success");
    }
    public void setAllPoints(){
        for(int i=0; i<imageWidth;i++){
            for(int j=0; j<imageHeight;j++){
                if(oldImage.getPixel(i,j) == Color.WHITE){
                    allPoints.add(new Point(i,j));
                }
            }
        }
    }
    public void Clustering(){
        double distance1,distance2,distance3,distance4; //1=rightEye, 2=leftEye, 3=nose, 4= mouth
        double minDistance1, minDistance2;
        //int cluster;
        for(Point currentPoint : allPoints) {
            //cluster = 0;
            distance1 = getDistance(currentPoint, rightEyeCentroid);
            distance2 = getDistance(currentPoint, leftEyeCentroid);
            distance3 = getDistance(currentPoint, noseCentroid);
            distance4 = getDistance(currentPoint, mouthCentroid);
            //Log.d("D1",""+distance1);
            //Log.d("D2",""+distance2);
            //Log.d("D3",""+distance3);
            //Log.d("D4",""+distance4);
            //closer to rightEyeCentroid or leftEyeCentroid
            minDistance1 = Math.min(distance1,distance2);
            minDistance2 = Math.min(distance3,distance4);
            minDistance1 = Math.min(minDistance1,minDistance2);
            //Log.d("minD",""+minDistance1);
            if(Double.compare(minDistance1,distance1) == 0){
                rightEyeCluster.add(currentPoint);
            }else if(Double.compare(minDistance1,distance2) == 0){
                leftEyeCluster.add(currentPoint);
            }else if(Double.compare(minDistance1,distance3) == 0){
                noseCluster.add(currentPoint);
            }else /*if (Double.compare(minDistance1,distance4) == 0)*/{
                mouthCluster.add(currentPoint);
            }
            /*switch(cluster){
                case 1: rightEyeCluster.add(currentPoint);break;
                case 2: leftEyeCluster.add(currentPoint);break;
                case 3: noseCluster.add(currentPoint);break;
                case 4: mouthCluster.add(currentPoint);break;
                default: break;
            }*/
        }
    }
    public double getDistance(Point p1, Point p2){
        return Math.sqrt(((p2.x-p1.x)*(p2.x-p1.x))+((p2.y-p1.y)*(p2.y-p1.y)));
    }
    public Point getNewCentroid(ArrayList<Point> Points){
        int numPoints = Points.size();
        int newX = 0;
        int newY = 0;
        for(Point currentPoint : Points){
            newX+=currentPoint.x;
            newY+=currentPoint.y;
        }
        newX = newX/numPoints;
        newY = newY/numPoints;

        return new Point(newX,newY);
    }
    public Bitmap getImageAfterClustering(){
        Point oldRightEyeCentroid;
        Point oldLeftEyeCentroid;
        Point oldNoseCentroid;
        Point oldMouthCentroid;
        //do{
            rightEyeCluster.clear();
            leftEyeCluster.clear();
            noseCluster.clear();
            mouthCluster.clear();
            //start to clustering
            Clustering();

            oldRightEyeCentroid = rightEyeCentroid;
            oldLeftEyeCentroid = leftEyeCentroid;
            oldNoseCentroid = noseCentroid;
            oldMouthCentroid = mouthCentroid;

            if(rightEyeCluster.size()!=0){
                rightEyeCentroid = getNewCentroid(rightEyeCluster);
                Log.d("RIGHT EYE CENTROID", "Point ("+rightEyeCentroid.x+","+rightEyeCentroid.y+")");
            }
            if(leftEyeCluster.size()!=0) {
                leftEyeCentroid = getNewCentroid(leftEyeCluster);
                Log.d("LEFT EYE CENTROID", "Point ("+leftEyeCentroid.x+","+leftEyeCentroid.y+")");
            }
            if(noseCluster.size()!=0) {
                noseCentroid = getNewCentroid(noseCluster);
                Log.d("NOSE CENTROID", "Point ("+noseCentroid.x+","+noseCentroid.y+")");
            }
            if(mouthCluster.size()!=0) {
                mouthCentroid = getNewCentroid(mouthCluster);
                Log.d("MOUTH CENTROID", "Point ("+mouthCentroid.x+","+mouthCentroid.y+")");
            }
        //until stable
        //}while((!oldRightEyeCentroid.equals(rightEyeCentroid.x,rightEyeCentroid.y))
        //       ||(!oldLeftEyeCentroid.equals(leftEyeCentroid.x,leftEyeCentroid.y))
        //        ||(!oldNoseCentroid.equals(noseCentroid.x,noseCentroid.y))
        //        ||(!oldMouthCentroid.equals(mouthCentroid.x,mouthCentroid.y)));
        //coloring Cluster Point
        for(Point currentPoint : rightEyeCluster){
            newImage.setPixel(currentPoint.x,currentPoint.y,Color.RED);
        }
        for(Point currentPoint : leftEyeCluster){
            newImage.setPixel(currentPoint.x,currentPoint.y,Color.BLUE);
        }
        for(Point currentPoint : noseCluster){
            newImage.setPixel(currentPoint.x,currentPoint.y,Color.GREEN);
        }
        for(Point currentPoint : mouthCluster){
            newImage.setPixel(currentPoint.x,currentPoint.y,Color.YELLOW);
        }

        return newImage;
    }
}

