package org.firstinspires.ftc.teamcode.Core;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TriMotorDrive {
    //private fields
    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;
    private DcMotor centerMotor = null;


    public TriMotorDrive (HardwareMap hardwareMap) {
        leftMotor = hardwareMap.get(DcMotor.class, "left_motor");
        rightMotor = hardwareMap.get(DcMotor.class, "right_motor");
        centerMotor = hardwareMap.get(DcMotor.class, "center_motor");

        // Set motor run modes
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        centerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set motor movement directions
        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        centerMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }


    /** setPowers
     * Ensures that the range of power sent to the motors is
     *
     * @param leftPower - power sent to the left motor
     * @param rightPower - power to the right motor
     * @param centerPower - power to the center motor
     */
    public void setPowers(double leftPower, double rightPower, double centerPower) {
        // Set motor powers
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
        centerMotor.setPower(centerPower);
        // Set motor movement directions
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        centerMotor.setDirection(DcMotor.Direction.FORWARD);
    }


    public void telemetry(Telemetry telemetry, double leftPower, double rightPower, double centerPower) {
        telemetry.addData("Left", leftPower);
        telemetry.addData("Right", rightPower);
        telemetry.addData("Center", centerPower);
    }
}
