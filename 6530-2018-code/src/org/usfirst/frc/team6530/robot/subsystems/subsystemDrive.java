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
import edu.wpi.first.wpilibj.command.Subsystem;

/**    finally a 6 cim tank drive
 *				that	WOOOORKS
 */			
//@SuppressWarnings("deprecation")
public class subsystemDrive extends Subsystem {
    double kP = 1.2;
    double kI = 0;
    double kD = 0;
    
    //double rightSpeed, leftSpeed, leftError, rightError, lastLeftError, lastRightError, leftProportion, rightProportion;
    //double leftIntegral, rightIntegral, leftDeriv, rightDeriv;
    //double totalRightError, totalLeftError = 0;
    double error, proportion, integral, deriv, lastError;
    double totalError = 0;
    double deadZone = 24; //24in
    boolean isStopped = false;
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	TalonSRX leftMotor1 = new TalonSRX(Constants.LEFT_SLAVE1);
    TalonSRX leftMotor2 = new TalonSRX(Constants.LEFT_SLAVE2);
    TalonSRX leftMotor3 = new TalonSRX(Constants.LEFT_MASTER);
    TalonSRX rightMotor1 = new TalonSRX(Constants.RIGHT_SLAVE1);
    TalonSRX rightMotor2 = new TalonSRX(Constants.RIGHT_SLAVE2);
    TalonSRX rightMotor3 = new TalonSRX(Constants.RIGHT_MASTER);
    
	
	
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new ManualCommandDrive());
	}

	public void DriveWithJoystick(Joystick joy) {
		
		double JoystickLeftVal = Xbox.RIGHT_Y(joy);
		double JoystickRightVal = Xbox.LEFT_Y(joy);
		
		
		
		// set Joystick to 0 if they are between -0.1 and 0.1
		if(JoystickLeftVal > -0.2 && JoystickLeftVal < 0.2) {
			JoystickLeftVal = 0;
		}
		if(JoystickRightVal > -0.2 && JoystickRightVal < 0.2) {
			JoystickRightVal = 0;
		}
		
		
		rightMotor1.set(ControlMode.PercentOutput, JoystickRightVal);
		rightMotor2.set(ControlMode.PercentOutput, JoystickRightVal);
		rightMotor3.set(ControlMode.PercentOutput, JoystickRightVal);
		
		leftMotor1.set(ControlMode.PercentOutput, JoystickLeftVal);
		leftMotor2.set(ControlMode.PercentOutput, JoystickLeftVal);
		leftMotor3.set(ControlMode.PercentOutput, JoystickLeftVal);
		
		
		
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

	/**
	 * Tank drive for automated driving
	 * @param left - Speed for left motor
	 * @param right - Speed for right motor
	 */
	//public void autonTankDrive(double left, double right){
	//	leftMotor3.set(ControlMode.PercentOutput, (left));
	//	rightMotor3.set(ControlMode.PercentOutput, (right));
	//}
	
	public void gyroStraight(double speed, double angle){
		setDriveValue(speed - .01*(Robot.SUB_GYRO.getYaw() - angle), speed + .01*(Robot.SUB_GYRO.getYaw() - angle));
	}
	
	
	public boolean autoDrive(double distance, double lastDistance, double finalDistance, double speed) {
		//rightSpeed = getRightMotorSpeed();
		//leftSpeed = getLeftMotorSpeed();
		//rightError = finalDistance - rightDistance;
		//leftError = finalDistance - leftDistance;
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

