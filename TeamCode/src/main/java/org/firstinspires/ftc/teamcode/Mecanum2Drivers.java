package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Tele Op2")
public class Mecanum2Drivers extends OpMode {
    DcMotor frontleft, frontright, backleft, backright, liftMotor, grabWheelOne, grabWheelTwo;
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
        liftMotor = hardwareMap.dcMotor.get("lift_Motor");
        grabWheelOne = hardwareMap.dcMotor.get("grab_Wheel_One");
        grabWheelTwo = hardwareMap.dcMotor.get("grab_Wheel_Two");


        frontleft.setDirection(DcMotor.Direction.REVERSE);
        backleft.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {                                                                    //Driver 1 is in charge of Driving and the z movement for the lift
        getJoyVals();                                                                       //Driver 2 is in charge of the fondation servo, grab wheels, and the arm. They are also in charge of anything else that
        //updates joyvalues with deadzones, xyzw                                            //will be added.

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

        if (gamepad1.dpad_up) {                 //Toggles the scissor lift to go up and down for driver 1
            liftMotor.setPower(1);
        } else
            liftMotor.setPower(0);

        if (gamepad1.dpad_down) {
            liftMotor.setPower(-1);
        } else
            liftMotor.setPower(0);

        if (gamepad2.x) {                       //Turns the wheels that collect a block on when the x button is held down by driver 2
            grabWheelOne.setPower(-1);
            grabWheelTwo.setPower(1);
        } else
            grabWheelOne.setPower(0);
            grabWheelTwo.setPower(0);

        if (gamepad2.dpad_down) {               //Grabs the foundation when toggled by player 2
            servoFoundation.setPosition(0);
        } else
            servoFoundation.setPosition(.8);


    }
}