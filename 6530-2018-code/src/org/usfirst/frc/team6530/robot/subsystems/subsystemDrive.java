package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Robot;
import org.usfirst.frc.team6530.robot.Constants;
//import org.usfirst.frc.team6530.robot.OI;
import org.usfirst.frc.team6530.robot.commands.ManualCommandDrive;
import org.usfirst.frc.team6530.robot.util.Xbox;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**    finally a 6 cim tank drive
 *				that	WOOOORKS
 */			
//@SuppressWarnings("deprecation")
public class subsystemDrive extends Subsystem {
    double kPDrive, kIDrive, kDDrive, error, proportion, integral, deriv, lastError, targetSpeed;
    double totalError = 0;
    double deadZoneDrive = 24; //24in
	double kP, kI, kD;
    boolean isStopped = false;
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	TalonSRX leftMotor1 = new TalonSRX(Constants.LEFT_SLAVE1);
    TalonSRX leftMotor2 = new TalonSRX(Constants.LEFT_SLAVE2);
    TalonSRX leftMotor3 = new TalonSRX(Constants.LEFT_MASTER);
    TalonSRX rightMotor1 = new TalonSRX(Constants.RIGHT_SLAVE1);
    TalonSRX rightMotor2 = new TalonSRX(Constants.RIGHT_SLAVE2);
    TalonSRX rightMotor3 = new TalonSRX(Constants.RIGHT_MASTER);
    
	public subsystemDrive() {
		//super("Drive", 1, 0, 0);
		//SmartDashboard.putNumber("kP", kP);
		//SmartDashboard.putNumber("kI", kI);
		//SmartDashboard.putNumber("kD", kD);
		//SmartDashboard.putNumber("deadZone", deadZoneDrive);
	}
	
	
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
		
		
		rightMotor1.set(ControlMode.PercentOutput, -JoystickRightVal);
		rightMotor2.set(ControlMode.PercentOutput, -JoystickRightVal);
		rightMotor3.set(ControlMode.PercentOutput, -JoystickRightVal);
		
		leftMotor1.set(ControlMode.PercentOutput, JoystickLeftVal);
		leftMotor2.set(ControlMode.PercentOutput, JoystickLeftVal);
		leftMotor3.set(ControlMode.PercentOutput, JoystickLeftVal);
		
		
		
	}
	
	public void setDriveValue(double RightVal, double LeftVal) {
		rightMotor1.set(ControlMode.PercentOutput, RightVal);
		rightMotor2.set(ControlMode.PercentOutput, RightVal);
		rightMotor3.set(ControlMode.PercentOutput, RightVal);
		
		leftMotor1.set(ControlMode.PercentOutput, -LeftVal);
		leftMotor2.set(ControlMode.PercentOutput, -LeftVal);
		leftMotor3.set(ControlMode.PercentOutput, -LeftVal);
		System.out.println("RightVal after being plugged in: "+RightVal);
	}

	/**
	 * Tank drive for automated driving
	 * @param left - Speed for left motor
	 * @param right - Speed for right motor
	 */
	
	public void gyroMove(double speed, double angle){
		//System.out.println("Gyro offset:"+Robot.SUB_GYRO.getYaw());
		//setDriveValue(speed - .01*(Robot.SUB_GYRO.getYaw() - angle), speed + .01*(Robot.SUB_GYRO.getYaw() - angle));
		setDriveValue(speed, speed);
		System.out.println(speed);
	}
	
	
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
	
}

