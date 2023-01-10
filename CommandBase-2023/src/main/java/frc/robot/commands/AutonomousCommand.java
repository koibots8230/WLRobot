// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.driveTrainSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousCommand extends CommandBase {
  private final driveTrainSubsystem m_driveTrainSubsystem;
  //just make it stop the bot
  public AutonomousCommand(driveTrainSubsystem _mDriveTrain) {
    m_driveTrainSubsystem = _mDriveTrain;
    addRequirements(m_driveTrainSubsystem);
    m_driveTrainSubsystem.stopDriving();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}
}
