package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Tele Op")
public class Driver extends OpMode {
    DcMotor frontleft, frontright, backleft, backright, arm, lift;
    private CRServo claw, foundL, foundR;
    public float x, y, z, w, pwr;
    public static double deadzone = 0.2;



    @Override
    public void init() {
        frontleft = hardwareMap.dcMotor.get("FL");
        frontright = hardwareMap.dcMotor.get("FR");
        backleft = hardwareMap.dcMotor.get("BL");
        backright = hardwareMap.dcMotor.get("BR");
        arm = hardwareMap.dcMotor.get("Arm");
        lift = hardwareMap.dcMotor.get("Lift");
        claw = hardwareMap.crservo.get("Claw");
        foundL = hardwareMap.crservo.get("FoundR");
        foundR = hardwareMap.crservo.get("FoundR");


        frontleft.setDirection(DcMotor.Direction.REVERSE);
        backleft.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {
        getJoyVals();
        //updates joyvalues with deadzones, xyzw

        pwr = y; //this can be tweaked for exponential power increase

        frontright.setPower(Range.clip(pwr - x + z, -1, 1));
        backleft.setPower(Range.clip(pwr - x - z, -1, 1));
        frontleft.setPower(Range.clip(pwr + x - z, -1, 1));
        backright.setPower(Range.clip(pwr + x + z, -1, 1));
    }


    public void getJoyVals() {
        y = -gamepad1.left_stick_y/2;
        x = gamepad1.left_stick_x/2;
        z = -gamepad1.right_stick_x/2;
        w = gamepad1.right_stick_y/2;
        //updates joystick values


        if (Math.abs(x) < deadzone) x = 0;
        if (Math.abs(y) < deadzone) y = 0;
        if (Math.abs(z) < deadzone) z = 0;
        if (Math.abs(w) < 0.9) w = 0;
        //checks deadzones

        if (gamepad1.dpad_up) {
            arm.setPower(-1);
        } else if (gamepad1.dpad_down) {
            arm.setPower(1);
        } else {
            arm.setPower(0);


            if (gamepad1.y) {
                lift.setPower(1);
            } else if (gamepad1.a) {
                lift.setPower(-1);
            } else {
                lift.setPower(0);
            }


            if (gamepad1.dpad_left) {
                claw.setPower(.5);
            } else if (gamepad1.dpad_right) {
                claw.setPower(-.5);
            } else {
                claw.setPower(0);
            }

            if (gamepad1.x) {
                foundL.setPower(.5);
                foundR.setPower(-.5);
            } else if (gamepad1.b) {
                foundR.setPower(-.5);
                foundL.setPower(.5);
            } else {
                foundL.setPower(0);
                foundR.setPower(0);
            }
        }
    }
}