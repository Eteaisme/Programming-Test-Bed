package ca.warp7.frc2024.Subsystems.Drivetrain.Gyro;

import ca.warp7.frc2024.Subsystems.Drivetrain.Drivetrain;
import edu.wpi.first.math.geometry.Pose2d;

public class GyroIOSim implements GyroIO{
    Drivetrain drivetrain;

    private Pose2d simOdometry = new Pose2d();
    
    public GyroIOSim(Drivetrain drivetrain) {
        this.drivetrain = drivetrain; 
    }
    
    private void calculateAngle() {
    }


    @Override
    public void updateInputs(GyroIOInputs inputs) {
        inputs.yaw = simOdometry.getRotation().getRadians();
    }

}