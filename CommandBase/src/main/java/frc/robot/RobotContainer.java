// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
//import frc.robot.commands.ExampleCommand;
//import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.IntakeBallCommand;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.driveTrainSubsystem;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();

  private final IntakeBallCommand m_intakeCommand = new IntakeBallCommand(m_intakeSubsystem, Constants.INTAKE_SPEED);

  private final driveTrainSubsystem m_dDriveTrainSubsystem = new driveTrainSubsystem();

  private final AutonomousCommand m_AutonomousCommand = new AutonomousCommand(m_dDriveTrainSubsystem);
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
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

    final XboxController xcontroll = new XboxController(0);//Don't know if this is correct but I think it is
    //define joystickbuttons
    final JoystickButton intake = new JoystickButton(xcontroll, 9);

    intake.whenActive(new IntakeBallCommand(m_intakeSubsystem, deadzone(xcontroll.getLeftTriggerAxis())));


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
