package org.firstinspires.ftc.teamcode.Core;

import org.firstinspires.ftc.robotcore.internal.android.dx.util.IntList;
import java.util.ArrayList;

/*** Handler for determining when a specific time in LinearOpMode has passed.
 * Times to check for passing are stored in timeToCheck.
 * previouslyRan corresponds to each index item in timesToCheck.
 * Once a time in timesToCheck has passed, the corresponding index in previouslyRan will move to true.
 * Because of this, this class will return true the first time ElapsedTime passes the timer,
 *  and false after subsequent checks,
 * This class should be managed in these steps:
 *  1. Run addDetectionTime() to save a time to check if code has ran.
 *  2. In the opModeIsActive() loop, TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO*/
public class AutoEventHandler {
    private IntList timesToCheck;
    private ArrayList<Boolean> previouslyRan;

    public AutoEventHandler() {
        timesToCheck = new IntList();
        previouslyRan = new ArrayList<Boolean>();
    }

    /** TODO */
    public void addDetectionTime(int millis) {
        timesToCheck.add(millis);
        previouslyRan.add(false);
    }

    /** TODO */
    public boolean actionOccurred(int index, double timeMillis) {
        if (previouslyRan.get(index)) {
            return false;
        }
        if (timesToCheck.get(index) > timeMillis) {
            return false;
        }
        previouslyRan.set(index, true);
        return true;
    }
}
