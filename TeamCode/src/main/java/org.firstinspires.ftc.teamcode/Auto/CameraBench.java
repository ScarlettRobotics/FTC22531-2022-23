package org.firstinspires.ftc.teamcode.Auto;

/** Detects the sleeve detected on the camera, then corrects itself to the right position.
 * Once the robot has done this, it moves forward 2 squares to land in the parking spot. */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Core.*;
import org.firstinspires.ftc.teamcode.Core.CV.WebcamCore;

@Autonomous(name="Camera Bench", group="Auto")

public class CameraBench extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    protected WebcamCore webcam;

    protected AutoEventHandler autoEventHandler;

    @Override
    public void runOpMode() {
        initialize();

        while(true){

            waitForStart();

            runtime.reset();
            addTelemetry(telemetry);
        }

    }

    /** Runs everything related to class setup */
    private void initialize() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //init motor/servo classes

        //init webcam classes
        webcam = new WebcamCore(hardwareMap);
        //init auto classes
        autoEventHandler = new AutoEventHandler();

        // Add times where the robot takes actions

    }

    private void addTelemetry(Telemetry telemetry) {
        telemetry.addData("FTC Team #", "BENCH");
        telemetry.addData("Elapsed time", "%4.2f", runtime.time());
        webcam.pipeline.telemetry(telemetry);
        autoEventHandler.telemetry(telemetry);
        telemetry.update();
    }
}
