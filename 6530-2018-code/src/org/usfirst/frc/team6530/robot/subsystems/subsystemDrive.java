package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Constants;
//import org.usfirst.frc.team6530.robot.util.Xbox;
//import org.usfirst.frc.team6530.robot.subsystems.subsystemGyro;
import org.usfirst.frc.team6530.robot.Robot;
import org.usfirst.frc.team6530.robot.commands.ManualCommandDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
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
    private boolean isStopped = false;
    
    double kP = 1.2;
    double kI = 0;
    double kD = 0;
    
    //double rightSpeed, leftSpeed, leftError, rightError, lastLeftError, lastRightError, leftProportion, rightProportion;
    //double leftIntegral, rightIntegral, leftDeriv, rightDeriv;
    //double totalRightError, totalLeftError = 0;
    double error, proportion, integral, deriv, lastError;
    double totalError = 0;
    double deadZone = 24; //24in
    
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

	@Override
	protected void initDefaultCommand() {
		if (DriverStation.getInstance().isOperatorControl() ) {
			setDefaultCommand(new ManualCommandDrive() );
		}
		
	}
	
}