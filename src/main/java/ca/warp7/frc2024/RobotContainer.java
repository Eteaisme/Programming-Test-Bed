// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package ca.warp7.frc2024;

import ca.warp7.frc2024.Subsystems.Drivetrain.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class RobotContainer {
  // Subsystems 
  private final DrivetrainSubsystem drivetrain; 

  public RobotContainer() {
    switch (Constants.CURRENT_MODE) {
      case REAL:
        drivetrain = new DrivetrainSubsystem(); 
        break;
      case SIM:
        drivetrain = new DrivetrainSubsystem(); 
        break; 
      default:
        drivetrain = new DrivetrainSubsystem(); 
        break;
    }
    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
