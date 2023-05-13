// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TankDriveSubsystemBase extends SubsystemBase {
  private final WPI_VictorSPX primaryVictorSPX;
  private final WPI_VictorSPX secondaryVictorSPX;
  private final CANSparkMax testMotor;
  private final ControlMode controlMode;
  /** Creates a new TankDriveSubsystemBase. */
  public TankDriveSubsystemBase(int _primaryMotorPort, int _secondaryMotorPort, ControlMode _controlMode) {
    primaryVictorSPX = new WPI_VictorSPX(_primaryMotorPort);
    secondaryVictorSPX = new WPI_VictorSPX(_secondaryMotorPort);
    secondaryVictorSPX.follow(primaryVictorSPX);
    controlMode = _controlMode;
    testMotor = new CANSparkMax(Constants.TEST_MOTOR_PORT, MotorType.kBrushless);
  }

  public TankDriveSubsystemBase(int _primaryMotorPort, int _secondaryMotorPort, boolean _primaryInverted, boolean _secondaryInvereted, ControlMode _controlMode) {
    primaryVictorSPX = new WPI_VictorSPX(_primaryMotorPort);
    secondaryVictorSPX = new WPI_VictorSPX(_secondaryMotorPort);
    primaryVictorSPX.setInverted(_primaryInverted);
    secondaryVictorSPX.setInverted(_secondaryInvereted);
    secondaryVictorSPX.follow(primaryVictorSPX);
    controlMode = _controlMode;
    testMotor = new CANSparkMax(Constants.TEST_MOTOR_PORT, MotorType.kBrushless);
  }

  public void setControlMode(ControlMode _newControlMode) {
    //controlMode = _newControlMode;
  }

  
  public double getEncoder() {
    return primaryVictorSPX.get();
  }

  public void setMotor(double motorSpeed) {
    primaryVictorSPX.set(controlMode, motorSpeed);
  }

  public void setTestMotor(double speed) {
    testMotor.set(speed);
  }

  public CANSparkMax getTestMotor() {
    return testMotor;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Motor Voltage", primaryVictorSPX.getBusVoltage());
  }

  public class RunTestMotor extends CommandBase {
    private TankDriveSubsystemBase m_tankdrive;
    public RunTestMotor(TankDriveSubsystemBase m_TankDriveSubsystemBase) {
      m_tankdrive = m_TankDriveSubsystemBase;
      addRequirements(m_tankdrive);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      m_tankdrive.getTestMotor().set(0.3);
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
  
}
