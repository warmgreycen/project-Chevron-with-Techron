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
	
	public subsystemElevator() {
		elevatorEncoder = new Encoder(7, 8, false, Encoder.EncodingType.k4X);
		distancePerPulse = (1.5 * Math.PI) / 90;
		elevatorEncoder.setDistancePerPulse(distancePerPulse);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new commandElevator());
	}
	public void moveElevator(double speed) {
		elevatorMotor.set(-speed);
	}
	public void up() {          /**Commands for use in auton */
		elevatorMotor.set(1);
	}
	public void down() {
		elevatorMotor.set(-1);
	}
	
	public double getElevatorDistance() {
		return elevatorEncoder.getDistance();
	}
}