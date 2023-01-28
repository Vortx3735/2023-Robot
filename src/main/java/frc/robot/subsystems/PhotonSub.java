// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;




import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PhotonSub extends SubsystemBase {
  PhotonCamera limelight;
  /** Creates a new ExampleSubsystem. */
  public PhotonSub() {
    limelight = new PhotonCamera("photonvision");
  } 

  public PhotonPipelineResult getTargets() {
    return limelight.getLatestResult();
  }
  
  public PhotonTrackedTarget getBestTarget(PhotonPipelineResult pipelineResult) {
    return pipelineResult.getBestTarget();
  }

  public void printStuff(PhotonTrackedTarget trackedTarget) {
    System.out.println("hewwo it worwking vewwy weww" + trackedTarget.getFiducialId());
  }

  // public boolean hasTargets(PhotonPipelineResult piplineResult) {
  //   return piplineResult.hasTargets();
  // }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    PortForwarder.add(5800, "photonvision.local", 5800);
    PhotonPipelineResult pipelineResult = getTargets();
    PhotonTrackedTarget trackedTarget = getBestTarget(pipelineResult);
    if (trackedTarget != null){
      printStuff(trackedTarget);
    } else {
      System.out.println("ur baddddddd LLLLLLLLLLLLLLLll get fricked");
    }
    
      

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
