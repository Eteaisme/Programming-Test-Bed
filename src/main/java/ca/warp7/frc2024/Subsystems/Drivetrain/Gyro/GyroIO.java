package ca.warp7.frc2024.Subsystems.Drivetrain.Gyro;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.geometry.Rotation2d;

public interface GyroIO {
    @AutoLog
    public static class GyroIOInputs {
        public boolean isConnected = false;
        public double yaw;
  }

  public default void updateInputs(GyroIOInputs inputs) {}
  
  public default void zeroYaw(){}
}
