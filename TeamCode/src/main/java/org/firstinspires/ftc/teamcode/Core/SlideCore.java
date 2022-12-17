package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * SlideCore
 * Handles inputs to the slide mechanism's motor.
 */
public class SlideCore {
    // Initialize DC motor variable
    protected final DcMotor slideMotor;
    // Height of each junction. This array is ordered ground -> low -> medium -> high junction.
    private final int[] junctionHeights = {-510, -2400, -4200, -5100}; //TODO ADJUST VALUES
    // The goal position for the slide to move to
    protected int goalPosition = junctionHeights[0];

    // Map DC motor variable to driver hub
    public SlideCore (HardwareMap hardwareMap) {
        slideMotor = hardwareMap.get(DcMotor.class, "slide_motor");
    }


    /* Moves the slide to the height of a junction. Input options for junctionType are ground, low, medium, and high.*/
    public void moveToJunction(final String junctionType) {
        switch(junctionType.toUpperCase()) {
            case "GROUND": //move to ground in arena
                goalPosition = junctionHeights[0];
                return;
            case "LOW": //move to low junction
                goalPosition = junctionHeights[1];
                return;
            case "MEDIUM": //move to medium junction
                goalPosition = junctionHeights[2];
                return;
            case "HIGH": //move to high junction
                goalPosition = junctionHeights[3];
        }
    }

    /* Changes the height of the slide up by 'conesMoved' cones.
     * If conesMoves is negative, the slide moves down by the specified cone amount. */
    public void adjustHeight(final int conesMoved) {
        // Height that needs to be moved to move to the next cone in a cone stack
        //TODO ADJUST VALUE
        final int coneHeight = -260;
        goalPosition += (coneHeight*conesMoved);
    }

    public void linearAdjustHeight(final int amount) {
        goalPosition += amount;
    }

    /* Updates the slide to move it towards a wanted position.
     * If this code isn't run, the slide will overshoot its target. */
    public void update() {
        slideMotor.setTargetPosition(goalPosition - slideMotor.getCurrentPosition());
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotor.setPower(1);
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("Slide Y", slideMotor.getCurrentPosition());
    }

}
