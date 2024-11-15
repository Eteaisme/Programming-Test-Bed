package ca.warp7.frc2024.Subsystems.Drivetrain;

import org.littletonrobotics.junction.Logger;

import ca.warp7.frc2024.Subsystems.Drivetrain.SwerveModuleIO.SwerveModuleIOInputs;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
public class SwerveModule {
    private final SwerveModuleIO io;
    private final String moduleName;

    private SwerveModuleIOInputsAutoLogged inputs = new SwerveModuleIOInputsAutoLogged();

    private SimpleMotorFeedforward driveFeedforward;
    private final PIDController driveFeedback;
    private final PIDController steerFeedback;

    private Rotation2d angleSetpoint = null; 
    private Double speedSetpoint = null; 
    private Rotation2d turnRelativeOffset = null; 

    private SwerveModulePosition[] odometryPositions = new SwerveModulePosition[] {};

    public SwerveModule(SwerveModuleIO io, String moduleName) {
        this.io = io;
        this.moduleName = moduleName;
        
        driveFeedforward = new SimpleMotorFeedforward(0.0, 0.0);
        driveFeedback = new PIDController(0.0, 0.0, 0.0);
        steerFeedback = new PIDController(0.0, 0.0, 0.0);       
    }


    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("Drive/Module " + moduleName, inputs);
    }


}