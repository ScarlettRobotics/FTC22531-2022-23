package org.firstinspires.ftc.teamcode.Auto;

/** MOVEMENTS TO MAKE:
 * adjust to high junction
 * strafe 7 in
 * move 52 in forward
 * strafe 12 in
 * CASES:
 *  1: strafe 12 in/36 in
 *  2: strafe 12 in
 *  3: strafe 36 in/12 in */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Core.*;
import org.firstinspires.ftc.teamcode.Core.CV.*;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Left Auto", group="Auto")

public class LeftAuto extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    protected TriMotorDrive drive;
    protected ClawCore claw;
    protected SlideCore slide;
    protected WebcamCore webcam;
    protected CameraServoCore cameraServo;

    protected AutoEventHandler autoEventHandler;
    protected SleeveDetector sleeveDetector;

    @Override
    public void runOpMode() {
        initialize();

        waitForStart();

        runtime.reset();
        cameraServo.resetCameraServo();

        sleeveDetector.updateSleevePos();

        // TODO rightMotor moves before leftMotor; they should move at the same time
        // strafe right to center on tile

        // run until the end of match (driver pressed STOP)
        while(opModeIsActive()) {
            // Centre, and close claw
            if (autoEventHandler.actionOccurred(0, runtime.time())) {
                drive.moveInches(0, 0, 2);
                claw.close();
            }

            // Move forward 2 tiles, and open claw to highest height
            if (autoEventHandler.actionOccurred(1, runtime.time())) {
                drive.moveInches(100, 100, 30);
            }

            // Move to highest position
            if (3000 < runtime.time() && runtime.time() <= 6000) {
                slide.slideUp();
            }

            // Centre with pole
            if (autoEventHandler.actionOccurred(2, runtime.time())) {
                drive.moveInches(0, 0, 11);
            }

            // Move slightly forward
            if (autoEventHandler.actionOccurred(3, runtime.time())) {
                drive.moveInches(5, 5, 0);
            }

            // Slightly move slide down
            if (8000 < runtime.time() && runtime.time() <= 8300) {
                slide.slideDown();
            }

            // Drop cone on slide
            if (autoEventHandler.actionOccurred(4, runtime.time())) {
                claw.open();
                drive.moveInches(-5, -5, 0);
            }

            // Park to correct position
            if (autoEventHandler.actionOccurred(5, runtime.time())) {
                if (sleeveDetector.getSleevePos() == 1) {
                    drive.moveInches(0, 0, -33);
                } else if (sleeveDetector.getSleevePos() == 3) {
                    drive.moveInches(0, 0, 11);
                } else {
                    drive.moveInches(0, 0, -11);
                }
            }

            // Clap while waiting
            if (runtime.time() >= 10000) {
                if ((int)(runtime.time() / 600) % 2 == 1) {
                    claw.open();
                } else {
                    claw.close();
                }
            }

            drive.update();
            addTelemetry(telemetry);
        }
    }

    /** Runs everything related to class setup */
    private void initialize() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //init motor/servo classes
        drive = new TriMotorDrive(hardwareMap);
        claw = new ClawCore(hardwareMap);
        slide = new SlideCore(hardwareMap);
        //init webcam classes
        webcam = new WebcamCore(hardwareMap);
        cameraServo = new CameraServoCore(hardwareMap);
        //init auto classes
        autoEventHandler = new AutoEventHandler();
        sleeveDetector = new SleeveDetector();

        // Add times where the robot takes actions
        autoEventHandler.addDetectionTime(0);
        autoEventHandler.addDetectionTime(1000);
        autoEventHandler.addDetectionTime(6000);
        autoEventHandler.addDetectionTime(7000);
        autoEventHandler.addDetectionTime(8300);
        autoEventHandler.addDetectionTime(10000);
    }

    private void addTelemetry(Telemetry telemetry) {
        telemetry.addData("FTC Team #", "22531");
        telemetry.addData("Elapsed time", "%4.2f", runtime.time());
        drive.telemetry(telemetry);
        sleeveDetector.telemetry(telemetry);
        telemetry.update();
    }
}
