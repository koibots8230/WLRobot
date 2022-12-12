package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//This drivetrain code is built in accordance with last year's tankdrive drivetrain.
public class driveTrainSubsystem extends SubsystemBase {
    private final TalonSRX leftMotor;
    private final TalonSRX rightMotor;
    public float speed = 50; //Assuming speed is in percentage.
    public driveTrainSubsystem() {
        TalonSRX rightMotor = new TalonSRX();
        TalonSRX leftMotor = new TalonSRX();
    }
    public void driveFwd() { // Function to drive forward at speed.
        rightMotor.set(ControlMode.PercentOutput, speed);
        leftMotor.set(ControlMode.PercentOutput, speed);
    }
    public void driveBkwd() { // Function to drive backward at speed.
        rightMotor.set(ControlMode.PercentOutput, -speed);
        leftMotor.set(ControlMode.PercentOutput, -speed);
    }
    public void turnRight() { // Turn right at speed. Differential turning.
        rightMotor.set(ControlMode.PercentOutput,-speed);
        leftMotor.set(ControlMode.PercentOutput, speed);
    }
    public void turnLeft() { // Turn left at speed. Differential turning.
        rightMotor.set(ControlMode.PercentOutput, speed);
        leftMotor.set(ControlMode.PercentOutput, -speed);
    }
    public void stopDriving() { // Brings the robot to a standstill.
        rightMotor.set(ControlMode.PercentOutput, 0);
        leftMotor.set(ControlMode.PercentOutput, 0);
    }
}
