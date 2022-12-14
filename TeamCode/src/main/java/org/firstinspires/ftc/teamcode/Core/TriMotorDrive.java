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

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        centerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        centerMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }


    /** setPowers
     * Ensures that the range of power sent to the motors is
     *
     * @param leftPower - power sent to the left motor
     * @param rightPower - power to the right motor
     * @param centerPower - power to the center motor
     */
    public void setPowers(double leftPower, double rightPower, double centerPower){
        double largest = 1.0;

        largest = Math.max(largest, Math.abs(leftPower));
        largest = Math.max(largest, Math.abs(rightPower));

        leftMotor.setPower(leftPower / largest);
        rightMotor.setPower(rightPower / largest);

        setCenterPower(centerPower);
    }

    /** setCenterPower
     *  helper function. Cleans up the setPowersFunction
     * @param centerPower - Power sent to the center motor. Ensures the maximum range is -1 to 1.
     */
    private void setCenterPower(double centerPower){
        double largest = 1.0;
        largest = Math.max(largest, Math.abs(centerPower));

        centerMotor.setPower((centerPower / largest));

    }

    public void telemetry(Telemetry telemetry, double leftPower, double rightPower, double centerPower) {
        telemetry.addData("Left", leftPower);
        telemetry.addData("Right", rightPower);
        telemetry.addData("Center", centerPower);
    }
}
