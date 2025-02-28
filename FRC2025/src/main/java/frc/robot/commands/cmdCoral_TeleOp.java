package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.subCoral;

public class cmdCoral_TeleOp extends Command {
   subCoral m_coral;
   DoubleSupplier speed;
   public cmdCoral_TeleOp(subCoral coral, DoubleSupplier speed) {
    m_coral = coral;
    this.speed = speed;
    addRequirements(m_coral);
  }

  @Override
  public void initialize() {  }

  @Override
  public void execute() {
    m_coral.TeleOp(speed.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    m_coral.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
