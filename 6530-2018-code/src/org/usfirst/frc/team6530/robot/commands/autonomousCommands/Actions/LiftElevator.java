package org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions;

import org.usfirst.frc.team6530.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to lift or lower elevator during auton. Starts up motor,
 * then stops after a specified period of time.
 */
public class LiftElevator extends Command {
	
	String mode;
	double height, timeOut;
	boolean isFinished;

    public LiftElevator(double timeOut, String mode) {
      requires(Robot.SUB_ELEVATOR);
      this.timeOut = timeOut;
      setTimeout(timeOut); 
    }
    protected void initialize() {}
    protected void execute() {
    	if(mode == "up") {
    		Robot.SUB_ELEVATOR.moveElevator(1);
    	}
    	else {
    		Robot.SUB_ELEVATOR.moveElevator(-1);
    	}
    }
    protected boolean isFinished() {
    	return isTimedOut();
    }
    protected void end() {
    	Robot.SUB_ELEVATOR.brake();
    }
    protected void interrupted() {
    	Robot.SUB_ELEVATOR.brake();
    }
}