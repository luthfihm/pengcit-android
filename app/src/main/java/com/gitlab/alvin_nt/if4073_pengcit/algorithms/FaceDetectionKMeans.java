package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by Fahmi on 21/12/2015.
 */
public class FaceDetectionKMeans {
    private Bitmap oldImage;
    private Bitmap newImage;
    private Point rightEyeCentroid;
    private Point leftEyeCentroid;
    private Point noseCentroid;
    private Point mouthCentroid;
    private int imageWidth;
    private int imageHeight;
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
        rightEyeCentroid.set(imageWidth * 3 / 10, imageHeight * 4 / 10);
        leftEyeCentroid.set(imageWidth * 7 / 10, imageHeight * 4 / 10);
        noseCentroid.set(imageWidth * 5 / 10, imageHeight * 6 / 10);
        mouthCentroid.set(imageWidth * 5 / 10, imageHeight * 8 / 10);
        rightEyeCluster = new ArrayList<>();
        leftEyeCluster = new ArrayList<>();
        noseCluster= new ArrayList<>();
        mouthCluster = new ArrayList<>();
        allPoints = new ArrayList<>();
        setAllPoints();
    }
    public void setAllPoints(){
        for(int i=0; i<imageWidth;i++){
            for(int j=0; j<imageHeight;i++){
                if(oldImage.getPixel(i,j) != Color.BLACK){
                    allPoints.add(new Point(i,j));
                }
            }
        }
    }
    public void Clustering(){
        double distance1,distance2,distance3,distance4; //1=rightEye, 2=leftEye, 3=nose, 4= mouth
        double minDistance;
        int cluster;
        for(Point currentPoint : allPoints) {
            distance1 = getDistance(currentPoint, rightEyeCentroid);
            distance2 = getDistance(currentPoint, leftEyeCentroid);
            distance3 = getDistance(currentPoint, noseCentroid);
            distance4 = getDistance(currentPoint, mouthCentroid);
            //closer to rightEyeCentroid or leftEyeCentroid
            if(distance1 <= distance2){
                minDistance = distance1;
                cluster = 1;
            }else{
                minDistance = distance2;
                cluster = 2;
            }
            //closer to noseCentroid or not
            if(minDistance > distance3){
                minDistance = distance3;
                cluster = 3;
            }
            //closer to mouthCentroid or not
            if(minDistance > distance4){
                //minDistance = distance4;
                cluster = 4;
            }
            switch(cluster){
                case 1: rightEyeCluster.add(currentPoint);break;
                case 2: leftEyeCluster.add(currentPoint);break;
                case 3: noseCluster.add(currentPoint);break;
                case 4: mouthCluster.add(currentPoint);break;
                default: break;
            }
        }
    }
    public double getDistance(Point p1, Point p2){
        return Math.sqrt(((p2.x-p1.x)^2)-((p2.y-p1.y)^2));
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
    public void startClustering(){
        Point oldRightEyeCentroid = rightEyeCentroid;
        Point oldLeftEyeCentroid = leftEyeCentroid;
        Point oldNoseCentroid = noseCentroid;
        Point oldMouthCentroid = mouthCentroid;
        do{
            Clustering();
            rightEyeCentroid = getNewCentroid(rightEyeCluster);
            leftEyeCentroid = getNewCentroid(leftEyeCluster);
            noseCentroid = getNewCentroid(noseCluster);
            mouthCentroid = getNewCentroid(mouthCluster);
            rightEyeCluster.clear();
            leftEyeCluster.clear();
            noseCluster.clear();
            mouthCluster.clear();
        //until stable
        }while((!oldRightEyeCentroid.equals(rightEyeCentroid))&&(!oldLeftEyeCentroid.equals(leftEyeCentroid))
                &&(!oldNoseCentroid.equals(noseCentroid))&&(!oldMouthCentroid.equals(mouthCentroid)));
    }
}

