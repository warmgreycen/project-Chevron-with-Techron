package org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions;

import org.usfirst.frc.team6530.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class LiftElevator extends Command {
	
	String mode;
	double height;

    //public LiftElevator(double timeOut, String mode) {
	public LiftElevator(double height, String mode) {
      requires(Robot.SUB_ELEVATOR);
      this.height = height;
      //setTimeout(timeOut); 
    }
    protected void initialize() {
    }
    protected void execute() {
    	if(mode == "up") {
    		if(Robot.SUB_ELEVATOR.getElevatorDistance() >= height) {
    			Robot.SUB_ELEVATOR.up();
    		}
    		else {
    			Robot.SUB_ELEVATOR.elevatorMotor.set(0);
    		}
    	}
    	else {
    		if(Robot.SUB_ELEVATOR.getElevatorDistance() <= height) {
    			Robot.SUB_ELEVATOR.down();
    		}
    		else {
    			Robot.SUB_ELEVATOR.elevatorMotor.set(0);
    		}
    	}
    }
    protected boolean isFinished() {
        return isTimedOut();
    }
    protected void end() {
    	Robot.SUB_ELEVATOR.elevatorMotor.set(0);
    }
    protected void interrupted() {
    }
}