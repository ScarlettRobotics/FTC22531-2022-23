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
    // Position that the slide wants to be at (value is at lowest position)
    private int goalPosition = -510;

    // Map DC motor variable to driver hub
    public SlideCore (HardwareMap hardwareMap) {
        slideMotor = hardwareMap.get(DcMotor.class, "slide_motor");
    }

    public void slideUp(){
        slideMotor.setPower(1);
    }

    public void slideDown(){
        slideMotor.setPower(-1);
    }

    public void slideStop(){
        slideMotor.setPower(0);
    }

    public void slideManual(double power){
        slideMotor.setPower(power);
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("Slide Y", slideMotor.getCurrentPosition());
    }

    // Currently, this code doesn't
    /* Moves the slide by the given amount * /
    public void linearAdjustHeight(int amount) {
        goalPosition += amount;
    /* Updates the slide to move it towards a wanted position.
     * If this code isn't run, the slide will overshoot its target. * /
    public void update() {
        slideMotor.setTargetPosition(goalPosition - slideMotor.getCurrentPosition());
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotor.setPower(1);
    } //*/

}
