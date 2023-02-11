// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSub extends SubsystemBase {
  CANSparkMax IntakeNeo1;
  CANSparkMax IntakeNeo2;
  /** Creates a new ExampleSubsystem. */
  public IntakeSub(int ID1, int ID2) {
    IntakeNeo1 = new CANSparkMax(ID1, MotorType.kBrushless);
    IntakeNeo2 = new CANSparkMax(ID2, MotorType.kBrushless);
    IntakeNeo2.follow(IntakeNeo1, true);
  }

  public void move(double percentSpeed){
    IntakeNeo1.set(percentSpeed);
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
