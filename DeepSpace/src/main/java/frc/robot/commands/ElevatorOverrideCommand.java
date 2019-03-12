/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.core.JoystickHelpers;

public class ElevatorOverrideCommand extends Command {
  private final int SpeedMultiplier = 500;


  public ElevatorOverrideCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);\
    requires(Robot.ElevatorOverrideSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double value = JoystickHelpers.DeadZoneInput(OI.ManualOverride.getY(Hand.kLeft), .2);
    /*if(value != 0) {
      Robot.ElevatorSubsystem.getCurrentCommand().cancel();
    }*/

    Robot.ElevatorOverrideSubsystem.MoveElevator((int)(value * SpeedMultiplier));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
