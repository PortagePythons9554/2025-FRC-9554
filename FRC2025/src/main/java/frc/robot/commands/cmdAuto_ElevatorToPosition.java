package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.subElevator;

public class cmdAuto_ElevatorToPosition extends Command {
  subElevator elevator;
  DoubleSupplier position;
  public cmdAuto_ElevatorToPosition(subElevator elevator, DoubleSupplier position) {
    this.elevator = elevator;
    this.position = position;
    addRequirements(elevator);
  }

  @Override
  public void initialize() {
    elevator.elevPID.setSetpoint(position.getAsDouble());
  }

  @Override
  public void execute() {
    elevator.moveToPosition();
  }

  @Override
  public void end(boolean interrupted) {
    elevator.stop();
  }

  @Override
  public boolean isFinished() {
    return false;//elevator.elevPID.atSetpoint();
  }
}
