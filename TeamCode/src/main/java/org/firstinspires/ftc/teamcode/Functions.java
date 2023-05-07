package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Functions {
    public static void RUN_CLOSE_TO_POSITION(DcMotor motor, int position) {
        double speed = 0;
        while (motor.getTargetPosition() != motor.getCurrentPosition()) {
            if (Math.abs(position - motor.getTargetPosition()) < 100)
                speed = 0.1;
            else
                speed = 0.8;

            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(speed);
        }
    }
}