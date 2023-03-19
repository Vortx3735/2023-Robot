// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// ask ethan what this is
// import javax.print.attribute.standard.JobHoldUntil;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import static frc.robot.subsystems.DriveSubsystem.*;
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
    
    //  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
    
    public static VorTXController con2 = new VorTXController(1);

    public static IntakeSubTalon intakesub = new IntakeSubTalon(13);
    public static IntakeComTalon intake = new IntakeComTalon(intakesub);
    
    
    // public static IntakeSubTalon intakesub = new IntakeSubTalon(25);
    // public static IntakeComTalon intake = new IntakeComTalon(intakesub);
    
    public static IndexerSubTalon indexersub = new IndexerSubTalon(26);
    public static IndexerComTalon indexer = new IndexerComTalon(indexersub);
    

    public static ClawSubTalon clawsub = new ClawSubTalon();
    public static ClawComTalon claw = new ClawComTalon(clawsub);

    //public static ElevatorSub elevatorsub = new ElevatorSub(1, 2);
    //public static ElevatorCom elevator = new ElevatorCom(elevatorsub);

    // public static PhotonSub limelight = new PhotonSub("ur mother");
    public static Gyro gyro = new Gyro();
    public static Compressor phCompressor = new Compressor(11, PneumaticsModuleType.CTREPCM);

    // public static Command basicDoubleScoreTopAuto = AutonCom.makeAutoCommand(swerve, "Basic Double Score Top", intakesub, intake);

    
    public static DriveSubsystem swerve = new DriveSubsystem();

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        phCompressor.enableDigital();

        // Configure the button bindings
        configureButtonBindings();

        indexersub.setDefaultCommand(
            new RunCommand(
                indexer::stop,
                indexersub
            ));
        intakesub.setDefaultCommand(
            new RunCommand(
                intake::stopIntake,
                intakesub
            )
        );
        
        /*
        elevatorsub.setDefaultCommand(
            new RunCommand(
                elevator::hold,
                elevatorsub
            )
        );
        */

    

        swerve.setDefaultCommand(
            // The left stick controls translation of the robot.
            // Turning is controlled by the X axis of the right stick.
            // R1 changes speed to triple
            // RS changes speed to normal
            // LS changes speed to half
            new RunCommand(
                () -> swerve.drive(
                    ChassisSpeeds.fromFieldRelativeSpeeds(
                        con1.getLeftY()*speedScale,
                        con1.getLeftX()*speedScale,
                        con1.getRightX()*3*speedScale, 
                        DriveSubsystem.getGyroscopeRotation())
                    // new ChassisSpeeds(
                    //     con1.getLeftY()*speedScale,
                    //     con1.getLeftX()*speedScale,
                    //     con1.getRightX()*3*speedScale)),
                    ),
                    swerve
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

        // //  index and intake
        con2.circle.whileTrue(
            new ParallelCommandGroup(
                new RunCommand(
                    indexer::start,
                    indexersub
                ),
                new RunCommand(
                    intake::startIntake,
                    intakesub
                )
            )
        );

        // // outtake
        con2.cross.whileTrue(
            new ParallelCommandGroup(
                new RunCommand(
                    indexer::rev,
                    indexersub
                ),
                new RunCommand(
                    intake::rev,
                    intakesub
                )
            )
        );

        // con1.triangle.whileTrue(
        //     new RunCommand(
        //         elevator::startMotor,
        //         elevatorsub
        //     )
        // );

        //  con1.square.whileTrue( 
        //      new RunCommand(
        //          elevator::reverseMotor,
        //          elevatorsub)
        //  );

        // R1 changes speed to quad
        // RS changes speed to normal
        // LS changes speed to half
        con1.r1.onTrue(
            new InstantCommand(
                () -> {
                    swerve.changeSpeed(4);
                },
                swerve
            )
        );
        con1.rs.onTrue(
            new InstantCommand(
                () -> {
                    swerve.changeSpeed(1);
                },
                swerve
            )
        );
        con1.ls.onTrue(
            new InstantCommand(
                () -> {
                    swerve.changeSpeed(0.5);
                },
                swerve
            )
        );

        con1.l1.onTrue(
           new InstantCommand(
                swerve::zeroGyroscope,
                swerve
           ) 
        );

        con2.l1.onTrue(
            new InstantCommand(
                intake::push,
                intakesub
            )
        );

        con2.r1.onTrue(
            new InstantCommand(
                claw::grab,
                clawsub
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

        //HARD-CODED AUTON, DON'T DELETE JUST COMMENT
        return new SequentialCommandGroup(
            new ParallelRaceGroup(
                new RunCommand(
                    () -> swerve.drive(
                        new ChassisSpeeds(
                            -2,
                            0,
                            0
                        )
                    ),
                swerve
                ),
                new WaitCommand(0.35)
            ),
            new ParallelRaceGroup(
                new RunCommand(
                    () -> swerve.drive(
                        new ChassisSpeeds(
                            1,
                            0,
                            0
                        )
                    ),
                swerve
                ),
                new WaitCommand(4)
            )
        );
    }
}