package org.usfirst.frc.team6530.robot.commands;

import org.usfirst.frc.team6530.robot.Robot;
import org.usfirst.frc.team6530.robot.OI;
import edu.wpi.first.wpilibj.command.Command;

public class commandClimber extends Command {

	public commandClimber() {
		requires(Robot.SUB_CLIMBER);
	}
	protected void initialize() {
	}
	protected void execute() {
		double move = OI.OPERATOR.getRawAxis(5); //right joystick on Operator
		
		/**
		 * if the climber hits either the top or the bottom switch,
		 * it will turn the joystick input into zero unless it 
		 * goes the other direction
		 */

		if (move < 0.25 && Robot.SUB_CLIMBER.getBottomSwitch())
		{
			move = 0.25;
		}
		
		if (move > 0.25 && Robot.SUB_CLIMBER.getTopSwitch())
		{
			move = 0.25;
		}

		Robot.SUB_CLIMBER.moveClimber(move);
	}
	protected boolean isFinished() {
		return false;
	}
	protected void end() {
	}
	protected void interrupted() {
	}
}
