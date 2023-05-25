// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.koibots;

import java.util.Map;

import com.koibots.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot
{
    DriveSubsystem driveSubsystem = new DriveSubsystem();
    PS4Controller controller = new PS4Controller(0);

    PowerDistribution pdp = new PowerDistribution(0, ModuleType.kCTRE);

    ShuffleboardTab electricalTab = Shuffleboard.getTab("Telemetry");

    ShuffleboardLayout pdpMainLayout = electricalTab.getLayout("PDP - Main", BuiltInLayouts.kList)
        .withSize(2, 6)
        .withPosition(0, 0) // or 1, 1
        .withProperties(Map.of("Label Position", "Left"));

    ShuffleboardLayout pdpFaultsLayout = electricalTab.getLayout("PDP - Faults", BuiltInLayouts.kList)
        .withSize(2, 6)
        .withPosition(2, 0) // or 3, 1
        .withProperties(Map.of("Label Position", "Left"));

    ShuffleboardLayout rioAndCanLayout = electricalTab.getLayout("RIO & CAN", BuiltInLayouts.kList)
        .withSize(2, 5)
        .withPosition(4, 1) // or 5, 1
        .withProperties(Map.of("Label Position", "left"));

    ShuffleboardLayout rio3vRailLayout = electricalTab.getLayout("RIO - 3V3 Rail", BuiltInLayouts.kList)
        .withSize(1, 2)
        .withPosition(6, 4) // or 7, 5
        .withProperties(Map.of("Label Position", "Left"));

    ShuffleboardLayout rio5vRailLayout = electricalTab.getLayout("RIO - 5V Rail", BuiltInLayouts.kList)
        .withSize(1, 2)
        .withPosition(7, 4) // or 8, 5
        .withProperties(Map.of("Label Position", "Left"));

    ShuffleboardLayout rio6vRailLayout = electricalTab.getLayout("RIO - 6V Rail", BuiltInLayouts.kList)
        .withSize(1, 2)
        .withPosition(8, 4) // or 9, 5
        .withProperties(Map.of("Label Position", "Left"));

    @Override
    public void robotInit() {
        
        rioAndCanLayout.addNumber("CAN: Utilization", () -> RobotController.getCANStatus().percentBusUtilization);
        rioAndCanLayout.addNumber("CAN: Bus Off Count", () -> RobotController.getCANStatus().busOffCount);
        rioAndCanLayout.addNumber("CAN: Errors Received", () -> RobotController.getCANStatus().receiveErrorCount);
        rioAndCanLayout.addNumber("CAN: Errors Transmitted", () -> RobotController.getCANStatus().transmitErrorCount);
        rioAndCanLayout.addNumber("CAN: Tx Full Count", () -> RobotController.getCANStatus().txFullCount);
        rioAndCanLayout.addNumber("RIO: Battery Voltage", RobotController::getBatteryVoltage);
        rioAndCanLayout.addNumber("RIO: Input Current", RobotController::getInputCurrent);
        rioAndCanLayout.addNumber("RIO: Input Voltage", RobotController::getInputVoltage);

        rio3vRailLayout.addNumber("3V3: Current", RobotController::getCurrent3V3);
        rio3vRailLayout.addNumber("3V3: Fault Count", RobotController::getFaultCount3V3);
        rio3vRailLayout.addNumber("3V3: Voltage", RobotController::getVoltage3V3);

        rio5vRailLayout.addNumber("5V: Current", RobotController::getCurrent5V);
        rio5vRailLayout.addNumber("5V: Fault Count", RobotController::getFaultCount5V);
        rio5vRailLayout.addNumber("5V: Voltage", RobotController::getVoltage5V);

        rio6vRailLayout.addNumber("6V: Fault Count", RobotController::getFaultCount6V);
        rio6vRailLayout.addNumber("6V: Current", RobotController::getCurrent6V);
        rio6vRailLayout.addNumber("6V: Voltage", RobotController::getVoltage6V);

        pdpMainLayout.addBoolean("Brownout", () -> pdp.getFaults().Brownout);
        pdpMainLayout.addBoolean("CAN Warning", () -> pdp.getFaults().CanWarning);
        pdpMainLayout.addDouble("Temperature", pdp::getTemperature);
        pdpMainLayout.addDouble("Total Current", pdp::getTotalCurrent);
        pdpMainLayout.addDouble("Total Energy", pdp::getTotalEnergy);
        pdpMainLayout.addDouble("Total Power", pdp::getTotalPower);
        pdpMainLayout.addDouble("Voltage", pdp::getVoltage);
        pdpMainLayout.addNumber("Channel 0 - Current", () -> pdp.getCurrent(0));
        pdpMainLayout.addNumber("Channel 1 - Current", () -> pdp.getCurrent(1));
        pdpMainLayout.addNumber("Channel 2 - Current", () -> pdp.getCurrent(2));
        pdpMainLayout.addNumber("Channel 3 - Current", () -> pdp.getCurrent(3));
        pdpMainLayout.addNumber("Channel 4 - Current", () -> pdp.getCurrent(4));
        pdpMainLayout.addNumber("Channel 5 - Current", () -> pdp.getCurrent(5));
        pdpMainLayout.addNumber("Channel 6 - Current", () -> pdp.getCurrent(6));
        pdpMainLayout.addNumber("Channel 7 - Current", () -> pdp.getCurrent(7));
        pdpMainLayout.addNumber("Channel 8 - Current", () -> pdp.getCurrent(8));
        pdpMainLayout.addNumber("Channel 9 - Current", () -> pdp.getCurrent(9));
        pdpMainLayout.addNumber("Channel 10 - Current", () -> pdp.getCurrent(10));
        pdpMainLayout.addNumber("Channel 11 - Current", () -> pdp.getCurrent(11));
        pdpMainLayout.addNumber("Channel 12 - Current", () -> pdp.getCurrent(12));
        pdpMainLayout.addNumber("Channel 13 - Current", () -> pdp.getCurrent(13));
        pdpMainLayout.addNumber("Channel 14 - Current", () -> pdp.getCurrent(14));
        pdpMainLayout.addNumber("Channel 15 - Current", () -> pdp.getCurrent(15));

        pdpFaultsLayout.addBoolean("Channel 0 - Breaker Fault", () -> pdp.getFaults().Channel0BreakerFault);
        pdpFaultsLayout.addBoolean("Channel 1 - Breaker Fault", () -> pdp.getFaults().Channel1BreakerFault);
        pdpFaultsLayout.addBoolean("Channel 2 - Breaker Fault", () -> pdp.getFaults().Channel2BreakerFault);
        pdpFaultsLayout.addBoolean("Channel 3 - Breaker Fault", () -> pdp.getFaults().Channel3BreakerFault);
        pdpFaultsLayout.addBoolean("Channel 4 - Breaker Fault", () -> pdp.getFaults().Channel4BreakerFault);
        pdpFaultsLayout.addBoolean("Channel 5 - Breaker Fault", () -> pdp.getFaults().Channel5BreakerFault);
        pdpFaultsLayout.addBoolean("Channel 6 - Breaker Fault", () -> pdp.getFaults().Channel6BreakerFault);
        pdpFaultsLayout.addBoolean("Channel 7 - Breaker Fault", () -> pdp.getFaults().Channel7BreakerFault);
        pdpFaultsLayout.addBoolean("Channel 8 - Breaker Fault", () -> pdp.getFaults().Channel8BreakerFault);
        pdpFaultsLayout.addBoolean("Channel 9 - Breaker Fault", () -> pdp.getFaults().Channel9BreakerFault);
        pdpFaultsLayout.addBoolean("Channel 10 - Breaker Fault", () -> pdp.getFaults().Channel10BreakerFault);
        pdpFaultsLayout.addBoolean("Channel 11 - Breaker Fault", () -> pdp.getFaults().Channel11BreakerFault);
        pdpFaultsLayout.addBoolean("Channel 12 - Breaker Fault", () -> pdp.getFaults().Channel12BreakerFault);
        pdpFaultsLayout.addBoolean("Channel 13 - Breaker Fault", () -> pdp.getFaults().Channel13BreakerFault);
        pdpFaultsLayout.addBoolean("Channel 14 - Breaker Fault", () -> pdp.getFaults().Channel14BreakerFault);
        pdpFaultsLayout.addBoolean("Channel 15 - Breaker Fault", () -> pdp.getFaults().Channel15BreakerFault);

    }

    @Override
    public void robotPeriodic()
    {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit()
    {
        Shuffleboard.startRecording();

        driveSubsystem.setDefaultCommand(
            driveSubsystem.new TankDrive(controller::getLeftY, controller::getRightY)
        );
    }

    @Override
    public void teleopExit() {
        CommandScheduler.getInstance().cancelAll();
        CommandScheduler.getInstance().clearComposedCommands();
        Shuffleboard.stopRecording();
    }
}
