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
    // Position that the slide wants to be at
    private int goalPosition;

    // Map DC motor variable to driver hub
    public SlideCore (HardwareMap hardwareMap) {
        slideMotor = hardwareMap.get(DcMotor.class, "slide_motor");
        goalPosition = slideMotor.getCurrentPosition();
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

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("Slide Y", slideMotor.getCurrentPosition());
    }

}
