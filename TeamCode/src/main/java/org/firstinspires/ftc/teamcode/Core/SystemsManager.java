package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class SystemsManager extends OpMode {
    // Initialize claw and slide classes
    protected TriMotorDrive drive;
    protected ClawCore claw;
    protected SlideCore slide;

    protected boolean pDpadLeft;
    protected boolean pDpadRight;

    protected CameraServoCore cameraServo;
    // Stores previous states of listed buttons
    private boolean pgamepad_dpad_up = false;
    private boolean pgamepad_dpad_down = false;

    @Override
    public void init() {
        // Define classes
        drive = new TriMotorDrive(hardwareMap);
        claw = new ClawCore(hardwareMap);
        slide = new SlideCore(hardwareMap);
        cameraServo = new CameraServoCore(hardwareMap);
        // Telemetry
        telemetry.addData("STATUS: ", "Initialized"); // the FTC equivalent to println()
        telemetry.addData("FTC Team #", "22531");
    }


    /* Moves the slide based on gamepad presses */
    protected void updateSlide(final int controllerNum) {
        // controllerNum determines the gamepad that controls the robot
        switch (controllerNum) {
            case 1:
                slide.slideManual(gamepad1.right_trigger - gamepad1.left_trigger);
                break;
            case 2:
                slide.slideManual(gamepad2.right_trigger - gamepad2.left_trigger);
                break;
        }
        slide.telemetry(telemetry);
    }

    /* Updates claw state based on gamepad presses. */
    protected void updateClaw(final int controllerNum) {
        // controllerNum determines the gamepad that controls the robot
        switch (controllerNum) {
            case 1:
                // Open/close claw if A/B is pressed (respectively)
                if (gamepad1.left_bumper) {
                    claw.open();
                } else if (gamepad1.right_bumper) {
                    claw.close();
                }
                break;
            case 2:
                // Open/close claw if A/B is pressed (respectively)
                if (gamepad2.left_bumper) {
                    claw.open();
                } else if (gamepad2.right_bumper) {
                    claw.close();
                }
                break;
        }
        claw.telemetry(telemetry);
    }

    protected void updateMotor(final int controllerNum) {
        double left, right, center;

        // controllerNum determines the gamepad that controls the robot
        switch(controllerNum) {
            case 1:
                // Move left/right wheels based on left/right stick movement
                left = gamepad1.left_stick_y;
                right = gamepad1.right_stick_y;
                center = gamepad1.right_trigger - gamepad1.left_trigger;
                // Snap turn
                if (!pDpadLeft && gamepad1.dpad_left) {
                    drive.moveInches(5, -5, 0);
                }
                if (!pDpadRight && gamepad1.dpad_right) {
                    drive.moveInches(-5, 5, 0);
                }
                pDpadLeft = gamepad1.dpad_left;
                pDpadRight = gamepad1.dpad_right;
                break;
            case 2:
                // Move left/right wheels based on left/right stick movement
                left = gamepad2.left_stick_y;
                right = gamepad2.right_stick_y;
                center = gamepad2.right_trigger - gamepad2.left_trigger;
                // Snap turn

                break;
            default:
                left = 0;
                right = 0;
                center = 0;
        }
        drive.setMoveVelocity(left, right, center);
        drive.telemetry(telemetry, left, right, center);
        drive.update();
    }
}
