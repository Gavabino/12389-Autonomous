package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Main")
public class Main extends LinearOpMode{
    //Initialize motor variables
    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftRear;
    DcMotor rightRear;

    @Override
    public void runOpMode() {
        //Declare motor variables
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        //Set zero power behavior to float
        DcMotor[] motors = {leftRear, leftFront, rightFront, rightRear};
        for (DcMotor motor : motors) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
        waitForStart();
        while (opModeIsActive()) {

            //Add encoder positions
            for (DcMotor motor : motors) {
                String name = motor.toString() + ":";
                telemetry.addData(name, motor.getCurrentPosition());
            }
            telemetry.update();
        }
    }
}
