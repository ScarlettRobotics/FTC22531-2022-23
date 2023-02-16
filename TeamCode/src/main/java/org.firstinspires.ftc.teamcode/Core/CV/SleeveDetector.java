package org.firstinspires.ftc.teamcode.Core.CV;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.*;

import java.util.Arrays;

/** Class that detects what position the sleeve is on */
public class SleeveDetector {
    private double[] sumPixelsPink, sumPixelsGreen, sumPixelsOrange;
    private int sleevePos;

    public int getSleevePos() {
        return sleevePos;
    }

    public void updateSleevePos(Mat hsvFilterPink, Mat hsvFilterGreen, Mat hsvFilterOrange) {
        // Sum of white pixels of respective Mat
        sumPixelsPink = sumPixels(hsvFilterPink);
        sumPixelsGreen = sumPixels(hsvFilterGreen);
        sumPixelsOrange = sumPixels(hsvFilterOrange);

        if (sumPixelsPink[0] > sumPixelsGreen[0] &&
                sumPixelsPink[0] > sumPixelsOrange[0]) {
            sleevePos = 1;
        } else if (sumPixelsGreen[0] > sumPixelsOrange[0] &&
                sumPixelsGreen[0] > sumPixelsPink[0]) {
            sleevePos = 2;
        } else if (sumPixelsOrange[0] > sumPixelsGreen[0] &&
                sumPixelsOrange[0] > sumPixelsPink[0]) {
            sleevePos = 3;
        } else {
            // error
            sleevePos = 0;
        }
    }

    private double[] sumPixels(Mat mask) {
        double[] out = {0, 0, 0};
        for (int i=0; i<mask.rows(); i++) {
            for (int j=0; j<mask.cols(); j++) {
                double[] temp = mask.get(i, j);
                out[0] += temp[0];
                out[1] += temp[1];
                out[2] += temp[2];
            }
        }
        return out;
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("\nCurrent class", "SleeveDetector.java");
        telemetry.addData("sumPixelsPink", "%4.2f %4.2f %4.2f",
                sumPixelsPink[0], sumPixelsPink[1], sumPixelsPink[2]);
                //sumPixelsPink[2] was originally sumPixelsOrange
        telemetry.addData("sumPixelsGreen", "%4.2f %4.2f %4.2f",
                sumPixelsGreen[0], sumPixelsGreen[1], sumPixelsGreen[2]);
        telemetry.addData("sumPixelsOrange", "%4.2f %4.2f %4.2f",
                sumPixelsOrange[0], sumPixelsOrange[1], sumPixelsOrange[2]);

        telemetry.addData("sleevePos", sleevePos);
    }
}
