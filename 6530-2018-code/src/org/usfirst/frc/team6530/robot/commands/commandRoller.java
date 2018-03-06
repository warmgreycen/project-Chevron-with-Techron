package org.usfirst.frc.team6530.robot.commands;

import org.usfirst.frc.team6530.robot.OI;
import org.usfirst.frc.team6530.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class commandRoller extends Command {
	
    public commandRoller() {
    	//Sets the required Subsystem
        requires(Robot.SUB_ROLLER);
    }
    protected void initialize() {
    }
    protected void execute() {    	
    	//telling the robot that the operator controller triggers control the motors
    	Robot.SUB_ROLLER.RollerDrive(OI.OPERATOR);
    }
    protected boolean isFinished() {
        return false;
    }
    protected void end() {
    }
    protected void interrupted() {
    }
}
