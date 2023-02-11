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
    protected SleeveDetector sleeveDetector;

    @Override
    public void runOpMode() {
        waitForStart();
        runtime.reset();

        // Define classes
        drive = new TriMotorDrive(hardwareMap);
        claw = new ClawCore(hardwareMap);
        slide = new SlideCore(hardwareMap);

        webcam = new WebcamCore(hardwareMap);
        cameraServo = new CameraServoCore(hardwareMap);

        sleeveDetector = new SleeveDetector();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //Stage 1: Scan and prepare for movements
        int sleevePos = sleeveDetector.sleevePos();
        waitForStart();

        runtime.reset();
        cameraServo.resetCameraServo();
        // TODO rightMotor moves before leftMotor; they should move at the same time
        //drive.moveInches(40, 40, 0);
        // strafe right to center on tile

        // run until the end of match (driver pressed STOP)
        while(opModeIsActive()) {
            if ((int)(runtime.time() / 600) % 2 == 1) {
                claw.close();
            } else {
                claw.open();
            }

            drive.update();
            // Telemetry
            telemetry.addData("FTC Team #", "22531");
            telemetry.addData("Elapsed time", "%4.2f", runtime.time());
            drive.telemetry(telemetry);
            telemetry.addData("sleevePos", sleevePos);
            telemetry.update();
            //TODO
        }
        //proceed forward

        //raise arm

        //turn right to post

        //forward to post

        //slide down slightly

        claw.open(); // To drop cone

        // Raise claw

        //Reverse

        //Lower slide


        //PARK

        //switch state based on cam
        //park 1

        //park 2

        //park 3

        //fin - burn rest of time standing still


    }
}
