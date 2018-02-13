package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.OI;
import org.usfirst.frc.team6530.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class commandElevator extends Command {

	double speed;

	public commandElevator() {
		// sets the requires subsystem
		requires(Robot.SUB_ELEVATOR);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		speed = OI.OPERATOR.getRawAxis(1);

		if (Math.abs 		(OI.OPERATOR.getRawAxis(1)) < 0) {
			speed =  		(OI.OPERATOR.getRawAxis(1)); 		
		} else if (Math.abs 
				     		(OI.OPERATOR.getRawAxis(1))  > 0){
    		speed = 	   -(OI.OPERATOR.getRawAxis(1));
    	} else{
    		speed = 0; 
    	}
	
		Robot.SUB_ELEVATOR.elevator(speed);
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