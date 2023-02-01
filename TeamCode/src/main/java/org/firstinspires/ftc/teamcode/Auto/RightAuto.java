package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Core.CameraServoCore;
import org.firstinspires.ftc.teamcode.Core.ClawCore;
import org.firstinspires.ftc.teamcode.Core.SlideCore;
import org.firstinspires.ftc.teamcode.Core.TriMotorDrive;

@Autonomous(name="Right Auto", group="Robot")

public class RightAuto extends LinearOpMode {

    protected TriMotorDrive drive;
    protected ClawCore claw;
    protected SlideCore slide;

    protected CameraServoCore cameraServo;

    @Override
    public void runOpMode() {
        // Define classes
        drive = new TriMotorDrive(hardwareMap);
        claw = new ClawCore(hardwareMap);
        slide = new SlideCore(hardwareMap);
        cameraServo = new CameraServoCore(hardwareMap);
        // Telemetry
        telemetry.addData("FTC Team #", "22531");

        //Stage 1: Scan and prepare for movements
        cameraServo.setCamera();

        claw.close(); // Grabs cone positioned in front

        //wait and scan

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
