package org.firstinspires.ftc.teamcode.Core.CV;

import org.opencv.core.*;

/** Class that detects what position the sleeve is on */
public class SleeveDetector {
    final Pipeline pipeline;
    public SleeveDetector() {
        pipeline = new Pipeline();
    }

    public int sleevePos() {
        // Sum of white pixels of respective Mat
        Scalar sumPixelsGreen = Core.sumElems(pipeline.getHsvFilterGreen());
        Scalar sumPixelsOrange = Core.sumElems(pipeline.getHsvFilterOrange());
        Scalar sumPixelsPurple = Core.sumElems(pipeline.getHsvFilterPurple());

        if (sumScalar(sumPixelsGreen) > sumScalar(sumPixelsOrange) &&
                sumScalar(sumPixelsGreen) > sumScalar(sumPixelsPurple)) {
            return 1;
        }
        if (sumScalar(sumPixelsOrange) > sumScalar(sumPixelsGreen) &&
                sumScalar(sumPixelsOrange) > sumScalar(sumPixelsPurple)) {
            return 2;
        }
        if (sumScalar(sumPixelsPurple) > sumScalar(sumPixelsGreen) &&
                sumScalar(sumPixelsPurple) > sumScalar(sumPixelsOrange)) {
            return 3;
        }
        // error
        return 0;
    }

    private double sumScalar(Scalar in) {
        return in.val[0] + in.val[1] + in.val[2] + in.val[3];
    }
}
