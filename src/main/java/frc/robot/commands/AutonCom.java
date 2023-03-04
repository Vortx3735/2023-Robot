// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPlanner;

import java.util.HashMap;
import java.util.List;


import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.SwerveAutoBuilder;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;




/** An example command that uses an example subsystem. */
public class AutonCom {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  
  //private static HashMap<String, Command> eventMap = new HashMap<>();
  


  public static Command makeAutoCommand(DriveSubsystem drivetrain, String name) {
    
    List<PathPlannerTrajectory> pathGroup = PathPlanner.loadPathGroup(name, new PathConstraints(4, 3));

    // eventMap.put("Score Cube", new ParallelRaceGroup(
    //   new ParallelCommandGroup(
    //     new RunCommand(
    //       RobotContainer.intake::rev,
    //       RobotContainer.intakesub
    //     ),
    //     new RunCommand(
    //       RobotContainer.indexer::rev,
    //       RobotContainer.indexersub
    //     )
    //   ),
    //   new WaitCommand(2)
    // ));
      

    // eventMap.put("Pick Cube", new ParallelRaceGroup(
    //   new ParallelCommandGroup(
    //     new RunCommand(
    //       RobotContainer.intake::startIntake,
    //       RobotContainer.intakesub
    //     ),
    //     new RunCommand(
    //       RobotContainer.indexer::start,
    //       RobotContainer.indexersub
    //     )
    //   ),
    //   new WaitCommand(2)
    // ));

    // eventMap.put("Pick Cube", new InstantCommand(RobotContainer.intake::startIntake, RobotContainer.intakesub));


    
    // SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
    //   drivetrain::getPose, 
    //   drivetrain::resetOdometry, 
    //   Constants.DriveConstants.kDriveKinematics, 
    //   new PIDConstants(1, 0, 0), //ETHANNNNNNN PID PID PID PID PID ENJOYYYYYY
    //   new PIDConstants(1, 0, 0), //SAME HEREREEEEEEEE
    //   drivetrain::setModuleStates, 
    //   eventMap, 
    //   true,
    //   drivetrain
    // );

    return null;
  }

}
