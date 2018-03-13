package org.usfirst.frc.team6530.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * interface for encoder use
 * @author Cole 
 */
public class subsystemEncoders extends Subsystem {
	Encoder rightEncoder, leftEncoder;
	double distancePerPulse;
	public boolean rightEncoderFunctional, leftEncoderFunctional;

	public subsystemEncoders() {
		rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
		leftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
		System.out.println("Encoders initialized");
		
		distancePerPulse = (6 * Math.PI) / 256;	  //distancePerPulse: Distance in one wheel turn (which is diameter*pi or circumference) divided by the number
		rightEncoder.setDistancePerPulse(distancePerPulse);//# of pulses in 1 revolution (256). The code then sends this number to the encoders.
		leftEncoder.setDistancePerPulse(distancePerPulse);
		
		rightEncoder.reset();
		leftEncoder.reset();
	}
	
	public double getRightEncoderDistance() { //Retrieves how far the right encoder has seen the right wheels go
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

	public double getDistance() {
		/*
    	 * A note on Encoders and the sign of distance:
    	 * Encoders will decrement when the roll backwards.  Therefore, if you want the robot to travel backwards during autonomous,
    	 * you must set BOTH the speed and the distance to a negative value (multiply by "BACKWARDS"
    	 */

		if (leftEncoderFunctional && rightEncoderFunctional)
			return (rightEncoder.getDistance() + (-1 * leftEncoder.getDistance()))/2;
		else if(leftEncoderFunctional) {
			System.out.println("encoder Left NOT FUNCTIONAL");
			return -leftEncoder.getDistance();
		}
		else if(rightEncoderFunctional) {
			System.out.println("encoder Right NOT FUNCTIONAL");
			return rightEncoder.getDistance();
		}
		else {
			System.out.println("Both Encoders NOT FUNCTIONAL");
			return (rightEncoder.getDistance() + (-1 * leftEncoder.getDistance()))/2;
		}
	}

    public void initDefaultCommand() {}
}

