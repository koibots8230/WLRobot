// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.TankDriveSubsystemBase;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class pidSetMotor extends PIDCommand {
  private TankDriveSubsystemBase driveSubsystem;
  /** Creates a new pidSetLeftCommand. */
  public pidSetMotor(TankDriveSubsystemBase _Subsystem, CommandXboxController _Controller, int _controlAxis) {
    super(
        // The controller that the command will use
        new PIDController(Constants.kpDrive, Constants.kiDrive, Constants.kdDrive),
        // This should return the measurement
        () -> _Subsystem.getEncoder(),
        // This should return the setpoint (can also be a constant)
        () -> (Math.pow(_Controller.getRawAxis(_controlAxis), 2) * Math.signum(_Controller.getRawAxis(_controlAxis))) * 12,
        // This uses the output
        output -> {
          // Use the output here
         // double actualOutput = output / (1 + Math.abs(output));
         Boolean isSlow = _Controller.getHID().getRightBumperPressed();
         double adjustedOutput;
         if (isSlow) {
          adjustedOutput =  output * Constants.SLOW_MODE_FACTOR;
         } else {
          adjustedOutput = output;
         }
          _Subsystem.setMotor(adjustedOutput / 24);
          SmartDashboard.putNumber("PID Output", output);
        });
    driveSubsystem = _Subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubsystem);
    // Configure additional PID options by calling `getController` here.
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}