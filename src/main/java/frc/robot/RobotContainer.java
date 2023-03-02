// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// ask ethan what this is
// import javax.print.attribute.standard.JobHoldUntil;

import edu.wpi.first.wpilibj.GenericHID;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
//import frc.robot.Constants.OIConstants;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public static VorTXController con1 = new VorTXController(0);

  private final DriveSubsystem m_robotDrive = new DriveSubsystem();

  public static VorTXController con2 = new VorTXController(1);


  public static IntakeSubTalon intakesub = new IntakeSubTalon(1, 2);
  public static IntakeComTalon intake = new IntakeComTalon(intakesub);

  //indexer uses same motor as intake
  //will have to remove all of the indexer in code
  //reorder Spark ids here once removed
  public static IndexerSub indexersub = new IndexerSub(3);
  public static IndexerCom indexer = new IndexerCom(indexersub);

  // public static ClawSub clawsub = new ClawSub(4);
  // public static ClawCom claw = new ClawCom(clawsub);

  public static ClawSubTalon clawsub = new ClawSubTalon(0);
  public static ClawComTalon claw = new ClawComTalon(clawsub);

  public static ElevatorSub elevatorsub = new ElevatorSub(5, 6);
  public static ElevatorCom elevator = new ElevatorCom(elevatorsub);

  public static PhotonSub limelight = new PhotonSub("ur mother");
  public static Gyro gyro = new Gyro();
  public static DriveSubsystem swerve = new DriveSubsystem();

  public static Command basicDoubleScoreTopAuto = AutonCom.makeAutoCommand(swerve, "Basic Double Score Top", intakesub, intake);

  //intake = 2 motors
  //indexer = 0 motors (same motor as intake)
  //claw = 1 motors
  //elevator = 2 motors
  //ramp = 2 motors
  //swerve = 8 motors
  //add 1-2 maybe
  //current total = 15 motors

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();

    indexersub.setDefaultCommand(
        new RunCommand(
            indexer::moveMotor,
            indexersub
        ));

    clawsub.setDefaultCommand(
        new RunCommand(
            claw::stopClaw,
            clawsub
        )
    );

    elevatorsub.setDefaultCommand(
        new RunCommand(
            elevator::stopMotor,
            elevatorsub
        )
    );

    intakesub.setDefaultCommand(
        new RunCommand(
            intake::stopIntake,
            intakesub
        )
    );

    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                con1.getLeftY(),
                con1.getLeftX(),
                con1.getRightX(),
                false),
            m_robotDrive
        )
    );

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    //  index and shoot
    con2.circle.whileTrue(
        new RunCommand(
            indexer::startMotor,
            indexersub
        )
    );
    // FOR CLAW IMPLEMENT A STOP-POINT
    con2.triangle.whileTrue(
        new SequentialCommandGroup(
            new ParallelRaceGroup (
                new RunCommand(
                claw::openClaw,
                clawsub),
                new WaitCommand(0.5)
            ),
            new RunCommand(
                claw::stopClaw, clawsub
            )
        )
    );

    con2.square.whileTrue(
        new RunCommand(
            elevator::startMotor,
            elevatorsub
        )
    );

    con1.circle.whileTrue(
        new RunCommand(
            intake::startIntake,
            intakesub
        )
    );
  }

  // command group
  //  c1
  //  c2

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return basicDoubleScoreTopAuto;
  }
}