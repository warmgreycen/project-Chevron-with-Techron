package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Robot;
import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.OI;
import org.usfirst.frc.team6530.robot.commands.ManualCommandDrive;
import org.usfirst.frc.team6530.robot.util.Xbox;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**    finally a 6 cim tank drive
 *				that	WOOOORKS
 */			
//@SuppressWarnings("deprecation")
public class subsystemDrive extends Subsystem { ;
///define variables
	TalonSRX leftMotor1 = new TalonSRX(Constants.LEFT_SLAVE1);
    TalonSRX leftMotor2 = new TalonSRX(Constants.LEFT_SLAVE2);
    TalonSRX leftMotor3 = new TalonSRX(Constants.LEFT_MASTER);
    TalonSRX rightMotor1 = new TalonSRX(Constants.RIGHT_SLAVE1);
    TalonSRX rightMotor2 = new TalonSRX(Constants.RIGHT_SLAVE2);
    TalonSRX rightMotor3 = new TalonSRX(Constants.RIGHT_MASTER);
    
    double kP;
    double kI;
    double kD;
    double finalDistance;
    
    //double rightSpeed, leftSpeed, leftError, rightError, lastLeftError, lastRightError, leftProportion, rightProportion;
    //double leftIntegral, rightIntegral, leftDeriv, rightDeriv;
    //double totalRightError, totalLeftError = 0;
    
    double 	error, 
    		proportion, 
    		integral, 
    		deriv, 
    		lastError;
    
    double totalError = 0;
    double deadZone = 24; //24in
    boolean isStopped = false;   
    
	public subsystemDrive() {
		SmartDashboard.putNumber("Final Distance", finalDistance);
		SmartDashboard.putNumber("kP", kP);
		SmartDashboard.putNumber("kI", kI);
		SmartDashboard.putNumber("kD", kD);
	}
	
	/** apply left motor invert */
    public static final double leftify(double left) {
		return left * (Constants.LEFT_MOTOR_INVERT ? -1.0 : 1.0);
	}
    /** apply right motor invert */
	public static final double rightify(double right) {
		return right * (Constants.RIGHT_MOTOR_INVERT ? -1.0 : 1.0);
	}
	public void initDefaultCommand() {
		setDefaultCommand(new ManualCommandDrive());
	}

	public void DriveWithJoystick(Joystick joy) {
		
		double JoystickLeftVal = Xbox.RIGHT_Y(joy);
		double JoystickRightVal = Xbox.LEFT_Y(joy);
		
		// set Joystick to 0 if they are between -0.1 and 0.1
		if(JoystickLeftVal > -0.1 && JoystickLeftVal < 0.1) {
			JoystickLeftVal = 0;
		}
		if(JoystickRightVal > -0.1 && JoystickRightVal < 0.1) {
			JoystickRightVal = 0;
		}
		rightMotor1.set(ControlMode.PercentOutput, -JoystickRightVal);
		rightMotor2.set(ControlMode.PercentOutput, -JoystickRightVal);
		rightMotor3.set(ControlMode.PercentOutput, -JoystickRightVal);
		
		leftMotor1.set(ControlMode.PercentOutput, JoystickLeftVal);
		leftMotor2.set(ControlMode.PercentOutput, JoystickLeftVal);
		leftMotor3.set(ControlMode.PercentOutput, JoystickLeftVal);
	}
	
