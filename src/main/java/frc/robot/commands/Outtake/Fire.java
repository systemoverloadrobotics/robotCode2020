/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Outtake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Outtake;
import java.util.function.BooleanSupplier;

public class Fire extends CommandBase {

	private final Outtake m_shoot;

	private BooleanSupplier m_fullPower;
	double m_RPM;

	public Fire(Outtake shoot, BooleanSupplier fullPower, double RPM) {
		m_shoot = shoot;
		m_fullPower = fullPower;
		addRequirements(shoot);

		m_RPM = RPM;
	}

	@Override
	public void execute(){
		m_RPM = 1;
		m_shoot.shoot(m_RPM);
	}

	@Override
	public void end(boolean interrupted){
		m_shoot.spinStop();
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
