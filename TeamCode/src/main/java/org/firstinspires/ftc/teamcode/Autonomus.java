package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Auto Block", group="Skystone");

public class Auto extends LinearOpMode {

        RobotHardware robot = new RobotHardware();
        private Gyroscope imu;

    @Override
    public void runOpMode() {

        robot.init(HardwareMap);

        waitForStart();

        /* the following formula is used to calculate the number of ticks per inch
        (3.14(3.93701)(2)/1120 = .02208 inches per encoder tick
         */

        robot.frontleft.setTargetPosition(-1087);     // goes forward 24 inches or one mat length
        robot.backleft.setTargetPosition(-1087);
        robot.frontright.setTargetPosition(1087);
        robot.backright.setTargetPosition(1087);
        robot.frontleft.setPower(-1);
        robot.backleft.setPower(-1);
        robot.backright.setPower(1);
        robot.backright.setPower(1);
        sleep(7000);

        while (robot.gyroSensor.getHeading() > 275 || robot.GyroSensor.getHeading() > 265)
            robot.frontleft.setPower-(1);
            robot.backleft.setPower(-1);
            robot.motoRF.setPower(-1);
            robot.backright.setPower-(1);
        }
        robot.frontleft.setPower(0)
        robot.motorLB.setPower(0)
        robot.frontright.setPower(0)
        robot.backright.setPower(0)
    {
        robot.frontleft.setTargetPosition(-1087);     // goes forward 24 inches or one mat length
        robot.motorLB.setTargetPosition(-1087);
        robot.frontright.setTargetPosition(1087);
        robot.backright.setTargetPosition(1087);
        robot.frontleft.setPower(-1);
        robot.backleft.setPower(-1);
        robot.frontright.setPower(1);
        robot.backright.setPower(1);
        sleep(7000);
    }
}
