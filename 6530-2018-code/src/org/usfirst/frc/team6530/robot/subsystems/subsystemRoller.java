package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.commands.commandRoller;
import org.usfirst.frc.team6530.robot.util.Xbox;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls the collector motors.
 */
public class subsystemRoller extends Subsystem {
	public Spark rightCollectorMotor = new Spark(Constants.PWM_INTAKE_RIGHT);
	public Spark leftCollectorMotor = new Spark(Constants.PWM_INTAKE_LEFT);



/** create variable to apply left motor invert */
public static final double leftify(double left) {
	return left * (Constants.LEFT_MOTOR_INVERT ? -1.0 : 1.0);
}
/** create variable to apply right motor invert */
public static final double rightify(double right) {
	return right * (Constants.RIGHT_MOTOR_INVERT ? -1.0 : 1.0);
}

public void initDefaultCommand() {
	setDefaultCommand(new commandRoller());
}
	public void RollerDrive(Joystick joy) {
    	
    	double left = Xbox.LT(joy);
    	double right = Xbox.RT(joy);
    	
    	//Quick Truncate
    	left = (left > 1.0 ? 1.0 : (left < -1.0 ? -1.0 : left));
    	right = (right > 1.0 ? 1.0 : (right < -1.0 ? -1.0 : right));
    	    	
    	rightCollectorMotor.set(rightify(right));
    		leftCollectorMotor.set(leftify(left));
	}
	public void intake() {
		leftCollectorMotor.set(1);
		rightCollectorMotor.set(-1);

	}
	public void spit() {
		leftCollectorMotor.set(-1);
		rightCollectorMotor.set(1);
	}
	

}

