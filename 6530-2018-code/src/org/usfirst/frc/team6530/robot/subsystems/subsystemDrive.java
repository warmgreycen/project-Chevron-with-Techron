package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.Robot;
//import org.usfirst.frc.team6530.robot.commands.ManualCommandDrive;
import org.usfirst.frc.team6530.robot.util.Xbox;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**    
 *		WPI doesn't support 6 cim tank drive or TalonSRX, so this is all custom	
 *		@author @git{ WarmGreycen	}
 */			
public class subsystemDrive extends Subsystem {
    double kPDrive, kIDrive, kDDrive, error, proportion, integral, deriv, lastError, targetSpeed;
    double totalError = 0;
    double deadZoneDrive = 24; //24in
	double kP, kI, kD;
    boolean isStopped = false;
	
/**
 * I set up my motors here babyyy
 * slaves follow masters. This is in the CTRE documentation, not me being a douche
 */
//	TalonSRX leftMotor1 = new TalonSRX(Constants.LEFT_SLAVE1);
//    TalonSRX leftMotor2 = new TalonSRX(Constants.LEFT_SLAVE2);
//    TalonSRX leftMotor3 = new TalonSRX(Constants.LEFT_MASTER);
//    TalonSRX rightMotor1 = new TalonSRX(Constants.RIGHT_SLAVE1);
//    TalonSRX rightMotor2 = new TalonSRX(Constants.RIGHT_SLAVE2);
//    TalonSRX rightMotor3 = new TalonSRX(Constants.RIGHT_MASTER);
    Victor leftMotor = new Victor(0);
    Victor rightMotor= new Victor(1);
    
	public subsystemDrive() {
		//super("Drive", 1, 0, 0);
		//SmartDashboard.putNumber("kP", kP);
		//SmartDashboard.putNumber("kI", kI);
		//SmartDashboard.putNumber("kD", kD);
		//SmartDashboard.putNumber("deadZone", deadZoneDrive);
	}
	/** Create constant to apply left motor invert */
    public static final double leftify(double left) {
		return left * (Constants.LEFT_MOTOR_INVERT ? -1.0 : 1.0);
	}
    /** Create constant to apply right motor invert */
	public static final double rightify(double right) {
		return right * (Constants.RIGHT_MOTOR_INVERT ? -1.0 : 1.0);
	}
	public void initDefaultCommand() {
		//setDefaultCommand(new ManualCommandDrive());
	}
/**
 * DriveWithJoystick is simple, right joystick = right motors and vice versa
 * @param joy: Input is coming from a joystick
 */
//	public void DriveWithJoystick(Joystick joy) {
//		double JoystickLeftVal = Xbox.RIGHT_Y(joy);
//		double JoystickRightVal = Xbox.LEFT_Y(joy);
//		
//		// Deadzone: set Joystick to 0 if they are between -0.1 and 0.1
//		if(JoystickLeftVal > -0.1 && JoystickLeftVal < 0.1) {
//			JoystickLeftVal = 0;
//		}
//		if(JoystickRightVal > -0.1 && JoystickRightVal < 0.1) {
//			JoystickRightVal = 0;
//		}
////		rightMotor1.set(ControlMode.PercentOutput, -JoystickRightVal);
////		rightMotor2.set(ControlMode.PercentOutput, -JoystickRightVal);
////		rightMotor3.set(ControlMode.PercentOutput, -JoystickRightVal);
////		
////		leftMotor1.set(ControlMode.PercentOutput, JoystickLeftVal);
////		leftMotor2.set(ControlMode.PercentOutput, JoystickLeftVal);
////		leftMotor3.set(ControlMode.PercentOutput, JoystickLeftVal);
//		setDriveValue(JoystickLeftVal, JoystickRightVal);
//	}
//	
//	 /** simple rocket league drive code; independent rotation and acceleration */
//    public void driveRLTank(Joystick joy) {
//    	double adder = Xbox.RT(joy) - Xbox.LT(joy);
//    	double left = adder + (Xbox.LEFT_X(joy) / 1.333333);
//    	double right = adder - (Xbox.LEFT_X(joy) / 1.333333);
//    	
//    	//Quick Truncate
//    	left = (left > 1.0 ? 1.0 : (left < -1.0 ? -1.0 : left));
//    	right = (right > 1.0 ? 1.0 : (right < -1.0 ? -1.0 : right));
//    	    	
//    	leftMotor1.set(ControlMode.PercentOutput, rightify(left));
//    		leftMotor2.set(ControlMode.PercentOutput, rightify(left));
//    			rightMotor1.set(ControlMode.PercentOutput, leftify(right));
//    				rightMotor2.set(ControlMode.PercentOutput, leftify(right));
//    					leftMotor3.set(ControlMode.PercentOutput,rightify(left));
//    						rightMotor3.set(ControlMode.PercentOutput,leftify(right));
//    }
//    
//    
//    /** drive code where rotation is dependent on acceleration , just like a car */
//    public void driveForza(Joystick joy) {
//    	double left = 0, 
//    		   right = 0;
//    	double acceleration = Xbox.RT(joy) - Xbox.LT(joy);
//    	
//    	if (Xbox.LEFT_X(joy) < 0) {
//    		right = acceleration;
//    		left = (acceleration * ((2 * (1 - Math.abs(Xbox.LEFT_X(joy)))) - 1)) ; 
//    	} else if (Xbox.LEFT_X(joy) > 0) {
//    		left = acceleration;
//    		right = (acceleration * ((2 * (1 - Math.abs(Xbox.LEFT_X(joy)))) - 1)) ; 
//    	} else {
//    		left = acceleration;
//    		right = acceleration;
//    		
//    	leftMotor1.set(ControlMode.PercentOutput, leftify(left));
//    		leftMotor2.set(ControlMode.PercentOutput, leftify(left));
//    			rightMotor1.set(ControlMode.PercentOutput, rightify(right));
//    				rightMotor2.set(ControlMode.PercentOutput, rightify(right));
//    					leftMotor3.set(ControlMode.PercentOutput,leftify(left));
//    						rightMotor3.set(ControlMode.PercentOutput,rightify(right));
//    	}
//    }
	
