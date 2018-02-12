package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.commands.ManualCommandDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**    finally a 6 cim tank drive
 *				that	WOOOORKS
 */			

public class subsystemDrive extends Subsystem{
	
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
		
	    rightMotor2.follow(rightMotor3);
		leftMotor2.follow(leftMotor3);
		rightMotor1.follow(rightMotor3);
		leftMotor1.follow(leftMotor3);
		
		rightMotor2.setNeutralMode(NeutralMode.Brake);
		rightMotor1.setNeutralMode(NeutralMode.Brake);
		rightMotor3.setNeutralMode(NeutralMode.Brake);
		leftMotor3.setNeutralMode(NeutralMode.Brake);
		leftMotor2.setNeutralMode(NeutralMode.Brake);
		leftMotor1.setNeutralMode(NeutralMode.Brake);
		
	    /**leftMotor2.set(ControlMode.Follower,Constants.LEFT_MASTER);
    	leftMotor1.set(ControlMode.Follower,Constants.LEFT_MASTER);
    	rightMotor2.set(ControlMode.Follower, Constants.RIGHT_MASTER);
    	rightMotor1.set(ControlMode.Follower, Constants.RIGHT_MASTER);*/
	    
	        
    	//tankDrive = new DifferentialDrive(leftMotor3, rightMotor3);	// Main Drive Train, add DifferentialDrive to beginning if build doesnt run to rio
		
    	
    	//subsystemGyro.initialize();	
		//ahrs = subsystemGyro.getAHRS();
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
	


	private void rotate(double rotMot){
	leftMotor3.set(ControlMode.PercentOutput, rotMot);
	rightMotor3.set(ControlMode.PercentOutput, rotMot);
	
}

// moves robot with left and right drive sticks
	public void tankDrive(double leftSpeed, double rightSpeed) {
	leftMotor3.set(ControlMode.PercentOutput, -leftSpeed);
	rightMotor3.set(ControlMode.PercentOutput, rightSpeed);
	
}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManualCommandDrive());
		
	}

}
