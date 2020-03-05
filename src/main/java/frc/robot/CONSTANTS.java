/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

public final class CONSTANTS {
	//General Constants
	public static final int PCM_ID = 3;

	// Subsystem Constants
	public static final class DRIVE {
		public static final int RIGHT_MASTER_ID = 4;
		public static final int RIGHT_FOLLOWER_1_ID = 5;
		public static final int RIGHT_FOLLOWER_2_ID = 6;
		public static final int LEFT_MASTER_ID = 7;
		public static final int LEFT_FOLLOWER_1_ID = 8;
		public static final int LEFT_FOLLOWER_2_ID = 9;
		public static final double SPEED_MULTIPLIER = 0.6;
		public static final double TURN_MULTIPLIER = 0.4;
		public static final int DOUBLESOLENOID_FOWARDCHANNEL_ID = 4;
		public static final int DOUBLESOLENOID_REVERSECHANNEL_ID = 5;
	}

	public static final class STORAGE {
		public static final int POLYCORD_MOTOR_ID = 12;
		public static final int STORAGE_GATE_ID = 13;
		public static final int SUPER_SOLENOID_TIME = 3;
		public static final double POLYCORD_FORWARD_POWER = 0.2;
		public static final double POLYCORD_REVERSE_POWER = -0.2;
		public static final int POLYCORD_STOP = 0;
		public static final int BALLS_IN_SWITCH_ID = 17;
		public static final int BALLS_OUT_SWITCH_ID = 18;
		public static final double POLYCORD_FORWARD_SPEED = .2;
		public static final double POLYCORD_BACKWARD_SPEED = .2;
        public static final int BALLS_INTAKE_SWITCH_ID = 19;
    }

	public static final class CLIMB {
		public static final int WINCH_ID = 15;
		public static final int ELEVATOR_ID = 16;
		public static final double ELEVATOR_POWER_CONSTANT = 0.3;
		public static final double REVERSE_ELEVATOR_POWER_CONSTANT = -0.3;
		public static final double WINCH_POWER_CONSTANT = 0.3;
		public static final double REVERSE_WINCH_POWER_CONSTANT = -0.3;
		public static final double ELEVATOR_STOP_CONSTANT = 0;
	}

	public static final class INTAKE {
		public static final int FORWARD_CHANNEL = 2;
		public static final int REVERSE_CHANNEL = 3;
		public static final int MASTER_ID = 10;
		public static final int FOLLOWER_ID = 11;
		public static final int INWARD = 1;
		public static final int OUTWARD = -1;
		public static final int INPUT_DIO_PORT_ID = 0;
	}

	public static final class OUTTAKE {
		public static final int OUTTAKE_WHEELS_ID = 14;
	}

	public static final class CONTROLS {
		public static final int LEFT_JOYSTICK_PORT = 0;
		public static final int RIGHT_JOYSTICK_PORT = 1;

		// For Testing
		public static final int TRIGGER = 1;
		public static final int X_AXIS = 0;
		public static final int Y_AXIS = 0;
		public static final int BUTTON_2 = 2;
		public static final int BUTTON_3 = 3;
		public static final int BUTTON_4 = 4;
		public static final int BUTTON_9 = 9;
		public static final int BUTTON_10 = 10;
		public static final int BUTTON_11 = 11;
		public static final int BUTTON_12 = 12;
	}
}
