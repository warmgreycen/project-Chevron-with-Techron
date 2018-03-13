package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.commands.commandElevator;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * Elevator Controller subsystem
 * @author WarmGreycen
 */
public class subsystemElevator extends Subsystem {
	double distancePerPulse;
	Encoder elevatorEncoder;
	
	public VictorSP elevatorMotor = new VictorSP(Constants.PWM_ELEVATOR);
	
	public void initDefaultCommand() {
		setDefaultCommand(new commandElevator());
	}
	public void moveElevator(double speed) {
		elevatorMotor.set(-speed);
	}
	public void up() {          /**Commands for use in auton */
		elevatorMotor.set(0.5);
	}
	public void down() {
		elevatorMotor.set(-0.5);
	}
	
	public void brake() {
		elevatorMotor.stopMotor();
	}
	
}