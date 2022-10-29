package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class ButtonMotor {
    public VictorSPX motor;
    public boolean motorStatus;

    ButtonMotor(int motorInt){
        motor = new VictorSPX(motorInt);
        motorStatus = false;
    }

    public void setMotor(boolean buttonValue){
        if(buttonValue){
            motor.set(ControlMode.PercentOutput, 100);
        } else{

        }
    }
}
