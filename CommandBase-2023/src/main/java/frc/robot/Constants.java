// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
//Hardware Ports:
    public static final int PRIMARY_LEFT_MOTOR_PORT = 0;
    public static final int PRIMARY_RIGHT_MOTOR_PORT = 1;
    public static final int SECONDARY_LEFT_MOTOR_PORT = 2;
    public static final int SECONDARY_RIGHT_MOTOR_PORT = 3;
    public static final int CONTROLLER_PORT = 0;
//PID Constants
    //PID Constants for pidSetMotor
    public static final double kpDrive = 0.2; 
    public static final double kiDrive = 0.6;
    public static final double kdDrive = 0;
    //PID Constants for Autonomous Command
    public static final double kpAuto = 0.5;
    public static final double kiAuto = 0.5;
    public static final double kdAuto = 0.5;
//Miscellaneous Constants
    public static final double DEADZONE = 0.1;
    public static final double AutoSpeed = 0.2;
    public static final double MAX_NORMAL_SPEED = 0.5;
    public static final int CONTROLLER_LEFT_AXIS = 1;
    public static final int CONTROLLER_RIGHT_AXIS = 5;
    public static final double JOYSTICK_CENTERPOINT = 0.1;
}