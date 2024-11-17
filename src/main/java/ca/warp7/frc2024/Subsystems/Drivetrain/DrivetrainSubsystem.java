package ca.warp7.frc2024.Subsystems.Drivetrain;

import org.littletonrobotics.junction.Logger;

import ca.warp7.frc2024.Constants;
import ca.warp7.frc2024.Constants.kDrivetrain.*;

import ca.warp7.frc2024.Subsystems.Drivetrain.Gyro.GyroIO;
import ca.warp7.frc2024.Subsystems.Drivetrain.Gyro.GyroIOInputsAutoLogged;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

public class DrivetrainSubsystem extends SubsystemBase {
    /* GYROSCOPE */
    private final GyroIO gyroIO;
    private GyroIOInputsAutoLogged gyroInputs = new GyroIOInputsAutoLogged();
    private Rotation2d rawGyroRotation = new Rotation2d(); 

    /* SWERVE MODULES */
    private final SwerveModule[] modules = new SwerveModule[4];
    private SwerveDriveKinematics kinematics = new SwerveDriveKinematics(getModuleTranslations());
    SwerveModulePosition[] modulePositions = new SwerveModulePosition[4];
    for (int moduleIndex = 0; moduleIndex < 4; moduleIndex++) {
      modulePositions[moduleIndex] = modules[moduleIndex].getPosition();
    }
    private SwerveModulePosition[] lastModulePositions = new SwerveModulePosition[]{
        new SwerveModulePosition(),
        new SwerveModulePosition(),
        new SwerveModulePosition(),
        new SwerveModulePosition()
    };
    
    private SwerveDrivePoseEstimator poseEstimator = new SwerveDrivePoseEstimator(kinematics, rawGyroRotation,lastModulePositions, new Pose2d()); 
    private SwerveDriveOdometry odometry = new SwerveDriveOdometry(kinematics, getPose().getRotation(),
            lastModulePositions);

    private SysIdRoutine sysId;
    private SysIdRoutine turnRoutine;

    private Rotation2d simRotation = new Rotation2d();

    public DrivetrainSubsystem(
        GyroIO gyroIO,
        SwerveModuleIO frontLeftModuleIO,
        SwerveModuleIO frontRightModuleIO,
        SwerveModuleIO backLeftModuleIO,
        SwerveModuleIO backRightModuleIO
    ) {
        this.gyroIO = gyroIO;
        modules[0] = new SwerveModule(frontLeftModuleIO, "frontLeftModule");
        modules[1] = new SwerveModule(frontRightModuleIO, "frontRightModule");
        modules[2] = new SwerveModule(backLeftModuleIO, "backLeftModule");
        modules[3] = new SwerveModule(backRightModuleIO, "backRightModule");

    }
    @Override
    public void periodic() {
        gyroIO.updateInputs(gyroInputs);
        for (var module : modules) {
            module.updateInputs();
        }

        Logger.processInputs("Drive/Gyro", gyroInputs);


        for (var module : modules) {
            module.periodic();
            module.setCurrentLimit();

        }

        if (DriverStation.isDisabled()) {
            for (var module : modules) {
                module.stop();
            }
        }
        
        if (gyroInputs.connected) {
        rawGyroRotation = gyroInputs.yawPosition;
        } else if (Constants.CURRENT_MODE == Constants.CURRENT_MODE.SIM) {
            rawGyroRotation = simRotation;
        }

        poseEstimator.updateWithTime(Timer.getFPGATimestamp(), rawGyroRotation, modulePositions);
        odometry.update(rawGyroRotation, modulePositions);
    }
}
