package frc.robot.subsystems;



// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.robot.Constants.ModuleConstants;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.opencv.ml.ANN_MLP;

import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.ctre.phoenix.sensors.SensorTimeBase;
import com.ctre.phoenixpro.configs.CANcoderConfiguration;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;


public class SwerveModule {
  private final CANSparkMax m_driveMotor;
  private final CANSparkMax m_turningMotor;
  private final CANCoder cancoder;
  private final CANCoderConfiguration cancoderConfig;
  private final double offset;

  private final RelativeEncoder driveEncoder;


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
      int turningMotorChannel,
      int sparkId,
      int cancoderId,
      boolean driveEncoderReversed,
      boolean turningEncoderReversed,
      double CANCoderOffset) {
    m_driveMotor = new CANSparkMax(sparkId, MotorType.kBrushless);
    m_turningMotor = new CANSparkMax(turningMotorChannel, MotorType.kBrushless);
        offset = CANCoderOffset;
      cancoder = new CANCoder(cancoderId);
      cancoderConfig = new CANCoderConfiguration();
      cancoderConfig.unitString = "rad";
      cancoderConfig.sensorCoefficient = 2 * Math.PI;
      cancoderConfig.sensorTimeBase = SensorTimeBase.PerSecond;
      cancoderConfig.initializationStrategy = SensorInitializationStrategy.BootToAbsolutePosition;
      cancoder.configAllSettings(cancoderConfig);
      

    // m_turningEncoder = new CANCoderWrapper(cancoderId, "rio");
    // m_turningEncoder.configMagnetOffset(CANCoderOffset);

    // Set the distance per pulse for the drive encoder. We can simply use the
    // distance traveled for one rotation of the wheel divided by the encoder
    // resolution.

    // Set whether drive encoder should be reversed or not

    driveEncoder = m_driveMotor.getEncoder();
    driveEncoder.setPositionConversionFactor(ModuleConstants.kDriveEncoderDistancePerPulse);

    // Set the distance (in this case, angle) in radians per pulse for the turning encoder.
    // This is the the angle through an entire rotation (2 * pi) divided by the
    // encoder resolution.
    // m_turningEncoder.setDistancePerPulse(ModuleConstants.kTurningEncoderDistancePerPulse);

    // Set whether turning encoder should be reversed or not
    // m_turningEncoder.setRev(turningEncoderReversed);

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


    driveEncoder.getPosition();


    return new SwerveModuleState(
        driveEncoder.getPosition(), new Rotation2d(coterminal(cancoder.getPosition() - offset))
    );
    
  }

  /**
   * Returns the current position of the module.
   *
   * @return The current position of the module.
   */
  public SwerveModulePosition getPosition() {

    driveEncoder.getPosition();

    return new SwerveModulePosition(
        driveEncoder.getPosition(), new Rotation2d(coterminal(cancoder.getPosition() - offset))
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
        SwerveModuleState.optimize(desiredState, new Rotation2d(coterminal(cancoder.getPosition() - offset)));

    // Calculate the drive output from the drive PID controller.
    final double driveOutput =
        m_drivePIDController.calculate(driveEncoder.getPosition(), state.speedMetersPerSecond);

    // Calculate the turning motor output from the turning PID controller.
    final double turnOutput =
        m_turningPIDController.calculate(coterminal(cancoder.getPosition() - offset), state.angle.getRadians());

    // Calculate the turning motor output from the turning PID controller.
    m_driveMotor.set(driveOutput);
    System.out.println("dwiving at " + driveOutput + "!!!!!");
    m_turningMotor.set(turnOutput);
    System.out.println("turning at " + turnOutput + "!!!");
  }

  /** Zeroes all the SwerveModule encoders. */
  public void resetEncoders() {
    driveEncoder.setPosition(0);
    cancoder.setPosition(0);
  }

  private static double coterminal(double rotation) {
    double coterminal = rotation;
    double full = Math.signum(rotation) * 2 * Math.PI;
    while (coterminal > Math.PI || coterminal < -Math.PI) coterminal -= full;
    return coterminal;
  }
}
