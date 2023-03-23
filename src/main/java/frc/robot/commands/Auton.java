package frc.robot.commands;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.SwerveAutoBuilder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Auton {
  private static HashMap<String, Command> eventMap;
  private static SwerveAutoBuilder autoBuilder;

  public static PathPlannerTrajectory straightPath;
  
  public static List<PathPlannerTrajectory> straightPathGroup; 
  public static Command straightPathCommand;  

  public static void init() {
    eventMap = buildEventMap();

    straightPathGroup = PathPlanner.loadPathGroup("Straight", new PathConstraints(4.5, 3));
    
    autoBuilder = new SwerveAutoBuilder(
        RobotContainer.swerve::getPose, 
        RobotContainer.swerve::resetPose, 
        new PIDConstants(5.0, 0.0, 0.0), 
        new PIDConstants(0.5, 0.0, 0.0), 
        RobotContainer.swerve::drive, 
        eventMap,
        RobotContainer.swerve
    );

    eventMap.put("Pick Cube", pickCube());
    eventMap.put("Score Cube", scoreCube());
    straightPathCommand = autoBuilder.fullAuto(straightPathGroup);
  }

    public static Command pickCube() {
        return new ParallelRaceGroup(
        new ParallelCommandGroup(
            new RunCommand(
                RobotContainer.intake::startIntake,
                RobotContainer.intakesub
            ),
            new RunCommand(
                RobotContainer.indexer::start,
                RobotContainer.indexersub
            )
        ),
        new WaitCommand(2)
        );
    }

  public static Command scoreCube() {
    return new ParallelRaceGroup(
        new ParallelCommandGroup(
          new RunCommand(
            RobotContainer.intake::rev,
            RobotContainer.intakesub
          ),
          new RunCommand(
            RobotContainer.indexer::rev,
            RobotContainer.indexersub
          )
        ),
        new WaitCommand(2)
    );
  }

  public static Command straightPath() {
    return straightPathCommand;
  }

  public static CommandBase none() {
    return Commands.none();
  }

  private static HashMap<String, Command> buildEventMap() {
    return new HashMap<>(
        Map.ofEntries(
            Map.entry("event1", Commands.print("event1")),
            Map.entry("event2", Commands.print("event2"))));
  }
}