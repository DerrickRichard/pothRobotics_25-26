Poth Robotics Code Repository - PiRate Brigade Team #15758
=========================

This repository contains custom FIRST Tech Challenge (FTC) robot control code written for the `org.firstinspires.ftc.teamcode` package. It includes:

-   A timed Autonomous OpMode (`ChickenAuto`)
-   A tank‑drive TeleOp OpMode (`TankDrive_TeleOp`)
-   The FTC SDK iterative OpMode base class (`OpMode.java`) for reference

The code is designed for use with the standard FTC SDK and REV Robotics Control Hub/Expansion Hub hardware.

* * * * *

Repository Structure
--------------------

```
teamcode/
│
├── chicken.java        # Autonomous OpMode
├── Silver.java         # TeleOp OpMode (Tank Drive)
└── OpMode.java         # FTC SDK iterative OpMode base class (reference only)

```

* * * * *

Autonomous Mode --- `ChickenAuto`
-------------------------------

`chicken.java` implements a simple timed autonomous routine named **ChickenAuto**.

### Features

-   Four‑motor tank‑drive configuration
-   Shooter motors included for safety shutdown
-   Drive motors use BRAKE zero‑power behavior
-   Shooter motors use FLOAT zero‑power behavior
-   Helper methods for clean power control:
    -   `setDrivePower(left, right)`
    -   `stopAll()`

### Behavior Summary

1.  Hardware is mapped and configured
2.  All motors are stopped for safety
3.  Robot drives forward at 40% power for approximately two seconds
4.  Robot stops and reports status through telemetry

This OpMode is intended for basic autonomous movement and early testing.

* * * * *

TeleOp Mode --- `TankDrive_TeleOp`
--------------------------------

`Silver.java` implements a tank‑drive TeleOp with ramping, deadzones, slow mode, and shooter/servo controls.

### Drive System

-   Tank drive
    -   Left stick controls left motors
    -   Right stick controls right motors
-   Deadzone filtering to prevent drift
-   Slow mode (left bumper) limiting power to 40 percent
-   Ramp smoothing to prevent sudden changes in motor power
-   Adjustable tuning constants for maximum power, slow mode, deadzone, and ramp rate

### Shooter System

-   Two shooter motors (`DcMotorEx`)
-   Controlled by gamepad triggers:
    -   Left trigger activates the primary shooter motor in reverse
    -   Right trigger activates the hex shooter motor forward

### Servo Control

-   Standard servo used as a continuous‑motion mechanism
-   Button‑based control:
    -   `X` sets reverse position
    -   `B` sets forward position
    -   Default position is 0.5 (stop)

### Telemetry Output

-   Runtime
-   Left and right drive power
-   Slow mode status
-   Shooter motor power levels
-   Servo position

This OpMode is designed for smooth, predictable driver control with safety and consistency in mind.

* * * * *

FTC SDK Base Class --- `OpMode.java`
----------------------------------

Included for reference, this file is the FTC SDK's iterative OpMode base class. It defines:

-   Lifecycle methods (`init`, `init_loop`, `start`, `loop`, `stop`)
-   Gamepad update handling
-   Telemetry update behavior
-   Runtime tracking
-   Internal safety and threading behavior

Your TeleOp (`Silver.java`) extends this class, while your Autonomous OpMode (`chicken.java`) extends `LinearOpMode`.

* * * * *

Hardware Configuration
----------------------

The following configuration names must match the FTC Robot Controller configuration:

| Component | Configuration Name |
| --- | --- |
| Left Front Drive Motor | `left_front_drive` |
| Left Back Drive Motor | `left_back_drive` |
| Right Front Drive Motor | `right_front_drive` |
| Right Back Drive Motor | `right_back_drive` |
| Shooter Motor | `shooter_motor` |
| Shooter Hex Motor | `shooter_hex_motor` |
| Servo | `servo` |

* * * * *

Getting Started
---------------

1.  Place these files in your FTC SDK project under:\
    `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/`
2.  Open the project in Android Studio
3.  Build and deploy to the Control Hub or Robot Controller phone
4.  Select:
    -   **ChickenAuto** under Autonomous
    -   **TankDrive_TeleOp** under TeleOp

* * * * *

Authors
-------

- Derrick Richard
- Emma Aldrige

* * * * *

License
-------
Liscensed under the MIT License
