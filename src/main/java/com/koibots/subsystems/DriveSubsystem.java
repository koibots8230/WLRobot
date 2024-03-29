package com.koibots.subsystems;

import java.util.Map;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static java.lang.Math.signum;


public class DriveSubsystem extends SubsystemBase
{
    VictorSPX primaryLeftMotor = new VictorSPX(12);
    VictorSPX primaryRightMotor = new VictorSPX(13);
    VictorSPX secondaryLeftMotor = new VictorSPX(14);    
    VictorSPX secondaryRightMotor = new VictorSPX(15);

    ShuffleboardTab driveTab = Shuffleboard.getTab("Telemetry");

    ShuffleboardLayout primaryLeftLogs = driveTab
        .getLayout("Primary Left Motor", BuiltInLayouts.kList)
        .withSize(2, 2)
        .withPosition(6, 0) // or 7 and 1
        .withProperties(Map.of("Label Position", "Left"));

    ShuffleboardLayout primaryRightLogs = driveTab
        .getLayout("Primary Right Motor", BuiltInLayouts.kList)
        .withSize(2, 2)
        .withPosition(8, 0) // or 9 and 1
        .withProperties(Map.of("Label Position", "Left"));

    ShuffleboardLayout secondaryLeftLogs = driveTab
        .getLayout("Secondary Left Motor", BuiltInLayouts.kList)
        .withSize(2, 2)
        .withPosition(6, 2) // or 7 and 3
        .withProperties(Map.of("Label Position", "Left"));

    ShuffleboardLayout secondaryRightLogs = driveTab
        .getLayout("Secondary Right Motor", BuiltInLayouts.kList)
        .withSize(2, 2)
        .withPosition(8, 2) // or 9 and 3
        .withProperties(Map.of("Label Position", "Left"));

    GenericEntry maxSpeed;

    public DriveSubsystem() {
        //secondaryLeftMotor.follow(primaryLeftMotor);
        //secondaryRightMotor.follow(primaryRightMotor);

        maxSpeed = driveTab.add("Max Speed", 0.2)
            .withWidget(BuiltInWidgets.kNumberSlider)
            .withProperties(Map.of("min", 0, "max", 1))
            .withSize(2, 1)
            .withPosition(4, 0) // or 5 and 1
            .getEntry();


            primaryLeftLogs.addNumber("0 - Voltage", primaryLeftMotor::getBusVoltage);
            primaryLeftLogs.addNumber("0 - Temperature", primaryLeftMotor::getTemperature);
            primaryLeftLogs.addNumber("0 - Output Volts", primaryLeftMotor::getMotorOutputVoltage);
    
            primaryRightLogs.addNumber("1 - Voltage", primaryRightMotor::getBusVoltage);
            primaryRightLogs.addNumber("1 - Temperature", primaryRightMotor::getTemperature);
            primaryRightLogs.addNumber("1 - Output Volts", primaryRightMotor::getMotorOutputVoltage);
    
            secondaryLeftLogs.addNumber("2 - Voltage", secondaryLeftMotor::getBusVoltage);
            secondaryLeftLogs.addNumber("2 - Temperature", secondaryLeftMotor::getTemperature);
            secondaryLeftLogs.addNumber("2 - Output Volts", secondaryLeftMotor::getMotorOutputVoltage);
    
            secondaryRightLogs.addNumber("3 - Voltage", secondaryRightMotor::getBusVoltage);
            secondaryRightLogs.addNumber("3 - Temperature", secondaryRightMotor::getTemperature);
            secondaryRightLogs.addNumber("3 - Output Volts", secondaryRightMotor::getMotorOutputVoltage);
    }

    private double getMaxSpeed() {
        return maxSpeed.getDouble(0.2);
    }

    public void setSpeed(double leftSpeed, double rightSpeed) {
        primaryLeftMotor.set(ControlMode.PercentOutput, leftSpeed);
        primaryRightMotor.set(ControlMode.PercentOutput, rightSpeed);
    }

    public class TankDrive extends CommandBase{
        DoubleSupplier leftJoystick;
        DoubleSupplier rightJoystick;

        public TankDrive(DoubleSupplier leftJoystick, DoubleSupplier rightJoystick) {
            this.leftJoystick = leftJoystick;
            this.rightJoystick = rightJoystick;

            addRequirements(DriveSubsystem.this);
        }

        @Override
        public void execute() {
            DriveSubsystem.this.setSpeed(
                transformInput(this.leftJoystick.getAsDouble()), 
                transformInput(this.rightJoystick.getAsDouble()));
        }

        private double transformInput(double in) {
            return signum(in) * (in * in) * DriveSubsystem.this.getMaxSpeed();
        }

        @Override
        public void end(boolean interrupted) {
            DriveSubsystem.this.setSpeed(0, 0);
        }
    }
}
