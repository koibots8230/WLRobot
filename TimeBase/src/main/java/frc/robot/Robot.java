// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//wpi imports
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//revrobotics imports
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.XboxController;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import com.ctre.phoenix.motorcontrol.can.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public static final double DEADZONE = .15;//should this be private?
  //so that we only have to change one number instead of 5
  //temporary numbers
  int intakenumber = 4;
  int shooternumber = 15;
  //defining motors
  WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(2);
  WPI_TalonSRX backLeftMotor = new WPI_TalonSRX(0);
  WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(3);
  WPI_TalonSRX backRightMotor = new WPI_TalonSRX(1);
  WPI_TalonSRX intakeMotor = new WPI_TalonSRX(intakenumber);
  WPI_TalonSRX uptakeShooterMotor = new WPI_TalonSRX(shooternumber);
  
  XboxController xcontroll;

  boolean isInverted = false;
  boolean isInRev = false;
  boolean isShootRev = false;
  double INTAKE_SPEED = 0.5;
  double SHOOTER_SPEED = 0.5;
  
  
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    
  }

  /** This function is called periodically during operator control. 
   * @return */
  @Override
  public void teleopPeriodic() {
    //defining the motors
    double lefttrain = xcontroll.getLeftY();
    double righttrain = xcontroll.getRightY();
    double intake = xcontroll.getLeftTriggerAxis();
    double shoot = xcontroll.getRightTriggerAxis();
    boolean invert = xcontroll.getStartButton();
    boolean revinmid = xcontroll.getYButton();
    boolean revshoot = xcontroll.getXButton();
    if (revinmid == true) {//if the y button is pressed
        isInRev = !isInRev;//sets it false if true, and true is false.
        INTAKE_SPEED *= -1;//reverse the speed
    }
    if (revshoot == true) {//if the x button is pressed
      isShootRev = !isShootRev;//sets it false if true, and true is false.
      SHOOTER_SPEED *= -1;//reverse the speed
    }
    if (Math.abs(intake) > DEADZONE) {//is left trigger value greater than .15?
      intakeMotor.set(INTAKE_SPEED);//yes: then set intake motor
    } else {
      intakeMotor.set(0);//no, turn off intake motor
    }
    if (Math.abs(shoot) > DEADZONE) {//is right trigger value greater than .15?
      uptakeShooterMotor.set(SHOOTER_SPEED);//yes: then set shooter motor
    } else {
      uptakeShooterMotor.set(0);//no: turn off shooter motor
    }
    if (invert == true) {//if the start button is pressed
      isInverted=!isInverted;//sets it false if true, and true is false.
    }
    if (isInverted == true) {//actually inverts the controls.
      lefttrain *= -1;
      righttrain *= -1;
    }
    //set left motors, set to zero if joystick is inside the deadzone
    frontLeftMotor.set(deadzone(lefttrain));
    backLeftMotor.set(deadzone(lefttrain));
    //set right motors, set to zero if joystick is inside the deadzone.
    frontRightMotor.set(deadzone(righttrain));
    backRightMotor.set(deadzone(righttrain));
  }
  




  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  //examples of functions to add!

  public void SetMotors(double Speed, CANSparkMax[] MotorArray) {
    for (CANSparkMax Motor :MotorArray) {
      Motor.set(Speed);
    }
  }
  public double deadzone(double doubleArgument) {
    if(Math.abs(doubleArgument) < DEADZONE) return 0;
    else return doubleArgument;
  }
}