// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import frc.robot.subsystems.ElevatorSub;
import edu.wpi.first.wpilibj2.command.CommandBase;



/** An example command that uses an example subsystem. */
public class ElevatorCom extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ElevatorSub elevate;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ElevatorCom(ElevatorSub inputElevate) {
    elevate = inputElevate;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(elevate);
  }

  public void startMotor() {
    elevate.move(0.3);
  }

  public void stopMotor() {
    elevate.move(0);
  }

  public void reverseMotor() {
    elevate.move(-0.3);
  }

  public void hold() {
    elevate.hold();
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
