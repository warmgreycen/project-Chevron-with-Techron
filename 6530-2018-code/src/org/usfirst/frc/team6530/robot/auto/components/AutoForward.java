package org.usfirst.frc.team6530.robot.auto.components;

import org.usfirst.frc.team6530.robot.Robot;

//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *This command moves the robot in a straight line forward or backwards, slowing it down as it is
 *within 2 ft of its intended distance and course-correcting with the gyro as needed.
 */
public class AutoForward extends Command {
	
	private double leftDistance, rightDistance, finalDistance;
	private double leftSpeed, rightSpeed;
	private double lastLeftDistance, lastRightDistance = 0;
	private boolean isRightStopped = false; 
	private boolean isLeftStopped = false;

    public AutoForward(double finalDistance) { //This arguments is the distances we want the robot to move.
    		this.finalDistance = finalDistance; //Makes local copies of these arguments to use during execute()
    		requires(Robot.SUB_DRIVE);
    		requires(Robot.SUB_ENCODERS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		SmartDashboard.putNumber("Final Distance", finalDistance);
    		Robot.SUB_ENCODERS.encoderReset(); //Sets encoder count to zero
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		System.out.println("Executing");
    		leftDistance = Robot.SUB_ENCODERS.getLeftEncoderDistance(); //Find distances traveled so far
    		rightDistance = Robot.SUB_ENCODERS.getRightEncoderDistance();
    		leftSpeed = Robot.SUB_DRIVE.getLeftMotorSpeed();
    		rightSpeed = Robot.SUB_DRIVE.getRightMotorSpeed();
    		//System.out.println(leftSpeed);
    		if(leftSpeed == 0.0) {
    			leftSpeed = 0.5;
    		}
    		if(rightSpeed == 0.0) {
    			rightSpeed = 0.5;
    		}
    		
    		isLeftStopped = Robot.SUB_DRIVE.autoDrive(leftDistance, lastLeftDistance, finalDistance, leftSpeed);
    		isRightStopped = Robot.SUB_DRIVE.autoDrive(rightDistance, lastRightDistance, finalDistance, rightSpeed);
    		
    		lastLeftDistance = leftDistance;
    		lastRightDistance = rightDistance;
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (isRightStopped && isLeftStopped);//Stops this command when robot has gone its specified distance.
    }

    // Called once after isFinished returns true
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		Robot.SUB_DRIVE.setDriveValue(0, 0);
    		isRightStopped = true;
    }
}
