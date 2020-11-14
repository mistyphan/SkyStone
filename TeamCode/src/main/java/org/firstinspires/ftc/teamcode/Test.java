package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;



        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.CRServo;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.GyroSensor;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.util.Range;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Test")
public class Test extends OpMode {
    private Servo testServo;


    @Override
    public void init() {

        testServo = hardwareMap.crservo.get("testServo");

    }

    @Override
    public void loop() {
        getJoyVals();
    }


    public void getJoyVals() {

        if (gamepad1.x) {
            testServo.setPower(1);
        } else if (gamepad1.b) {
            testServo.setPower(-1);
        } else {
            testServo.setPower(0);
        }
    }
}
