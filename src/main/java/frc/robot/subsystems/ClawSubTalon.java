// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClawSubTalon extends SubsystemBase {
  DoubleSolenoid phClawDoubleSolenoid;
  boolean isToggled;
  /** Creates a new ExampleSubsystem. */
  public ClawSubTalon() {
    phClawDoubleSolenoid = new DoubleSolenoid(11, PneumaticsModuleType.CTREPCM, 2, 3);
    phClawDoubleSolenoid.set(kForward);
    isToggled = false;
  }

  public void toggleClaw(){
    phClawDoubleSolenoid.toggle();
  }

  public void changeToggle(){
    if (isToggled) {
      isToggled = false;
    } else {
      isToggled = true;
    }
  }

  public boolean getToggled() {
    return isToggled;
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
