// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.TankDriveSubsystemBase;

public class AutoBalanceCommand extends CommandBase {
  private final AHRS gyro;
  private final TankDriveSubsystemBase rightDrive;
  private final TankDriveSubsystemBase leftDrive;
  /** Creates a new AutoBalanceCommand. */
  public AutoBalanceCommand(AHRS _Gyro, TankDriveSubsystemBase _rightDrive, TankDriveSubsystemBase _leftDrive) {
    // Use addRequirements() here to declare subsystem dependencies.
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
    double rightDirection = 0;
    double leftDirection = 0;
    if (Math.abs(gyro.getWorldLinearAccelX()) > 0.2) {
      rightDirection = gyro.getRoll() * gyro.getWorldLinearAccelX();
      leftDirection = gyro.getRoll() * gyro.getWorldLinearAccelX() * -1;
    } else {
      rightDirection = gyro.getRoll();
      leftDirection = gyro.getRoll();
    }

    rightDrive.setMotor(Constants.AUTO_SPEED * Math.signum(rightDirection));
    leftDrive.setMotor(Constants.AUTO_SPEED * Math.signum(leftDirection));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (false == interrupted) {
      rightDrive.setMotor(0);
      leftDrive.setMotor(0);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(gyro.getRoll()) <= 2.5) {
      return true;
    }
    return false;
  }
}
