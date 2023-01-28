// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexerSub extends SubsystemBase {
  CANSparkMax IndexerNeo;
  /** Creates a new ExampleSubsystem. */
  public IndexerSub(int ID) {
    IndexerNeo = new CANSparkMax(ID, MotorType.kBrushless);
  }

  public void move(double percentSpeed){
    IndexerNeo.set(percentSpeed);
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
