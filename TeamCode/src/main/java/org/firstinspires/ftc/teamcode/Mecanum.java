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
    DcMotor frontleft, frontright, backleft, backright, arm;
    public float x, y, z, w, pwr;
    public static double deadzone = 0.2;
    private Servo servoLine;
    private Servo servoArm;
    private Servo servoFoundation;

    double LineVal = 0;
    double foundVal = 0;
    double armVal = 0;

    @Override
    public void init() {
        frontleft = hardwareMap.dcMotor.get("front_left");
        frontright = hardwareMap.dcMotor.get("front_right");
        backleft = hardwareMap.dcMotor.get("back_left");
        backright = hardwareMap.dcMotor.get("back_right");
        servoLine = hardwareMap.servo.get("servoLine");
        servoFoundation = hardwareMap.get(Servo.class, "servoFoundation");
        servoArm = hardwareMap.get(Servo.class, "servoArm");
        arm = hardwareMap.dcMotor.get("arm");

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

        if (gamepad1.dpad_right) {
            servoArm.setPosition(0);
        } else if (gamepad1.dpad_left) {
            servoArm.setPosition(.3);
        }


        if (gamepad1.a) {
            servoFoundation.setPosition(.8);
        } else if (gamepad1.b) {
            // move to 180 degrees.
            servoFoundation.setPosition(0);
        }

        if (gamepad1.x) {
            servoLine.setPosition(0.35);
        } else if (gamepad1.y) {
            // move to 180 degrees.
            servoLine.setPosition(0);
        }

        if (gamepad1.dpad_down) {
            arm.setPower(.75);
        } else if (gamepad1.dpad_up) {
            arm.setPower(-.75);
        }
        else {
            arm.setPower(0);
        }


      //servoArm.setPosition(gamepad2.left_stick_x);
      //servoFoundation.setPosition(gamepad2.right_stick_x);
      //servoLine.setPosition(gamepad2.left_stick_y);






        telemetry.addData("Arm Position",servoArm.getPosition());
        telemetry.addData("Foundation Position",servoFoundation.getPosition());
        telemetry.addData("Line Position",servoLine.getPosition());

    }
}