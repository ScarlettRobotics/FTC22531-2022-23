package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Core.*;
import org.firstinspires.ftc.teamcode.Core.CV.SleeveDetector;
import org.firstinspires.ftc.teamcode.Core.CV.WebcamCore;

@Autonomous(name="Right Auto", group="Robot")

public class RightAuto extends LinearOpMode {
    protected TriMotorDrive drive;
    protected ClawCore claw;
    protected SlideCore slide;

    protected WebcamCore webcam;
    protected CameraServoCore cameraServo;
    protected SleeveDetector sleeveDetector;

    @Override
    public void runOpMode() {
        // Define classes
        drive = new TriMotorDrive(hardwareMap);
        claw = new ClawCore(hardwareMap);
        slide = new SlideCore(hardwareMap);

        webcam = new WebcamCore(hardwareMap);
        cameraServo = new CameraServoCore(hardwareMap);

        sleeveDetector = new SleeveDetector();

        // Telemetry
        telemetry.addData("FTC Team #", "22531");

        //Stage 1: Scan and prepare for movements
        cameraServo.resetCameraServo();
        claw.close();
        int sleevePos = sleeveDetector.sleevePos();
        telemetry.addData("sleevePos", sleevePos);

        // strafe left to center on tile

        //proceed forward

        //raise arm

        //turn left to post

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
