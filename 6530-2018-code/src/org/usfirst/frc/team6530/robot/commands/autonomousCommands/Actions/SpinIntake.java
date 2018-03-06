//package org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions;
//import edu.wpi.first.wpilibj.SpeedController;
//import edu.wpi.first.wpilibj.command.Command;
//
//import org.usfirst.frc.team6530.robot.enumeration.Direction;
//import org.usfirst.frc.team6530.robot.Robot;
//
///**
// *
// */
//public class SpinIntake extends Command {
//	private Direction spinDirection;
//	private boolean stopAtEnd;
//	private SpeedController speedController;
//	/**
//	 * Spin the intake wheels
//	 * 
//	 * @param spinDirectionIn - true is spin direction in; false if out
//	 * @param stopAtEnd - set motors to 0 when button is released
//	 */
//	public SpinIntake(Direction spinDirectionIn, boolean stopAtEnd) {
//		this.spinDirection=spinDirectionIn;
//		this.stopAtEnd=stopAtEnd;
//		requires(Robot.SUB_ROLLER);
//	}
//
//	// Called just before this Command runs the first time
//	//@Override
//	protected void initialize() {
//		speedController = Robot.SUB_ROLLER.speedController;
//	}
//
//	// Called repeatedly when this Command is scheduled to run
//	@Override
//	protected void execute() {
//		if (stopAtEnd) {
//			speedController.set(0);
//		}
//		else { 
//			Robot.SUB_ROLLER.spinIntake(spinDirection);
//		}
//	}
//
//	// Make this return true when this Command no longer needs to run execute()
//	//@Override
//	protected boolean isFinished() {
//		return false;
//	}
//
//	// Called once after isFinished returns true
//	@Override
//	protected void end() {
//	}
//
//	// Called when another command which requires one or more of the same
//	// subsystems is scheduled to run
//	@Override
//	protected void interrupted() {
//	}
//}