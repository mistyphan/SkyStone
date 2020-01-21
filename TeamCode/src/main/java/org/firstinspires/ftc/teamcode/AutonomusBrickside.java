package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.teamcode.VuInterface;

@Autonomous(name= "AutoEncoder", group="Skystone")
// below is the Annotation that registers this OpMode with the FtcRobotController app.
// @Autonomous classifies the OpMode as autonomous, name is the OpMode title and the
// optional group places the OpMode into the Exercises group.
// uncomment the @Disable annotation to remove the OpMode from the OpMode list.

//@Disabled
    public class AutonomusBrickside extends LinearOpMode {
    DcMotor frontleft;
    DcMotor backleft;
    DcMotor frontright;
    DcMotor backright;
    Servo servoLine;
    public float x, y, z, w, pwr;
    public static double deadzone = 0.2;
    double velocity;
    double pi = 3.1415;
    int countsPerMotorRev = 1120;
    double gearReduction = 32 / 24;     // This is < 1.0 if geared UP
    double wheelDiamater = 3.93701;     // For figuring circumference
    double ticksPerInchDec = (countsPerMotorRev * gearReduction) / (wheelDiamater * pi);
    int ticksPerInch = (int) ticksPerInchDec;
    long FLTarget;
    long BLTarget;
    long FRTarget;
    long BRTarget;
    double speed;
    double time;
    double inches;
    double yEncoderRaw;
    double xEncoderRaw;
    double FLD;
    double BLD;
    double FRD;
    double BRD;
    int FLInches;
    long BLInches;
    long FRInches;
    long BRInches;
    private VuInterface BrickVision;// = new VuInterface(hardwareMap);
    private OpenGLMatrix lastBrickPose;


    private Gyroscope imu;
    //called when init button is  pressed.

    @Override
    public void runOpMode() {
        frontleft = hardwareMap.dcMotor.get("FL");
        frontright = hardwareMap.dcMotor.get("FR");
        backleft = hardwareMap.dcMotor.get("BL");
        backright = hardwareMap.dcMotor.get("BR");
        //servoLine = hardwareMap.servo.get("servoLine");
        BrickVision = new VuInterface(hardwareMap);

        //robot.init(HardwareMap);
        BrickVision.initializeVuforia();

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for  button.


        waitForStart();

        while (true) {
            if (BrickVision.isTargetVisible()) {
                telemetry.addData("Visible", "Yes");
            }
            else {
                telemetry.addData("Visible", "no");
            }
            telemetry.update();
        }



    }

    public void driveTime(double angle, int time, double speed) {

        double y = speed * Math.sin(Math.toRadians(angle));
        double x = speed * Math.cos(Math.toRadians(angle));

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

    public void driveEncoder(double angle, double speed, double inches) {

        double y = speed * Math.sin(Math.toRadians(angle));
        double x = speed * Math.cos(Math.toRadians(angle));

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

        int FLInches = (int) Math.round(fl * inches);
        int FRInches = (int) Math.round(fr * inches);
        int BLInches = (int) Math.round(bl * inches);
        int BRInches = (int) Math.round(br * inches);

        int FLTarget = frontleft.getCurrentPosition() + (FLInches);
        int BLTarget = backleft.getCurrentPosition() + (BLInches);
        int FRTarget = frontright.getCurrentPosition() + (FRInches);
        int BRTarget = backright.getCurrentPosition() + (BRInches);

        frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontleft.setTargetPosition(FLTarget);
        backleft.setTargetPosition(BLTarget);
        frontright.setTargetPosition(FRTarget);
        backright.setTargetPosition(BRTarget);

        frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontleft.setPower(fl);
        frontright.setPower(fr);
        backleft.setPower(bl);
        backright.setPower(br);

        while (frontleft.isBusy() && (backleft.isBusy() && frontright.isBusy() && backright.isBusy())) {
        }


        frontleft.setPower(0);
        backleft.setPower(0);
        frontright.setPower(0);
        backright.setPower(0);
    }

    public void drive(double angle, double speed) {
        double y = speed * Math.sin(Math.toRadians(angle));
        double x = speed * Math.cos(Math.toRadians(angle));

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
        frontleft.setPower(speed);
        frontright.setPower(speed);
        backleft.setPower(speed);
        backright.setPower(speed);
    }
}