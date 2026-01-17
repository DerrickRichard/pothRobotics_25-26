package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="ChickenAuto", group="Autonomous")
public class chicken extends LinearOpMode {

    // Drive motors
    private DcMotor leftFrontDrive;
    private DcMotor leftBackDrive;
    private DcMotor rightFrontDrive;
    private DcMotor rightBackDrive;

    // Shooter motors (for safety stop)
    private DcMotorEx shooterMotor;
    private DcMotorEx shooterHexMotor;

    // Continuous servo
    private Servo continuousServo;

    // Servo constants
    private static final double CONTINUOUS_SERVO_STOP = 0.5;

    @Override
    public void runOpMode() {

        // ===== Hardware Mapping =====
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive   = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive  = hardwareMap.get(DcMotor.class, "right_back_drive");

        shooterMotor    = hardwareMap.get(DcMotorEx.class, "shooter_motor");
        shooterHexMotor = hardwareMap.get(DcMotorEx.class, "shooter_hex_motor");

        continuousServo = hardwareMap.get(Servo.class, "servo");

        // ===== Motor Directions =====
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);

        // ===== Zero Power Behavior =====
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterHexMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // Ensure everything is stopped before start
        stopAll();

        telemetry.addData("Status", "Initialized - Chicken ready");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {

            // ===== Drive forward (timed) =====
            setDrivePower(0.4, 0.4);
            sleep(800);   // ~4–6 inches (TUNE THIS)
            setDrivePower(0, 0);

            telemetry.addData("Status", "Chicken cooked");
            telemetry.update();
        }

        // Safety stop
        stopAll();
    }

    // ===== Helper Methods =====
    private void setDrivePower(double left, double right) {
        leftFrontDrive.setPower(left);
        leftBackDrive.setPower(left);
        rightFrontDrive.setPower(right);
        rightBackDrive.setPower(right);
    }

    private void stopAll() {
        setDrivePower(0, 0);
        shooterMotor.setPower(0);
        shooterHexMotor.setPower(0);
        continuousServo.setPosition(CONTINUOUS_SERVO_STOP);
    }
}
