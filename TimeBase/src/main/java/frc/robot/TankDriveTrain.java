package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class TankDriveTrain {
    public VictorSPX frontLeftMotor;
    public VictorSPX frontRightMotor;
    public VictorSPX backLeftMotor;
    public VictorSPX backRightMotor;

    public TankDriveTrain(int frontLeft, int frontRight, int backLeft, int backRight){
        frontLeftMotor = new VictorSPX(frontLeft);
        frontRightMotor = new VictorSPX(frontRight);
        backLeftMotor = new VictorSPX(backLeft);
        backRightMotor = new VictorSPX(backRight);

        backLeftMotor.follow(frontLeftMotor);
        backLeftMotor.setInverted(false);
        backRightMotor.follow(frontRightMotor);
        backRightMotor.setInverted(false);
    }

    public void driveRobot(double axisOneValue, double axisTwoValue){
        frontLeftMotor.set(ControlMode.PercentOutput, axisOneValue);
        frontRightMotor.set(ControlMode.PercentOutput, axisTwoValue);
    }

    public void driveRobot(double stickOneYAxisValue, double stickOneXAxisValue, double stickTwoYAxisValue, double stickTwoXAxisValue){
        frontLeftMotor.set(ControlMode.PercentOutput, stickOneYAxisValue + stickOneXAxisValue);
        frontRightMotor.set(ControlMode.PercentOutput, stickTwoYAxisValue + stickTwoXAxisValue);
    }
}
