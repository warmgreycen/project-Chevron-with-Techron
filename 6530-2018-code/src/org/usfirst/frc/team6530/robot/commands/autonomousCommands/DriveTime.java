package org.usfirst.frc.team6530.robot.commands.autonomousCommands;
import org.usfirst.frc.team6530.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveTime extends Command {
	private double m_speed, m_rotate, m_time;

	public DriveTime(double speed, double rotate, double time) {
		requires(Robot.AUTO_DRIVE);
		m_speed = speed;
		m_rotate = rotate;
		m_time = time;
	}

	protected void initialize() {
		setTimeout(m_time);
		
	}

	protected void execute() {
		Robot.AUTO_DRIVE.ArcadeDrive(m_speed, m_rotate, false);
	}
	protected boolean isFinished() {
		return isTimedOut();
	}
	protected void end() {
		Robot.AUTO_DRIVE.ArcadeDrive(0,0, false);
	}
	protected void interrupted() {
		end();
	}
}