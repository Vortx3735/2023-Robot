// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubTalon extends SubsystemBase {
  TalonSRX IntakeTalon;
  DoubleSolenoid phIntakeDoubleSolenoid;
  boolean isToggled;
  /** Creates a new ExampleSubsystem. */
  public IntakeSubTalon(int ID) {
    IntakeTalon = new TalonSRX(ID);
    phIntakeDoubleSolenoid = new DoubleSolenoid(11, PneumaticsModuleType.CTREPCM, 0, 1);
    phIntakeDoubleSolenoid.set(kReverse);
    isToggled = false;
  }

  public void move(double percentSpeed){
    IntakeTalon.set(TalonSRXControlMode.PercentOutput, percentSpeed);
  }

  public void toggleIntake(){
    phIntakeDoubleSolenoid.toggle();
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
