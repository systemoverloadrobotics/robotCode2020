package frc.robot.subsystems;

import frc.robot.Constants.IntakeConstants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {

    public DoubleSolenoid = new DoubleSoleniod(IntakeConstants.PCM_ID, IntakeConstants.FORWARD_CHANNEL, IntakeConstants.REVERSE_CHANNEL);
    public WPI_VictorSPX intakeMotorOne = intakeMotorOne.configFactoryDefault();
    public WPI_VictorSPX intakeMotorTwo = intakeMotorTwo.follow(intakeMotorOne);
    public WPI_VictorSPX intakeMotorTwo.setInverted(false);
    public DigitalInput input = new DigitalInput(getPosition(0););


    public Intake(){
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
        intakeMotorOne(ControlMode.PercentOuput.INWARD);
        intakeMotorTwo(ControlMode.PercentOuput.INWARD);
    }

    public void spinOut() {
        intakeMotorOne(ControlMode.PercentOuput.OUTWARD);
        intakeMotorTwo(ControlMode.PercentOuput.OUTWARD);
    }

    public void stop(){
        intakeMotorOne.stopMotor();
        intakeMotorTwo.stopMotor();
    }

}