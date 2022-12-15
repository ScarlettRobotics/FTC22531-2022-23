package org.firstinspires.ftc.teamcode.Core;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ClawCore {
    // Initialize claw variables
    private Servo rightClaw = null;
    private Servo leftClaw = null;
    // Stores state of the claw
    private boolean clawIsOpen = false;

    // Map claw variables to driver hub
    public ClawCore (HardwareMap hardwareMap) {
        rightClaw = hardwareMap.get(Servo.class, "claw_right");
        leftClaw = hardwareMap.get(Servo.class, "claw_left");
    }


    /** clawToggle
     *  Sets the claw to be in either open or closed position.
     *  The state of the claw is stored as a private field within the object instance. This ensures
     *  that the claw will still open and close even if it has been bumped or stressed to a different point.
     */
    public void clawToggle() {
        if (clawIsOpen) {
            clawClose();
        } else {
            clawOpen();
        }
    }


    /** clawOpen
     *  Opens the claw to a pre-set width, then updates clawIsOpen.
     */
    public void clawOpen() {
        rightClaw.setPosition(0.815);
        leftClaw.setPosition(0.660);
        clawIsOpen = true;
    }

    /** clawClose
     *  Closes the claw to a pre-set width, then updates clawIsOpen.
     */
    public void clawClose() {
        rightClaw.setPosition(0.665);
        leftClaw.setPosition(0.80);
        clawIsOpen = false;
    }

    public void telemetry(Telemetry telemetry) {
        telemetry.addData("Claw Right POS", rightClaw.getPosition());
        telemetry.addData("Claw Left POS", leftClaw.getPosition());
    }
}
