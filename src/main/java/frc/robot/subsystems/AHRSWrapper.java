// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class AHRSWrapper extends SubsystemBase{
  /** Creates a new ExampleSubsystem. 
   * @cameraName the name from the photonvision gui
  */
  AHRS gyro;
  public AHRSWrapper() {
    gyro = new AHRS();
    gyro.calibrate();
    gyro.enableBoardlevelYawReset(false);
  } 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.println(gyro.getYaw());  
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
    SmartDashboard.putNumber("Orientation Degrees", gyro.getYaw());
  }
}
