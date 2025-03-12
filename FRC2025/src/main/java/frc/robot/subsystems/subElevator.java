// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class subElevator extends SubsystemBase {
    SparkMax leftElevatorMotor;
    SparkMax rightElevatorMotor;
    SparkBaseConfig leftConfig;
    SparkBaseConfig rightConfig;
    AbsoluteEncoder elevatorEncoder;
    

    public subElevator() {
        leftElevatorMotor = new SparkMax(6, MotorType.kBrushless);
        rightElevatorMotor = new SparkMax(5, MotorType.kBrushless);
        elevatorEncoder = rightElevatorMotor.getAbsoluteEncoder();

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
        
        
        
    public void moveToL1(double speed){
    double currentPosition = elevatorEncoder.getPosition();
            if(currentPosition >= 10){
                stop();
            } else {
                leftElevatorMotor.set(speed);
                rightElevatorMotor.set(speed);
            }
        
    }

    public void moveToL2(double speed){
    double currentPosition = elevatorEncoder.getPosition();
        if(currentPosition >= 1.0){
            stop();
        } else {
            leftElevatorMotor.set(speed);
            rightElevatorMotor.set(speed);
        }
    }

    public void moveToL3(double speed){
    double currentPosition = elevatorEncoder.getPosition();
            if(currentPosition >= 30){
                stop();
            } else {
                leftElevatorMotor.set(speed);
                rightElevatorMotor.set(speed);
            }
        }
    
    
    public void stop() {
        leftElevatorMotor.stopMotor();
        rightElevatorMotor.stopMotor();
    }


}