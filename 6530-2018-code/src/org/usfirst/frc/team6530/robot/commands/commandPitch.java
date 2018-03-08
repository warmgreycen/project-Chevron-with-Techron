//package org.usfirst.frc.team6530.robot.commands;
//
//import org.usfirst.frc.team6530.robot.OI;
//import org.usfirst.frc.team6530.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
//public class commandPitch extends Command {
//	
//    public commandPitch() {
//    	//Sets the required Subsystem
//        requires(Robot.SUB_PITCH);
//    }
//    protected void initialize() {
//    }
//    protected void execute() {    
//    	//sets the triggers on driver controller to control the pitch motor
//double speed = OI.DRIVER.getRawAxis(5);
//    	
//    	if(OI.DRIVER.getRawAxis(2) > 0.15){
//    		speed = OI.DRIVER.getRawAxis(2);
//    	}
//    	
//    	else if(OI.DRIVER.getRawAxis(3) > 0.15){
//    		speed = -OI.DRIVER.getRawAxis(3);
//    	}
//    	
//    	else{
//    		speed = 0;
//    	}
//    	
//    	Robot.SUB_PITCH.intake(speed);
//    }
//    protected boolean isFinished() {
//        return false;
//    }
//    protected void end() {
//    }
//    protected void interrupted() {
//    }
//}