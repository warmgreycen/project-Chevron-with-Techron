package org.usfirst.frc.team6530.robot;

//import org.usfirst.frc.team6530.robot.commands.ButtonCommandEat;
//import org.usfirst.frc.team6530.robot.commands.ButtonCommandSpit;
//import org.usfirst.frc.team6530.robot.enumeration.Direction;
//import org.usfirst.frc.team6530.robot.util.Xbox;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.buttons.Button;
//import edu.wpi.first.wpilibj.buttons.JoystickButton;

/** the output/input setup */
/** the output/input setup */
public class OI {
	
	public static final Joystick DRIVER = new Joystick(0);
	public static final Joystick OPERATOR = new Joystick(1);
	
	public double getLeftInput() { //Retrieves joystick input used
		return DRIVER.getRawAxis(3);
	}
	
	public double getRightInput() { //Retrieves speed needed for right drive
		return DRIVER.getRawAxis(1);
	}
	/** 
	 * assigns what every SmartDash and controller button does
	 * 
	 * ye() gets called at teleop enable, assigning button values to controller input
	 * still in ye(), below controller value assigns, place each SmartDash button
	 * */
	
	//public static void oi() { 
		/// manipulator wheels
			//Button spinIn = new JoystickButton(DRIVER, Xbox.LB);
			//	spinIn.whileHeld(new ButtonCommandEat());
			//Button spinOut = new JoystickButton(DRIVER, Xbox.RB);
				//spinOut.whileHeld(new ButtonCommandSpit());
		/// manipulator liftUP placeholder

	}

