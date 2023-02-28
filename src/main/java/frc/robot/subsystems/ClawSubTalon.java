// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClawSubTalon extends SubsystemBase {
  WPI_TalonSRX ClawTalon;
  /** Creates a new ExampleSubsystem. */
  public ClawSubTalon(int ID) {
    ClawTalon = new WPI_TalonSRX(ID);
    ClawTalon.setNeutralMode(NeutralMode.Brake);
  }

  public void move(double percentSpeed){
    ClawTalon.set(TalonSRXControlMode.PercentOutput, percentSpeed);
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