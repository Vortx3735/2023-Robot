
/**
 * This class represents the Swerve subsystem of the robot, which controls the movement and positioning of the swerve modules.
 * 
 * These comments were written by ChatGPT because idfk how to code swerve stfu
 * :D
 */
package frc.robot.subsystems;

import com.kauailabs.navx.frc.*;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Swerve extends SubsystemBase {
  /** The gyro object used to obtain the current yaw of the robot. */
  public AHRS gyro;

  /** The object used for odometry calculation. */
  private SwerveDriveOdometry swerveOdometry;

  /** An array of SwerveModule objects representing the swerve modules on the robot. */
  private SwerveModule[] mSwerveMods;

  /** The Field2d object used to display the robot position on the SmartDashboard. */
  private Field2d field;

  /**
   * Constructor for the Swerve subsystem.
   */
  public Swerve() {
    gyro = new AHRS();
    gyro.calibrate();
    zeroGyro();

    // Initialize the swerve odometry object with the current yaw and module states.
    swerveOdometry = new SwerveDriveOdometry(Constants.Swerve.swerveKinematics, getYaw(), getStates());

    // Initialize the array of SwerveModule objects.
    mSwerveMods =
        new SwerveModule[] {
          new SwerveModule(0, Constants.Swerve.Mod0.constants),
          new SwerveModule(1, Constants.Swerve.Mod1.constants),
          new SwerveModule(2, Constants.Swerve.Mod2.constants),
          new SwerveModule(3, Constants.Swerve.Mod3.constants)
        };

    // Initialize the Field2d object and add it to the SmartDashboard.
    field = new Field2d();
    SmartDashboard.putData("Field", field);
  }

  /**
   * This method controls the movement of the robot by specifying the translation and rotation of the robot.
   * @param translation The desired translation of the robot.
   * @param rotation The desired rotation of the robot.
   * @param fieldRelative A boolean indicating whether the movement should be field-relative or robot-relative.
   * @param isOpenLoop A boolean indicating whether the movement should be performed in open loop or closed loop.
   */
  public void drive(
      Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop) {
    // Convert the translation and rotation to swerve module states.
    SwerveModuleState[] swerveModuleStates =
        Constants.Swerve.swerveKinematics.toSwerveModuleStates(
            fieldRelative
                ? ChassisSpeeds.fromFieldRelativeSpeeds(
                    translation.getX(), translation.getY(), rotation, getYaw())
                : new ChassisSpeeds(translation.getX(), translation.getY(), rotation));
    
    // Limit the swerve module speeds to the maximum allowable speed.
    SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.Swerve.maxSpeed);
}

  

  /**
   * Drives the robot by calculating the desired state of each swerve module based on the provided
   * translation and rotation speeds. The translation and rotation can be specified in a field-relative
   * or robot-relative manner.
   *
   * @param translation The translation speed, as a Translation2d object.
   * @param rotation The rotation speed, as a double in radians per second.
   * @param fieldRelative If true, the translation and rotation will be interpreted as field-relative
   *                      values. If false, they will be interpreted as robot-relative values.
   * @param isOpenLoop If true, the swerve modules will be set to open loop control mode. If false,
   *                  the swerve modules will be set to closed loop control mode.
   */
  
  /* Used by SwerveControllerCommand in Auto */
  public void setModuleStates(SwerveModuleState[] desiredStates) {
    SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, Constants.Swerve.maxSpeed);

    for (SwerveModule mod : mSwerveMods) {
      mod.setDesiredState(desiredStates[mod.moduleNumber], false);
    }
  }

  /**
   * Sets the desired state of each swerve module.
   *
   * @param desiredStates The desired state of each swerve module, as an array of SwerveModuleState objects.
   */
  
  public Pose2d getPose() {
    return swerveOdometry.getPoseMeters();
  }

  /**
   * Returns the current pose of the robot, as a Pose2d object.
   *
   * @return The current pose of the robot.
   */

  //module positions???
  public void resetOdometry(Pose2d pose) {
    swerveOdometry.resetPosition(getYaw(), getStates(), pose);
  }

  /**
   * Resets the robot's odometry to the provided pose.
   *
   * @param pose The pose to which the robot's odometry should be reset, as a Pose2d object.
   */

  public SwerveModulePosition[] getStates() {
    SwerveModulePosition[] states = new SwerveModulePosition[4];
    for (SwerveModule mod : mSwerveMods) {
      states[mod.moduleNumber] = mod.getState();
    }
    return states;
  }

  /**
   * Returns the current state of each swerve module, as an array of SwerveModulePosition objects.
   *
   * @return The current state of each swerve module.
   */

  public void zeroGyro() {
    gyro.zeroYaw();
  }

  /**
   * Resets the robot's gyro to zero.
   */

  public Rotation2d getYaw() {
    return (Constants.Swerve.invertGyro)
        ? Rotation2d.fromDegrees(360 - gyro.getAngle())
        : Rotation2d.fromDegrees(gyro.getAngle());
  }

    /**
     * This method is called periodically by WPILib to update the subsystem. It updates the robot's
     * odometry, updates the Field2d object (which displays the robot's pose on the field on
     * Shuffleboard), and displays information about each swerve module on Shuffleboard.
     */
    @Override
    public void periodic() {
        swerveOdometry.update(getYaw(), getStates());
        field.setRobotPose(getPose());

        for (SwerveModule mod : mSwerveMods) {
            SmartDashboard.putNumber(
                "Mod " + mod.moduleNumber + " Cancoder", mod.getCanCoder().getDegrees());
            SmartDashboard.putNumber(
                "Mod " + mod.moduleNumber + " Integrated", mod.getState().angle.getDegrees());
            SmartDashboard.putNumber(
                "Mod " + mod.moduleNumber + " Distance", mod.getState().distanceMeters);
        }
    }
}