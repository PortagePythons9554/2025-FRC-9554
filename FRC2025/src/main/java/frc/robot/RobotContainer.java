// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.subDrive;
import frc.robot.commands.cmdDrive_TeleOp;
import frc.robot.subsystems.subElevator;
import frc.robot.subsystems.subCoral;
import frc.robot.commands.cmdAuto_ElevatorToPosition;
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
        () -> MathUtil.applyDeadband(m_operatorController.getLeftY(), 0.01),
        () -> MathUtil.applyDeadband(m_operatorController.getLeftX() * -1, 0.01),
        () -> MathUtil.applyDeadband(m_operatorController.getRightX() * -1, 0.01)));
  }

  private void driverTwoFunctions() {
    m_elevator.setDefaultCommand(new cmdElevator_TeleOp(m_elevator, ()->MathUtil.applyDeadband(m_buttonPusher.getLeftY()*-1, 0.03)));
    m_buttonPusher.x().whileTrue(new cmdAuto_ElevatorToPosition(m_elevator, Constants.Elevator.L2));
    m_buttonPusher.y().whileTrue(new cmdAuto_ElevatorToPosition(m_elevator, Constants.Elevator.L3));
    m_buttonPusher.start().whileTrue(new cmdAuto_ElevatorToPosition(m_elevator, Constants.Elevator.Bottom));
    m_buttonPusher.a().whileTrue(new cmdCoral_TeleOp(m_coral, ()->0.3));
    m_buttonPusher.b().whileTrue(new cmdCoral_TeleOp(m_coral, ()->-0.3));

      }
    
    

  public Command getAutonomousCommand() {
    return Commands.run(() -> m_driveTrain.drive(-.125,0,0),m_driveTrain).withTimeout(2.).andThen(Commands.runOnce(()-> m_driveTrain.drive(0, 0, 0), m_driveTrain));
  }
}
