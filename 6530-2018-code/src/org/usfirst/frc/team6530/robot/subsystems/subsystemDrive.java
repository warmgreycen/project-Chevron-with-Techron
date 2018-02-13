package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Robot;
import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.OI;
import org.usfirst.frc.team6530.robot.commands.ManualCommandDrive;
import org.usfirst.frc.team6530.robot.util.Xbox;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;


public class subsystemDrive extends Subsystem {
	    	// Put methods for controlling this subsystem
	    	// here. Call these from Commands.
	    	TalonSRX leftMotor1 = new TalonSRX(Constants.LEFT_SLAVE1);
		    TalonSRX leftMotor2 = new TalonSRX(Constants.LEFT_SLAVE2);
		    TalonSRX leftMotor3 = new TalonSRX(Constants.LEFT_MASTER);
		    TalonSRX rightMotor1 = new TalonSRX(Constants.RIGHT_SLAVE1);
		    TalonSRX rightMotor2 = new TalonSRX(Constants.RIGHT_SLAVE2);
		    TalonSRX rightMotor3 = new TalonSRX(Constants.RIGHT_MASTER);
		    
	    	
		    private boolean leftInverted = false;
		    private boolean rightInverted = true;
		    
	
	    	
	    	public void initDefaultCommand() {
	    		// Set the default command for a subsystem here.
	    		setDefaultCommand(new ManualCommandDrive());
	    	}

	    	public void DriveWithJoystick(Joystick joy) {
	    		
	    		double JoystickLeftVal = Xbox.RIGHT_Y(joy);
	    		double JoystickRightVal = Xbox.LEFT_Y(joy);
	    		
	    
	    		
	    		
	    		// set Joystick to 0 if they are between -0.2 and 0.2
	    		if(JoystickLeftVal > -0.1 && JoystickLeftVal < 0.1) {
	    			JoystickLeftVal = 0;
	    		}
	    		if(JoystickRightVal > -0.1 && JoystickRightVal < 0.1) {
	    			JoystickRightVal = 0;
	    		}
	    		
	    		
	    		rightMotor1.set(ControlMode.PercentOutput, JoystickRightVal);
	    		rightMotor2.set(ControlMode.PercentOutput, JoystickRightVal);
	    		rightMotor3.set(ControlMode.PercentOutput, JoystickRightVal);
	    		
	    		leftMotor1.set(ControlMode.PercentOutput, JoystickLeftVal);
	    		leftMotor2.set(ControlMode.PercentOutput, JoystickLeftVal);
	    		leftMotor3.set(ControlMode.PercentOutput, JoystickLeftVal);
	    		
	    		leftMotor1.setInverted(leftInverted);
	    	    leftMotor2.setInverted(leftInverted);
	    	    leftMotor3.setInverted(leftInverted);
	    	    rightMotor1.setInverted(rightInverted);
	    	    rightMotor2.setInverted(rightInverted);
	    	    rightMotor3.setInverted(rightInverted);
	    	}
	    		
	    	
	    	
	    	public void setDriveValue(double RightVal, double LeftVal) {
	    		rightMotor1.set(ControlMode.Position, RightVal);
	    		rightMotor2.set(ControlMode.Position, RightVal);
	    		rightMotor3.set(ControlMode.Position, RightVal);
	    		
	    		leftMotor1.set(ControlMode.Position, LeftVal);
	    		leftMotor2.set(ControlMode.Position, LeftVal);
	    		leftMotor3.set(ControlMode.Position, LeftVal);
	    	}


	    }	   