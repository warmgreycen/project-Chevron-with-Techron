package org.usfirst.frc.team6530.robot;

/** where all the static final variables get set */
public class Constants {
	
	public static final double
		/// BOT SPECS
			DISTANCE_BETWEEN_WHEELS = 20, // inches between wheels; used for turn calculations 
			WHEEL_DIAMETER          = 6; // wheel diameter in inches
//			TALON    MAP
//	 			 -FRONT-	
//	LEFT|3|0|2|            |1|5|4|RIGHT
//				  -REAR-
	
//	
	public static final int
	//PROTO PWM
	PROTO_LEFT = 0,
	PROTO_RIGHT = 1,
	/// DRIVE MOTORS CAN_BUS
	RIGHT_MASTER   		 = 1,
	RIGHT_SLAVE1	 = 5,
		RIGHT_SLAVE2 = 4,
	
	LEFT_MASTER     	= 3,
	LEFT_SLAVE1  	= 0,
		LEFT_SLAVE2 = 2,

		//INTAKE SPARKS(PWM)
		PWM_INTAKE_LEFT = 0,
		PWM_INTAKE_RIGHT = 1,
	//ELEVATOR VICTOR SP PWM
		PWM_ELEVATOR = 2,
	
	//CLIMBER PWM
		PWM_CLIMBER = 3,
		//DIO PORTS
				CLIMBER_BOTTOM_SWITCH = 5,
				CLIMBER_TOP_SWITCH = 6,
	//PITCH PWM
				PWM_PITCH = 6,
	
		
				//LIMITS
				SPEED_LIMIT = 1;
	static final int
		/// CAMERA SPECS
			CAM_HEIGHT = 1080,
			CAM_WIDTH  = 1920,
		/// I2C
			I2C_DEVICE_ADDRESS = 4,
	// input control IDs
			DRIVER = 0,
			OPERATOR = 1;

	public static final boolean 
			LEFT_MOTOR_INVERT         = false,
			RIGHT_MOTOR_INVERT        = true;
			
	//Auto constants (all in inches or degrees)
		public static final double
		//Elevator/Roller Claw Constants
			ELEVATOR_TIMEOUT_SWITCH = 2.5,
			ROLLER_CLAW_TIMEOUT = 5,
		
		//GoBalance
			GB_FORWARD1 = 130,
			GB_ANGLE1 = 90,
			GB_FORWARD2 = 10,
			GB_FORWARD3 = 10,
			GB_ANGLE2 = 90,
		//MiddleGoSwitch
			M_LEFTDIAGONAL = 150,
			M_RIGHTDIAGONAL = 200,
		//SidesGoSwitch
			SGS_FORWARD1 = 196 + 13 + 2,
			SGS_ANGLE = 90,
			SGS_FORWARD2 = (24.06-2.5) + 132.88,
			SGS_FORWARD3 = 18 - (13 + 2),
		//SidesGoForward
			SGF_LENGTH = 90,
			SGF_ANGLE = 90;


}