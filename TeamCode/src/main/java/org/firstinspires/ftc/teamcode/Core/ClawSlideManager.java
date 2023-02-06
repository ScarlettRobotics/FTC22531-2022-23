package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class ClawSlideManager extends OpMode {
    // Initialize claw and slide classes
    protected TriMotorDrive drive;
    protected ClawCore claw;
    protected SlideCore slide;

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
                if (gamepad1.dpad_up) {
                    slide.slideUp();
                } else if (gamepad1.dpad_down) {
                    slide.slideDown();
                } else {
                    slide.slideStop();
                }
                break;
            case 2:
                slide.slideManual((-gamepad2.left_trigger+gamepad2.right_trigger));
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
            case 2:
                // Open/close claw if A/B is pressed (respectively)
                if (gamepad2.left_bumper) {
                    claw.open();
                } else if (gamepad2.right_bumper) {
                    claw.close();
                }
        }
        claw.telemetry(telemetry);
    }
}
