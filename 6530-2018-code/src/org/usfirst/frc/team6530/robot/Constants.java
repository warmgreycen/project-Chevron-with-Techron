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
			
;
			


}