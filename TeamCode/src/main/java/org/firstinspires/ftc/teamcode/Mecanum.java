package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import android.os.Handler;
import android.os.SystemClock;

import android.util.Log;
import android.view.ViewParent;

@TeleOp(name = "Tele Op")
public class Mecanum extends OpMode {
    DcMotor frontleft, frontright, backleft, backright, lift, slide;
    public float x, y, z, w, pwr;
    public static double deadzone = 0.2;
    private Servo servoRotate;
    private Servo servoPinch;


    @Override
    public void init() {
        frontleft = hardwareMap.dcMotor.get("front_left");
        frontright = hardwareMap.dcMotor.get("front_right");
        backleft = hardwareMap.dcMotor.get("back_left");
        backright = hardwareMap.dcMotor.get("back_right");
        servoRotate = hardwareMap.servo.get("servo_Rotate");
        servoPinch = hardwareMap.get(Servo.class,"servo_Pinch");

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
        y = -gamepad1.left_stick_y / 2;
        x = gamepad1.left_stick_x / 2;
        z = -gamepad1.right_stick_x / 2;
        w = gamepad1.right_stick_y / 2;
        //updates joystick values


        if (Math.abs(x) < deadzone) x = 0;
        if (Math.abs(y) < deadzone) y = 0;
        if (Math.abs(z) < deadzone) z = 0;
        if (Math.abs(w) < 0.9) w = 0;
        //checks deadzones

        if (gamepad1.dpad_up) {
            lift.setPower(.75);
        } else if (gamepad1.dpad_down) {
            lift.setPower(-.75);
        }
        else {
            lift.setPower(0);


        if (gamepad1.dpad_left) {
            slide.setPower(.5);
        } else if (gamepad1.dpad_right) {
            slide.setPower(-.5);
        }
        else {
            slide.setPower(0);
        }

        if (gamepad1.x) {
            servoRotate.setPosition(0.35);
        } else if (gamepad1.b) {
            servoRotate.setPosition(0);
        }

        if (gamepad1.x) {
            servoPinch.setPosition(0.35);
        } else if (gamepad1.b) {
            servoPinch.setPosition(0);
        }
}
    }
}