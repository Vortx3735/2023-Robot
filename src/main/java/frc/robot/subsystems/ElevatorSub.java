// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSub extends SubsystemBase {
  CANSparkMax ElevatorNeo1;
  CANSparkMax ElevatorNeo2;
  /** Creates a new ExampleSubsystem. */
  public ElevatorSub(int ID1, int ID2) {
    ElevatorNeo1 = new CANSparkMax(ID1, MotorType.kBrushless);
    ElevatorNeo2 = new CANSparkMax(ID2, MotorType.kBrushless);
    ElevatorNeo2.follow(ElevatorNeo1, true);
  }

  public void move(double percentSpeed){
    ElevatorNeo1.set(percentSpeed);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
