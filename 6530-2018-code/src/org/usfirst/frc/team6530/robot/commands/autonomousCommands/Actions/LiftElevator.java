package org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions;

import org.usfirst.frc.team6530.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

//"You make elevator music I make elevating music"
public class LiftElevator extends Command {
	
	String mode;
	double height;
	boolean isFinished;

    //public LiftElevator(double timeOut, String mode) {
	public LiftElevator(double height, String mode) {
      requires(Robot.SUB_ELEVATOR);
      this.height = height;
      //setTimeout(timeOut); 
    }
    protected void initialize() {
    	Robot.SUB_ELEVATOR.resetElevatorEncoder();
    }
    protected void execute() {
    	if(mode == "up") {
    		if(Robot.SUB_ELEVATOR.getElevatorDistance() < height) {
    			Robot.SUB_ELEVATOR.up();
    		}
    		else {
    			Robot.SUB_ELEVATOR.brake();
    			isFinished = true;
    		}
    	}
    	else {
    		if(Robot.SUB_ELEVATOR.getElevatorDistance() > height) {
    			Robot.SUB_ELEVATOR.down();
    		}
    		else {
    			Robot.SUB_ELEVATOR.brake();
    			isFinished = true;
    		}
    	}
    }
    protected boolean isFinished() {
        return isFinished;
    }
    protected void end() {
    	Robot.SUB_ELEVATOR.brake();
    }
    protected void interrupted() {
    	Robot.SUB_ELEVATOR.brake();
    }
}