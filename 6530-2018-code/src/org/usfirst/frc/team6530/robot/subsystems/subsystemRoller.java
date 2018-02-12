package org.usfirst.frc.team6530.robot.subsystems;
import org.usfirst.frc.team6530.robot.commands.commandRoller;

import org.usfirst.frc.team6530.robot.Constants;


import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class subsystemRoller extends Subsystem {
	
	//Creates 2 Victor objects
	Spark right = new Spark(Constants.PWM_INTAKE_RIGHT);
	Spark left = new Spark(Constants.PWM_INTAKE_LEFT);
	
	//Method to use intake base
	public void intake(double speed) {
		right.set(speed);
		left.set(-speed);
		
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new subsystemRoller());
		
	}

	private void setDefaultCommand(subsystemRoller subsystemRoller) {
		// TODO Auto-generated method stub
		
	}

}