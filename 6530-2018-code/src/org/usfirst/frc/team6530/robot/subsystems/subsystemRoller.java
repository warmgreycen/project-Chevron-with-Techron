package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Constants;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls the collector/fidget spinner motors.
 */
public class subsystemRoller extends Subsystem {
	Spark rightCollectorMotor = new Spark(Constants.PWM_INTAKE_RIGHT);
	Spark leftCollectorMotor = new Spark(Constants.PWM_INTAKE_LEFT);

	public void initDefaultCommand() {

	}

	// The speeds are negative for intake and outtake since the motors need to spin
	// in opposite directions
	// intake spins the motors towards the inside of the collector and outtake spins
	// the motors away from the collector
	public void intake() {
		leftCollectorMotor.set(1);
		rightCollectorMotor.set(-1);

	}

	// The outtake speed is slower so the power cubes do not fly uncontrollably out
	// of the collector
	public void outtake() {
		leftCollectorMotor.set(-1);
		rightCollectorMotor.set(1);
	}

}