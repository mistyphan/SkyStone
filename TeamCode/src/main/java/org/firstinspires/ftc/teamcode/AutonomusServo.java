package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name= "Foundation", group="Skystone")
// below is the Annotation that registers this OpMode with the FtcRobotController app.
// @Autonomous classifies the OpMode as autonomous, name is the OpMode title and the
// optional group places the OpMode into the Exercises group.
// uncomment the @Disable annotation to remove the OpMode from the OpMode list.

//@Disabled
    public class AutonomusServo extends LinearOpMode {
    DcMotor frontleft;
    DcMotor backleft;
    DcMotor frontright;
    DcMotor backright;
    Servo servoLine;

    //RobotHardware robot = new RobotHardware();
    private Gyroscope imu;
    //called when init button is  pressed.
    double wheelDiameter = 3.93701;                                     // this calculates how many encoder ticks per inch there is. It can be customizable for the diameter of the wheel,
    double gearReduction = 1.33334;                                     // gear reduction, and how many ticks there are on the encoder
    int ticksPerRev = 1120;
    double inchesPerMotorRev = wheelDiameter * gearReduction;
    double inchesPerTick = inchesPerMotorRev / ticksPerRev;
    double ticksPerInchDec = 1 / inchesPerTick;
    int ticksPerInch = (int) Math.round(ticksPerInchDec);


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

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        servoLine.setPosition(.8);


        }
        /*public void drive (int distance) {
        {
            int goTo =  distance * ticksPerInch;
            frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            frontleft.setTargetPosition(goTo);
            backleft.setTargetPosition(5600);
            frontleft.setTargetPosition(5600);
            backleft.setTargetPosition(5600);

            frontleft.setPower(.5);
            backleft.setPower(-.5);
            frontright.setPower(.5);
            backleft.setPower(-.5);

            frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);



            while (frontleft.isBusy() && backleft.isBusy() && frontright.isBusy() && backright.isBusy()) {
            }

            frontleft.setPower(0);
            backleft.setPower(0);
            frontright.setPower(0);
            backright.setPower(0);
        }
        */
        }
    }
