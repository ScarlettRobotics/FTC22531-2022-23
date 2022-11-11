package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ClawCore {
    private Servo leftClaw = null;

    private Servo rightClaw = null;
    public void init (HardwareMap hardwareMap){
        leftClaw = hardwareMap.get(Servo.class, "claw_left");
        rightClaw = hardwareMap.get(Servo.class, "claw_right");
    }

    public void telemetry(Telemetry tem){
        tem.addData("Claw Left POS:", leftClaw.getPosition());
        tem.addData("Claw Right POS:", rightClaw.getPosition());
    }

    public void clawOpen(){
        leftClaw.setPosition(0.3);
        rightClaw.setPosition(0.6);
    }

    public void clawClose(){
        leftClaw.setPosition(0.6);
        rightClaw.setPosition(0.2);
    }
}
