// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkMaxAlternateEncoder;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class subElevator extends SubsystemBase {
    SparkMax leftElevatorMotor;
    SparkMax rightElevatorMotor;
    SparkBaseConfig leftConfig;
    SparkBaseConfig rightConfig;
    RelativeEncoder elevatorEncoder;
    public PIDController elevPID = new PIDController(0.1, 0, 0);

    public subElevator() {
        elevPID.setTolerance(5);

        leftElevatorMotor = new SparkMax(6, MotorType.kBrushless);
        rightElevatorMotor = new SparkMax(5, MotorType.kBrushless);
        elevatorEncoder = leftElevatorMotor.getEncoder();


        // L1_rotations = 2.625;
        // elevPID = new PIDController(L1_rotations, L1_rotations, L1_rotations);

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

    public void moveToPosition(){
        leftElevatorMotor.set(MathUtil.clamp(elevPID.calculate(elevatorEncoder.getPosition()), -0.6, 1));
        rightElevatorMotor.set(MathUtil.clamp(elevPID.calculate(elevatorEncoder.getPosition()), -0.6, 1));
        //System.out.println("Target: " + elevPID.getSetpoint() + " Drive: " + elevPID.calculate(elevatorEncoder.getPosition()));
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