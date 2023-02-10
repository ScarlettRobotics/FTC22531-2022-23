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
<<<<<<< Updated upstream
import org.firstinspires.ftc.teamcode.Core.CameraServoCore;
import org.firstinspires.ftc.teamcode.Core.ClawCore;
import org.firstinspires.ftc.teamcode.Core.SlideCore;
import org.firstinspires.ftc.teamcode.Core.TriMotorDrive;
=======
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Core.*;
import org.firstinspires.ftc.teamcode.Core.CV.*;
>>>>>>> Stashed changes

@Autonomous(name="Left Auto", group="Robot")

public class LeftAuto extends LinearOpMode {
<<<<<<< Updated upstream

=======
    private ElapsedTime runtime = new ElapsedTime();
>>>>>>> Stashed changes
    protected TriMotorDrive drive;
    protected ClawCore claw;
    protected SlideCore slide;

    protected CameraServoCore cameraServo;

    @Override
    public void runOpMode() {

        waitForStart();
        runtime.reset();

        // Define classes
        drive = new TriMotorDrive(hardwareMap);
        claw = new ClawCore(hardwareMap);
        slide = new SlideCore(hardwareMap);
<<<<<<< Updated upstream
=======

       // webcam = new WebcamCore(hardwareMap);
>>>>>>> Stashed changes
        cameraServo = new CameraServoCore(hardwareMap);
        // Telemetry
        telemetry.addData("FTC Team #", "22531");

<<<<<<< Updated upstream
        //Stage 1: Scan and prepare for movements
        cameraServo.setCamera();

        claw.close(); // Grabs cone positioned in front

        //wait and scan

        // strafe right to center on tile
=======
        //sleeveDetector = new SleeveDetector();

         while(opModeIsActive()){
            //Stage 1: Scan and prepare for movements
            cameraServo.resetCameraServo();
            claw.close();
            //int sleevePos = sleeveDetector.sleevePos();

            drive.moveInches(10, 10, 0);
            // strafe right to center on tile
                telemetry.addData("FTC Team #", "22531");
                //telemetry.addData("sleevePos", sleevePos);
                telemetry.update();
                //TODO

            //proceed forward
>>>>>>> Stashed changes

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
}
