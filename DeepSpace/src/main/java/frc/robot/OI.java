package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commandGroups.CargoGround;
import frc.robot.commandGroups.CargoOne;
import frc.robot.commandGroups.CargoThree;
import frc.robot.commandGroups.CargoTwo;
import frc.robot.commandGroups.HatchGround;
import frc.robot.commandGroups.HatchOne;
import frc.robot.commandGroups.HatchThree;
import frc.robot.commandGroups.HatchTwo;
import frc.robot.commandGroups.Park;
import frc.robot.commands.AlignToTarget;
import frc.robot.commands.EjectCargo;
import frc.robot.commands.EjectHatch;
import frc.robot.commands.IntakeCargo;
import frc.robot.commands.SetIntakePosition;
import frc.robot.commands.SetPneumaticPosition;
import frc.robot.commands.StopIntake;
import frc.robot.subsystems.Wrist;

public class OI {
  // Driver Joystick
  public XboxController DriveJoystick;
  public static JoystickButton AlignToTargetButton;
  public static JoystickButton PneumaticsIn;
  public static JoystickButton PneumaticsOut;

  // Button Board
  public static Joystick ButtonBoard;
  public static JoystickButton HatchFloor;
  public static JoystickButton HatchOne;
  public static JoystickButton HatchTwo;
  public static JoystickButton HatchThree;
  public static JoystickButton CargoFloor;
  public static JoystickButton CargoOne;
  public static JoystickButton CargoTwo;
  public static JoystickButton CargoThree;
  public static JoystickButton Park;
  public static JoystickButton IntakeIn;
  public static JoystickButton IntakeOut;

  //Elevator Override Controls
  public static XboxController ManualOverride;
  public static JoystickButton IntakeInOverride;
  public static JoystickButton IntakeOutOverride;


  public OI() {
    // Driver controller
    DriveJoystick = new XboxController(RobotMap.DriveController);

    //Elevator controller
    ManualOverride = new XboxController(RobotMap.ElevatorController);

    IntakeInOverride = new JoystickButton(ManualOverride, 5);
    IntakeInOverride.whenPressed(new SetPneumaticPosition(false));

    IntakeOutOverride = new JoystickButton(ManualOverride, 6);
    IntakeOutOverride.whenPressed(new SetPneumaticPosition(true));

    //DriveJoystick;
    AlignToTargetButton = new JoystickButton(DriveJoystick, RobotMap.AlignToTarget);
    //AlignToTargetButton.whileHeld(new SetIntakePosition(Wrist.HATCH_PICKUP));
    AlignToTargetButton.whileHeld(new AlignToTarget(DriveJoystick));

    PneumaticsIn = new JoystickButton(DriveJoystick, 5);
    PneumaticsIn.whenPressed(new EjectHatch(true));

    PneumaticsOut = new JoystickButton(DriveJoystick, 6);
    PneumaticsOut.whenPressed(new EjectHatch(false));

    // Button Board
    ButtonBoard = new Joystick(RobotMap.ButtonBoard);

    HatchFloor = new JoystickButton(ButtonBoard, RobotMap.HatchFloor);
    HatchFloor.whenPressed(new HatchGround());

    HatchOne = new JoystickButton(ButtonBoard, RobotMap.HatchOne);
    HatchOne.whenPressed(new HatchOne());

    HatchTwo = new JoystickButton(ButtonBoard, RobotMap.HatchTwo);
    HatchTwo.whenPressed(new HatchTwo());

    HatchThree = new JoystickButton(ButtonBoard, RobotMap.HatchThree);
    HatchThree.whenPressed(new HatchThree());

    CargoFloor = new JoystickButton(ButtonBoard, RobotMap.CargoFloor);
    CargoFloor.whenPressed(new CargoGround());

    CargoOne = new JoystickButton(ButtonBoard, RobotMap.CargoOne);
    CargoOne.whenPressed(new CargoOne());

    CargoTwo = new JoystickButton(ButtonBoard, RobotMap.CargoTwo);
    CargoTwo.whenPressed(new CargoTwo());

    CargoThree = new JoystickButton(ButtonBoard, RobotMap.CargoThree);
    CargoThree.whenPressed(new CargoThree());

    Park = new JoystickButton(ButtonBoard, RobotMap.Park);
    Park.whenPressed(new Park());

    IntakeIn = new JoystickButton(ButtonBoard, RobotMap.IntakeIn);
    IntakeIn.whileHeld(new IntakeCargo());
    IntakeIn.whenReleased(new StopIntake());

    IntakeOut = new JoystickButton(ButtonBoard, RobotMap.IntakeOut);
    IntakeOut.whileHeld(new EjectCargo());
    IntakeOut.whenReleased(new StopIntake());

    //ManualOverride = new JoystickButton(ButtonBoard, RobotMap.Joystick);
  }  
}