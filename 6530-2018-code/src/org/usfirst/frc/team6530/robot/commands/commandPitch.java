package org.usfirst.frc.team6530.robot.commands;

import org.usfirst.frc.team6530.robot.OI;
import org.usfirst.frc.team6530.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class commandPitch extends Command {
	
    public commandPitch() {
    	//Sets the required Subsystem
        requires(Robot.SUB_PITCH);
    }
    protected void initialize() {
    }
    protected void execute() {    
    	//sets the triggers on OPERATOR controller to control the pitch motor
double speed = OI.OPERATOR.getRawAxis(5);
    	
    	if(OI.OPERATOR.getRawAxis(2) > 0.15){
    		speed = OI.OPERATOR.getRawAxis(2);
    	}
    	
    	else if(OI.OPERATOR.getRawAxis(3) > 0.15){
    		speed = -OI.OPERATOR.getRawAxis(3);
    	}
    	
    	else{
    		speed = 0;
    	}
    										//REPLACED OPERATOR WITH OPERATOR FOR ASSEMBLY TODAY
    	Robot.SUB_PITCH.intake(speed);
    }
    protected boolean isFinished() {
        return false;
    }
    protected void end() {
    }
    protected void interrupted() {
    }
}