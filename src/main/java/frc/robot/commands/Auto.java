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
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto extends SequentialCommandGroup {
  List<PathPlannerTrajectory> pathGroup = PathPlanner.loadPathGroup("Straight", new PathConstraints(1, 2));

  private static HashMap<String, Command> eventMap = new HashMap<>();
  
  /** Creates a new Auto. */
  public Auto(DriveSubsystem drivetrain) {

    

    // SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
    //   drivetrain::getPosition, 
    //   drivetrain::resetOdometry, 
    //   Constants.DriveConstants.kDriveKinematics, 
    //   new PIDConstants(1, 0, 0), //ETHANNNNNNN PID PID PID PID PID ENJOYYYYYY
    //   new PIDConstants(1, 0, 0), //SAME HEREREEEEEEEE
    //   drivetrain::setModuleStates, 
    //   eventMap, 
    //   true,
    //   drivetrain
    // );

    //new SwerveAutoBuilder(null, null, null, null, null, eventMap, null)

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      // autoBuilder.fullAuto(pathGroup)
    );
  }
}
