// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.driveTrainSubsystem;

public class MoveForward extends CommandBase {

  private final driveTrainSubsystem DriveTrain;

  /** Creates a new LeftDriveTrain. */
  public MoveForward(driveTrainSubsystem driveTrainArg) {
    DriveTrain = driveTrainArg;
    addRequirements(driveTrainArg);
    DriveTrain.stopDriving();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DriveTrain.driveFwd();
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
