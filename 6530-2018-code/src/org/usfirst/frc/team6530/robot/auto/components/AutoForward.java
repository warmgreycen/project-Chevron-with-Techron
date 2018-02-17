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
	
	private double finalDistance;
	private double leftSpeed, rightSpeed;
	double lastLeftDistance, lastRightDistance, rightDistance, leftDistance;
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
    		//Robot.SUB_DRIVE.enable();
    		//Robot.SUB_DRIVE.setOutputRange(0, 1);
    		//Robot.SUB_DRIVE.setSetpoint(finalDistance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		leftDistance = Robot.SUB_ENCODERS.getLeftEncoderDistance(); //Find distances traveled so far
    		rightDistance = Robot.SUB_ENCODERS.getRightEncoderDistance();
    		leftSpeed = Robot.SUB_ENCODERS.getLeftEncoderSpeed();
    		rightSpeed = Robot.SUB_ENCODERS.getRightEncoderSpeed();
    		System.out.println("Left Distance "+leftDistance);
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
    		//System.out.println("Position: "+Robot.SUB_DRIVE.getPosition() );
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
