package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class AxisMotor {
    public VictorSPX motor;

    AxisMotor(int motorInt){
        motor = new VictorSPX(motorInt);
    }

    public void setMotor(double axisValue){
        motor.set(ControlMode.PercentOutput, axisValue);
    }
}
