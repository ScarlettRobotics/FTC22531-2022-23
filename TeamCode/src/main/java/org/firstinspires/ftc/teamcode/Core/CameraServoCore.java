package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class CameraServoCore {
    private Servo cameraServo;

    public CameraServoCore (HardwareMap hardwareMap){
        cameraServo = hardwareMap.get(Servo.class, "webcam_servo");
    }

    public void setCamera(){
        cameraServo.setPosition(0.90);
    }

    public void cameraUp(){

    }

    public void cameraDown(){

    }

    public void telemetry(Telemetry telemetry){
        telemetry.addData("Camera Servo POS", cameraServo.getPosition());
    }
}
