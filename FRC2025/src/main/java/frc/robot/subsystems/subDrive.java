// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

public class subDrive extends SubsystemBase {
  SparkMax m_rightRearMotor;
  SparkMax m_leftRearMotor;
  SparkMax m_rightFrontMotor;
  SparkMax m_leftFrontMotor;
  MecanumDrive driveTrain;

  private AHRS navx = new AHRS(NavXComType.kMXP_SPI); // Instantiate a NavX Gyroscope connected to a roboRIO USB port

  public subDrive() {
    m_rightRearMotor = new SparkMax(9, MotorType.kBrushless);
    m_leftRearMotor = new SparkMax(Constants.kLeftRearID, MotorType.kBrushless);
    m_rightFrontMotor = new SparkMax(Constants.kRightFrontID, MotorType.kBrushless);
    m_leftFrontMotor = new SparkMax(Constants.kLeftFrontID, MotorType.kBrushless);

    driveTrain = new MecanumDrive(m_leftFrontMotor, m_leftRearMotor, m_rightFrontMotor, m_rightRearMotor);

    SparkMaxConfig config = new SparkMaxConfig();
    config
      .idleMode(IdleMode.kCoast);

    m_rightRearMotor.configure(config.inverted(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_leftRearMotor.configure(config.inverted(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_rightFrontMotor.configure(config.inverted(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_leftFrontMotor.configure(config.inverted(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    driveTrain = new MecanumDrive(m_leftFrontMotor::set, m_leftRearMotor::set, m_rightFrontMotor::set,m_rightRearMotor::set);
  }

  public void zeroGyro() {
    navx.reset();
  }

  public double getYaw() {
    return navx.getYaw();
  }

  public void drive(double forward, double strafe, double rotation) {
    driveTrain.driveCartesian(forward, strafe, rotation);
  }

  public void stop() {
    m_leftFrontMotor.stopMotor();
    m_leftRearMotor.stopMotor();
    m_rightFrontMotor.stopMotor();
    m_rightRearMotor.stopMotor();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Gyro Yaw", getYaw());

  }
}
