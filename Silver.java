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

    // Continuous rotation servo (actually a NORMAL servo)
    private Servo continuousServo;

    // Servo position constants
    private static final double CONTINUOUS_SERVO_REVERSE = 0.0;
    private static final double CONTINUOUS_SERVO_STOP    = 0.5;
    private static final double CONTINUOUS_SERVO_FORWARD = 1.0;

    // ===== NEW: Drive tuning constants =====
    private static final double MAX_DRIVE_POWER = 0.75;
    private static final double SLOW_MODE_POWER = 0.40;
    private static final double DEADZONE = 0.05;
    private static final double RAMP_RATE = 0.05;

    // ===== NEW: Ramp state =====
    private double lastLeftPower = 0.0;
    private double lastRightPower = 0.0;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables.
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive   = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive  = hardwareMap.get(DcMotor.class, "right_back_drive");

        // Initialize servo and PARK IT
        continuousServo = hardwareMap.get(Servo.class, "servo");
        continuousServo.setPosition(CONTINUOUS_SERVO_STOP); // FIX: for the servo constant spinning.

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

        // ===== NEW: Explicit motor modes =====
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Shooter motor initialization
        shooterMotor = hardwareMap.get(DcMotorEx.class, "shooter_motor");
        shooterMotor.setDirection(DcMotor.Direction.FORWARD);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        shooterHexMotor = hardwareMap.get(DcMotorEx.class, "shooter_hex_motor");
        shooterHexMotor.setDirection(DcMotor.Direction.FORWARD);
        shooterHexMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        telemetry.addData("Status", "EAT MOR CHIKN!!!!");
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

        // ===== NEW: Deadzone =====
        leftPower  = Math.abs(leftPower)  < DEADZONE ? 0 : leftPower;
        rightPower = Math.abs(rightPower) < DEADZONE ? 0 : rightPower;

        // ===== NEW: Slow mode =====
        double driveLimit = gamepad1.left_bumper ? SLOW_MODE_POWER : MAX_DRIVE_POWER;

        leftPower  = Range.clip(leftPower, -driveLimit, driveLimit);
        rightPower = Range.clip(rightPower, -driveLimit, driveLimit);

        // ===== NEW: Ramp smoothing =====
        leftPower = Range.clip(leftPower, lastLeftPower - RAMP_RATE, lastLeftPower + RAMP_RATE);
        rightPower = Range.clip(rightPower, lastRightPower - RAMP_RATE, lastRightPower + RAMP_RATE);

        lastLeftPower = leftPower;
        lastRightPower = rightPower;

        // Send power to drive motors
        setDrivePower(leftPower, rightPower);

        // Shooter control using triggers
        if (gamepad1.left_trigger > 0.1) {
            shooterMotor.setPower(-1.0);
        } else {
            shooterMotor.setPower(0.0);
        }

        if (gamepad1.right_trigger > 0.1) {
            shooterHexMotor.setPower(1.0);
        } else {
            shooterHexMotor.setPower(0.0);
        }

        // Servo control — ONLY move on button press
        if (gamepad1.x) {
            continuousServo.setPosition(CONTINUOUS_SERVO_REVERSE);
        }
        else if (gamepad1.b) {
            continuousServo.setPosition(CONTINUOUS_SERVO_FORWARD);
        }

        // Telemetry
        telemetry.addData("Run Time", runtime.toString());
        telemetry.addData("Drive", "L: %.2f  R: %.2f", leftPower, rightPower);
        telemetry.addData("Slow Mode", gamepad1.left_bumper);
        telemetry.addData("Shooter Motor", shooterMotor.getPower());
        telemetry.addData("Shooter Hex", shooterHexMotor.getPower());
        telemetry.addData("Servo Position", continuousServo.getPosition());
        telemetry.update();
    }

    @Override
    public void stop() {

        // Stop drive motors
        setDrivePower(0, 0);

        // Stop shooter motors
        shooterMotor.setPower(0);
        shooterHexMotor.setPower(0);

        // Park servo safely
        continuousServo.setPosition(CONTINUOUS_SERVO_STOP);
    }

    // ===== NEW: Helper method =====
    private void setDrivePower(double left, double right) {
        leftFrontDrive.setPower(left);
        leftBackDrive.setPower(left);
        rightFrontDrive.setPower(right);
        rightBackDrive.setPower(right);
    }
}
