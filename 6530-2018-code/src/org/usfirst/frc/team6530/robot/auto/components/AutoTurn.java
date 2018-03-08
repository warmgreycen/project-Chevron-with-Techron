package org.usfirst.frc.team6530.robot.auto.components;

import org.usfirst.frc.team6530.robot.Robot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurn extends Command implements PIDOutput{
	
	PIDController turnController;
    double rotateToAngleRate;
    
    /* The following PID Controller coefficients will need to be tuned */
    /* to match the dynamics of your drive system.  Note that the      */
    /* SmartDashboard in Test mode has support for helping you tune    */
    /* controllers by displaying a form where you can enter new P, I,  */
    /* and D constants and test the mechanism.                         */
    
    static final double kP = 0.002;
    static final double kI = 0.001;
    static final double kD = 0.00;
    static final double kF = 0.00;
    
    static final double kToleranceDegrees = 2.0f;    
    
    double kTargetAngleDegrees, leftValue, rightValue, currentAngle, difference;
    boolean isStopped = false;

    public AutoTurn(double kTargetAngleDegrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.kTargetAngleDegrees = kTargetAngleDegrees;
    	requires(Robot.SUB_DRIVE);
    	requires(Robot.SUB_GYRO);
    	turnController = new PIDController(kP, kI, kD, kF, Robot.SUB_GYRO.getAHRS(), this);
        turnController.setInputRange(-180.0f,  180.0f);
        //turnController.setOutputRange(-1.0, 1.0);
        turnController.setOutputRange(-0.30, 0.30);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        //turnController.setContinuous(true);
        turnController.setContinuous(false);
        turnController.disable();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.SUB_GYRO.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.SUB_GYRO.getAngle();
    	difference = kTargetAngleDegrees - currentAngle;
    	System.out.println("Current Angle: "+currentAngle);
    	System.out.println("Target: "+kTargetAngleDegrees);
    	System.out.println("Difference: "+difference);
    	if (!turnController.isEnabled()) {
			turnController.setSetpoint(kTargetAngleDegrees);
			rotateToAngleRate = 0; // This value will be updated in the pidWrite() method.
			turnController.enable();
		}
		leftValue = rotateToAngleRate;
		rightValue = -rotateToAngleRate;
		Robot.SUB_DRIVE.setDriveValue(leftValue,  rightValue);
		
//		if(rotateToAngleRate >= -0.2 && rotateToAngleRate <= 0.2) {
//    		turnController.disable();
//    		isStopped = true;
//    	}
		Timer.delay(0.005);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(difference) < 0.5) {
			turnController.disable();
			Robot.SUB_DRIVE.brake();
			isStopped = true;
			System.out.println("Done turning");
			return isStopped;
    	}
		else {
			return false;
		}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.SUB_DRIVE.brake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.SUB_DRIVE.brake();
    }

	@Override
	public void pidWrite(double output) {
		rotateToAngleRate = output;
	}
}
