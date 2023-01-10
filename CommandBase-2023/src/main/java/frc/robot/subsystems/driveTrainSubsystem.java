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
        frontleftMotor = new TalonSRX(0);
        backleftMotor = new TalonSRX(15);
        frontrightMotor = new TalonSRX(2);
        backrightMotor = new TalonSRX(1);
    }
    public void activateLeft(double activateSpeed) {
        frontleftMotor.set(ControlMode.PercentOutput, activateSpeed);
        backleftMotor.set(ControlMode.PercentOutput, activateSpeed);
    }
    public void activateRight(double activateSpeed) {
        frontrightMotor.set(ControlMode.PercentOutput, activateSpeed);
        backrightMotor.set(ControlMode.PercentOutput, activateSpeed);
    }
    public void stopDriving() { // Brings the robot to a standstill.
        backrightMotor.set(ControlMode.PercentOutput, 0);
        frontrightMotor.set(ControlMode.PercentOutput, 0);
        frontleftMotor.set(ControlMode.PercentOutput, 0);
        backleftMotor.set(ControlMode.PercentOutput, 0);
    }
}
