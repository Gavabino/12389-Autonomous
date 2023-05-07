package org.firstinspires.ftc.teamcode;

import com.opencsv.CSVReader;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.io.FileReader;

@TeleOp(name = "Reader")
public class Reader extends LinearOpMode {
    @Override
    public void runOpMode() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader(Recorder.FILE_NAME));
            String[] nextRecord;

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    telemetry.addData("Encoder", cell);
                }
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
    }
}
