// Basic FTC TeleOp program for a four-motor robot using tank drive.
// Driver controls each side of the robot independently using joysticks, and bottons for servo.
// Includes runtime telemetry and motor safety enhancements.
// Ball shooting mechanism is implemented.

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TankDrive_TeleOp", group="Concept")
public class Silver extends OpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    // Ball shooter motors
    private DcMotorEx shooterMotor = null;
    private DcMotorEx shooterHexMotor = null;
    private Servo servo = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables.
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "" +
                "" +
                "left_front_drive");
        leftBackDrive   = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive  = hardwareMap.get(DcMotor.class, "right_back_drive");
        servo = hardwareMap.get(Servo.class, "servo");

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

        // Shooter motor initialization
        shooterMotor = hardwareMap.get(DcMotorEx.class, "shooter_motor");
        shooterMotor.setDirection(DcMotor.Direction.FORWARD);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT); // smoother spin-down

        shooterHexMotor = hardwareMap.get(DcMotorEx.class, "shooter_hex_motor");
        shooterHexMotor.setDirection(DcMotor.Direction.FORWARD);
        shooterHexMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        telemetry.addData("Status", "KFC sounds really good right now...");
    }

    @Override
    public void init_loop() { }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        // Tank drive control
        double leftPower = -gamepad1.left_stick_y;
        double rightPower = -gamepad1.right_stick_y;

        // Clip power values to stay within [-0.75, 0.75]
        leftPower = Range.clip(leftPower, -0.75, 0.75);
        rightPower = Range.clip(rightPower, -0.75, 0.75);

        // Send calculated power to motors
        leftFrontDrive.setPower(leftPower);
        leftBackDrive.setPower(leftPower);
        rightFrontDrive.setPower(rightPower);
        rightBackDrive.setPower(rightPower);

        // Shooter control using triggers
        if (gamepad1.left_trigger > 0.1) {
            shooterMotor.setPower(-1.0);   // Shoot forward
        } else {
            shooterMotor.setPower(0.0);    // Stop shooter
        }

        if (gamepad1.right_trigger > 0.1) {
            shooterHexMotor.setPower(1.0); // Shoot forward
        } else {
            shooterHexMotor.setPower(0.0); // Stop shooter
        }

        if (gamepad1.x) { // reverse the servo
            servo.setPosition(0.0);
        }
        if (gamepad1.b) { // forward servo
            servo.setPosition(1.0);
        }    


        // Telemetry for joystick input and motor power
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Drive Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        telemetry.addData("Shooter Motor", "Power (%.2f)", shooterMotor.getPower());
        telemetry.addData("Shooter Hex", "Power (%.2f)", shooterHexMotor.getPower());
        telemetry.addData("Set Position", servo.getPosition());
        telemetry.update();
    }

    @Override
    public void stop() {
        // Stop drive motors
        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);

        // Stop shooter motors
        shooterMotor.setPower(0);
        shooterHexMotor.setPower(0);
    }
}
