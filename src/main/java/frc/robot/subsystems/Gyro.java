// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;




import com.kauailabs.navx.frc.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyro extends SubsystemBase {
  /** Creates a new ExampleSubsystem. 
   * @cameraName the name from the photonvision gui
  */
  AHRS gyro;
  public Gyro() {
    gyro = new AHRS();
    gyro.calibrate();
  } 


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println(gyro.getAngle());
    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}