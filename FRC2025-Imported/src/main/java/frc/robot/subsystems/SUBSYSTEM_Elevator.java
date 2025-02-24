package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.XboxController;

public class SUBSYSTEM_Elevator extends SubsystemBase {

        private static SparkMax m_leftElevMotor; 
        private static SparkMax m_rightElevMotor;

        private static DifferentialDrive ElevDrive;
        private static XboxController controller = new XboxController(Constants.OperatorConstants.kDriverControllerPort);
        

        public SUBSYSTEM_Elevator() {
        
        m_leftElevMotor = new SparkMax(Constants.kLeftElevID, MotorType.kBrushless);
        m_rightElevMotor = new SparkMax(Constants.kRightElevID, MotorType.kBrushless);



        SparkMaxConfig config = new SparkMaxConfig();
        m_leftElevMotor.configure(config.inverted(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_rightElevMotor.configure(config.inverted(true), ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        }
        

        public void periodic(){  

         boolean Apress = controller.getAButtonPressed();
         boolean Bpress = controller.getBButtonPressed();

        //elevator goes up          
           if(Apress){
            m_leftElevMotor.set(0.5);
            m_rightElevMotor.set(0.5);
        //elevator goes down
           }else if(Bpress){
            m_leftElevMotor.set(-0.5);
            m_rightElevMotor.set(-0.5);
           }else{
            m_leftElevMotor.set(0.1);
            m_rightElevMotor.set(0.1);
           }
        }


}
