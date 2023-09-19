
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import frc.robot.subsystems.ClawSubTalon;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import frc.robot.subsystems.IntakeSubTalon;



/** An example command that uses an example subsystem. */
public class ClawComTalon extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ClawSubTalon claw;
  private IntakeSubTalon intake;
  

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ClawComTalon(ClawSubTalon inputClaw) {
    claw = inputClaw;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(claw);
  }

  public void open() {
    if(intake.phIntakeDoubleSolenoid.get() == kForward)
    {
      claw.openClaw();
    }
  }

  public void grab() {
    claw.toggleClaw();
  }

  public void close() {
    claw.closeClaw();
  }
  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
