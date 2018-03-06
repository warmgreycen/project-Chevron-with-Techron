package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

public class subsystemPID {

    private static boolean initialized = false;
    
	public static void initialize() {
		
		if (initialized)
			return;
		
		subsystemGyro.initialize();	
		//ahrs = subsystemGyro.getAHRS();
		
		pidOut = new TurnOutput();

		pidCtrl = new PIDController(kP,kI,kD,kF,ahrs,pidOut);
		
		pidCtrl.setInputRange(-180.0, 180.0);
		pidCtrl.setOutputRange(-maxSpeed, maxSpeed);
		//pidCtrl.setAbsoluteTolerance(0.5);
		pidCtrl.setContinuous(true);
		
		initialized = true;
	}
			
	// instance data and methods
	private static PIDController pidCtrl;
	
	private static final double kP = 0.04;
	private static final double kI = 0.02; 
	private static final double kD = 0.1;
	private static final double kF = 0;     // F not needed for PID position control
	private static final double maxSpeed = 0.75;
	private static double angleTargetDeg = 0.0;	
	private static TurnOutput pidOut;
	private static AHRS ahrs;
	
	public static void setAngle(double angleDeg) {
		
		angleTargetDeg = angleDeg;
		pidCtrl.setOutputRange(-maxSpeed, maxSpeed);
		pidCtrl.setSetpoint(angleTargetDeg);
	}

	public static void setAngle(double angleDeg, double speed) {
		
		angleTargetDeg = angleDeg;
		pidCtrl.setOutputRange(-speed, speed);
		pidCtrl.setSetpoint(angleTargetDeg);
	}
	
	public static void reset() {
		disable();
		Robot.SUB_GYRO.reset();
		angleTargetDeg = 0.0;
	}
	
	public static void enable() {
		pidCtrl.enable();
	}
	
	public static void disable() {
		pidCtrl.disable();
	}
	
	public static double getLeft() {
		return pidOut.getValue();
	}
	
	public static double getRight() {
		return -pidOut.getValue();
	}
	
	public static class TurnOutput implements PIDOutput {
		private double myOutput = 0.0;
		
		public double getValue() {
			return myOutput;
		}
		
		public void pidWrite(double output) {
			myOutput = output;
		}
	}	
}