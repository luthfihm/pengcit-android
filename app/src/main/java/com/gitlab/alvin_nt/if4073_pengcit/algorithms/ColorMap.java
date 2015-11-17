package com.gitlab.alvin_nt.if4073_pengcit.algorithms;


import static java.lang.Math.abs;

/**
 * Created by Fahmi on 17/11/2015.
 */
public class ColorMap {
    private RGB[] ucupSample;
    private RGB[] linggaSample; //standar
    private RGB[] afikSample; //lebih terang
    private RGB[] ahmadSample; //lebih gelap
    private int threshold;

    public ColorMap(){
        ucupSample = new RGB[25];
        linggaSample = new RGB[25];
        afikSample = new RGB[25];
        ahmadSample = new RGB[25];
        threshold = 10;

        ucupSample[0]= new RGB(176, 133, 80);
        ucupSample[1]= new RGB(180, 137, 84);
        ucupSample[2]= new RGB(168, 125, 74);
        ucupSample[3]= new RGB(138,  96, 56);
        ucupSample[4]= new RGB(149, 103, 54);
        ucupSample[5]= new RGB(117,  87, 59);
        ucupSample[6]= new RGB(167, 116, 73);
        ucupSample[7]= new RGB(126,  90, 58);
        ucupSample[8]= new RGB(170, 119, 72);
        ucupSample[9]= new RGB(152, 114, 77);
        ucupSample[10]= new RGB(143, 105, 69);
        ucupSample[11]= new RGB(179, 128, 83);
        ucupSample[12]= new RGB(126, 105, 76);
        ucupSample[13]= new RGB(133,  93, 58);
        ucupSample[14]= new RGB(148, 110, 71);
        ucupSample[15]= new RGB(182, 135, 89);
        ucupSample[16]= new RGB(165, 119, 67);
        ucupSample[17]= new RGB(184, 138, 89);
        ucupSample[18]= new RGB(135,  97, 58);
        ucupSample[19]= new RGB(175, 131, 82);
        ucupSample[20]= new RGB(162, 108, 64);
        ucupSample[21]= new RGB(204, 155, 112);
        ucupSample[22]= new RGB(141,  93, 53);
        ucupSample[23]= new RGB(153, 105, 59);
        ucupSample[24]= new RGB(147,  95, 55);

        linggaSample[0]= new RGB(144, 100, 73);
        linggaSample[1]= new RGB(151, 109, 93);
        linggaSample[2]= new RGB(136, 95, 75);
        linggaSample[3]= new RGB(131, 94, 68);
        linggaSample[4]= new RGB(106, 75, 57);
        linggaSample[5]= new RGB(153, 108, 87);
        linggaSample[6]= new RGB(162, 113, 96);
        linggaSample[7]= new RGB(149, 103, 80);
        linggaSample[8]= new RGB(169, 120, 103);
        linggaSample[9]= new RGB(140, 93, 73);
        linggaSample[10]= new RGB(121, 83, 62);
        linggaSample[11]= new RGB(113, 83, 59);
        linggaSample[12]= new RGB(128, 90, 71);
        linggaSample[13]= new RGB(99, 72, 51);
        linggaSample[14]= new RGB(103, 71, 50);
        linggaSample[15]= new RGB(113, 75, 54);
        linggaSample[16]= new RGB(151, 101, 74);
        linggaSample[17]= new RGB(147, 99, 77);
        linggaSample[18]= new RGB(115, 73, 57);
        linggaSample[19]= new RGB(107, 71, 49);
        linggaSample[20]= new RGB(142, 91, 72);
        linggaSample[21]= new RGB(150, 95, 74);
        linggaSample[22]= new RGB(88, 60, 38);
        linggaSample[23]= new RGB(140, 83, 64);
        linggaSample[24]= new RGB(151, 100, 79);

        afikSample[0]= new RGB(152,111,79);
        afikSample[1]= new RGB(157, 121, 89);
        afikSample[2]= new RGB(142, 107, 79);
        afikSample[3]= new RGB(126, 89, 60);
        afikSample[4]= new RGB(133, 99, 72);
        afikSample[5]= new RGB(186, 140, 107);
        afikSample[6]= new RGB(175, 126, 94);
        afikSample[7]= new RGB(189, 144, 111);
        afikSample[8]= new RGB(157, 109, 73);
        afikSample[9]= new RGB(131, 82, 50);
        afikSample[10]= new RGB(151, 112, 83);
        afikSample[11]= new RGB(133, 87, 61);
        afikSample[12]= new RGB(139, 104, 72);
        afikSample[13]= new RGB(121, 84, 55);
        afikSample[14]= new RGB(147, 106, 76);
        afikSample[15]= new RGB(141, 90, 63);
        afikSample[16]= new RGB(153, 101, 61);
        afikSample[17]= new RGB(170, 121, 78);
        afikSample[18]= new RGB(135, 83, 59);
        afikSample[19]= new RGB(129, 91, 52);
        afikSample[20]= new RGB(163, 117, 84);
        afikSample[21]= new RGB(130, 91, 60);
        afikSample[22]= new RGB(165, 117, 79);
        afikSample[23]= new RGB(123, 86, 57);
        afikSample[24]= new RGB(162, 114, 76);

        ahmadSample[0]= new RGB(102, 73, 55);
        ahmadSample[1]= new RGB(127, 85, 61);
        ahmadSample[2]= new RGB(128, 86, 62);
        ahmadSample[3]= new RGB(86, 67, 53);
        ahmadSample[4]= new RGB(106, 70, 46);
        ahmadSample[5]= new RGB(134, 88, 62);
        ahmadSample[6]= new RGB(111, 66, 45);
        ahmadSample[7]= new RGB(86, 55, 37);
        ahmadSample[8]= new RGB(127, 87, 62);
        ahmadSample[9]= new RGB(109, 71, 50);
        ahmadSample[10]= new RGB(91, 64, 45);
        ahmadSample[11]= new RGB(101, 69, 58);
        ahmadSample[12]= new RGB(81, 63, 51);
        ahmadSample[13]= new RGB(66, 50, 35);
        ahmadSample[14]= new RGB(64, 42, 28);
        ahmadSample[15]= new RGB(94, 61, 46);
        ahmadSample[16]= new RGB(117, 72, 49);
        ahmadSample[17]= new RGB(81, 53, 41);
        ahmadSample[18]= new RGB(105, 65, 40);
        ahmadSample[19]= new RGB(83, 54, 40);
        ahmadSample[20]= new RGB(117, 76, 58);
        ahmadSample[21]= new RGB(129, 84, 63);
        ahmadSample[22]= new RGB(121, 73, 50);
        ahmadSample[23]= new RGB(105, 63, 47);
        ahmadSample[24]= new RGB(112, 74, 53);
    }
    /*public RGB[] getUcupSample(){
        return ucupSample;
    }
    public RGB[] getLinggaSample(){
        return linggaSample;
    }
    public RGB[] getAfikSample(){
        return afikSample;
    }
    public RGB[] getAhmadSample(){
        return ahmadSample;
    }*/
    public boolean isOnColorMap (RGB currentPixel){
        boolean found = false;
        int i=0;
        while((i<=25) && (!found)){

            if ((abs(ucupSample[i].getRed() - currentPixel.getRed()) <= threshold) &&
                    (abs(ucupSample[i].getGreen() - currentPixel.getGreen()) <= threshold) &&
                    (abs(ucupSample[i].getBlue() - currentPixel.getBlue()) <= threshold)){
                found = true;
            }
            if ((abs(linggaSample[i].getRed() - currentPixel.getRed()) <= threshold) &&
                    (abs(linggaSample[i].getGreen() - currentPixel.getGreen()) <= threshold) &&
                    (abs(linggaSample[i].getBlue() - currentPixel.getBlue()) <= threshold))
            {
                found = true;
            }
            if ((abs(ucupSample[i].getRed() - currentPixel.getRed()) <= threshold) &&
                    (abs(afikSample[i].getGreen() - currentPixel.getGreen()) <= threshold) &&
                    (abs(afikSample[i].getBlue() - currentPixel.getBlue()) <= threshold))
            {
                found = true;
            }
            if ((abs(ahmadSample[i].getRed() - currentPixel.getRed()) <= threshold) &&
                    (abs(ahmadSample[i].getGreen() - currentPixel.getGreen()) <= threshold) &&
                    (abs(ahmadSample[i].getBlue() - currentPixel.getBlue()) <= threshold))
            {
                found = true;
            }
            i++;
        }
        return found;
    }
}
