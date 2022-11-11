package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Core.ClawCore;
import org.firstinspires.ftc.teamcode.Core.TriMotorDrive;


/** TankDrive
 *  Tank drive. left stick controls left motor, right stick controls right motor.
 *  Strafing is done with the bumpers.
 */
@TeleOp
public class TankDrive extends OpMode {
    TriMotorDrive drive = new TriMotorDrive();
    ClawCore claw = new ClawCore();


    @Override
    public void init() {
        telemetry.addData("STATUS:", "Initialized");
        telemetry.addData("FTC Team #", "22531");
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {
        //DriveTrain
        double left = -gamepad1.left_stick_y;
        double right = gamepad1.right_stick_y;
        double center = 0;
        telemetry.addData("Left Stick:", left);
        telemetry.addData("Right Stick:", right);

        if (gamepad1.left_bumper) {
            center = - 1;
        } else if (gamepad1.right_bumper){
            center = 1;
        } else {
            center = 0;
        }
        telemetry.addData("Center Wheel", center);

        drive.setPowers(left, right, center);


        //Claw
        if (gamepad1.a){
            claw.clawOpen();
        } else if (gamepad1.b) {
            claw.clawClose();
        }

        //Slide
        //Still to implement
    }
}