	 /** simple rocket league drive code; independent rotation and acceleration */
    public void driveRLTank(Joystick joy) {
    	double adder = Xbox.RT(joy) - Xbox.LT(joy);
    	double left = adder + (Xbox.LEFT_X(joy) / 1.333333);
    	double right = adder - (Xbox.LEFT_X(joy) / 1.333333);
    	
    	//Quick Truncate
    	left = (left > 1.0 ? 1.0 : (left < -1.0 ? -1.0 : left));
    	right = (right > 1.0 ? 1.0 : (right < -1.0 ? -1.0 : right));
    	    	
    	leftMotor1.set(ControlMode.PercentOutput, leftify(left));
    		leftMotor2.set(ControlMode.PercentOutput, leftify(left));
    			rightMotor1.set(ControlMode.PercentOutput, rightify(right));
    				rightMotor2.set(ControlMode.PercentOutput, rightify(right));
    					leftMotor3.set(ControlMode.PercentOutput,leftify(left));
    						rightMotor3.set(ControlMode.PercentOutput,rightify(right));
    }
    
    
    /** drive code where rotation is dependent on acceleration 
     * @param radius 0.00-1.00, 1 being zero radius and 0 being driving in a line */
    public void driveForza(Joystick joy, double ramp, double radius) {
    	double left = 0, 
    		   right = 0;
    	double acceleration = Xbox.RT(joy) - Xbox.LT(joy);
    	
    	if (Xbox.LEFT_X(joy) < 0) {
    		right = acceleration;
    		left = (acceleration * ((2 * (1 - Math.abs(Xbox.LEFT_X(joy)))) - 1)) / radius; 
    	} else if (Xbox.LEFT_X(joy) > 0) {
    		left = acceleration;
    		right = (acceleration * ((2 * (1 - Math.abs(Xbox.LEFT_X(joy)))) - 1)) / radius; 
    	} else {
    		left = acceleration;
    		right = acceleration;
    		
    	leftMotor1.set(ControlMode.PercentOutput, leftify(left));
    		leftMotor2.set(ControlMode.PercentOutput, leftify(left));
    			rightMotor1.set(ControlMode.PercentOutput, rightify(right));
    				rightMotor2.set(ControlMode.PercentOutput, rightify(right));
    					leftMotor3.set(ControlMode.PercentOutput,leftify(left));
    						rightMotor3.set(ControlMode.PercentOutput,rightify(right));
    	}
    }
	
	public void setDriveValue(double RightVal, double LeftVal) {
		rightMotor1.set(ControlMode.Position, RightVal);
		rightMotor2.set(ControlMode.Position, RightVal);
		rightMotor3.set(ControlMode.Position, RightVal);
		
		leftMotor1.set(ControlMode.Position, -LeftVal);
		leftMotor2.set(ControlMode.Position, -LeftVal);
		leftMotor3.set(ControlMode.Position, -LeftVal);
	}
		
		public double getRightMotorSpeed() {
			return ((WPI_TalonSRX) rightMotor3).get();
		}
		
		public double getLeftMotorSpeed() {
			return ((WPI_TalonSRX) leftMotor3).get();
		}

	public void gyroStraight(double speed, double angle){
		setDriveValue(speed - .01*(Robot.SUB_GYRO.getYaw() - angle), speed + .01*(Robot.SUB_GYRO.getYaw() - angle));
	}
	
	
	public boolean autoDrive(double distance, double lastDistance, double speed) {
		//rightSpeed = getRightMotorSpeed();
		//leftSpeed = getLeftMotorSpeed();
		//rightError = finalDistance - rightDistance;
		//leftError = finalDistance - leftDistance;
		kP = SmartDashboard.getNumber("kP", 0);
		kI = SmartDashboard.getNumber("kI", 0);
		kD = SmartDashboard.getNumber("kD", 0);
		
		error = finalDistance - distance;
		lastError = finalDistance - lastDistance;
	//Total Error Calculations	
		if(error < deadZone && error != 0) {//Left motors
			totalError += error;
		}
		else {
			totalError = 0;
			isStopped = true;
		}
		/*if(rightError < deadZone && rightError != 0) {//Right Motors
			totalRightError += rightError;
		}
		else {
			totalRightError = 0;
			isRightStopped = true;
		}
		if(totalLeftError > 50/kI) {//Integral limiting
			totalLeftError = 0;
		}
		totalRightError += rightError;*/
	//P, I, and D Calculations
		proportion = error * kP;
		//rightProportion = rightError * kP;
		integral = totalError * kI;
		//rightIntegral = totalRightError * kI;
		deriv = (error-lastError) * kD;
		//rightDeriv = (rightError-lastRightError) * kD;
		
		//lastLeftError = leftError; //Move these 2 into AutonMove after calling this function for both sides.
		//lastRightError = rightError;
		
		speed += proportion + integral + deriv;
		//rightSpeed += rightProportion + rightIntegral + rightDeriv;
		return isStopped;
		
	}
	
}

