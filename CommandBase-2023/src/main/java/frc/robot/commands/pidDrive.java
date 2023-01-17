// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class pidDrive extends PIDCommand {
  private DriveTrainSubsystem driveTrainSubsystem;
  /** Creates a new pidDrive. */
  public pidDrive(DriveTrainSubsystem _driveSubsystem) {
    super(
        // The controller that the command will use
        new PIDController(Constants.kpDrive, Constants.kiDrive, Constants.kdDrive),
        // This should return the measurement
        () -> _driveSubsystem.leftMeasurement() + _driveSubsystem.rightMeasurment(),
        // This should return the setpoint (can also be a constant)
        () -> _driveSubsystem.controller.getRawAxis(1) + _driveSubsystem.controller.getRawAxis(5),
        // This uses the output
        output -> {
          // Use the output here
          _driveSubsystem.pidActivateLeft(output - _driveSubsystem.controller.getRawAxis(1));
          _driveSubsystem.pidActivateRight(output - _driveSubsystem.controller.getRawAxis(5));
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    driveTrainSubsystem = _driveSubsystem;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (driveTrainSubsystem.controller.getRawAxis(1) + driveTrainSubsystem.controller.getRawAxis(5) < 0.3) {
      return true;
    }
    return false;
  }
}
