package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ElevatorOverride;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.SecondStageElevator;
import frc.robot.subsystems.Wrist;

public class Robot extends TimedRobot {
  public static DriveBase DriveBase = new DriveBase();
  public static Elevator ElevatorSubsystem = new Elevator();
  public static SecondStageElevator SecondStageElevatorSubsystem = new SecondStageElevator();
  public static ElevatorOverride ElevatorOverrideSubsystem = new ElevatorOverride();
  public static Intake IntakeSubsystem = new Intake();
  public static Wrist WristSubsystem = new Wrist();
  public static OI Oi = new OI();

  // drive motors
  public static WPI_TalonSRX LeftMotor = new WPI_TalonSRX(RobotMap.LeftMotor);
  public static WPI_VictorSPX LeftFollow = new WPI_VictorSPX(RobotMap.LeftFollow);
  public static WPI_TalonSRX RightMotor= new WPI_TalonSRX(RobotMap.RightMotor);
  public static WPI_VictorSPX RightFollow = new WPI_VictorSPX(RobotMap.RightFollow);

  // arm and elevator
  public static WPI_TalonSRX Elevator;
  public static WPI_TalonSRX ElevatorSecondary;
  public static WPI_VictorSPX Wrist;
  public static WPI_VictorSPX Intake;

  public static AnalogInput ElevatorPot;
  public static AnalogInput WristPot;

  // pneumatics
  public static Compressor compressor;
  public static DoubleSolenoid armExtender;
  public static DoubleSolenoid ejectHatch;


  // elevator
  public static WPI_VictorSPX Climber;


  public static DifferentialDrive DifferentalDrive = new DifferentialDrive(LeftMotor, RightMotor);

  Command _autonomousCommand;
  SendableChooser<Command> _chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    _chooser.setDefaultOption("Default Auto", new ArcadeDrive());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", _chooser);
    CameraServer.getInstance().startAutomaticCapture();

    LeftFollow.follow(LeftMotor);
    RightFollow.follow(RightMotor);

    // elevator and arm
    Elevator = new WPI_TalonSRX(RobotMap.ElevatorMain);
    ElevatorSecondary = new WPI_TalonSRX(RobotMap.ElevatorSecondary);
    Wrist = new WPI_VictorSPX(RobotMap.Wrist);
    Intake = new WPI_VictorSPX(RobotMap.Intake);

    ElevatorPot = new AnalogInput(RobotMap.ElevatorPot);
    //WristPot = new AnalogInput(RobotMap.WristPot);

    // climber
    Climber = new WPI_VictorSPX(RobotMap.Climber);

    compressor = new Compressor(0);
    armExtender = new DoubleSolenoid(0, 1);
    ejectHatch = new DoubleSolenoid(2, 3);
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
    //_autonomousCommand = _chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    /*if (_autonomousCommand != null) {
      _autonomousCommand.cancel();
    }*/
  }

  @Override
  public void autonomousPeriodic() {
    teleopPeriodic();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    /*if (_autonomousCommand != null) {
      _autonomousCommand.cancel();
    }*/
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}