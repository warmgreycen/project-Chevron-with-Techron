package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.commands.commandRoller;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls the collector/fidget spinner motors.
 */
public class subsystemRoller extends Subsystem {
	Spark rightCollectorMotor = new Spark(Constants.PWM_INTAKE_RIGHT);
	Spark leftCollectorMotor = new Spark(Constants.PWM_INTAKE_LEFT);

	public void initDefaultCommand() {
		setDefaultCommand(new commandRoller());
	}
	public void MoveRoller(double speed) {
		leftCollectorMotor.set(speed);
		rightCollectorMotor.set(-speed);

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
	public void spit() {
		leftCollectorMotor.set(-1);
		rightCollectorMotor.set(1);
	}
	
	public void stop() {
		leftCollectorMotor.set(0);
		rightCollectorMotor.set(0);
	}
}