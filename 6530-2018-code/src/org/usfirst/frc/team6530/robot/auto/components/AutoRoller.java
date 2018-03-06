package org.usfirst.frc.team6530.robot.auto.components;

import org.usfirst.frc.team6530.robot.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import edu.wpi.first.wpilibj.command.Command;


public class AutoRoller extends Command implements ActionListener {
	
	String mode;
	Timer timer;
	boolean isDone = false;
	
    public AutoRoller() {
    	timer = new Timer(5000, this);
        requires(Robot.SUB_ROLLER);
    }

    protected void initialize() {
    	timer.start();
    	Robot.SUB_ROLLER.spit();
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return isDone;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Robot.SUB_ROLLER.spit();
		timer.stop();
		isDone = true;
	}
}
