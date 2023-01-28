// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.TankDriveSubsystemBase;

public class moveForwardntimes extends CommandBase {
  /** Creates a new moveForwardntimes. */
  private final int m_iterations;
  private int count = 0;
  private int m_invert = 1;
  private final TankDriveSubsystemBase m_rightDrive;
  private final TankDriveSubsystemBase m_leftDrive;
  public moveForwardntimes(int iterations, TankDriveSubsystemBase leftDrive, TankDriveSubsystemBase rightDrive, boolean invert) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_iterations = iterations;
    m_rightDrive = rightDrive;
    m_leftDrive = leftDrive;
    if (invert) {
      m_invert = -1;
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_rightDrive.setMotor(Constants.AUTO_SPEED * m_invert);
    m_leftDrive.setMotor(Constants.AUTO_SPEED * m_invert);
    count++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (count >= m_iterations) {
      return true;
    }
    return false;
  }
}
