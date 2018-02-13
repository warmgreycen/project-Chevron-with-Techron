package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Constants;
//import org.usfirst.frc.team6530.robot.util.Xbox;
//import org.usfirst.frc.team6530.robot.subsystems.subsystemGyro;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**    finally a 6 cim tank drive
 *				that	WOOOORKS
 */			
//@SuppressWarnings("deprecation")
public class subsystemDrive extends Subsystem {
	
	WPI_TalonSRX leftMotor1;
	WPI_TalonSRX leftMotor2;
	WPI_TalonSRX leftMotor3;
	WPI_TalonSRX rightMotor1;
	WPI_TalonSRX rightMotor2;
	WPI_TalonSRX rightMotor3;
	
	
	DifferentialDrive tankDrive;
	
	private boolean leftInverted = true;
    private boolean rightInverted = false;
    public boolean isPrecision = false;
    
	public AHRS navX;
	
	/**
	 * Init for drive train
	 * 
	 * @param leftID - CAN ID of left main Talon SRX
	 * @param leftSID - CAN ID of left slave Talon SRX
	 * @param rightID - CAN ID of right main Talon SRX
	 * @param rightSID - CAN ID of right slave Talon SRX
	 * 
	 * @author Nic A
	 */
	public subsystemDrive(){
		
	    WPI_TalonSRX leftMotor1 = new WPI_TalonSRX(Constants.LEFT_SLAVE1);
	    WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(Constants.LEFT_SLAVE2);
	    WPI_TalonSRX leftMotor3 = new WPI_TalonSRX(Constants.LEFT_MASTER);
	    WPI_TalonSRX rightMotor1 = new WPI_TalonSRX(Constants.RIGHT_SLAVE1);
	    WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(Constants.RIGHT_SLAVE2);
	    WPI_TalonSRX rightMotor3 = new WPI_TalonSRX(Constants.RIGHT_MASTER);
	    
	    leftMotor1.setInverted(leftInverted);
	    leftMotor2.setInverted(leftInverted);
	    leftMotor3.setInverted(leftInverted);
	    rightMotor1.setInverted(rightInverted);
	    rightMotor2.setInverted(rightInverted);
	    rightMotor3.setInverted(rightInverted);
		
	    leftMotor2.set(ControlMode.Follower,Constants.LEFT_MASTER);
    	leftMotor1.set(ControlMode.Follower,Constants.LEFT_MASTER);
    	rightMotor2.set(ControlMode.Follower, Constants.RIGHT_MASTER);
    	rightMotor1.set(ControlMode.Follower, Constants.RIGHT_MASTER);
    	
    	tankDrive = new DifferentialDrive(leftMotor3, rightMotor3);	// Main Drive Train, add DifferentialDrive to beginning if build doesnt run to rio
		
    	
    	//subsystemGyro.initialize();	
		//ahrs = subsystemGyro.getAHRS();
	}
	
		public void setTankDrive(double leftSpeed, double rightSpeed) {
			tankDrive.tankDrive(leftSpeed, rightSpeed, true); //3rd argument is squaredInputs; 'true' means controls are less sensitive at	
	}


		public void setRightMotorSpeed(double speed){
			rightMotor3.set(speed);
}

		public void setLeftMotorSpeed(double speed){
			leftMotor3.set(speed);
}
		
		public double getRightMotorSpeed() {
			return rightMotor3.get();
		}
		
		public double getLeftMotorSpeed() {
			return leftMotor3.get();
		}

//public void initDefaultCommand() { //If nothing else is running and it's in Operator part of match, run Joystick input
		//if (DriverStation.getInstance().isOperatorControl() ) {
//			setDefaultCommand(new JoystickDrive() );
		//}


	/**
	 * Tank drive for automated driving
	 * @param left - Speed for left motor
	 * @param right - Speed for right motor
	 */
	public void autonTankDrive(double left, double right){
		leftMotor3.set(ControlMode.PercentOutput, (left));
		rightMotor3.set(ControlMode.PercentOutput, (right));
	}
	
	public void gyroStraight(double speed, double angle){
		autonTankDrive(speed - .01*(navX.getYaw() - angle), speed + .01*(navX.getYaw() - angle));
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
}