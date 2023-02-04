// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexerSub extends SubsystemBase {
  CANSparkMax IndexerNeo1;
  CANSparkMax IndexerNeo2;
  /** Creates a new ExampleSubsystem. */
  public IndexerSub(int ID1, int ID2) {
    IndexerNeo1 = new CANSparkMax(ID1, MotorType.kBrushed);
    IndexerNeo2 = new CANSparkMax(ID2, MotorType.kBrushed);
    IndexerNeo2.follow(IndexerNeo1, true);
  }

  public void move(double percentSpeed){
    IndexerNeo1.set(percentSpeed);
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
