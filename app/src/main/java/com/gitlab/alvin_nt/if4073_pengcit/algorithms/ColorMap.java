package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

/**
 * Created by Fahmi on 17/11/2015.
 */
public class ColorMap {
    private RgbFreq[] ucupSample;
    private RgbFreq[] linggaSample; //standar
    private RgbFreq[] afikSample; //lebih terang
    private RgbFreq[] ahmadSample; //lebih gelap

    public ColorMap(){
        ucupSample = new RgbFreq[25];
        linggaSample = new RgbFreq[25];
        afikSample = new RgbFreq[25];
        ahmadSample = new RgbFreq[25];

        ucupSample[0].addColor(176, 133, 80);
        ucupSample[1].addColor(180, 137, 84);
        ucupSample[2].addColor(168, 125, 74);
        ucupSample[3].addColor(138,  96, 56);
        ucupSample[4].addColor(149, 103, 54);
        ucupSample[5].addColor(117,  87, 59);
        ucupSample[6].addColor(167, 116, 73);
        ucupSample[7].addColor(126,  90, 58);
        ucupSample[8].addColor(170, 119, 72);
        ucupSample[9].addColor(152, 114, 77);
        ucupSample[10].addColor(143, 105, 69);
        ucupSample[11].addColor(179, 128, 83);
        ucupSample[12].addColor(126, 105, 76);
        ucupSample[13].addColor(133,  93, 58);
        ucupSample[14].addColor(148, 110, 71);
        ucupSample[15].addColor(182, 135, 89);
        ucupSample[16].addColor(165, 119, 67);
        ucupSample[17].addColor(184, 138, 89);
        ucupSample[18].addColor(135,  97, 58);
        ucupSample[19].addColor(175, 131, 82);
        ucupSample[20].addColor(162, 108, 64);
        ucupSample[21].addColor(204, 155, 112);
        ucupSample[22].addColor(141,  93, 53);
        ucupSample[23].addColor(153, 105, 59);
        ucupSample[24].addColor(147,  95, 55);

        linggaSample[0].addColor(144, 100, 73);
        linggaSample[1].addColor(151, 109, 93);
        linggaSample[2].addColor(136, 95, 75);
        linggaSample[3].addColor(131, 94, 68);
        linggaSample[4].addColor(106, 75, 57);
        linggaSample[5].addColor(153, 108, 87);
        linggaSample[6].addColor(162, 113, 96);
        linggaSample[7].addColor(149, 103, 80);
        linggaSample[8].addColor(169, 120, 103);
        linggaSample[9].addColor(140, 93, 73);
        linggaSample[10].addColor(121, 83, 62);
        linggaSample[11].addColor(113, 83, 59);
        linggaSample[12].addColor(128, 90, 71);
        linggaSample[13].addColor(99, 72, 51);
        linggaSample[14].addColor(103, 71, 50);
        linggaSample[15].addColor(113, 75, 54);
        linggaSample[16].addColor(151, 101, 74);
        linggaSample[17].addColor(147, 99, 77);
        linggaSample[18].addColor(115, 73, 57);
        linggaSample[19].addColor(107, 71, 49);
        linggaSample[20].addColor(142, 91, 72);
        linggaSample[21].addColor(150, 95, 74);
        linggaSample[22].addColor(88, 60, 38);
        linggaSample[23].addColor(140, 83, 64);
        linggaSample[24].addColor(151, 100, 79);

        afikSample[0].addColor(152,111,79);
        afikSample[1].addColor(157, 121, 89);
        afikSample[2].addColor(142, 107, 79);
        afikSample[3].addColor(126, 89, 60);
        afikSample[4].addColor(133, 99, 72);
        afikSample[5].addColor(186, 140, 107);
        afikSample[6].addColor(175, 126, 94);
        afikSample[7].addColor(189, 144, 111);
        afikSample[8].addColor(157, 109, 73);
        afikSample[9].addColor(131, 82, 50);
        afikSample[10].addColor(151, 112, 83);
        afikSample[11].addColor(133, 87, 61);
        afikSample[12].addColor(139, 104, 72);
        afikSample[13].addColor(121, 84, 55);
        afikSample[14].addColor(147, 106, 76);
        afikSample[15].addColor(141, 90, 63);
        afikSample[16].addColor(153, 101, 61);
        afikSample[17].addColor(170, 121, 78);
        afikSample[18].addColor(135, 83, 59);
        afikSample[19].addColor(129, 91, 52);
        afikSample[20].addColor(163, 117, 84);
        afikSample[21].addColor(130, 91, 60);
        afikSample[22].addColor(165, 117, 79);
        afikSample[23].addColor(123, 86, 57);
        afikSample[24].addColor(162, 114, 76);

        ahmadSample[0].addColor(102, 73, 55);
        ahmadSample[1].addColor(127, 85, 61);
        ahmadSample[2].addColor(128, 86, 62);
        ahmadSample[3].addColor(86, 67, 53);
        ahmadSample[4].addColor(106, 70, 46);
        ahmadSample[5].addColor(134, 88, 62);
        ahmadSample[6].addColor(111, 66, 45);
        ahmadSample[7].addColor(86, 55, 37);
        ahmadSample[8].addColor(127, 87, 62);
        ahmadSample[9].addColor(109, 71, 50);
        ahmadSample[10].addColor(91, 64, 45);
        ahmadSample[11].addColor(101, 69, 58);
        ahmadSample[12].addColor(81, 63, 51);
        ahmadSample[13].addColor(66, 50, 35);
        ahmadSample[14].addColor(64, 42, 28);
        ahmadSample[15].addColor(94, 61, 46);
        ahmadSample[16].addColor(117, 72, 49);
        ahmadSample[17].addColor(81, 53, 41);
        ahmadSample[18].addColor(105, 65, 40);
        ahmadSample[19].addColor(83, 54, 40);
        ahmadSample[20].addColor(117, 76, 58);
        ahmadSample[21].addColor(129, 84, 63);
        ahmadSample[22].addColor(121, 73, 50);
        ahmadSample[23].addColor(105, 63, 47);
        ahmadSample[24].addColor(112, 74, 53);
    }
    public RgbFreq[] getUcupSample(){
        return ucupSample;
    }
    public RgbFreq[] getLinggaSample(){
        return linggaSample;
    }
    public RgbFreq[] getAfikSample(){
        return afikSample;
    }
    public RgbFreq[] getAhmadSample(){
        return ahmadSample;
    }
}
