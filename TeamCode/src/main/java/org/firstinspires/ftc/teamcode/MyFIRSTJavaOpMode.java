package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;



@TeleOp(name = "9/29")

public class MyFIRSTJavaOpMode extends LinearOpMode {
    private DcMotor rightMotor;
    private DcMotor leftMotor;
    private Servo servoOne;

    @Override
    public void runOpMode() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        servoOne = hardwareMap.get(Servo.class, "servoOne");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double targetPower = 0;
            targetPower = -this.gamepad1.right_stick_y;
            rightMotor.setPower(targetPower);


        double targetPowerR = 0;
        //while (opModeIsActive()) {
            targetPowerR = -this.gamepad1.left_stick_y;
            leftMotor.setPower(targetPowerR * -1);


            // check to see if we need to move the servo.
        if (gamepad1.y) {
            // move to 0 degrees.
            servoOne.setPosition(0);
        } else if (gamepad1.x || gamepad1.b) {
            // move to 90 degrees.
            servoOne.setPosition(0.5);
        } else if (gamepad1.a) {
            // move to 180 degrees.
            servoOne.setPosition(1);
        }
    }
}}