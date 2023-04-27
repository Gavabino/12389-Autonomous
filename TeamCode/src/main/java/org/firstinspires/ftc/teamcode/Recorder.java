package org.firstinspires.ftc.teamcode;

import android.os.Environment;

import com.opencsv.CSVWriter;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@TeleOp(name="Main")
public class Recorder extends LinearOpMode{
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
        //Test writing data
        CSVWriter writer;
        try {
            writer = new CSVWriter(new FileWriter(Environment.getExternalStorageDirectory().getPath()+"/"+"FIRST"+ "/" + "Data" + ".csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.left_stick_y > 0.5 && gamepad1.left_stick_x > 0.5) {
                rightFront.setPower(-0.75);
                leftFront.setPower(0);
                rightRear.setPower(0);
                leftRear.setPower(-0.75);
            } else if (gamepad1.left_stick_y > 0.5 && gamepad1.left_stick_x < -0.5) {
                rightFront.setPower(0);
                leftFront.setPower(-0.75);
                rightRear.setPower(-0.75);
                leftRear.setPower(0);
            } else if (gamepad1.left_stick_y < -0.5 && gamepad1.left_stick_x < -0.5) {
                rightFront.setPower(0.75);
                leftFront.setPower(0);
                rightRear.setPower(0);
                leftRear.setPower(0.75);
            } else if (gamepad1.left_stick_y < -0.5 && gamepad1.left_stick_x > 0.5) {
                rightFront.setPower(0);
                leftFront.setPower(0.75);
                rightRear.setPower(0.75);
                leftRear.setPower(0);
            } else if (gamepad1.left_stick_y > 0.5) {
                rightFront.setPower(-0.75);
                leftFront.setPower(-0.75);
                rightRear.setPower(-0.75);
                leftRear.setPower(-0.75);
            } else if (gamepad1.left_stick_y < -0.5) {
                rightFront.setPower(0.75);
                leftFront.setPower(0.75);
                rightRear.setPower(0.75);
                leftRear.setPower(0.75);
            } else if (gamepad1.left_stick_x > 0.5) {
                rightFront.setPower(-0.75);
                leftFront.setPower(0.75);
                rightRear.setPower(0.75);
                leftRear.setPower(-0.75);
            } else if (gamepad1.left_stick_x < -0.5) {
                rightFront.setPower(0.75);
                leftFront.setPower(-0.75);
                rightRear.setPower(-0.75);
                leftRear.setPower(0.75);
            } else if (gamepad1.right_bumper) {
                rightFront.setPower(0.75);
                leftFront.setPower(-0.75);
                rightRear.setPower(0.75);
                leftRear.setPower(-0.75);
            } else if (gamepad1.left_bumper) {
                rightFront.setPower(-0.75);
                leftFront.setPower(0.75);
                rightRear.setPower(-0.75);
                leftRear.setPower(0.75);
            } else {
                rightFront.setPower(0);
                leftFront.setPower(0);
                rightRear.setPower(0);
                leftRear.setPower(0);
            }
            if (gamepad1.right_stick_x > 0.5) {
                rightFront.setPower(1);
                leftFront.setPower(-1);
                rightRear.setPower(1);
                leftRear.setPower(-1);
            } else if (gamepad1.right_stick_x < -0.5) {
                rightFront.setPower(-1);
                leftFront.setPower(1);
                rightRear.setPower(-1);
                leftRear.setPower(1);
            }
            ArrayList<String> data = new ArrayList<>();
            //Add encoder positions
            for (int i = 0; i < motors.length; i++) {
                DcMotor motor = motors[i];
                String name = motor.getDeviceName();
                telemetry.addData(name, motor.getCurrentPosition());
                data.add(i, Integer.toString(motor.getCurrentPosition()));
            }
            writer.writeNext(data.toArray(new String[0]));
            try {
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            telemetry.update();
            sleep(200);
        }
    }
}
