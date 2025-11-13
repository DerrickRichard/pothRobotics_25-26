// Autonomous program to drive foward ~4 inches. No encoders are used.
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="chickenAuto", group="Autonomous")
public class Chicken extends LinearOpMode {

    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    @Override
    public void runOpMode() {
        leftDrive  = hardwareMap.get(DcMotor.class, "front_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "back_drive");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Time to fry the chicken.(Initialized)");
        telemetry.update();

        waitForStart();

        // Drive forward for ~4 inches (adjust time as needed)
        leftDrive.setPower(0.4);
        rightDrive.setPower(0.4);

        sleep(500); // Tune this value based on testing

        leftDrive.setPower(0);
        rightDrive.setPower(0);

        telemetry.addData("Status", "Completed chicken frying");
        telemetry.update();
    }
}
