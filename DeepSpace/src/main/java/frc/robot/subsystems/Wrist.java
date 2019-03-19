/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Add your docs here.
 */
public class Wrist extends PIDSubsystem {

  public static final double Kp = 0.8;
  public static final double Ki = 0.0001;
  public static final double Kd = 0.0;

  public static final double HATCH_PICKUP = 0.92;
  public static final double BALL_PICKUP = 2.24;
  public static final double HATCH_STOW = 2.24;
  public static final double BALL_DEPLOY = 1.55;
  public static final double STOW = 0.0;

  WPI_VictorSPX intakeMotor = new WPI_VictorSPX(5);
  AnalogInput wristPot = new AnalogInput(0);
  
  public Wrist() {
    // Intert a subsystem name and PID values here
    super("Wrist", Kp, Ki, Kd);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.

    setSetpoint(HATCH_STOW);
    enable();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //wristPot = new AnalogInput(0);
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return wristPot.getVoltage();
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    intakeMotor.set(output);
    //System.out.println("output: " + output);
    
    //System.out.println(wristPot.getVoltage());
  }
}
