// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class subElevator extends SubsystemBase {
    SparkMax leftElevatorMotor;
    SparkMax rightElevatorMotor;
    SparkBaseConfig leftConfig;
    SparkBaseConfig rightConfig;
    RelativeEncoder elevatorEncoder;

    public subElevator() {
        leftElevatorMotor = new SparkMax(6, MotorType.kBrushless);
        rightElevatorMotor = new SparkMax(5, MotorType.kBrushless);
        elevatorEncoder = leftElevatorMotor.getEncoder();

        leftConfig = new SparkMaxConfig();
        leftConfig
                .idleMode(IdleMode.kBrake)
                .inverted(false);

        rightConfig = new SparkMaxConfig();
        rightConfig
                .idleMode(IdleMode.kBrake)
                .inverted(true);

        leftElevatorMotor.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightElevatorMotor.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void TeleOp(double speed) {
        leftElevatorMotor.set(speed);
        rightElevatorMotor.set(speed);
    }

    public void stop() {
        leftElevatorMotor.stopMotor();
        rightElevatorMotor.stopMotor();
    }
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Elevator Encoder", elevatorEncoder.getPosition());
    }
}
