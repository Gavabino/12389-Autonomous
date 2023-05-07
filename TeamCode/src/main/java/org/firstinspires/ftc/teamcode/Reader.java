package org.firstinspires.ftc.teamcode;

import com.opencsv.CSVReader;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

@TeleOp(name = "Reader")
public class Reader extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotor leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        DcMotor rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        DcMotor leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        DcMotor rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        DcMotor[] motors = {leftRear, leftFront, rightFront, rightRear};
        ArrayList<int[]> postitions = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(Recorder.FILE_NAME));
            String[] nextRecord;

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    telemetry.addData("Encoder", cell);
                }
                postitions.add(Arrays.stream(nextRecord).mapToInt(Integer::parseInt).toArray());
                telemetry.update();
                sleep(1000);
                telemetry.clear();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            telemetry.addLine("Failed to read");
        }
        waitForStart();
        while (opModeIsActive()) {
            for (int i = 0; i < postitions.size(); i++) {
                int[] position = postitions.get(i);
                for (int j = 0; j < position.length; j++) {
                    DcMotor motor = motors[j];
                    Functions.RUN_CLOSE_TO_POSITION(motor, position[j]);
                }

            }
        }
    }
}
