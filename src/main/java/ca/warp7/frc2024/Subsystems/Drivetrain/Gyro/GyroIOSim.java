package ca.warp7.frc2024.Subsystems.Drivetrain.Gyro;

import ca.warp7.frc2024.Subsystems.Drivetrain.DrivetrainSubsystem;
import edu.wpi.first.math.geometry.Pose2d;

public class GyroIOSim implements GyroIO{
    DrivetrainSubsystem drivetrain;

    private Pose2d simOdometry = new Pose2d();
    
    public GyroIOSim(DrivetrainSubsystem drivetrain) {
        this.drivetrain = drivetrain; 
    }
    
    private void calculateAngle() {}


    @Override
    public void updateInputs(GyroIOInputs inputs) {
        inputs.yaw = simOdometry.getRotation().getRadians();
    }

}