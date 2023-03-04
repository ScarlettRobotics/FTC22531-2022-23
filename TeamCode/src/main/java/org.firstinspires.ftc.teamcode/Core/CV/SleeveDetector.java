package org.firstinspires.ftc.teamcode.Core.CV;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.*;

/** Class that detects what position the sleeve is on */
public class SleeveDetector {
    private int sumPixelsPink = 0, sumPixelsGreen = 0, sumPixelsOrange = 0;
    private int sleevePos;

    public int getSleevePos() {
        return sleevePos;
    }

    public void updateSleevePos(Mat hsvFilterPink, Mat hsvFilterGreen, Mat hsvFilterOrange) {
        sumPixelsPink = 0;
        sumPixelsGreen = 0;
        sumPixelsOrange = 0;
        // Sum of white pixels of respective Mat
        sumPixelsPink = sumPixels(hsvFilterPink);
        sumPixelsGreen = sumPixels(hsvFilterGreen);
        sumPixelsOrange = sumPixels(hsvFilterOrange);

        if (sumPixelsPink > sumPixelsGreen &&
                sumPixelsPink > sumPixelsOrange) {
            sleevePos = 1;
        } else if (sumPixelsGreen > sumPixelsOrange &&
                sumPixelsGreen > sumPixelsPink) {
            sleevePos = 2;
        } else if (sumPixelsOrange > sumPixelsGreen &&
                sumPixelsOrange > sumPixelsPink) {
            sleevePos = 3;
        } else {
            // error
            sleevePos = 0;
        }
    }

    /** Adds the RGB values of each pixel value, then outputs the result */
    private int sumPixels(Mat mask) {
        // Final output
        int out = 0;

        // Stores how many rows and cols are in the mask,
        // so the data doesn't have to be retrieved every instance (optimization)
        int lenRows = mask.rows();
        int lenCols = mask.cols();

        double[] pixelValue;
        int crntRow = 0, crntCol = 0;
        // Loop through each row (large increment to optimize)
        for (crntRow=0; crntRow<lenRows; crntRow+=5) {
            // Loop through each column (large increment to optimize)
            for (crntCol=0; crntCol<lenCols; crntCol+=5) {
                pixelValue = mask.get(crntRow, crntCol);
                // Mask hasn't been processed yet
                if (pixelValue == null) {
                    return out;
                }

                out += pixelValue[0];
            }
        }
        return out;
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("\nCurrent class", "SleeveDetector.java");
        telemetry.addData("sumPixelsPink", sumPixelsPink);
        telemetry.addData("sumPixelsGreen", sumPixelsGreen);
        telemetry.addData("sumPixelsOrange", sumPixelsOrange);

        telemetry.addData("sleevePos", sleevePos);
    }
}
