package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//This drivetrain code is built in accordance with last year's tankdrive drivetrain.
public class driveTrainSubsystem extends SubsystemBase {
    private final TalonSRX frontleftMotor;
    private final TalonSRX backleftMotor;
    private final TalonSRX frontrightMotor;
    private final TalonSRX backrightMotor;//right and left motors should move together.
    public final CommandXboxController m_controller;
    
    //Assuming speed is in percentage.
    //FYI: 1: double is just a better float. 2: speed is a percentage but as a decimal.
    public driveTrainSubsystem(CommandXboxController controller) {
        m_controller = controller;
        frontleftMotor = new TalonSRX(Constants.FRONT_LEFT_MOTOR_PORT);
        backleftMotor = new TalonSRX(Constants.BACK_LEFT_MOTOR_PORT);
        frontrightMotor = new TalonSRX(Constants.FRONT_RIGHT_MOTOR_PORT);
        backrightMotor = new TalonSRX(Constants.BACK_RIGHT_MOTOR_PORT);
    }

    public void activateLeft() {
        double activateSpeed = m_controller.getLeftY();
        frontleftMotor.set(ControlMode.PercentOutput, activateSpeed);
        backleftMotor.set(ControlMode.PercentOutput, activateSpeed);
    }

    public void activateRight() {
        double activateSpeed = m_controller.getRightY();
        frontrightMotor.set(ControlMode.PercentOutput, activateSpeed);
        backrightMotor.set(ControlMode.PercentOutput, activateSpeed);
    }

    public void pidActivateRight(double activateSpeed) {
        frontrightMotor.set(ControlMode.PercentOutput, activateSpeed);
        backrightMotor.set(ControlMode.PercentOutput, activateSpeed);
    }
    
    public void stopDriving() { // Brings the robot to a standstill.
        backrightMotor.set(ControlMode.PercentOutput, 0);
        frontrightMotor.set(ControlMode.PercentOutput, 0);
        frontleftMotor.set(ControlMode.PercentOutput, 0);
        backleftMotor.set(ControlMode.PercentOutput, 0);
    }

    public double leftMeasurement() {
        return frontleftMotor.getActiveTrajectoryArbFeedFwd();
    }

    public double rightMeasurment() {
        return frontrightMotor.getActiveTrajectoryArbFeedFwd();
    }
    
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
      
    }
}
