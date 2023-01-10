// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.event.BooleanEvent;
import edu.wpi.first.wpilibj.event.EventLoop;
//import frc.robot.commands.ExampleCommand;
//import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.SetLeft;
import frc.robot.commands.SetRight;
import frc.robot.subsystems.driveTrainSubsystem;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final driveTrainSubsystem m_dDriveTrainSubsystem = new driveTrainSubsystem();
  private final AutonomousCommand m_AutonomousCommand = new AutonomousCommand(m_dDriveTrainSubsystem);
  private final CommandXboxController driverController = new CommandXboxController(0);
  private final SetLeft moveLeftCommand = new SetLeft(m_dDriveTrainSubsystem);
  private final SetRight moveRightCommand = new SetRight(m_dDriveTrainSubsystem);
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  RobotContainer() {
  

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    Trigger rightTrigger = driverController.rightStick()
      .whileTrue(m_AutonomousCommand);
    Trigger lefTrigger = driverController.leftStick()
      .whileTrue(moveLeftCommand);
  
    
    
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public AutonomousCommand getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_AutonomousCommand;
  }

  public double deadzone(double doubleArgument) {
    if(Math.abs(doubleArgument) < Constants.DEADZONE) return 0;
    else return doubleArgument;
  }
}
