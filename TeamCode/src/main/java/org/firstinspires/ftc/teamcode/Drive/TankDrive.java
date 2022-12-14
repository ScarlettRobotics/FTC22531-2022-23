package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Core.ClawCore;
import org.firstinspires.ftc.teamcode.Core.SlideCore;
import org.firstinspires.ftc.teamcode.Core.TriMotorDrive;


/**
 * TankDrive 1 Player
 * Designed for driving to be on controller 1, upper systems on controller 1.
 * Tank drive. left stick controls left motor, right stick controls right motor. Triggers on centre motor.
 */
@TeleOp(name = "TankDrive1P", group = "auto")
public class TankDrive extends OpMode {
    // Initialize classes from other files
    TriMotorDrive drive;
    ClawCore claw;
    SlideCore slide;

    @Override
    public void init() {
        drive = new TriMotorDrive(hardwareMap);
        claw = new ClawCore(hardwareMap);
        slide = new SlideCore(hardwareMap);
        telemetry.addData("DRIVE MODE: ", "TankDrive 2 Player");
        telemetry.addData("STATUS: ", "Initialized");
        telemetry.addData("FTC Team #", "20718");
        telemetry.update();
    }

    @Override
    public void loop() {
        telemetry.update();

        //// DRIVETRAIN
        // Move left/right wheels based on left/right stick movement
        double left = gamepad1.left_stick_y;
        double right = gamepad1.right_stick_y;
        double center = gamepad1.right_trigger + (-gamepad1.left_trigger);
        drive.setPowers(left, right, center);
        drive.telemetry(telemetry, left, right, center);

        //// CLAW
        // Open/close claw if A/B is pressed (respectively)
        if (gamepad1.a) {
            claw.clawOpen();
        } else if (gamepad1.b) {
            claw.clawClose();
        }
        claw.telemetry(telemetry);

        //// SLIDE
        // Move slide based on LT/RT presses
        double slidePower = 0;
        if (gamepad1.left_bumper == true) {
            slidePower = 1;
        } else if (gamepad1.right_bumper) {
            slidePower = -1;
        }
        slide.setSlidePower(slidePower);
        slide.telemetry(telemetry, slidePower);
    }
}
