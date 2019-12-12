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
    double ticksPerInch = (countsPerMotorRev * gearReduction) / ( wheelDiamater * pi);
    int frontleftTarget;
    int backleftTarget;
    int frontrightTarget;
    int backrightTarget;


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


        drive(90, .5, 12);


    }
        public void drive (int angle, double pwr, double distance) {

            //double y = java.lang.Math.sin(angle) * velocity;
            //double x = java.lang.Math.cos(angle) * velocity;



            frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            frontleftTarget = frontleft.getCurrentPosition() + (int)(y * ticksPerInch);
            backleftTarget = backleft.getCurrentPosition() + (int)(x * ticksPerInch);
            frontrightTarget = frontright.getCurrentPosition() + (int)(y * ticksPerInch);
            backleftTarget =  backright.getCurrentPosition() + (int)(x * ticksPerInch);

            x += java.lang.Math.cos(angle*pi/180)*distance;
            y += java.lang.Math.sin(angle*pi/180)*distance;


            if (Math.abs(x) < deadzone) x = 0;
            if (Math.abs(y) < deadzone) y = 0;

            frontright.setPower(Range.clip(pwr - x + z, -1, 1));
            backleft.setPower(Range.clip(pwr - x - z, -1, 1));
            frontleft.setPower(Range.clip(pwr + x - z, -1, 1));
            backright.setPower(Range.clip(pwr + x + z, -1, 1));

    }
}