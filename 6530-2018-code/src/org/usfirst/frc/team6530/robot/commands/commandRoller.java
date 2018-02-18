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
    	
    	Robot.SUB_ROLLER.RollerDrive(OI.DRIVER);
    	//Robot.SUB_ROLLER.intake(speed);
    	
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