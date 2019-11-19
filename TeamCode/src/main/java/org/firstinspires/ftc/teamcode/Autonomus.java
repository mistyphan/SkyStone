// simple autonomous program that drives bot forward 2 seconds then ends.

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

// below is the Annotation that registers this OpMode with the FtcRobotController app.
// @Autonomous classifies the OpMode as autonomous, name is the OpMode title and the
// optional group places the OpMode into the Exercises group.
// uncomment the @Disable annotation to remove the OpMode from the OpMode list.

@Autonomous(name="Drive Forward", group="Exercises")
//@Disabled
public class Autonomus extends LinearOpMode
{
    DcMotor frontleft;
    DcMotor backleft;
    DcMotor frontright;
    DcMotor backright;
    Servo servoOne;

    // called when init button is  pressed.

    @Override
    public void runOpMode() throws InterruptedException
    {
        frontleft = hardwareMap.dcMotor.get("front_left");
        frontright = hardwareMap.dcMotor.get("front_right");
        backleft = hardwareMap.dcMotor.get("back_left");
        backright = hardwareMap.dcMotor.get("back_right");
        servoOne = hardwareMap.servo.get("servoOne");

        frontleft.setDirection(DcMotor.Direction.REVERSE);
        backleft.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        moveStraight(0.75, 5 );
        moveStraight(-0.75, 5 );

        servoOne.setPosition(.5);
        sleep(10000);

    }


    public void moveStraight (double power, double seconds)
    {
        frontleft.setPower(power);
        backleft.setPower(power);
        frontright.setPower(power);
        backright.setPower(power);

        long milliseconds = (long) (seconds * 1000);
        sleep(milliseconds);

        frontleft.setPower(0.0);
        backleft.setPower(0.0);
        frontright.setPower(0.0);
        backright.setPower(0.0);

    }
}