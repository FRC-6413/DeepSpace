/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AlignToTarget extends Command {

  XboxController driveJoystick;

  double kP = -0.1;
  double min_command = 0.5;

  public AlignToTarget(XboxController controller) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveBase);
    
    driveJoystick = controller;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  //System.out.println("Command Started");
  double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);

  double heading_error = Math.abs(tx) <= 3 ? 0 : tx/25;
  double steering_adjust = 0.0;
  
  steering_adjust = heading_error != 0 ? heading_error : 0;    

  if(steering_adjust != 0 && Math.abs(steering_adjust) < min_command) {
    steering_adjust = steering_adjust < 0 ? -min_command : min_command;
  }


  //left_command += steering_adjust;
  //right_command -= steering_adjust;

  //System.out.println("steering_adjust:" + steering_adjust);
  //System.out.println("joystick: " + driveJoystick.getY(Hand.kLeft));

  Robot.driveBaseRobotDrive41.arcadeDrive(-driveJoystick.getY(Hand.kLeft), steering_adjust);
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
