package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/*** Manages the drivetrain of the robot.
 * "Tri" refers to the three wheels in the drivetrain. */
public class TriMotorDrive {
    //// CLASS VARIABLES
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor centerMotor;
    //// CONSTANT VARIABLES
    private final int ENCODER_VALUES_PER_ROTATION = 1400;
    // TODO ADJUST VALUE
    private final double INCHES_PER_ROTATION = 12.36;

    /** Init */
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


    /** setMoveVelocity
     * Uses RUN_USING_ENCODER to move all motors by an inputted velocity
     *
     * @param leftVelocity - power sent to the left motor
     * @param rightVelocity - power sent to the right motor
     * @param centerVelocity - power sent to the center motor
     */
    public void setMoveVelocity(double leftVelocity, double rightVelocity, double centerVelocity) {
        // All input variables equal 0
        if (leftVelocity == rightVelocity && rightVelocity == centerVelocity &&
                leftVelocity == 0) {
            // Current powers of motors are already zero
            // i.e. doesn't need to be reset to stationary
            if (leftMotor.getPower() == 0 && rightMotor.getPower() == 0 && centerMotor.getPower() == 0) {
                return;
            }
        }
        if (leftMotor.getMode() != DcMotor.RunMode.RUN_USING_ENCODER) {
            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            centerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        leftMotor.setPower(leftVelocity);
        rightMotor.setPower(rightVelocity);
        centerMotor.setPower(centerVelocity);
    }

    /** Converts inches to encoder values using constants
     * MAY BE UNRELIABLE, AS FRICTION IS UNACCOUNTED FOR */
    private int inchesToEncoderValues(double inches) {
        return Math.toIntExact(Math.round(inches * ENCODER_VALUES_PER_ROTATION / INCHES_PER_ROTATION));
    }

    /** Uses RUN_TO_POSITION to move the motors by a distance. */
    public void moveInches(double leftInches, double rightInches, double centerInches) {
        // Change target position of motors
        leftMotor.setTargetPosition(leftMotor.getTargetPosition() + inchesToEncoderValues(leftInches));
        rightMotor.setTargetPosition(rightMotor.getTargetPosition() + inchesToEncoderValues(rightInches));
        centerMotor.setTargetPosition(centerMotor.getTargetPosition() + inchesToEncoderValues(centerInches));
        // Set motor run modes to RUN_TO_POSITION if not previously done so
        if (leftMotor.getMode() != DcMotor.RunMode.RUN_TO_POSITION) {
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            centerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }

    /** If the motors are in RUN_TO_POSITION, motors progress to their target position */
    public void update() {
        if (leftMotor.getMode() == DcMotor.RunMode.RUN_TO_POSITION) {
            leftMotor.setTargetPosition(leftMotor.getTargetPosition());
            leftMotor.setPower(1);
            rightMotor.setTargetPosition(rightMotor.getTargetPosition());
            rightMotor.setPower(1);
            centerMotor.setTargetPosition(centerMotor.getTargetPosition());
            centerMotor.setPower(1);
        }
    }

    public void telemetry(Telemetry telemetry, double leftPower, double rightPower, double centerPower) {
        telemetry.addData("Left", leftPower);
        telemetry.addData("Right", rightPower);
        telemetry.addData("Center", centerPower);
    }
}
