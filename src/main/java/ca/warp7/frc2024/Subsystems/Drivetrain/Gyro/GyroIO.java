package ca.warp7.frc2024.Subsystems.Drivetrain.Gyro;

import org.littletonrobotics.junction.AutoLog;
import edu.wpi.first.math.geometry.Rotation2d;

public interface GyroIO {
    @AutoLog
    public static class GyroIOInputs {
        public boolean isConnected = false;
        public Rotation2d yawPosition = new Rotation2d();
        public Rotation2d rollPosition = new Rotation2d();
        public Rotation2d pitchPosition = new Rotation2d();
        public double yawVelocityRadPerSec = 0.0;
  }

  public default void updateInputs(GyroIOInputs inputs) {}
  
  public default void zeroYaw(){}
}
