// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClawSubTalon extends SubsystemBase {
  DoubleSolenoid phClawDoubleSolenoid;
  boolean isToggled;
  String state;
  /** Creates a new ExampleSubsystem. */
  public ClawSubTalon() {
    phClawDoubleSolenoid = new DoubleSolenoid(11, PneumaticsModuleType.CTREPCM, 2, 3);
    phClawDoubleSolenoid.set(Value.kOff);
  }

  public void toggleClaw(){
    phClawDoubleSolenoid.toggle();
  }

  public String getState(){
    if (phClawDoubleSolenoid.get() == kForward){
      state = "Closed";
    } else {
      state = "Opened";
    }
    return state;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Claw Pneumatic Open", phClawDoubleSolenoid.get() == kForward);

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
    SmartDashboard.putBoolean("Claw Pneumatic Open", phClawDoubleSolenoid.get() == kForward);
    System.out.println(getState());
  }
}
