// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.SensorSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousCommand extends PIDCommand {
  private SensorSubsystem sensors;

  /** Creates a new AutonomousCommand. */
  public AutonomousCommand(SensorSubsystem _sensors) {
    super(
        // The controller that the command will use
        new PIDController(Constants.kpAuto, Constants.kiAuto, Constants.kdAuto),
        // This should return the measurement
        () -> _sensors.getGyroAngle(),
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          // Use the output here
        });
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(_sensors);
    sensors = _sensors;
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (sensors.getGyroAngle() < 2.5) {
      return true;
    }
    return false;
  }
}