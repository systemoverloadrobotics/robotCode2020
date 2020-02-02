package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Intake extends SubsystemBase {

    WPI_VictorSPX test = new WPI_VictorSPX(1);

    public Intake(){
        test.set(ControlMode.PercentOutput, 3);

    }

    public void spinIn(){


    }

    public void spinOut(){


    }

    public void stop(){
        
    }

}