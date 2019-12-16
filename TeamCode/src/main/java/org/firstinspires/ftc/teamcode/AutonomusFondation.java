package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.lang.annotation.Target;

@Autonomous(name= "Foundation2", group="Skystone")
// below is the Annotation that registers this OpMode with the FtcRobotController app.
// @Autonomous classifies the OpMode as autonomous, name is the OpMode title and the
// optional group places the OpMode into the Exercises group.
// uncomment the @Disable annotation to remove the OpMode from the OpMode list.

//@Disabled
    public class AutonomusFondation extends LinearOpMode {
    DcMotor frontleft;
    DcMotor backleft;
    DcMotor frontright;
    DcMotor backright;
    Servo servoLine;
    public float x, y, z, w, pwr;
    public static double deadzone = 0.2;
    double velocity;
    double pi = 3.1415;
    int countsPerMotorRev = 1120 ;
    double gearReduction = 32/24 ;     // This is < 1.0 if geared UP
    double wheelDiamater = 3.93701;     // For figuring circumference
    double ticksPerInchDec = (countsPerMotorRev * gearReduction) / ( wheelDiamater * pi);
    int ticksPerInch = (int) ticksPerInchDec;
    int frontleftTarget;
    int backleftTarget;
    int frontrightTarget;
    int backrightTarget;
    double speed;
    double time;


    private Gyroscope imu;
    //called when init button is  pressed.

    @Override
    public void runOpMode() {
        frontleft = hardwareMap.dcMotor.get("front_left");
        frontright = hardwareMap.dcMotor.get("front_right");
        backleft = hardwareMap.dcMotor.get("back_left");
        backright = hardwareMap.dcMotor.get("back_right");
        servoLine = hardwareMap.servo.get("servoLine");

        //robot.init(HardwareMap);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for  button.


        waitForStart();


        drive(0, 500, 0.5);
        sleep(2000);
        drive(90, 500, 0.5);
        sleep(2000);
        drive(180, 500, 0.5);
        sleep(2000);
        drive(270, 500, 0.5);
        sleep(2000);


      }
      public void drive (double angle, int time, double speed) {

        double y = speed * java.lang.Math.sin(Math.toRadians(angle));
        double x = speed * java.lang.Math.cos(Math.toRadians(angle));

        double fl = -(y + x);
        double fr = y - x;
        double bl = -(y - x);
        double br = y + x;

        double max = Math.max(Math.max(Math.abs(fl), Math.abs(fr)), Math.max(Math.abs(bl), Math.abs(br)));
        if (max > 1) {
            fl /= max;
            fr /= max;
            bl /= max;
            br /= max;
        }

        frontleft.setPower(fl);
        frontright.setPower(fr);
        backleft.setPower(bl);
        backright.setPower(br);

        sleep(time);

        frontleft.setPower(0);
        backleft.setPower(0);
        frontright.setPower(0);
        backright.setPower(0);
        }

    }
