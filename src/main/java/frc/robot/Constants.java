package frc.robot;

// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public enum AutomationStrategy {
    ENHANCED,
    MINIMAL
  }

  public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.631;
  public static final double DRIVETRAIN_WHEELBASE_METERS = 0.632;

  /*public static final double BACK_LEFT_MODULE_STEER_OFFSET   = -Math.toRadians(154.775);
  public static final double BACK_RIGHT_MODULE_STEER_OFFSET  = -Math.toRadians(76.2);
  public static final double FRONT_LEFT_MODULE_STEER_OFFSET  = -Math.toRadians(148.8);
  public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(22.412);*/

  public static final double FRONT_LEFT_MODULE_STEER_OFFSET  = -Math.toRadians(145.723);
  public static final double BACK_RIGHT_MODULE_STEER_OFFSET  = -Math.toRadians(291.270);
  public static final double BACK_LEFT_MODULE_STEER_OFFSET   = -Math.toRadians(29.531);
  public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(282.393);

  // public static final double BACK_LEFT_MODULE_STEER_OFFSET   = -Math.toRadians(56.602);
  // public static final double BACK_RIGHT_MODULE_STEER_OFFSET  = -Math.toRadians(79.717);
  // public static final double FRONT_LEFT_MODULE_STEER_OFFSET  = -Math.toRadians(145.107);
  // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(35);
  //152.578
  //249.609
  //323.350
  //197.227

  // was mostly working
  // 142.471
  // 79.717
  // 142.559
  // 19.512

  public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 14;
  public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 17;
  public static final int BACK_LEFT_MODULE_STEER_ENCODER = 16;
  public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 15;

  public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 4;
  public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 3;

  public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 10;
  public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 9;

  public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 6;
  public static final int BACK_LEFT_MODULE_STEER_MOTOR = 5;

  public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 8;
  public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 7;

  // public static SwerveModule frontLeft = new SwerveModule(3, 4, 14, false, false, Constants.ModuleConstants.frontLeftOffset);
  // public static SwerveModule frontRight = new SwerveModule(10, 9, 17, false, false, Constants.ModuleConstants.frontRightOffset);
  // public static SwerveModule backLeft = new SwerveModule(5, 6, 16, true, true,Constants.ModuleConstants.backLeftOffset);
  // public static SwerveModule backRight = new SwerveModule(8, 7, 15, true, true, Constants.ModuleConstants.backRightOffset);

  public static final class DriveConstants {

    public static final int kFrontLeftDriveMotorPort = 0;
    public static final int kRearLeftDriveMotorPort = 2;
    public static final int kFrontRightDriveMotorPort = 4;
    public static final int kRearRightDriveMotorPort = 6;

    public static final int kFrontLeftTurningMotorPort = 1;
    public static final int kRearLeftTurningMotorPort = 3;
    public static final int kFrontRightTurningMotorPort = 5;
    public static final int kRearRightTurningMotorPort = 7;

    // change these ports for cancoders
    public static final int[] kFrontLeftTurningEncoderPorts = new int[] { 0, 1 };
    public static final int[] kRearLeftTurningEncoderPorts = new int[] { 2, 3 };
    public static final int[] kFrontRightTurningEncoderPorts = new int[] { 4, 5 };
    public static final int[] kRearRightTurningEncoderPorts = new int[] { 6, 7 };

    public static final boolean kFrontLeftTurningEncoderReversed = false;
    public static final boolean kRearLeftTurningEncoderReversed = true;
    public static final boolean kFrontRightTurningEncoderReversed = false;
    public static final boolean kRearRightTurningEncoderReversed = true;

    public static final int[] kFrontLeftDriveEncoderPorts = new int[] { 8, 9 };
    public static final int[] kRearLeftDriveEncoderPorts = new int[] { 10, 11 };
    public static final int[] kFrontRightDriveEncoderPorts = new int[] { 12, 13 };
    public static final int[] kRearRightDriveEncoderPorts = new int[] { 14, 15 };

    public static final boolean kFrontLeftDriveEncoderReversed = false;
    public static final boolean kRearLeftDriveEncoderReversed = true;
    public static final boolean kFrontRightDriveEncoderReversed = false;
    public static final boolean kRearRightDriveEncoderReversed = true;

    public static final double kTrackWidth = 0.631;
    // Distance between centers of right and left wheels on robot
    public static final double kWheelBase = 0.623;
    // Distance between front and back wheels on robot
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
        new Translation2d(kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

    public static final boolean kGyroReversed = false;

    // These are example values only - DO NOT USE THESE FOR YOUR OWN ROBOT!
    // These characterization values MUST be determined either experimentally or
    // theoretically
    // for *your* robot's drive.
    // The SysId tool provides a convenient method for obtaining these values for
    // your robot.
    public static final double ksVolts = 1;
    public static final double kvVoltSecondsPerMeter = 0.8;
    public static final double kaVoltSecondsSquaredPerMeter = 0.15;

    public static final double kMaxSpeedMetersPerSecond = 4.5;
  }

  public static final class ModuleConstants {

    public static final double backLeftOffset = 152.578;
    public static final double backRightOffset = 249.609;
    public static final double frontLeftOffset = 323.350;
    public static final double frontRightOffset = 197.227;
    public static final double kMaxModuleAngularSpeedRadiansPerSecond = 2 * Math.PI;
    public static final double kMaxModuleAngularAccelerationRadiansPerSecondSquared = 2 * Math.PI;

    public static final int kEncoderCPR = 1024;
    public static final double kWheelDiameterMeters = 0.15;
    public static final double kDriveEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;

    public static final double kTurningEncoderDistancePerPulse =
        // Assumes the encoders are on a 1:1 reduction with the module shaft.
        (2 * Math.PI) / (double) kEncoderCPR;

    public static final double kPModuleTurningController = 0.04;

    public static final double kPModuleDriveController = 0.00;
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static final class AutoConstants {
    public static final double kMaxSpeedMetersPerSecond = 3;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;
    public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static final double kPXController = 1;
    public static final double kPYController = 1;
    public static final double kPThetaController = 1;

    // Constraint for the motion profiled robot angle controller
    public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(
        kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
  }

}
