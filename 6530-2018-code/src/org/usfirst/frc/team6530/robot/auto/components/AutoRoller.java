package org.usfirst.frc.team6530.robot.auto.components;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class AutoRoller extends Command {
	String mode;
	boolean isFinished;

    public AutoRoller(String mode) {
      this.mode = mode;
      requires(Robot.SUB_ROLLER);
    }
    protected void initialize() {
    	if(mode == "spit") {
    		setTimeout(Constants.ROLLER_CLAW_TIMEOUT);
    	}
    }
    protected void execute() {
    	if(mode == "intake") {
    		if(Robot.pdp.getCurrent(10) >= 30 || Robot.pdp.getCurrent(11) >= 30) {
    			Robot.SUB_ROLLER.stop();
    			isFinished = true;
    		}
    		else {
    			Robot.SUB_ROLLER.intake();
    		}
    	}
    	else {
    		Robot.SUB_ROLLER.spit();
    	}
    }
    protected boolean isFinished() {
    	if(mode == "intake") {
    		return isFinished;
    	}
    	else {
    		return isTimedOut();
    	}
    }
    protected void end() {
    	Robot.SUB_ROLLER.stop();
    }
    protected void interrupted() {
    	Robot.SUB_ROLLER.stop();
    }
}