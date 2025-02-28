// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.subDrive;
import frc.robot.commands.cmdDrive_TeleOp;
import frc.robot.subsystems.subElevator;
import frc.robot.subsystems.subCoral;
import frc.robot.commands.cmdCoral_TeleOp;
import frc.robot.commands.cmdElevator_TeleOp;

public class RobotContainer {
  private final subDrive m_driveTrain = new subDrive();
  private final subElevator m_elevator = new subElevator();
  private final subCoral m_coral = new subCoral();
  private final CommandXboxController m_operatorController = new CommandXboxController(
      OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_buttonPusher = new CommandXboxController(1);
  
  public RobotContainer() {
    driverOneFunctions();
    driverTwoFunctions();

  }

  private void driverOneFunctions() {
    m_driveTrain.setDefaultCommand(new cmdDrive_TeleOp(m_driveTrain,
        () -> MathUtil.applyDeadband(m_operatorController.getLeftY(), 0.05),
        () -> MathUtil.applyDeadband(m_operatorController.getLeftX() * -1, 0.05),
        () -> MathUtil.applyDeadband(m_operatorController.getRightX() * -1, 0.05)));
  }

  private void driverTwoFunctions() {
    m_elevator.setDefaultCommand(new cmdElevator_TeleOp(m_elevator, ()->MathUtil.applyDeadband(m_buttonPusher.getLeftY()*-1, 0.05)));
    m_buttonPusher.a().whileTrue(new cmdCoral_TeleOp(m_coral, ()->0.2));
    m_buttonPusher.b().whileTrue(new cmdCoral_TeleOp(m_coral, ()->-0.2));
  }

  public Command getAutonomousCommand() {
    return new InstantCommand();// Autos.exampleAuto(m_exampleSubsystem);
  }
}
