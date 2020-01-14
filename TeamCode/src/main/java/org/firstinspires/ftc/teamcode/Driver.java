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
    DcMotor frontleft, frontright, backleft, backright, arm;
    public float x, y, z, w, pwr;
    public static double deadzone = 0.2;
    private CRServo Grabber;
    private CRServo servoFoundationLeft;
    private CRServo servoFoundationRight;


    @Override
    public void init() {
        frontleft = hardwareMap.dcMotor.get("FL");
        frontright = hardwareMap.dcMotor.get("FR");
        backleft = hardwareMap.dcMotor.get("BL");
        backright = hardwareMap.dcMotor.get("BR");
        arm = hardwareMap.dcMotor.get("Arm");
        Grabber = hardwareMap.crservo.get("Grabber");
        servoFoundationLeft = hardwareMap.crservo.get("ServoFoundLeft");
        servoFoundationRight = hardwareMap.crservo.get("ServoFoundRight");


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
        y = -gamepad1.left_stick_y;
        x = gamepad1.left_stick_x;
        z = -gamepad1.right_stick_x;
        w = gamepad1.right_stick_y;
        //updates joystick values


        if (Math.abs(x) < deadzone) x = 0;
        if (Math.abs(y) < deadzone) y = 0;
        if (Math.abs(z) < deadzone) z = 0;
        if (Math.abs(w) < 0.9) w = 0;
        //checks deadzones

        if (gamepad1.dpad_up) {
            arm.setPower(.35);
        } else if (gamepad1.dpad_down) {
            arm.setPower(-.35);
        } else {
            arm.setPower(0);
        }

        if (gamepad1.a) {
            servoFoundationLeft.setPower(.5);
            servoFoundationRight.setPower(-.5);
        } else if (gamepad1.y) {
            servoFoundationLeft.setPower(-.5);
            servoFoundationRight.setPower(.5);
        } else {
            servoFoundationLeft.setPower(0);
            servoFoundationRight.setPower(0);
        }
        if (gamepad1.x) {
            Grabber.setPower(.5);
        } else {
            Grabber.setPower(0);
        }
    }
}