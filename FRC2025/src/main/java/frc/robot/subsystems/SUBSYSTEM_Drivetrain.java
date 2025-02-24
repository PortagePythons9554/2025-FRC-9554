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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;



public class SUBSYSTEM_Drivetrain extends SubsystemBase {
    SparkMax m_rightRearMotor; 
    SparkMax m_leftRearMotor;
    SparkMax m_rightFrontMotor;
    SparkMax m_leftFrontMotor;

  private AHRS navx = new AHRS(NavXComType.kMXP_SPI); // Instantiate a NavX Gyroscope connected to a roboRIO USB port
  private static MecanumDrive driveTrain;




  
  public SUBSYSTEM_Drivetrain() {
    m_rightRearMotor = new SparkMax(Constants.kRightRearID, MotorType.kBrushless);
    m_leftRearMotor = new SparkMax(Constants.kLeftRearID, MotorType.kBrushless);
    m_rightFrontMotor = new SparkMax(Constants.kRightFrontID, MotorType.kBrushless);
    m_leftFrontMotor = new SparkMax(Constants.kLeftFrontID, MotorType.kBrushless);

    

    driveTrain = new MecanumDrive(m_leftFrontMotor, m_leftRearMotor, m_rightFrontMotor, m_rightRearMotor);
    

    SparkMaxConfig config = new SparkMaxConfig();

    m_rightRearMotor.configure(config.inverted(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_leftRearMotor.configure(config.inverted(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_rightFrontMotor.configure(config.inverted(false), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    m_leftFrontMotor.configure(config.inverted(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    

    driveTrain = new MecanumDrive(m_leftFrontMotor::set, m_leftRearMotor::set, m_rightFrontMotor::set, m_rightRearMotor::set);
  }

  public void zeroGyro() {
		navx.reset();
	}
	
  public double getYaw() {
		return navx.getYaw();
	}

  public void drive(double forward, double strafe, double rotation) {
    double robotYaw = getYaw();
        double radians = Math.toRadians(robotYaw);

        double tempX = forward * Math.cos(radians) - strafe * Math.sin(radians);
        double tempY = forward * Math.sin(radians) + strafe * Math.cos(radians);

        double maxInput = Math.max(Math.abs(tempX), Math.abs(tempY));
        if (maxInput > 1.0) {
            tempX /= maxInput;
            tempY /= maxInput;
        }
    driveTrain.driveCartesian(forward, strafe, rotation);  

}



  public void stop(){
    m_leftFrontMotor.set(0);
    m_leftRearMotor.set(0);
    m_rightFrontMotor.set(0);
    m_rightRearMotor.set(0);

  }

  @Override
public void periodic() {
    // Code in this method runs periodically, typically every 20 milliseconds
    // Example: Print the yaw angle to the SmartDashboard
    SmartDashboard.putNumber("Gyro Yaw", getYaw());
  


}
}
