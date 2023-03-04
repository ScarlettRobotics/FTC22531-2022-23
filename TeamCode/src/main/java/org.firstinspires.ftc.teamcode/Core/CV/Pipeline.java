package org.firstinspires.ftc.teamcode.Core.CV;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.*;
import org.opencv.imgproc.*;
import org.openftc.easyopencv.OpenCvPipeline;

/**
 * Pipeline class.
 *
 * <p>An OpenCV pipeline generated by GRIP.
 * Has filters for pink, green, and orange.
 * idk what most of this does lol
 *x
 * @author GRIP
 */
public class Pipeline extends OpenCvPipeline {
    //Outputs
    private Mat webcamOutput = new Mat();
    private Mat hsvFilterPink = new Mat();
    private Mat hsvFilterGreen = new Mat();
    private Mat hsvFilterOrange = new Mat();

    private int sumPixelsPink = 0, sumPixelsGreen = 0, sumPixelsOrange = 0;
    private int sleevePos;

    /**
     * This is the primary method that runs the entire pipeline and updates the outputs.
     */
    public Mat processFrame(Mat source0) {
        // Step CV_flip0:
        Mat cvFlipSrc = source0;
        FlipCode cvFlipFlipcode = FlipCode.BOTH_AXES;
        cvFlip(cvFlipSrc, cvFlipFlipcode, webcamOutput);

        // Step HSV_Threshold0:
        Mat hsvThreshold0Input = webcamOutput;
        double[] hsvThreshold0Hue = {121.0, 169.0};
        double[] hsvThreshold0Saturation = {94.0, 190.0};
        double[] hsvThreshold0Value = {41.0, 255.0};
        hsvThreshold(hsvThreshold0Input, hsvThreshold0Hue, hsvThreshold0Saturation, hsvThreshold0Value, hsvFilterPink);

        // Step HSV_Threshold1:
        Mat hsvThreshold1Input = webcamOutput;
        double[] hsvThreshold1Hue = {53.0, 100.0};
        double[] hsvThreshold1Saturation = {66.0, 255.0};
        double[] hsvThreshold1Value = {94.0, 255.0};
        hsvThreshold(hsvThreshold1Input, hsvThreshold1Hue, hsvThreshold1Saturation, hsvThreshold1Value, hsvFilterGreen);

        // Step HSV_Threshold2:
        Mat hsvThreshold2Input = webcamOutput;
        double[] hsvThreshold2Hue = {0.0, 22.0};
        double[] hsvThreshold2Saturation = {163.0, 255.0};
        double[] hsvThreshold2Value = {158.0, 255.0};
        hsvThreshold(hsvThreshold2Input, hsvThreshold2Hue, hsvThreshold2Saturation, hsvThreshold2Value, hsvFilterOrange);

        /* Detects the sleeve based on inputs*/
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
        return webcamOutput;
    }

    /** Adds the RGB values of each pixel value of a black and white image, then outputs the result */
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
                    continue;
                }

                if (pixelValue[0] != 0) {
                    out++;
                }
            }
        }
        return out;
    }

    /**
     * This method is a generated getter for the output of a CV_flip.
     * @return Mat output from CV_flip.
     */
    public Mat getWebcamOutput() {
        return webcamOutput;
    }

    /**
     * This method is a generated getter for the output of a HSV_Threshold.
     * @return Mat output from HSV_Threshold.
     */
    public Mat getHsvFilterPink() {
        return hsvFilterPink;
    }

    /**
     * This method is a generated getter for the output of a HSV_Threshold.
     * @return Mat output from HSV_Threshold.
     */
    public Mat getHsvFilterGreen() {
        return hsvFilterGreen;
    }

    /**
     * This method is a generated getter for the output of a HSV_Threshold.
     * @return Mat output from HSV_Threshold.
     */
    public Mat getHsvFilterOrange() {
        return hsvFilterOrange;
    }

    public int getSleevePos() {
        return sleevePos;
    }

    public void addTelemetry(Telemetry telemetry) {
        telemetry.addData("\nCurrent class", "Pipeline.java");
        telemetry.addData("sumPixelsPink", sumPixelsPink);
        telemetry.addData("sumPixelsGreen", sumPixelsGreen);
        telemetry.addData("sumPixelsOrange", sumPixelsOrange);
        telemetry.addData("sleevePos", sleevePos);
    }

    /**
     * Code used for CV_flip.
     * Per OpenCV spec 0 -> flip on X axis.
     * >0 -> flip on Y axis.
     * <0 -> flip on both axes.
     */
    public enum FlipCode {
        X_AXIS(0),
        Y_AXIS(1),
        BOTH_AXES(-1);
        public final int value;
        FlipCode(int value) {
            this.value = value;
        }
    }

    /**
     * Flips an image along X, Y or both axes.
     * @param src Image to flip.
     * @param flipcode FlipCode of which direction to flip.
     * @param dst flipped version of the Image.
     */
    private void cvFlip(Mat src, FlipCode flipcode, Mat dst) {
        Core.flip(src, dst, flipcode.value);
    }

    /**
     * Segment an image based on hue, saturation, and value ranges.
     *
     * @param input The image on which to perform the HSL threshold.
     * @param hue The min and max hue
     * @param sat The min and max saturation
     * @param val The min and max value
     * @param out The image in which to store the output.
     */
    private void hsvThreshold(Mat input, double[] hue, double[] sat, double[] val,
                              Mat out) {
        Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HSV);
        Core.inRange(out, new Scalar(hue[0], sat[0], val[0]),
                new Scalar(hue[1], sat[1], val[1]), out);
    }




}

