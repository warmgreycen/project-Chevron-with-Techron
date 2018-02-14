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
public class subsystemDrive extends Subsystem {
    double kPDrive, kIDrive, kDDrive, error, proportion, integral, deriv, lastError;
    double totalError = 0;
    double deadZoneDrive = 24; //24in
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
		SmartDashboard.putNumber("kP", kPDrive);
		SmartDashboard.putNumber("kI", kIDrive);
		SmartDashboard.putNumber("kD", kDDrive);
		SmartDashboard.putNumber("deadZone", deadZoneDrive);
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
			return Robot.SUB_ENCODERS.getRightEncoderDistance();
		}
		
		public double getLeftMotorSpeed() {
			return Robot.SUB_ENCODERS.getLeftEncoderDistance();
		}

	/**
	 * Tank drive for automated driving
	 * @param left - Speed for left motor
	 * @param right - Speed for right motor
	 */
	
	public void gyroMove(double speed, double angle){
		setDriveValue(speed - .01*(Robot.SUB_GYRO.getYaw() - angle), speed + .01*(Robot.SUB_GYRO.getYaw() - angle));
	}
	
	
	public boolean autoDrive(double distance, double lastDistance, double finalDistance, double speed) {

		
	}
	
	public double pidCalc() {
		kPDrive = SmartDashboard.getNumber("kP", 0);
		kIDrive = SmartDashboard.getNumber("kI", 0);
		kDDrive = SmartDashboard.getNumber("kD", 0);
		deadZoneDrive = SmartDashboard.getNumber("deadZone", 0);
		
		error = finalDistance - distance;
		lastError = finalDistance - lastDistance;
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
		gyroMove(speed, 0);
		return isStopped;
		
	}
	
	
	
}

