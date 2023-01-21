// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TankDriveSubsystemBase extends SubsystemBase {
  private final WPI_VictorSPX primaryVictorSPX;
  private final WPI_VictorSPX secondaryVictorSPX;
  private ControlMode controlMode;
  /** Creates a new TankDriveSubsystemBase. */
  public TankDriveSubsystemBase(int _primaryMotorPort, int _secondaryMotorPort, ControlMode _controlMode) {
    primaryVictorSPX = new WPI_VictorSPX(_primaryMotorPort);
    secondaryVictorSPX = new WPI_VictorSPX(_secondaryMotorPort);
    secondaryVictorSPX.follow(primaryVictorSPX);
    controlMode = _controlMode;
  }

  public TankDriveSubsystemBase(int _primaryMotorPort, int _secondaryMotorPort, boolean _primaryInverted, boolean _secondaryInvereted, ControlMode _controlMode) {
    primaryVictorSPX = new WPI_VictorSPX(_primaryMotorPort);
    secondaryVictorSPX = new WPI_VictorSPX(_secondaryMotorPort);
    primaryVictorSPX.setInverted(_primaryInverted);
    secondaryVictorSPX.setInverted(_secondaryInvereted);
    secondaryVictorSPX.follow(primaryVictorSPX);
    controlMode = _controlMode;
  }

  public void setControlMode(ControlMode _newControlMode) {
    controlMode = _newControlMode;
  }

  
  public double getEncoder() {
    return primaryVictorSPX.get();
  }

  public void setMotor(double motorSpeed) {
    primaryVictorSPX.set(controlMode, motorSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
