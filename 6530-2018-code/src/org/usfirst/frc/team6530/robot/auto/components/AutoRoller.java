package org.usfirst.frc.team6530.robot.auto.components;

import org.usfirst.frc.team6530.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class AutoRoller extends Command {

    public AutoRoller(double timeOut) {

      requires(Robot.SUB_ROLLER);
      setTimeout(timeOut); 
    }
    protected void initialize() {
    }
    protected void execute() {
    	Robot.SUB_ROLLER.spit();
    }
    protected boolean isFinished() {
        return isTimedOut();
    }
    protected void end() {
    	Robot.SUB_ROLLER.stop();
    }
    protected void interrupted() {
    }
}