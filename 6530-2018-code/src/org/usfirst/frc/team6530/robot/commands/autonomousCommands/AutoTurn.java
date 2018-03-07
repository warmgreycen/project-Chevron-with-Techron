//package org.usfirst.frc.team6530.robot.commands.autonomousCommands;
//
//import org.usfirst.frc.team6530.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class AutoTurn extends Command {
//	private final double degrees, rate;
//	private double startingOrientation;
//	private static final double LERP_START=60;
//	private static final double LERP_STOP=40;
//	private static final double LERP_END=0.2;
//	private static final double MIN_DEG=0.01;
//    public AutoTurn(double rate, double degrees) {
//        // Use requires() here to declare subsystem dependencies
//        // eg. requires(chassis);
//    	requires(Robot.AUTO_DRIVE);
//    	this.degrees=degrees;
//    	this.rate   =rate;
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	//Robot.gyro.reset();
//    	startingOrientation = Robot.SUB_GYRO.getAngle();
//    	//finalOrientation = Robot.gyro.getAngle() + degrees;
//    }
//    private double degreesRotated() {
//    	return (Robot.SUB_GYRO.getAngle());
//    }
//    private double calcThrottle(double deg, double throttle) {//set throttle given degrees from target
//    	if (deg<MIN_DEG) {//if at correct location, stop
//    		return 0;
//    	}
//    	if (deg<=LERP_STOP) {//if through lerp period, min speed
//    		return LERP_END;
//    	}
//    	if (deg>=LERP_START) {//if not at lerp period, given speed
//    		return throttle;
//    	}
//    	return (deg-LERP_STOP)*(throttle-LERP_END)/(LERP_START-LERP_STOP)+LERP_END;//deceleration/linear interpolation code
//    }
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	//double deg=degrees-degreesRotated();
//    	//double newThrottleLeft=calcThrottle(deg, throttleLeft);
//    	//double newThrottleRight = calcThrottle(deg, throttleRight);
//    	
//    	Robot.AUTO_DRIVE.ArcadeDrive(0, calcThrottle(degreesRotated(), rate), false);
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return (degrees > 0 ? Robot.SUB_GYRO.getAngle() >= startingOrientation + degrees : Robot.SUB_GYRO.getAngle() <= startingOrientation - degrees);
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//}