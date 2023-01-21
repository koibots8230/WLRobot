// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorSubsystem extends SubsystemBase {
  /** Creates a new SensorSubsystem. */
  private final ADXRS450_Gyro gyro;
  public SensorSubsystem() {
    gyro = new ADXRS450_Gyro();
    gyro.calibrate();
  }

  public double getGyroAngle() {
    return gyro.getAngle();
  }

  public void calibrateGyro() {
    gyro.calibrate();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Current Gyro Angle", getGyroAngle());
  }
}