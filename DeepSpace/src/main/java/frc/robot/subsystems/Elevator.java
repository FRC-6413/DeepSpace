/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Add your docs here.
 */
public class Elevator extends PIDSubsystem {
  /**
   * Add your docs here.
   */

  private static final double Kp = 0.3;
  private static final double Ki = 0.0;
  private static final double Kd = 0.0;

  WPI_TalonSRX elevatorStage1 = new WPI_TalonSRX(0);
  
  public static int suffering = 1080;

  public static final double STOW = 0.0;
  public static final double SECOND_HATCH = 4;
  public static final double THIRD_HATCH = suffering;


  public Elevator() {
    // Intert a subsystem name and PID values here
    super("Elevator", Kp, Ki, Kd);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
    setSetpoint(STOW);
    enable();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return elevatorStage1.getSelectedSensorPosition();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    elevatorStage1.set(output);
    System.out.println("output: " + output);
  }
}