	public void setDriveValue(double RightVal, double LeftVal) {
//		rightMotor1.set(ControlMode.PercentOutput, RightVal);
//		rightMotor2.set(ControlMode.PercentOutput, RightVal);
//		rightMotor3.set(ControlMode.PercentOutput, RightVal);
//		
//		leftMotor1.set(ControlMode.PercentOutput, -LeftVal);
//		leftMotor2.set(ControlMode.PercentOutput, -LeftVal);
//		leftMotor3.set(ControlMode.PercentOutput, -LeftVal);
		
		leftMotor.set(-LeftVal);
		rightMotor.set(RightVal);
	}

	/** drive based on the gyro input */
	public void gyroMove(double speed, double angle){
		System.out.println("Gyro offset:"+Robot.SUB_GYRO.getYaw());
		setDriveValue(speed - .01*(Robot.SUB_GYRO.getYaw() - angle), speed + .01*(Robot.SUB_GYRO.getYaw() - angle));
		setDriveValue(speed, speed);
		//System.out.println(speed);
	}
	
	/**drive mode in code based auton */
	public boolean autoDrive(double distance, double lastDistance, double finalDistance, double speed) {
		targetSpeed = pidCalc(distance, lastDistance, finalDistance, speed);
		setDriveValue(targetSpeed, targetSpeed);
		if(targetSpeed == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public double getLeftMotorSpeed() {
		return leftMotor.get();
	}
	
	//public boolean autoRotate(double currentAngle, double lastAngle, double finalAngle, double turnSpeed) {
	//	targetSpeed = pidCalc(currentAngle);
	//}
	
	public double pidCalc(double currentVal, double lastVal, double finalVal, double speed) {
		kPDrive = 1;
		kIDrive = 0.1;
		kDDrive = 0;
		deadZoneDrive = 24;
		
		error = finalVal - currentVal;
		lastError = finalVal - lastVal;
	//Total Error Calculations	
		if(error < deadZoneDrive && error != 0) {//Left motors
			totalError += error;
		}
		else if(error != 0){
			totalError = 0;
		}
		else {
			isStopped = true;
		}

	//P, I, and D Calculations
		proportion = error * kPDrive;
		integral = totalError * kIDrive;
		deriv = (error-lastError) * kDDrive;
		
		speed += proportion + integral + deriv;
		speed /= 200;
		System.out.println("Adjusted speed:"+speed);
		return speed;
	}
	
	public void brake() {
		leftMotor.stopMotor();
		rightMotor.stopMotor();
	}
	
}

