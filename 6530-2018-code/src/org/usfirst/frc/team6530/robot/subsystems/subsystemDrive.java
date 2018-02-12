package org.usfirst.frc.team6530.robot.subsystems
	   /** TalonSRX leftMotor1 = new TalonSRX(Constants.LEFT_SLAVE1);
	    TalonSRX leftMotor2 = new TalonSRX(Constants.LEFT_SLAVE2);
	    TalonSRX leftMotor3 = new TalonSRX(Constants.LEFT_MASTER);
	    TalonSRX rightMotor1 = new TalonSRX(Constants.RIGHT_SLAVE1);
	    TalonSRX rightMotor2 = new TalonSRX(Constants.RIGHT_SLAVE2);
	    TalonSRX rightMotor3 = new TalonSRX(Constants.RIGHT_MASTER);
	    package org.usfirst.frc.team3360.robot.subsystems;
*/
import org.usfirst.frc.team6530.robot.Robot;
import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.OI;
import org.usfirst.frc.team6530.robot.commands.ManualCommandDrive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;



	    /**
	     * An example subsystem.  You can replace me with your own Subsystem.
	     */
public class subsystemDrive extends Subsystem {
	    	// Put methods for controlling this subsystem
	    	// here. Call these from Commands.
	    	/TalonSRX leftMotor1 = new TalonSRX(Constants.LEFT_SLAVE1);
		    TalonSRX leftMotor2 = new TalonSRX(Constants.LEFT_SLAVE2);
		    TalonSRX leftMotor3 = new TalonSRX(Constants.LEFT_MASTER);
		    TalonSRX rightMotor1 = new TalonSRX(Constants.RIGHT_SLAVE1);
		    TalonSRX rightMotor2 = new TalonSRX(Constants.RIGHT_SLAVE2);
		    TalonSRX rightMotor3 = new TalonSRX(Constants.RIGHT_MASTER);
		    
	    	TalonSRX TankDriveR1 = Constants.LEFT_SLAVE1;
	    	TalonSRX TankDriveR2 = RobotMap.TankDriveR2;
	    	TalonSRX TankDriveR3 = RobotMap.TankDriveR3;
	    	
	    	TalonSRX TankDriveL1 = Constants.LEFT_SLAVE1;
	    	TalonSRX TankDriveL2 = Constants.LEFT_SLAVE1;
	    	TalonSRX TankDriveL3 = Constants.LEFT_SLAVE1;
	    	
	    	
	    	public void initDefaultCommand() {
	    		// Set the default command for a subsystem here.
	    		setDefaultCommand(new ManualCommandDrive());
	    	}

	    	public void DriveWithJoystick() {
	    		
	    		double JoystickLeftVal = OI.DRIVER.getLJoystick().getRawAxis(1);
	    		double JoystickRightVal = OI.DRIVER.getRJoystick().getRawAxis(1);
	    		
	    		
	    		
	    		// set Joystick to 0 if they are between -0.1 and 0.1
	    		if(JoystickLeftVal > -0.1 && JoystickLeftVal < 0.1) {
	    			JoystickLeftVal = 0;
	    		}
	    		if(JoystickRightVal > -0.1 && JoystickRightVal < 0.1) {
	    			JoystickRightVal = 0;
	    		}
	    		
	    		
	    		TankDriveR1.set(ControlMode.PercentOutput, -JoystickRightVal);
	    		TankDriveR2.set(ControlMode.PercentOutput, -JoystickRightVal);
	    		TankDriveR3.set(ControlMode.PercentOutput, -JoystickRightVal);
	    		
	    		TankDriveL1.set(ControlMode.PercentOutput, JoystickLeftVal);
	    		TankDriveL2.set(ControlMode.PercentOutput, JoystickLeftVal);
	    		TankDriveL3.set(ControlMode.PercentOutput, JoystickLeftVal);
	    		
	    		
	    		
	    	}
	    	
	    	public void setDriveValue(double RightVal, double LeftVal) {
	    		TankDriveR1.set(ControlMode.Position, -RightVal);
	    		TankDriveR2.set(ControlMode.Position, -RightVal);
	    		TankDriveR3.set(ControlMode.Position, -RightVal);
	    		
	    		TankDriveL1.set(ControlMode.Position, -LeftVal);
	    		TankDriveL2.set(ControlMode.Position, -LeftVal);
	    		TankDriveL3.set(ControlMode.Position, -LeftVal);
	    	}


	    }	   