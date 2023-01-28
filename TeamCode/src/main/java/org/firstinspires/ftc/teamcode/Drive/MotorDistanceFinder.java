package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Core.ClawSlideManager;

/** Used to find the conversion value for encoder values -> rotation
 * conversion value for rotation -> cm will be done with maths */
public class MotorDistanceFinder extends ClawSlideManager {
    private int goalPosition = 0;
    @Override
    public void loop() {
        drive.rightMotor.setTargetPosition(goalPosition);
        drive.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        drive.rightMotor.setPower(1);
        if (gamepad1.a) goalPosition = 260;
    }
}
