package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.Robot;

//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *This command moves the robot in a straight line forward or backwards, slowing it down as it is
 *within 3 ft of the way through its intended distance and course-correcting with the gyro as needed.
 */
public class AutonomousMove extends Command {
	
	private double leftDistance, leftSpeed, finalDistance;
	private boolean isStopped = false; 

    public AutonomousMove(double finalDistance) { //This arguments is the distances we want the robot to move.
    	this.finalDistance = finalDistance; //Makes local copies of these arguments to use during execute()
    	requires(Robot.SUB_DRIVE);
    	requires(Robot.SUB_ENCODERS);
    	requires(Robot.SUB_GYRO);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		Robot.SUB_ENCODERS.encoderReset(); //Sets encoder count to zero
    		Robot.SUB_GYRO.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		leftDistance = Robot.SUB_ENCODERS.getLeftEncoderDistance(); //Find distance traveled so far and current speed using left encoder as reference
    		//rightDistance = Robot.SUB_ENCODERS.getRightEncoderDistance();
    		leftSpeed = Robot.SUB_DRIVE.getLeftMotorSpeed();
    		//rightSpeed = Robot.SUB_DRIVE.getRightMotorSpeed();
    		
    		
    		if(leftDistance < finalDistance) {//If the wheels haven't reached their intended distance, check to see how close robot is to final distance
    			if( (finalDistance - leftDistance) <= 36 ) {//If wheels are within 3 ft of final distance, slow them down
    				System.out.println("Checking, with 3 ft on left");
    				Robot.SUB_DRIVE.gyroStraight( (.999*leftSpeed), 0);
    			}
    			else {//If not within 3 ft of final distance, go full speed
    				Robot.SUB_DRIVE.gyroStraight( leftSpeed, 0);
    			}
    		}
    		else {//If robot is at or a bit past final distance, stop it, then end the entire command
    			Robot.SUB_DRIVE.setTankDrive(0, 0);
    			isStopped = true;
    		}
    		
    		/*if(rightDistance < finalDistance){
    			if ( (finalDistance - rightDistance) <= 36 ){
    				System.out.println("Checking, with 3 ft on right");
    				Robot.SUB_DRIVE.gyroStraight( (rightSpeed * .999), 0);
    			}
    			else {
    				driveStraight(-.5, "right");
    			}
    		}
    		else {
    			Robot.SUB_DRIVE.setTankDrive(0, 0);
    			isStopped = true;
    		}*/
    		
    		
    }
    
    /*public void driveStraight(double speed, String side){ //Use gyro to correct any drifts to left or right
    		angleError = Robot.SUB_GYRO.getAngle();
    		//System.out.println(angleError);
    		
    		if(side == "left" && (angleError > 0) ){
    			speed = Robot.SUB_DRIVE.gyroStraight(speed, 0);
    			Robot.SUB_DRIVE.setLeftMotorSpeed(speed);
    		}
    		else if(side == "right" && (angleError < 0) ){
    			speed *= ( 1+Robot.sensorSystem.getCorrectionSpeed(speed) );
    			Robot.SUB_DRIVE.setRightMotorSpeed(speed);
    		}
    }*/
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isStopped;//Stops this command when robot has gone its specified distance.
    }

    // Called once after isFinished returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		Robot.SUB_DRIVE.setTankDrive(0, 0);
    		isStopped = true;
    }
}
