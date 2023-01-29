package org.firstinspires.ftc.teamcode.Core;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TriMotorDrive {
    //// CLASS VARIABLES
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor centerMotor;
    //// CONSTANT VARIABLES
    private final int ENCODER_VALUES_PER_ROTATION = 1400;
    // TODO ADJUST VALUE
    private final int INCHES_PER_ROTATION = 10;

    /* Init */
    public TriMotorDrive (HardwareMap hardwareMap) {
        // Map DcMotor variables to hardwareMap
        leftMotor = hardwareMap.get(DcMotor.class, "left_motor");
        rightMotor = hardwareMap.get(DcMotor.class, "right_motor");
        centerMotor = hardwareMap.get(DcMotor.class, "center_motor");

        // Set motor movement directions
        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        centerMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    /** setPowers
     * Uses RUN_USING_ENCODER to move all motors by an inputted velocity
     *
     * @param leftVelocity - power sent to the left motor
     * @param rightVelocity - power to the right motor
     * @param centerVelocity - power to the center motor
     */
    public void setMoveVelocity(double leftVelocity, double rightVelocity, double centerVelocity) {
        // Set motor run modes to RUN_USING_ENCODER if not previously done so
        if (leftMotor.getMode() != DcMotor.RunMode.RUN_USING_ENCODER) {
            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            centerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        // Set motor powers
        leftMotor.setPower(leftVelocity);
        rightMotor.setPower(rightVelocity);
        centerMotor.setPower(centerVelocity);
    }


    public void telemetry(Telemetry telemetry, double leftPower, double rightPower, double centerPower) {
        telemetry.addData("Left", leftPower);
        telemetry.addData("Right", rightPower);
        telemetry.addData("Center", centerPower);
    }
}
