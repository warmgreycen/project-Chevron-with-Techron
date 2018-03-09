package org.usfirst.frc.team6530.robot;

import org.usfirst.frc.team6530.robot.commands.commandDeploy;

//import org.usfirst.frc.team6530.robot.commands.ButtonCommandEat;
//import org.usfirst.frc.team6530.robot.commands.ButtonCommandSpit;
//import org.usfirst.frc.team6530.robot.enumeration.Direction;
//import org.usfirst.frc.team6530.robot.util.Xbox;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/** the output/input setup */
/** the output/input setup */
public class OI {
	
	public static final Joystick DRIVER = new Joystick(0);
	public static final Joystick OPERATOR = new Joystick(1);
	public static final Button DEPLOY = new JoystickButton(OPERATOR, 5);
	
	public OI() {
		DEPLOY.whenPressed(new commandDeploy() );
	}
	public double getLeftInput() { //Retrieves joystick input used
		return DRIVER.getRawAxis(1);
	}
	
	public double getRightInput() { //Retrieves speed needed for right drive
		return DRIVER.getRawAxis(5);
	}
	public double getRightInputOperator() { //Retrieves speed needed for right drive
		return OPERATOR.getRawAxis(5);
	}

	}

