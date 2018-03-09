package org.usfirst.frc.team6530.robot.commands;

import org.usfirst.frc.team6530.robot.OI;
import org.usfirst.frc.team6530.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class commandDeploy extends Command {
	
	double speed;
    public commandDeploy() {
    	//Sets the required Subsystem
        requires(Robot.SUB_DEPLOY);
        setTimeout(0.25);
    }
    protected void initialize() {
    }
    protected void execute() {    
    	//sets the triggers on OPERATOR controller to control the pitch motor
    	speed = 1;
    	
//    	if(OI.OPERATOR.getRawAxis(2) > 0.15){
//    		speed = OI.OPERATOR.getRawAxis(2);
//    	}
    	
//    	else if(OI.OPERATOR.getRawAxis(3) > 0.15){
//    		speed = -OI.OPERATOR.getRawAxis(3);
//    	}
    	
//    	else{
//    		speed = 0;
//    	}
    	
    	Robot.SUB_DEPLOY.open(speed);
    }
    protected boolean isFinished() {
        return isTimedOut();
    }
    protected void end() {
    }
    protected void interrupted() {
    }
}