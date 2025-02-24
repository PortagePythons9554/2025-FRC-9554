// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SUBSYSTEM_Elevator extends SubsystemBase {
    SparkMax leftElevatorMotor;
    SparkMax rightElevatorMotor;

    public void ElevatorSubsystem() {
        leftElevatorMotor = new SparkMax(6, MotorType.kBrushless);
        rightElevatorMotor = new SparkMax(5, MotorType.kBrushless);

        SparkMaxConfig config = new SparkMaxConfig();
         leftElevatorMotor.configure(config.inverted(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
         rightElevatorMotor.configure(config.inverted(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    }  
         public void moveUp() {
          leftElevatorMotor.set(1.0);
          rightElevatorMotor.set(-1.0);
      }
  
      public void moveDown() {
          leftElevatorMotor.set(-1.0);
          rightElevatorMotor.set(1.0);
      }
  
      public void stop() {
          leftElevatorMotor.stopMotor();
          rightElevatorMotor.stopMotor();
      }

      public void setElevSpeed(double elevSpeed){
      leftElevatorMotor.set(elevSpeed);
      rightElevatorMotor.set(elevSpeed);
      }
    }
  
