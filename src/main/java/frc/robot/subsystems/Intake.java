package frc.robot.subsystems;

import frc.robot.Constants.IntakeConstants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {

    public DoubleSolenoid = new DoubleSoleniod(IntakeConstants.PCM_ID,IntakeConstants.FORWARD_CHANNEL,IntakeConstants.OUTWARD_CHANNEL);
    public WPI_VictorSPX intakeMotorOne = new WPI_VictorSPX(IntakeConstants.MASTER_ID);
    public WPI_VictorSPX intakeMotorTwo = new WPI_VictorSPX(IntakeConstants.FOLLOWER_ID);
    public DigitalInput input = new DigitalInput(0);


    public Intake(){
        public WPI_VictorSPX intakeMotorOne = intakeMotorOne.configFactoryDefault();
        public WPI_VictorSPX intakeMotorTwo = intakeMotorTwo.follower(intakeMotorOne);
        public WPI_VictorSPX intakeMotorTwo.selfInverted(false);
    }

    public void extend() {
        test.set(DoubleSolenoid.Value.kforward);
    }

    public void retract() {
        test.set(DoubleSolenoid.Value.kreverse);
    }

    public void getPosition() {
        input.get();
    }

    public void spinIn(){

        intakeMotorOne.set(ControlMode.PercentOutput,IntakeConstants.INWARD);
        intakeMotorTwo.set(ControlMode.PercentOutput,IntakeConstants.INWARD);
    }

    public void spinOut() {

        intakeMotorOne.set(ControlMode.PercentOutput,IntakeConstants.OUTWARD);
        intakeMotorTwo.set(ControlMode.PercentOutput,IntakeConstants.OUTWARD);
    }

    public void stop(){

        intakeMotorOne.stopMotor();
        intakeMotorTwo.stopMotor();
    }

}