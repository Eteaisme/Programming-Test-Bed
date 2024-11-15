package ca.warp7.frc2024.Subsystems.Drivetrain;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.geometry.Rotation2d;

public interface SwerveModuleIO {
   @AutoLog
    public static class SwerveModuleIOInputs {
        public double drivePositionRad = 0.0;
        public double drivePositionMeters = 0.0;
        public double driveVelocityRadPerSec = 0.0;
        public double driveVelocityMeterPerSec = 0.0;
        public double driveAppliedVolts = 0.0;
        public double[] driveCurrentAmps = new double[] {};

        public Rotation2d turnAbsolutePosition = new Rotation2d();
        public Rotation2d turnPosition = new Rotation2d();
        public double turnVelocityRadPerSec = 0.0;
        public double turnAppliedVolts = 0.0;
        public double[] turnCurrentAmps = new double[] {};

        public double[] odometryTimestamps = new double[] {};
        public double[] odometryDrivePositionsRad = new double[] {};
        public Rotation2d[] odometryTurnPositions = new Rotation2d[] {};
  }

  /** Updates the set of loggable inputs. */
  public default void updateInputs(SwerveModuleIOInputs inputs) {}

  /** Run the drive motor at the specified voltage. */
  public default void setDriveVoltage(double volts) {}

  /** Run the turn motor at the specified voltage. */
  public default void setTurnVoltage(double volts) {}

  /** Enable or disable brake mode on the drive motor. */
  public default void setDriveBrakeMode(boolean enable) {}

  /** Enable or disable brake mode on the turn motor. */
  public default void setTurnBrakeMode(boolean enable) {}

  public default void setDrivePIDFF(double p, double i, double d, double ff) {}

  public default void setTurnPIDFF(double p, double i, double d, double ff) {}

  public default void setDriveVelocity(double velocityRadPerSec) {}

  public default void setTurnPosition(double angle) {}

  public default double getTurnPositionError(double angle) {
    return 0.0;
  }

  public default double getAbsoluteEncoderOffset() {
    return 0.0;
  }

  public default void setTurningCurrentLimit(int limit){
  }

  public default void setDriveCurrentLimit(int limit){

  }
}