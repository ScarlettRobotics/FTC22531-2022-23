package org.firstinspires.ftc.teamcode.Core.CV;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.*;

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

    /** Adds the RGB values of each pixel value, then outputs the result
     * TODO BROKEN :((( */
    private double[] sumPixels(Mat mask) {
        // Final output
        double[] out = {0};

        // Stores how many rows and cols are in the mask,
        // so the data doesn't have to be retrieved every instance (optimization)
        int lenRows = mask.rows();
        int lenCols = mask.cols();

        // Loop through each row (large increment to optimize)
        for (int crntRow=0; crntRow<lenRows; crntRow+=5) {
            // Loop through each column (large increment to optimize)
            for (int crntCol=0; crntCol<lenCols; crntCol+=5) {
                double[] pixelValue = mask.get(crntRow, crntCol);
                // Mask hasn't been processed yet
                if (pixelValue == null) {
                    return out;
                }

                out[0] += pixelValue[0];
            }
        }
        return out;
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("\nCurrent class", "SleeveDetector.java");
        telemetry.addData("sumPixelsPink", "%4.2f", sumPixelsPink[0]);
        telemetry.addData("sumPixelsGreen", "%4.2f", sumPixelsGreen[0]);
        telemetry.addData("sumPixelsOrange", "%4.2f", sumPixelsOrange[0]);

        telemetry.addData("sleevePos", sleevePos);
    }
}
