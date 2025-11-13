# FTC Poth Robotics – Team Code

Welcome to the Poth FTC robot codebase! This project includes both TeleOp and Autonomous programs designed for a two-motor tank drive robot, with future support for a ball shooter mechanism.

---

## Project Structure

| File                  | Description                                                                 |
|-----------------------|-----------------------------------------------------------------------------|
| `Silver.java`         | TeleOp mode for tank drive control with joystick input and shooter prep     |
| `Chicken.java`        | Autonomous mode that drives ~4 inches forward using timed motor power       |
| `OpMode.java`         | FTC SDK base class for defining OpModes (provided by the SDK)               |

---

## TeleOp Mode – `Silver.java`

- **Tank Drive**: Left joystick controls left motor, right joystick controls right motor.
- **Shooter Ready**: Code for a ball shooter is included but commented out until hardware is added.
- **Motor Safety**: Uses BRAKE mode for better stopping control.
- **Telemetry**: Displays runtime and motor power during operation.

---

## Autonomous Mode – `chicken.java`

- **No Encoders**: Drives forward ~4 inches using timed motor power (`sleep()`).
- **Simple Setup**: Great for quick testing or basic autonomous movement.
- **Telemetry**: Fun status messages like “Time to fry the chicken” and “Completed chicken frying.”

### What We Still Need to Do:
- Make sure `"front_drive"` and `"back_drive"` match your motor names in the configuration.
- Adjust `sleep(500)` to fine-tune distance based on your robot’s speed and surface.
- For precise movement, consider switching to encoder-based autonomous later.

---

## Future Expansion Ideas

- Add encoder-based autonomous routines for accurate navigation.
- Implement shooter motor control using gamepad buttons.
- Add turning logic or sensor-based decision-making.
- Create autonomous sequences for competition tasks (e.g., parking, scoring).

---

## Requirements

- FTC SDK (compatible with your control system)
- Android Studio
- Configured hardware map with correct motor names

---

## Helpful Resources

- [FTC SDK GitHub](https://github.com/FIRST-Tech-Challenge/FtcRobotController)
- [REV Robotics FTC Guide](https://www.revrobotics.com/ftc/)
- [FTC Game Manual](https://www.firstinspires.org/resource-library/ftc/game-and-season-info)

---

## Authors

Derrick Richard
Emma Aldrige

---

