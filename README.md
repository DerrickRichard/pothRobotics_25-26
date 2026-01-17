# Poth Robotics 25-26

## Overview
This repository contains the code for the Poth Robotics 25-26 FTC season. It includes both autonomous and teleoperated programs for our robot. The code is written in Java for the FTC SDK and is designed to run on the Robot Controller hardware with the Driver Hub.

---

## Contents
- **Autonomous Programs**
  - `chicken.java`: Simple timed autonomous for short forward movements.
- **TeleOp Programs**
  - `Silver.java`: Tank drive with shooter controls and continuous rotation servo. Includes slow mode, deadzone handling, and ramped motor power for smoother control.

---

## Setup

1. **Hardware Mapping**
   Ensure that your motors and servos are mapped exactly as in the code:
   - **Drive Motors**
     - `left_front_drive`
     - `left_back_drive`
     - `right_front_drive`
     - `right_back_drive`
   - **Shooter Motors**
     - `shooter_motor`
     - `shooter_hex_motor`
   - **Continuous Servo**
     - `servo`

2. **Motor Directions**
   - Left motors: `FORWARD`
   - Right motors: `REVERSE`

3. **Motor Modes**
   - Drive motors: `RUN_WITHOUT_ENCODER`
   - Shooter motors: `FLOAT` on zero power

4. **Zero Power Behavior**
   - Drive motors: `BRAKE`
   - Shooter motors: `FLOAT`

---

## Drivers Manual

### Driving
- Use **left joystick** to control left side and **right joystick** to control right side of the robot.
- Joysticks respond with a maximum power of `0.75` for normal operation.
- Press **left bumper** for slow mode (`0.4` max power) for precise movements.
- Motors are ramped, so joystick movements will gradually increase/decrease speed for smoother control.
- Deadzone of `0.05` is applied to avoid unintended small movements.

### Shooting
- **Left trigger** activates the main shooter motor (`shooter_motor`) at full reverse power.
- **Right trigger** activates the secondary shooter motor (`shooter_hex_motor`) at full forward power.
- Release triggers to stop the motors immediately.

### Servo Operation
- **X button** rotates the continuous servo in reverse.
- **B button** rotates the continuous servo forward.
- No buttons pressed: servo stops.

### Tips for Precise Movement
- Use **slow mode** when aligning to game elements or scoring zones.
- Make small joystick adjustments; the ramping will smooth out sudden changes.
- Always start with a small forward/backward movement to gauge power calibration.

### Telemetry
- The Driver Hub displays:
  - Run time
  - Drive power values (left and right)
  - Shooter motor powers
  - Servo position
  - Slow mode status

This telemetry is helpful for monitoring robot status and diagnosing issues during matches or practice.

---

## Notes
- This code is designed to be safe for driver operation.
- **Autonomous code** should be tuned and tested carefully before use; it is currently based on timed movements without encoder feedback.
- Always ensure hardware configuration matches the code.

---

## License
This repository is for team use and collaboration. Modify responsibly and test thoroughly on practice fields.
