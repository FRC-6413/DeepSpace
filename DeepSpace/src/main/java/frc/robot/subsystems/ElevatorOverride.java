/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.ElevatorOverrideCommand;

/**
 * Add your docs here.
 */
public class ElevatorOverride extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX ElevatorTalon = Robot.Elevator;
  WPI_TalonSRX SecondStageElevatorTalon = Robot.ElevatorSecondary;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ElevatorOverrideCommand());
  }

  public void MoveFirstStageElevator(double speed) {
    /*Robot.ElevatorSubsystem.setDesiredSetpoint(Robot.ElevatorSubsystem.GetCurrentSetpoint() + speed);
    Robot.SecondStageElevatorSubsystem.setDesiredSetpoint(Robot.SecondStageElevatorSubsystem.GetCurrentSetpoint() + speed); */
    ElevatorTalon.set(speed);
  }

  public void MoveSecondStageElevator(double speed) {
    SecondStageElevatorTalon.set(speed);
  }
}
