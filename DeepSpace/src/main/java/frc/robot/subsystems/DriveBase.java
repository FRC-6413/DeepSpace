/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.commands.ArcadeDriveClass;
import frc.robot.core.JoystickHelpers;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveBase extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //private final WPI_TalonSRX leftMotor = Robot.leftMotor;
  //private final WPI_TalonSRX rightMotor = Robot.rightMotor;

  private final DifferentialDrive robotDrive41 = Robot.driveBaseRobotDrive41;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArcadeDriveClass());
  }

  public void arcadeDriveMethod(XboxController controller) {
    double x = JoystickHelpers.deadZoneInput(controller.getX(Hand.kRight), 0.3);
    double y = JoystickHelpers.deadZoneInput(controller.getY(Hand.kLeft), 0.1) * -1;

    robotDrive41.arcadeDrive(y, x);
  }

  public void stopAll() {
    robotDrive41.arcadeDrive(0, 0);
  }

}
