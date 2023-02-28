// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubTalon extends SubsystemBase {
  TalonSRX IntakeTalon1;
  TalonSRX IntakeTalon2;
  /** Creates a new ExampleSubsystem. */
  public IntakeSubTalon(int ID1, int ID2) {
    IntakeTalon1 = new TalonSRX(ID1);
    IntakeTalon2 = new TalonSRX(ID2);
    IntakeTalon2.follow(IntakeTalon1);
    IntakeTalon2.setInverted(true);
  }

  public void move(double percentSpeed){
    IntakeTalon1.set(TalonSRXControlMode.PercentOutput, percentSpeed);
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
