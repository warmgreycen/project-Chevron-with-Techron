package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Robot;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *Contains methods for interfacing with encoders
 */
public class subsystemEncoders extends Subsystem {
	
	//define encoders
	Encoder rightEncoder, leftEncoder;
	double distancePerPulse;

	public subsystemEncoders() {
		rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k1X);
		leftEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k1X);
		System.out.println("Encoders initialized");
		
		distancePerPulse = (6 * Math.PI) / 256;	  //distancePerPulse: Distance in one wheel turn (which is diameter*pi or circumference) divided by the number
		rightEncoder.setDistancePerPulse(distancePerPulse);//# of pulses in 1 revolution (256). The code then sends this number to the encoders.
		leftEncoder.setDistancePerPulse(distancePerPulse);
		
		//Set encoder spin count to zero
		rightEncoder.reset();
		leftEncoder.reset();
	}
	
	public double getRightEncoderDistance() { //Retrieves how far the right encoder has seen the right wheels go
		//System.out.print(rightEncoder.getDistance() );
		return rightEncoder.getDistance();
	}
	
	public double getLeftEncoderDistance() { //Retrieves how far the left encoder has seen the left wheels go
		return leftEncoder.getDistance();
	}
	
	public int getLeftEncoderCount(){ //Retrieves current encoder count of spins
		return leftEncoder.get();
	}
	
	public int getRightEncoderCount() {
		return rightEncoder.get();
	}
	
	public double getRightEncoderSpeed() {
		return rightEncoder.getRate();
	}
	
	public double getLeftEncoderSpeed() {
		return leftEncoder.getRate();
	}
	public void encoderReset() { //Starts over count on encoders
		rightEncoder.reset();
		leftEncoder.reset();
	}
/*	
	public double getAngleError() { //Tells how far off (in degrees) robot is from a perfectly straight heading
		return gyro.getAngle();
	}
	
	public void resetGyro(){
		gyro.reset();
	}
	
	public double getCorrectionSpeed(double angleError) { //Converts angle error into a the additional speed we need to correct it
		return (angleError / 760);
	}
	*/
    public void initDefaultCommand() {}
}

