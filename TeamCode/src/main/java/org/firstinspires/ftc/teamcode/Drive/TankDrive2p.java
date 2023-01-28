package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Core.ClawSlideManager;


/**
 * TankDrive 1 Player
 * Designed for driving to be on controller 1, upper systems on controller 1.
 * Tank drive. left stick controls left motor, right stick controls right motor. Triggers on centre motor.
 */
@TeleOp(name = "TankDrive2P", group = "auto")
public class TankDrive2p extends ClawSlideManager {
    @Override
    public void loop() {
        telemetry.addData("STATUS", "Running");
        telemetry.update();

        //// DRIVETRAIN
        // Move left/right wheels based on left/right stick movement
        double left = gamepad1.left_stick_y;
        double right = gamepad1.right_stick_y;
        double center = gamepad1.right_trigger + (-gamepad1.left_trigger);
        //cameraServo.setCamera();
        drive.setPowers(left, right, center);
        drive.telemetry(telemetry, left, right, center);

        updateClaw(2);
        updateSlide(2);
    }
}
