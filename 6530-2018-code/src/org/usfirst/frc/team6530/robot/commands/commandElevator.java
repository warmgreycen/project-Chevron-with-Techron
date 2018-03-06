package org.usfirst.frc.team6530.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6530.robot.OI;
import org.usfirst.frc.team6530.robot.Robot;

/**
 * Controls the elevator.
 */
public class commandElevator extends Command {

	public commandElevator() {
		requires(Robot.SUB_ELEVATOR);
	}
	protected void initialize() {
	}
	protected void execute() {
		Robot.SUB_ELEVATOR.moveElevator(OI.OPERATOR.getY());
	}
	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}