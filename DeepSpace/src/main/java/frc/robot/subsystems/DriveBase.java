package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.commands.ArcadeDrive;
import frc.robot.core.JoystickHelpers;

public class DriveBase extends Subsystem {
  //private final WPI_TalonSRX _leftMotor = Robot.LeftMotor;
  //private final WPI_TalonSRX _rightMotor = Robot.RightMotor;

  private DifferentialDrive _robotDrive;

  @Override
  public void initDefaultCommand() {
    _robotDrive = Robot.DifferentalDrive;
    setDefaultCommand(new ArcadeDrive());
  }

  public void ArcadeDriveMethod(XboxController controller) {
    double x = JoystickHelpers.DeadZoneInput(controller.getX(Hand.kRight), 0.3);
    double y = JoystickHelpers.DeadZoneInput(controller.getY(Hand.kLeft), 0.1) * -1;

    _robotDrive.arcadeDrive(y, x);
  }

  public void StopAll() {
    _robotDrive.arcadeDrive(0, 0);
  }

}
