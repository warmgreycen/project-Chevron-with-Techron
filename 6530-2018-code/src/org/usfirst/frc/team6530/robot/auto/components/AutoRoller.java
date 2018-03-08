//package org.usfirst.frc.team6530.robot.auto.components;
//
//import org.usfirst.frc.team6530.robot.Constants;
//import org.usfirst.frc.team6530.robot.Robot;
//import edu.wpi.first.wpilibj.command.Command;
//
//
//public class AutoRoller extends Command {
//	String mode;
//
//    public AutoRoller(String mode) {
//      this.mode = mode;
//      requires(Robot.SUB_ROLLER);
//      setTimeout(Constants.ROLLER_CLAW_TIMEOUT);
//    }
//    protected void initialize() {
//    }
//    protected void execute() {
//    	if(mode == "spit") {
//    		Robot.SUB_ROLLER.spit();
//    	}
//    	else {
//    		Robot.SUB_ROLLER.intake();
//    	}
//    }
//    protected boolean isFinished() {
//        return isTimedOut();
//    }
//    protected void end() {
//    	Robot.SUB_ROLLER.stop();
//    }
//    protected void interrupted() {
//    	Robot.SUB_ROLLER.stop();
//    }
//}