package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.commands.commandElevator;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * Controls the two motors on the elevator to lift and drop.
 */
public class subsystemElevator extends Subsystem {

	VictorSP leftElevatorMotor = new VictorSP(Constants.PWM_ELEVATOR);
	

	// Since the movement of the motors will be simultaneous, we can group them.
	SpeedControllerGroup elevatorMotorGroup = new SpeedControllerGroup(leftElevatorMotor);

	public void initDefaultCommand() {
		setDefaultCommand(new commandElevator());
	}

	public void moveElevator(double speed) {
		elevatorMotorGroup.set(speed);
	}
}