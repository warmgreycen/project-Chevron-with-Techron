package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.commands.commandClimber;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;


public class subsystemClimber extends Subsystem {
	DigitalInput bottom_switch = new DigitalInput(Constants.CLIMBER_BOTTOM_SWITCH);
	DigitalInput top_switch = new DigitalInput(Constants.CLIMBER_TOP_SWITCH);
    Spark ClimberMotor = new Spark(Constants.PWM_CLIMBER);
  
    
	public void initDefaultCommand() {
		setDefaultCommand(new commandClimber());
	}

	public void moveClimber(double speed) {
		ClimberMotor.set(speed);
	}
	
	public final boolean getBottomSwitch()
	{
		return bottom_switch.get();
	}


	public final boolean getTopSwitch()
	{
		return top_switch.get();
	}
//	public void setMotor(double move) {
//		setMotor(move);
//	}
//	public void setAllStop() {
//		setMotor(0);
//	}
}

	

