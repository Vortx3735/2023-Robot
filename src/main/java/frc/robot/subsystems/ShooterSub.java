// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
//  import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
//  import com.ctre.phoenix.motorcontrol.can.TalonFX;
//  import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSub extends SubsystemBase {
  TalonSRX miniCim;
  VictorSPX motor775;

  /** Creates a new ExampleSubsystem. */
  public ShooterSub(int motor775ID, int miniCimID) {
    motor775 = new VictorSPX(motor775ID); //victor
    miniCim = new TalonSRX(miniCimID); //srx

    motor775.setNeutralMode(NeutralMode.Coast);
    miniCim.setNeutralMode(NeutralMode.Coast);

    miniCim.follow(motor775);
    motor775.setInverted(false);
  }

  public void move(double percentSpeed){
    motor775.set(VictorSPXControlMode.PercentOutput, percentSpeed);
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
