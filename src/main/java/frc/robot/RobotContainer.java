// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// ask ethan what this is
// import javax.print.attribute.standard.JobHoldUntil;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

// import frc.robot.commands.*;
import frc.robot.commands.ClawCom;
import frc.robot.commands.IndexerCom;
import frc.robot.commands.ElevatorCom;
import frc.robot.commands.IntakeCom;
import frc.robot.commands.TeleopSwerve;

// import frc.robot.subsystems.*;
import frc.robot.subsystems.VorTXController;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.IndexerSub;
import frc.robot.subsystems.PhotonSub;
import frc.robot.subsystems.ClawSub;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.Swerve;

import frc.robot.autos.exampleAuto;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public static VorTXController con1 = new VorTXController(0);
  public static JoystickButton circleButton1 = con1.circle;
  public static JoystickButton triangleButton1 = con1.triangle;
  public static JoystickButton crossButton1 = con1.cross;
  public static JoystickButton squareButton1 = con1.square;

  public static VorTXController con2 = new VorTXController(1);
  public static JoystickButton circleButton2 = con2.circle;
  public static JoystickButton triangleButton2 = con2.triangle;
  public static JoystickButton crossButton2 = con2.cross;
  public static JoystickButton squareButton2 = con2.square;
  public static JoystickButton leftBumper2 = con2.l2;
  
  private final double translationAxis = con2.getLeftY();
  private final double strafeAxis = con2.getLeftX(); 
  private final double rotationAxis = con2.getRightX();
  
  //triangle on con2
  private final JoystickButton zeroGyro = new JoystickButton(con2, 4);
  //right bumper on con2
  private final JoystickButton robotCentric = new JoystickButton(con2, 5);

  public static IntakeSub intakesub = new IntakeSub(1, 2);
  public static IntakeCom intake = new IntakeCom(intakesub);

  //indexer uses same motor as intake
  //will have to remove all of the indexer in code
  //reorder Spark ids here once removed
  public static IndexerSub indexersub = new IndexerSub(9);
  public static IndexerCom indexer = new IndexerCom(indexersub);

  public static ClawSub clawsub = new ClawSub(4);
  public static ClawCom claw = new ClawCom(clawsub);

  public static ElevatorSub elevatorsub = new ElevatorSub(5, 6);
  public static ElevatorCom elevator = new ElevatorCom(elevatorsub);

  public static PhotonSub limelight = new PhotonSub("ur mother");
  public static Gyro gyro = new Gyro();   
  public static Swerve s_Swerve = new Swerve();

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
    s_Swerve.setDefaultCommand(
      new TeleopSwerve(
          s_Swerve,
          () -> -con2.getRawAxis((int) translationAxis),
          () -> -con2.getRawAxis((int) strafeAxis),
          () -> -con2.getRawAxis((int) rotationAxis),
          () -> robotCentric.getAsBoolean()
        )
    );
    // Configure the button bindings
    configureButtonBindings();

    indexersub.setDefaultCommand(
      new RunCommand(
        indexer::moveMotor,
        indexersub

      )
    );

    clawsub.setDefaultCommand(
      new RunCommand(
        claw::stoppedClaw,
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
        intake::stopMotor,
        intakesub)
    );
    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */ 
  private void configureButtonBindings() {
    zeroGyro.whileTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));

    //  index and shoot
        circleButton1.whileTrue(
          new RunCommand(
            indexer::startMotor,
            indexersub   
          )
        );
        // FOR CLAW IMPLEMENT A STOP-POINT
        triangleButton1.whileTrue(
          new RunCommand(
            claw::openClaw,
            clawsub
          )
        );

        squareButton1.whileTrue(
          new RunCommand(
            elevator::startMotor,
            elevatorsub
          )
        );

        circleButton2.whileTrue(
          new RunCommand(
            intake::startMotor,
            intakesub)
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
    return new exampleAuto(s_Swerve);
  }
}