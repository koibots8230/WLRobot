// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.Controlmode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

  private final TalonSRX shooterMotor;
  /** Creates a new ShootUp. */
  public ShooterSubsystem() {
    TalonSRX shooterMotor = new TalonSRX(15);
  }
  public void turnOnMotor(){
    shooterMotor.set(1);
  }
  public void turnOffMotor(){
    shooterMotor.set(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
