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
		/// DRIVE MOTORS
			RIGHT_MASTER   		 = 1,
				RIGHT_SLAVE1	 = 5,
					RIGHT_SLAVE2 = 4,
				
			LEFT_MASTER     	= 3,
				LEFT_SLAVE1  	= 0,
					LEFT_SLAVE2 = 2,

					// Collector Sparks (PWM)
 			PWM_INTAKE_LEFT = 0,
 			PWM_INTAKE_RIGHT = 1,
 			//ELEVATOR VICTOR SP PWM
 			PWM_ELEVATOR = 2,
 			
					
	//LIMITS
			SPEED_LIMIT = 1;
	
	public static final int
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
		//GoBalance
			GB_LONGLEG = 130,
			GB_SHORTLEG = 30,
			GB_DIAGONAL = Math.pow(GB_LONGLEG, 2) + Math.pow(GB_SHORTLEG, 2),
			GB_FORWARD = 299.65 - GB_LONGLEG,
			GB_ANGLE1 = Math.atan(GB_SHORTLEG/GB_LONGLEG),
			GB_ANGLE2 = 90,
		//MiddleGoSwitch
			M_LONGLEG = 0,
			M_SHORTLEG = 0,
		//SidesGoSwitch
			SGS_LONGLEG = 166,
			SGS_SHORTLEG = 40,
			SGS_DIAGONAL = Math.pow(SGS_LONGLEG, 2) + Math.pow(SGS_SHORTLEG, 2),
			SGS_ANGLE1 = Math.atan(SGS_SHORTLEG/SGS_LONGLEG),
			SGS_SIDEWAYS = 132 + (SGS_SHORTLEG-16.5), //16.5 is distance marked "A" on paper
			SGS_ANGLE2 = 90,
		//SidesGoForward
			SGF_LENGTH = 80,
			SGF_ANGLE = 20;


}