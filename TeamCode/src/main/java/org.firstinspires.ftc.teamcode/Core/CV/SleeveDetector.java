package org.firstinspires.ftc.teamcode.Core.CV;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.*;

/** Class that detects what position the sleeve is on */
public class SleeveDetector {
    final Pipeline pipeline;
    public SleeveDetector() {
        pipeline = new Pipeline();
    }
    private Scalar sumPixelsPink, sumPixelsGreen, sumPixelsOrange;
    private int sleevePos;

    public int getSleevePos() {
        return sleevePos;
    }

    public void updateSleevePos() {
        // Sum of white pixels of respective Mat
        sumPixelsPink = Core.sumElems(pipeline.getHsvFilterPink());
        sumPixelsGreen = Core.sumElems(pipeline.getHsvFilterGreen());
        sumPixelsOrange = Core.sumElems(pipeline.getHsvFilterOrange());

        if (sumScalar(sumPixelsPink) > sumScalar(sumPixelsGreen) &&
                sumScalar(sumPixelsPink) > sumScalar(sumPixelsOrange)) {
            sleevePos = 1;
        } else if (sumScalar(sumPixelsGreen) > sumScalar(sumPixelsOrange) &&
                sumScalar(sumPixelsGreen) > sumScalar(sumPixelsPink)) {
            sleevePos = 2;
        } else if (sumScalar(sumPixelsOrange) > sumScalar(sumPixelsGreen) &&
                sumScalar(sumPixelsOrange) > sumScalar(sumPixelsPink)) {
            sleevePos = 3;
        }
        // error
        sleevePos = 0;
    }

    private double sumScalar(Scalar in) {
        return in.val[0] + in.val[1] + in.val[2] + in.val[3];
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("sumPixelsPink", sumPixelsPink);
        telemetry.addData("sumPixelsGreen", sumPixelsGreen);
        telemetry.addData("sumPixelsOrange", sumPixelsOrange);

        telemetry.addData("sumPixelsPink to double", "%4.2f", sumScalar(sumPixelsPink));
        telemetry.addData("sumPixelsGreen to double", "%4.2f", sumScalar(sumPixelsGreen));
        telemetry.addData("sumPixelsOrange to double", "%4.2f", sumScalar(sumPixelsOrange));

        telemetry.addData("sleevePos", sleevePos);
    }
}
