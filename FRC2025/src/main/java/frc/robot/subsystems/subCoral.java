package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class subCoral extends SubsystemBase {
  SparkMax topMotor = new SparkMax(8,MotorType.kBrushless);;
  SparkMax bottomMotor = new SparkMax(7,MotorType.kBrushless);;
  SparkMaxConfig topConfig = new SparkMaxConfig();
  SparkMaxConfig bottomConfig = new SparkMaxConfig();

  public subCoral() {
    topConfig
      .idleMode(IdleMode.kCoast)
      .inverted(false);

    bottomConfig
      .idleMode(IdleMode.kCoast)
      .inverted(false);

    topMotor.configure(topConfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    bottomMotor.configure(bottomConfig,ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  }

  public void stop(){
    topMotor.stopMotor();
    bottomMotor.stopMotor();
  }

  public void TeleOp(double speed){
    topMotor.set(speed);
    bottomMotor.set(-speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
