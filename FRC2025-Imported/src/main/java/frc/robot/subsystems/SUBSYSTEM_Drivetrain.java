// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.MecanumDrive; 
import edu.wpi.first.wpilibj.XboxController;
import com.studica.frc.AHRS;
import edu.wpi.first.math.geometry.Rotation2d;


public class SUBSYSTEM_Drivetrain extends SubsystemBase {
  private static SparkMax m_rightRearMotor; 
  private static SparkMax m_leftRearMotor;
  private static SparkMax m_rightFrontMotor;
  private static SparkMax m_leftFrontMotor;

  private AHRS navx = new AHRS(AHRS.NavXComType.kUSB1); // Instantiate a NavX Gyroscope connected to a roboRIO USB port
  private static MecanumDrive robotDrive;


  private static XboxController controller = new XboxController(Constants.OperatorConstants.kDriverControllerPort);


  
  public SUBSYSTEM_Drivetrain() {
    m_rightRearMotor = new SparkMax(Constants.kRightRearID, MotorType.kBrushless);
    m_leftRearMotor = new SparkMax(Constants.kLeftRearID, MotorType.kBrushless);
    m_rightFrontMotor = new SparkMax(Constants.kRightFrontID, MotorType.kBrushless);
    m_leftFrontMotor = new SparkMax(Constants.kLeftFrontID, MotorType.kBrushless);

    

    robotDrive = new MecanumDrive(m_leftFrontMotor, m_leftRearMotor, m_rightFrontMotor, m_rightRearMotor);
    

    SparkMaxConfig config = new SparkMaxConfig();
    m_rightRearMotor.configure(config.inverted(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_leftRearMotor.configure(config.inverted(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_rightFrontMotor.configure(config.inverted(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_leftFrontMotor.configure(config.inverted(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    robotDrive = new MecanumDrive(m_leftFrontMotor::set, m_leftRearMotor::set, m_rightFrontMotor::set, m_rightRearMotor::set);
  }

  @Override
  public void periodic(){
    // This method will be called once per scheduler run.
    double left_x = controller.getLeftX();
    double left_y = controller.getLeftY();
    double right_x = controller.getRightX();


    m_rightRearMotor.set(left_y + left_x - right_x);
    m_leftRearMotor.set(left_y - left_x + right_x);
    m_rightFrontMotor.set(left_y - left_x - right_x);
    m_leftFrontMotor.set(left_y + left_x + right_x);
  }

  public void stop(){
    m_leftFrontMotor.set(0);
    m_leftRearMotor.set(0);
    m_rightFrontMotor.set(0);
    m_rightRearMotor.set(0);

  }
public void zeroGyro() {
		navx.reset();
	}

  //Changed it to field orientated with the gyroscope
	public void driveCartesian(double ySpeed, double xSpeed, double zRotation, Rotation2d currentAngle) {
      robotDrive.driveCartesian(ySpeed, xSpeed, zRotation, currentAngle);

}

}
