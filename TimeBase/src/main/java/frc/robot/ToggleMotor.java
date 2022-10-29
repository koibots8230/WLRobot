package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class ToggleMotor {
    public VictorSPX motor;

    ToggleMotor(int motorInt){
        motor = new VictorSPX(motorInt);
    }
}
