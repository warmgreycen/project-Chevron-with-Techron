package org.usfirst.frc.team6530.robot.commands;

import org.usfirst.frc.team6530.robot.OI;
import org.usfirst.frc.team6530.robot.Robot;
import org.usfirst.frc.team6530.robot.subsystems.Limelight;

import edu.wpi.first.wpilibj.command.Command;

public class LimelightLEDOFF extends Command {
	
    public LimelightLEDOFF() {
    	//Sets the required Subsystem
        requires(Robot.LIMELIGHT);
    }
    protected void initialize() {
    	Robot.LIMELIGHT.setLEDs(Limelight.LIMELIGHT_LED_OFF);
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