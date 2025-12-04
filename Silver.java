// This is a basic FTC TeleOp program for a four-motor robot using tank drive.
// It allows the driver to control each side of the robot independently using the joysticks.
// Includes runtime telemetry and motor safety enhancements.
// Ball shooting mechanism program is implemented, but commented out until hardware is added.

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TankDrive_TeleOp", group="Concept")
public class Silver extends OpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    // Ball shooter motor (commented until hardware is added)
    // private DcMotor shooterMotor = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables.
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive   = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive  = hardwareMap.get(DcMotor.class, "right_back_drive");

        // Reverse one side’s motors to ensure both sides drive forward together.
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);

        // Set zero power behavior to BRAKE for better stopping control.
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Shooter motor initialization (commented out)
        /*
        shooterMotor = hardwareMap.get(DcMotor.class, "shooter_motor");
        shooterMotor.setDirection(DcMotor.Direction.FORWARD);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        */

        telemetry.addData("Status", "Initialized like a fried chicken bucket from KFC.");
    }

    @Override
    public void init_loop() { }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        double leftPower = -gamepad1.left_stick_y;
        double rightPower = -gamepad1.right_stick_y;

        // Clip power values to stay within [-1.0, 1.0]
        leftPower = Math.max(-0.2, Math.min(0.2, leftPower));
        rightPower = Math.max(-0.2, Math.min(0.2, rightPower));

        // Send calculated power to motors
        leftFrontDrive.setPower(leftPower);
        leftBackDrive.setPower(leftPower);
        rightFrontDrive.setPower(rightPower);
        rightBackDrive.setPower(rightPower);

        // Shooter control (commented out)
        /*
        if (gamepad1.dpad_up) {
            shooterMotor.setPower(1.0); // Shoot forward
        } else if (gamepad1.dpad_down) {
            shooterMotor.setPower(-1.0); // Reverse spin
        } else {
            shooterMotor.setPower(0.0); // Stop shooter
        }
        */

        // Telemetry for joystick input and motor power
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        // Shooter telemetry (commented out)
        // telemetry.addData("Shooter", "Power (%.2f)", shooterMotor.getPower());

        telemetry.update();
    }

    @Override
    public void stop() {
        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
        // Shooter stop (commented out)
        // shooterMotor.setPower(0);
    }
}
