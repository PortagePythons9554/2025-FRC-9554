// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SUBSYSTEM_Drivetrain;
import java.util.function.DoubleSupplier;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class COMMAND_Drive extends Command {
  private final SUBSYSTEM_Drivetrain m_driveTrain;
  private final DoubleSupplier m_forward;
  private final DoubleSupplier m_strafe;
  private final DoubleSupplier m_rotation;
  
  
  public COMMAND_Drive(SUBSYSTEM_Drivetrain driveTrain, DoubleSupplier forward, DoubleSupplier strafe, DoubleSupplier rotation) {
    m_driveTrain = driveTrain;
    m_forward = forward;
    m_strafe = strafe;
    m_rotation = rotation;
    addRequirements(m_driveTrain);
  }


// Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  m_driveTrain.drive(m_forward.getAsDouble(), m_strafe.getAsDouble(), m_rotation.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
