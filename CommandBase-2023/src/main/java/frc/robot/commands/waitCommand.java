// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.subsystems.TankDriveSubsystemBase;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class waitCommand extends CommandBase {
  /** Creates a new waitCommand. */
  int m_waitSeconds;
  int count = 0;
  private final AHRS gyro;
  private final TankDriveSubsystemBase rightDrive;
  private final TankDriveSubsystemBase leftDrive;
  public waitCommand(int wait20ms, AHRS _Gyro, TankDriveSubsystemBase _rightDrive, TankDriveSubsystemBase _leftDrive) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_waitSeconds = wait20ms;
    gyro = _Gyro;
    rightDrive = _rightDrive;
    leftDrive = _leftDrive;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    count++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    new AutoBalanceCommand(gyro, rightDrive, leftDrive).schedule();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (count >= m_waitSeconds) {
      return true;
    }
    return false;
  }
}
