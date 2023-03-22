// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPlanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.texastorque.torquelib.swerve.TorqueSwerveModule2022;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.SwerveAutoBuilder;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
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
  
  public List<PathPlannerTrajectory> pathGroup = PathPlanner.loadPathGroup("Straight", new PathConstraints(4.5, 3));

  public HashMap<String, Command> eventMap = new HashMap<>();
  private static AutoBalance balance = new AutoBalance();
  
  /** Creates a new Auto. */
  public Auto() {

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

  //   eventMap.put("Balance", new ParallelRaceGroup(
  //     new RunCommand(
  //         () -> drivetrain.drive(
  //             new ChassisSpeeds(
  //                 balance.autoBalanceRoutine(),
  //                 0,
  //                 0
  //             )
  //         ),
  //     drivetrain
  //     ),s
  //     new WaitCommand(4)
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

    //new SwerveAutoBuilder(null, null, null, null, null, eventMap, null)

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      //autoBuilder.fullAuto(pathGroup)
    );
  }
}
