package frc.robot.subsystems;

import com.ctre.phoenixpro.hardware.CANcoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder;

import edu.wpi.first.hal.EncoderJNI;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants.ModuleConstants;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.jni.CANSparkMaxJNI;


public class SwerveModule {
  private final Spark m_driveMotor;
  private final Spark m_turningMotor;

  private final Encoder m_driveEncoder;
  private final CANCoderWrapper m_turningEncoder;

  private final CANSparkMax driveEncoder;

  private final PIDController m_drivePIDController =
      new PIDController(ModuleConstants.kPModuleDriveController, 0, 0);

  // Using a TrapezoidProfile PIDController to allow for smooth turning
  private final ProfiledPIDController m_turningPIDController =
      new ProfiledPIDController(
          ModuleConstants.kPModuleTurningController,
          0,
          0,
          new TrapezoidProfile.Constraints(
              ModuleConstants.kMaxModuleAngularSpeedRadiansPerSecond,
              ModuleConstants.kMaxModuleAngularAccelerationRadiansPerSecondSquared
            )
        );

  /**
   * Constructs a SwerveModule.
   *
   * @param driveMotorChannel The channel of the drive motor.
   * @param turningMotorChannel The channel of the turning motor.
   * @param driveEncoderChannels The channels of the drive encoder.
   * @param turningEncoderChannels The channels of the turning encoder.
   * @param driveEncoderReversed Whether the drive encoder is reversed.
   * @param turningEncoderReversed Whether the turning encoder is reversed.
   */
  public SwerveModule(
      int driveMotorChannel,
      int turningMotorChannel,
      int[] driveEncoderChannels,
      int[] turningEncoderChannels,
      boolean driveEncoderReversed,
      boolean turningEncoderReversed) {
    m_driveMotor = new Spark(driveMotorChannel);
    m_turningMotor = new Spark(turningMotorChannel);

    driveEncoder = new CANSparkMax(9, MotorType.kBrushless);

    m_driveEncoder = new Encoder(driveEncoderChannels[0], driveEncoderChannels[1]);

    m_turningEncoder = new CANCoderWrapper(turningEncoderChannels[0], "rio");

    // Set the distance per pulse for the drive encoder. We can simply use the
    // distance traveled for one rotation of the wheel divided by the encoder
    // resolution.
    m_driveEncoder.setDistancePerPulse(ModuleConstants.kDriveEncoderDistancePerPulse);

    // Set whether drive encoder should be reversed or not
    m_driveEncoder.setReverseDirection(driveEncoderReversed);

    driveEncoder.setInverted(driveEncoderReversed);
    // driveEncoder.setPositionConversionFactor(ModuleConstants.kDriveEncoderDistancePerPulse);
    // new SparkMaxRelativeEncoder.setPositionConversionFactor(ModuleConstants.kDriveEncoderDistancePerPulse);

    // Set the distance (in this case, angle) in radians per pulse for the turning encoder.
    // This is the the angle through an entire rotation (2 * pi) divided by the
    // encoder resolution.
    m_turningEncoder.setDistancePerPulse(ModuleConstants.kTurningEncoderDistancePerPulse);

    // Set whether turning encoder should be reversed or not
    m_turningEncoder.setRev(turningEncoderReversed);

    // Limit the PID Controller's input range between -pi and pi and set the input
    // to be continuous.
    m_turningPIDController.enableContinuousInput(-Math.PI, Math.PI);
  }

  public int getInverted(int val) {
    return -val;
  }

  /**
   * Returns the current state of the module.
   *
   * @return The current state of the module.
   */
  public SwerveModuleState getState() {


    driveEncoder.get();


    return new SwerveModuleState(
        m_driveEncoder.getRate(), new Rotation2d(m_turningEncoder.getDistance())
    );
    
  }

  /**
   * Returns the current position of the module.
   *
   * @return The current position of the module.
   */
  public SwerveModulePosition getPosition() {

    driveEncoder.getEncoder().getPosition();

    return new SwerveModulePosition(
        m_driveEncoder.getDistance(), new Rotation2d(m_turningEncoder.getDistance())
    );
  }

  /**
   * Sets the desired state for the module.
   *
   * @param desiredState Desired state with speed and angle.
   */
  public void setDesiredState(SwerveModuleState desiredState) {
    // Optimize the reference state to avoid spinning further than 90 degrees
    SwerveModuleState state =
        SwerveModuleState.optimize(desiredState, new Rotation2d(m_turningEncoder.getDistance()));

    // Calculate the drive output from the drive PID controller.
    final double driveOutput =
        m_drivePIDController.calculate(m_driveEncoder.getRate(), state.speedMetersPerSecond);

    // Calculate the turning motor output from the turning PID controller.
    final double turnOutput =
        m_turningPIDController.calculate(m_turningEncoder.getDistance(), state.angle.getRadians());

    // Calculate the turning motor output from the turning PID controller.
    m_driveMotor.set(driveOutput);
    m_turningMotor.set(turnOutput);
  }

  /** Zeroes all the SwerveModule encoders. */
  public void resetEncoders() {
    m_driveEncoder.reset();
    m_turningEncoder.reset();
  }
}
