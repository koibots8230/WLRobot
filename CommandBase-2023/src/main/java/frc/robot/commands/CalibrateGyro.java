// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SensorSubsystem;

public class CalibrateGyro extends CommandBase {
  private final SensorSubsystem sensors;
  /** Creates a new CalibrateGyro. */
  public CalibrateGyro(SensorSubsystem _sensors) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(_sensors);
    sensors = _sensors;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    sensors.calibrateGyro();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
