package org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions;

import org.usfirst.frc.team6530.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class LiftElevator extends Command {
	
	String mode;

    public LiftElevator(double timeOut, String mode) {

      requires(Robot.SUB_ELEVATOR);
      setTimeout(timeOut); 
    }
    protected void initialize() {
    }
    protected void execute() {
    	if(mode == "up") {
    		Robot.SUB_ELEVATOR.up();
    	}
    	else {
    		Robot.SUB_ELEVATOR.down();
    	}
    }
    protected boolean isFinished() {
        return isTimedOut();
    }
    protected void end() {
    	Robot.SUB_ELEVATOR.elevatorMotor.set(-1);
    }
    protected void interrupted() {
    }
}