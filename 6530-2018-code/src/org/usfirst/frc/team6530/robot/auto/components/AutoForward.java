package org.usfirst.frc.team6530.robot.auto.components;

import org.usfirst.frc.team6530.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoForward extends Command implements PIDOutput{
	
	PIDController turnController;
    double rotateToAngleRate;
    
    /* The following PID Controller coefficients will need to be tuned */
    /* to match the dynamics of your drive system.  Note that the      */
    /* SmartDashboard in Test mode has support for helping you tune    */
    /* controllers by displaying a form where you can enter new P, I,  */
    /* and D constants and test the mechanism.                         */
    
    static final double kP = 0.03;
    static final double kI = 0.00;
    static final double kD = 0.00;
    static final double kF = 0.00;
    
    static final double kToleranceDegrees = 2.0f;
    static final double kTargetAngleDegrees = 0;
    
    double leftValue, rightValue, finalDistance, currentDistance, difference, magnitude;
    boolean isStopped = false;

    public AutoForward(double finalDistance) {
    	this.finalDistance = finalDistance;
    	requires(Robot.SUB_DRIVE);
    	requires(Robot.SUB_GYRO);
    	requires(Robot.SUB_ENCODERS);
    	turnController = new PIDController(kP, kI, kD, kF, Robot.SUB_GYRO.getAHRS(), this);
        turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(true);
        turnController.disable();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.SUB_ENCODERS.encoderReset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentDistance = Robot.SUB_ENCODERS.getLeftEncoderDistance();
    	difference = finalDistance - currentDistance;
    	System.out.println("Difference: "+difference);
    	
    	if(!turnController.isEnabled()) {
			// Acquire current yaw angle, using this as the target angle.
			turnController.setSetpoint(Robot.SUB_GYRO.getYaw() );
			rotateToAngleRate = 0; // This value will be updated in the pidWrite() method.
			turnController.enable();
		}
		magnitude = .5;
		leftValue = magnitude + rotateToAngleRate;
		rightValue = magnitude - rotateToAngleRate;
		Robot.SUB_DRIVE.setDriveValue(leftValue,  rightValue);
		if(difference < 0.2) {
			Robot.SUB_DRIVE.setDriveValue(0,0);
			turnController.disable();
			isStopped = true;
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isStopped;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.SUB_DRIVE.setDriveValue(0,0);
    }

	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;
	}
}
