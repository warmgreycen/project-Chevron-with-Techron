package org.usfirst.frc.team6530.robot.commands;

import org.usfirst.frc.team6530.robot.OI;
import org.usfirst.frc.team6530.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class commandRoller extends Command {
	
    public commandRoller() {
    	//Sets the required Subsystem
        requires(Robot.SUB_ROLLER);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    	
    	double speed = OI.OPERATOR.getRawAxis(5);
    	
    	if(OI.OPERATOR.getRawAxis(2) > 0.15){
    		speed = OI.OPERATOR.getRawAxis(2);
    	}
    	
    	else if(OI.OPERATOR.getRawAxis(3) > 0.15){
    		speed = -OI.OPERATOR.getRawAxis(3);
    	}
    	else if(Robot.pdp.getCurrent(10) >= 40 || Robot.pdp.getCurrent(11) >= 40) {
    		speed = 0;
    	}
    	
    	else{
    		speed = 0;
    	}
    	
    	Robot.SUB_ROLLER.MoveRoller(speed*.8);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}