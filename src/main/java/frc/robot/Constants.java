package frc.robot;

// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

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

  

  public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.631;
  public static final double DRIVETRAIN_WHEELBASE_METERS = 0.632;

  /*public static final double BACK_LEFT_MODULE_STEER_OFFSET   = -Math.toRadians(154.775);
  public static final double BACK_RIGHT_MODULE_STEER_OFFSET  = -Math.toRadians(76.2);
  public static final double FRONT_LEFT_MODULE_STEER_OFFSET  = -Math.toRadians(148.8);
  public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(22.412);*/

  public static final double FRONT_LEFT_MODULE_STEER_OFFSET  = -2.557;
  public static final double BACK_RIGHT_MODULE_STEER_OFFSET  = -5.085;
  public static final double BACK_LEFT_MODULE_STEER_OFFSET   = -3.654 + Math.PI;
  public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -4.956;

  // public static final double FRONT_LEFT_MODULE_STEER_OFFSET  = -Math.toRadians(145.723);
  // public static final double BACK_RIGHT_MODULE_STEER_OFFSET  = -Math.toRadians(291.270);
  // public static final double BACK_LEFT_MODULE_STEER_OFFSET   = -Math.toRadians(29.531);
  // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(282.393);

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

}
