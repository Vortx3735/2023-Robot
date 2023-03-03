// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSub extends SubsystemBase {
  CANSparkMax ElevatorNeo1;
  CANSparkMax ElevatorNeo2;
  private PIDController hold;
  private int setpoint;
  /** Creates a new ExampleSubsystem. */
  public ElevatorSub(int ID1, int ID2) {
    ElevatorNeo1 = new CANSparkMax(ID1, MotorType.kBrushless);
    ElevatorNeo2 = new CANSparkMax(ID2, MotorType.kBrushless);
    ElevatorNeo2.follow(ElevatorNeo1, true);
    // ElevatorNeo1.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, (float)20);
    ElevatorNeo1.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, (float)0.1);
    hold = new PIDController(0.01, 0, 0);
    setpoint = 0;
  }

  public void move(double percentSpeed){
    ElevatorNeo1.set(percentSpeed);
  }

  public void hold() {
    double pos = ElevatorNeo1.getEncoder().getPosition();
    ElevatorNeo1.set(hold.calculate(pos, setpoint));

    setpoint = (int)(pos);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.println(ElevatorNeo1.getEncoder().getPosition());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
