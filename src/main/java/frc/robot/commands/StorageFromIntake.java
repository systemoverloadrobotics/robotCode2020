/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Storage;


public class StorageFromIntake extends CommandBase {

	private final Storage m_storage;

	public StorageFromIntake(Storage storage) {
		m_storage = storage;
		addRequirements(storage);
	}

	public void initialize() {
		m_storage.moveIn();
	}

	public void end(boolean interrupted) {
		m_storage.moveStop();
	}

	public boolean isFinished() {
		return m_storage.getPos1();
	}
}
