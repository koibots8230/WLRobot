// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.TankDriveSubsystemBase;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousCommand extends SequentialCommandGroup {
  /** Creates a new AutonomousCommand. */
  public AutonomousCommand(AHRS _Gyro, TankDriveSubsystemBase _rightDrive, TankDriveSubsystemBase _leftDrive) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new AutoBalanceCommand(_Gyro, _rightDrive, _leftDrive),
      new waitCommand(20),
      new AutoBalanceCommand(_Gyro, _rightDrive, _leftDrive),
      new waitCommand(20),
      new AutoBalanceCommand(_Gyro, _rightDrive, _leftDrive),
      new waitCommand(20),
      new AutoBalanceCommand(_Gyro, _rightDrive, _leftDrive),
      new waitCommand(20),
      new AutoBalanceCommand(_Gyro, _rightDrive, _leftDrive),
      new waitCommand(20),
      new AutoBalanceCommand(_Gyro, _rightDrive, _leftDrive),
      new waitCommand(20),
      new AutoBalanceCommand(_Gyro, _rightDrive, _leftDrive)
    );
  }


}
