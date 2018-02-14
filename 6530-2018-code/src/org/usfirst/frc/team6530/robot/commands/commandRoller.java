package org.usfirst.frc.team6530.robot.commands;

import org.usfirst.frc.team6530.robot.Robot;
import org.usfirst.frc.team6530.robot.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Makes collector/fidget spinner intake/outtake cubes.
 */

public class commandRoller extends Command {

	public commandRoller() {
		requires(Robot.SUB_ROLLER);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	protected void execute() {
		if (OI.OPERATOR.getRawButtonPressed(7))
			Robot.SUB_ROLLER.intake();
		else if (OI.OPERATOR.getRawButtonPressed(8))
			Robot.SUB_ROLLER.outtake();
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