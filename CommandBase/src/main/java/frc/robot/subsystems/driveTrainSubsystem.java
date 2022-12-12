package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//This drivetrain code is built in accordance with last year's tankdrive drivetrain.
public class driveTrainSubsystem extends SubsystemBase {
    private final TalonSRX frontleftMotor;
    private final TalonSRX backleftMotor;
    private final TalonSRX frontrightMotor;
    private final TalonSRX backrightMotor;//right and left motors should move together.
    
    public double speed = 0.5; //Assuming speed is in percentage.
    //FYI: 1: double is just a better float. 2: speed is a percentage but as a demical.
    public driveTrainSubsystem() {
        frontleftMotor = new TalonSRX(2);
        backleftMotor = new TalonSRX(0);
        frontrightMotor = new TalonSRX(3);
        backrightMotor = new TalonSRX(1);
    }
    public void driveFwd() { // Function to drive forward at speed.
        backrightMotor.set(ControlMode.PercentOutput, speed);
        frontrightMotor.set(ControlMode.PercentOutput, speed);
        frontleftMotor.set(ControlMode.PercentOutput, speed);
        backleftMotor.set(ControlMode.PercentOutput, speed);
    }
    public void driveBkwd() { // Function to drive backward at speed.
        backrightMotor.set(ControlMode.PercentOutput, -speed);
        frontrightMotor.set(ControlMode.PercentOutput, -speed);
        frontleftMotor.set(ControlMode.PercentOutput, -speed);
        backleftMotor.set(ControlMode.PercentOutput, -speed);
    }
    public void turnRight() { // Turn right at speed. Differential turning.
        backrightMotor.set(ControlMode.PercentOutput, -speed);
        frontrightMotor.set(ControlMode.PercentOutput, -speed);
        frontleftMotor.set(ControlMode.PercentOutput, speed);
        backleftMotor.set(ControlMode.PercentOutput, speed);
    }
    public void turnLeft() { // Turn left at speed. Differential turning.
        backrightMotor.set(ControlMode.PercentOutput, speed);
        frontrightMotor.set(ControlMode.PercentOutput, speed);
        frontleftMotor.set(ControlMode.PercentOutput, -speed);
        backleftMotor.set(ControlMode.PercentOutput, -speed);
    }
    public void stopDriving() { // Brings the robot to a standstill.
        backrightMotor.set(ControlMode.PercentOutput, 0);
        frontrightMotor.set(ControlMode.PercentOutput, 0);
        frontleftMotor.set(ControlMode.PercentOutput, 0);
        backleftMotor.set(ControlMode.PercentOutput, 0);
    }
}
