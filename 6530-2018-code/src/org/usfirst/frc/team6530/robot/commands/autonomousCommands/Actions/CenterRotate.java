package org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions;

import org.usfirst.frc.team6530.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CenterRotate extends Command {

	double currentAngle, lastAngle, finalAngle, turnSpeed;
	
    public CenterRotate(double finalAngle) {;
    		this.finalAngle = finalAngle;
    		requires(Robot.SUB_GYRO);
    		requires(Robot.SUB_DRIVE);
    }
    protected void initialize() {
    }
    protected void execute() {
    		
    }
    protected boolean isFinished() {
        return false;
    }
    protected void end() {
    }

    protected void interrupted() {
    }
}
