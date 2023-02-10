// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ClawCom;
import frc.robot.commands.IndexerCom;
import frc.robot.subsystems.IndexerSub;
import frc.robot.commands.ElevatorCom;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.VorTXController;
import frc.robot.subsystems.PhotonSub;
import frc.robot.subsystems.ClawSub;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.ElevatorSub;

import edu.wpi.first.wpilibj2.command.RunCommand;
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

  public static IndexerSub indexersub = new IndexerSub(13, 11);
  public static IndexerCom indexer = new IndexerCom(indexersub);

  public static ClawSub clawsub = new ClawSub(10);
  public static ClawCom claw = new ClawCom(clawsub);

  public static ElevatorSub elevatorsub = new ElevatorSub(16, 17);
  public static ElevatorCom elevator = new ElevatorCom(elevatorsub);

  public static PhotonSub limelight = new PhotonSub("ur mother");
  public static Gyro gyro = new Gyro();   

//intake = 4 motors
//claw = 1 motors
//elevator = 2 motors
//ramp = 2 motors
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
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

    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */ 
  private void configureButtonBindings() {
    //     // index and shoot
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

        crossButton1.whileTrue(
          new RunCommand(
            claw::closeClaw,
            clawsub
            )
        );

        squareButton1.whileTrue(
          new RunCommand(
            elevator::startMotor,
            elevatorsub
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
    return indexer;
  }
}