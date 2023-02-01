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
import org.firstinspires.ftc.teamcode.Core.CameraServoCore;
import org.firstinspires.ftc.teamcode.Core.ClawCore;
import org.firstinspires.ftc.teamcode.Core.SlideCore;
import org.firstinspires.ftc.teamcode.Core.TriMotorDrive;

@Autonomous(name="Left Auto", group="Auto")

public class LeftAuto extends LinearOpMode {

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
        //CameraServoCore.setCameraServo(0.90);

        claw.close(); // Grabs cone positioned in front

        //wait and scan

        // strafe right to center on tile

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
