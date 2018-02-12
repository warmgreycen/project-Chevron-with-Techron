package org.usfirst.frc.team6530.robot.commands;


import org.usfirst.frc.team6530.robot.OI;
import org.usfirst.frc.team6530.robot.Robot;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ManualCommandDrive extends Command {
	
	
	
	double lastLeftSpeed;
	double lastRightSpeed;
	
    public ManualCommandDrive() {
    	//sets requirement system
        requires(Robot.SUB_DRIVE);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    
   	
    	Robot.SUB_DRIVE.tankDrive(OI.DRIVER.getRawAxis(1), OI.DRIVER.getRawAxis(5));
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