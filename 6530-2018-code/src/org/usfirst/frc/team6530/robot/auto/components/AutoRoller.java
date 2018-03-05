package org.usfirst.frc.team6530.robot.auto.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import org.usfirst.frc.team6530.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoRoller extends Command implements ActionListener {
	
	String mode;
	Timer timer;
	boolean isDone = false;
	
    public AutoRoller() {
    		timer = new Timer(5000, this);
        requires(Robot.SUB_ROLLER);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	    	timer.start();
	    	Robot.SUB_ROLLER.spit();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Robot.SUB_ROLLER.spit();
		timer.stop();
		isDone = true;
	}
}
