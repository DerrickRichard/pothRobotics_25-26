//This code is NOT functional. Do NOT use it on a robot for autonomous.

// Autonomous program to drive forward ~4 inches using timed motor power.
// No encoders are used, so distance is approximate and must be tuned.

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="chickenAuto", group="Autonomous")
public class chicken extends LinearOpMode {

    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    @Override
    public void runOpMode() {
        // Map motors to configuration names
        leftDrive  = hardwareMap.get(DcMotor.class, "front_drive");  // left motor
        rightDrive = hardwareMap.get(DcMotor.class, "back_drive");   // right motor

        // Set motor directions so both drive forward together
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Set zero power behavior to BRAKE for better stopping control
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized - Time to fry the chicken");
        telemetry.update();

        // Wait for the start button on Driver Station
        waitForStart();

        if (opModeIsActive()) {
            // Drive forward for ~4 inches (adjust time as needed)
            leftDrive.setPower(0.4);
            rightDrive.setPower(0.4);

            sleep(6000); // Tune this value based on testing

            // Stop motors
            leftDrive.setPower(0);
            rightDrive.setPower(0);

            telemetry.addData("Status", "Completed chicken frying");
            telemetry.update();
        }
    }
}
