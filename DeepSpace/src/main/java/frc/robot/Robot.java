package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.subsystems.DriveBase;

public class Robot extends TimedRobot {
  public static DriveBase DriveBase = new DriveBase();
  public static OI Oi;

  public static WPI_TalonSRX LeftMotor;
  public static WPI_TalonSRX RightMotor;

  public static DifferentialDrive DifferentalDrive;

  Command _autonomousCommand;
  SendableChooser<Command> _chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    Oi = new OI();
    _chooser.setDefaultOption("Default Auto", new ArcadeDrive());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", _chooser);

    LeftMotor = new WPI_TalonSRX(RobotMap.LeftMotor);
    RightMotor = new WPI_TalonSRX(RobotMap.RightMotor);

    DifferentalDrive = new DifferentialDrive(LeftMotor, RightMotor);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }
 
  @Override
  public void autonomousInit() {
    _autonomousCommand = _chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (_autonomousCommand != null) {
      _autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (_autonomousCommand != null) {
      _autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}