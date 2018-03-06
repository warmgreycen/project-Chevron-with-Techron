package org.usfirst.frc.team6530.robot.commands;

import org.usfirst.frc.team6530.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * here we tell the bot where to find the gyro information, 
 * it'll put the angle on to smartdash
 */
public class getNavX extends Command {

	 public getNavX() {
	    	requires(Robot.SUB_GYRO);
	    }
	    protected void initialize() {
	    }
	    protected void execute() {
	    	Robot.SUB_GYRO.initialize();
	    	SmartDashboard.putNumber("NavX Angle", Robot.SUB_GYRO.getAngle());
	    	System.out.println(Robot.SUB_GYRO.getAngle());
	    }
	    protected boolean isFinished() {
	        return false;
	    }
	    protected void end() {
	    }
	    protected void interrupted() {
	    }
	}
