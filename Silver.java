// This is a basic FTC TeleOp program for a two-motor robot using tank drive.
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
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    // NEW - Ball shooter motor (commented until hardware is added)
    // private DcMotor shooterMotor = null;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables.
        leftDrive = hardwareMap.get(DcMotor.class, "front_drive");  // Assuming front_drive is left
        rightDrive = hardwareMap.get(DcMotor.class, "back_drive");  // Assuming back_drive is right

        // Reverse one motor to ensure both sides drive forward together.
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Set zero power behavior to BRAKE for better stopping control.
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // NEW - Initialize shooter motor
        /*
        shooterMotor = hardwareMap.get(DcMotor.class, "shooter_motor");
        shooterMotor.setDirection(DcMotor.Direction.FORWARD);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        */

        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, and before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double leftPower = -gamepad1.left_stick_y;
        double rightPower = -gamepad1.right_stick_y;

        // Clip power values to stay within [-1.0, 1.0]
        leftPower = Math.max(-1.0, Math.min(1.0, leftPower));
        rightPower = Math.max(-1.0, Math.min(1.0, rightPower));

        // Send calculated power to motors
        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);

        // NEW - Ball shooter control using d-pad
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
        // NEW - telemetry.addData("Shooter", "Power (%.2f)", shooterMotor.getPower());

        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        // NEW - leftDrive.setPower(0);
        // NEW - rightDrive.setPower(0);
        // NEW - shooterMotor.setPower(0);
    }
}

// Derrick was here...

// Ball shooting stuff, whatever its called
// NEW - is added before the comments where the ball shooter stuff is added
