package org.firstinspires.ftc.teamcode.Auto;

/** Detects the sleeve detected on the camera, then corrects itself to the right position.
 * Once the robot has done this, it moves forward 2 squares to land in the parking spot. */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Core.*;
import org.firstinspires.ftc.teamcode.Core.CV.*;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Standard Auto", group="Auto")

public class StandardAuto extends LinearOpMode {
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

        // TODO find a way to process frames
        sleeveDetector.updateSleevePos(webcam.pipeline.getHsvFilterPink(), webcam.pipeline.getHsvFilterGreen(), webcam.pipeline.getHsvFilterOrange());

        // TODO rightMotor moves before leftMotor; they should move at the same time
        // strafe right to center on tile

        // run until the end of match (driver pressed STOP)
        while(opModeIsActive()) {
            // Align with correct position
            if (autoEventHandler.actionOccurred(0, runtime.time())) {
                switch (sleeveDetector.getSleevePos()) {
                    case 1:
                        drive.moveInches(0, 0, -28);
                        break;
                    case 3:
                        drive.moveInches(0, 0, 28);
                        break;
                }
            }

            // Move forward 2 tiles
            if (autoEventHandler.actionOccurred(1, runtime.time())) {
                drive.moveInches(48, 48, 0);
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
    }

    private void addTelemetry(Telemetry telemetry) {
        telemetry.addData("FTC Team #", "22531");
        telemetry.addData("Elapsed time", "%4.2f", runtime.time());
        drive.telemetry(telemetry);
        sleeveDetector.telemetry(telemetry);
        autoEventHandler.telemetry(telemetry);
        telemetry.update();
    }
}
