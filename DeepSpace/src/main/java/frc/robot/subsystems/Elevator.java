/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.Robot;

public class Elevator extends PIDSubsystem {
  private static final double Kp = 0.0009;
  private static final double Ki = 0;
  private static final double Kd = 0.0005;

  private int _currentDesiredSetpoint;

  WPI_TalonSRX elevatorStage = Robot.Elevator;

  public Elevator() {
    super("Elevator", Kp, Ki, Kd);
    
    _currentDesiredSetpoint = 0;

    elevatorStage.setSelectedSensorPosition(0);
    setSetpoint(0);    
    getPIDController().setContinuous(false);
    setAbsoluteTolerance(.05);

    enable();
  }

  @Override
  public void initDefaultCommand() {
  }

  @Override
  protected double returnPIDInput() {
    return (double)elevatorStage.getSelectedSensorPosition();
  }

  @Override
  protected void usePIDOutput(double output) {    
    elevatorStage.set(output);
  }

  public void setDesiredSetpoint(int setpoint) {
    _currentDesiredSetpoint = setpoint;
    setSetpoint(setpoint);
  }

  public int GetCurrentSetpoint() {
    return _currentDesiredSetpoint;
  }
}
