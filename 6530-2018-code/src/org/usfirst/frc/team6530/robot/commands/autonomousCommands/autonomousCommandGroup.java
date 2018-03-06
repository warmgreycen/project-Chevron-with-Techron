package org.usfirst.frc.team6530.robot.commands.autonomousCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

import org.usfirst.frc.team6530.robot.enumeration.Direction;
import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
import org.usfirst.frc.team6530.robot.commands.commandRoller;
import org.usfirst.frc.team6530.robot.subsystems.subsystemEncoders;
import org.usfirst.frc.team6530.robot.subsystems.subsystemGyro;
import org.usfirst.frc.team6530.robot.subsystems.subsystemRoller;
//import org.usfirst.frc.team6530.robot.commands.autonomousCommands.PIDAutoDrive;
import org.usfirst.frc.team6530.robot.commands.autonomousCommands.AutoTurn;

import org.usfirst.frc.team6530.robot.commands.autonomousCommands.autonomousCommandGroup;



/**
 * AutoCmd - generate the autonomous command sequence. Note that this class constructs
 * the command list and provides methods to add new commands and then calls a generator
 * to generate the command list based on field setup.
 * 
 * speed and direction are stateful.
 */
public class autonomousCommandGroup extends CommandGroup {
	private static final int FORWARD = -1;
	private static final int BACKWARD = 1;
	private double driveSpeed = 1;
	private double turnSpeed = 0.25;

	private autonomousCommandGroupGenerator autoGenerator;

	public autonomousCommandGroup() {
		addSequential(new ResetEncoders());
		addSequential(new ResetGyro());
	}

	// track state
	public void setDriveSpeed(double speed) { driveSpeed=speed; }
	public void setTurnSpeed(double speed) { turnSpeed=speed; }

	/*
	 * A note on Encoders and the sign of distance:
	 * Encoders will decrement when the roll backwards.  Therefore, if you want the robot to travel backwards during autonomous,
	 * you must set BOTH the speed and the distance to a negative value (multiply by "BACKWARDS"
	 */
	public void driveForward(double distance) {
		addSequential(new PIDAutoDrive(FORWARD*driveSpeed, distance));
	}
	public void driveBackward(double distance) {
		addSequential(new PIDAutoDrive(BACKWARD*driveSpeed, distance));
	}

	public void turnLeft(double angle) {
		addSequential(new AutoTurn(turnSpeed, angle));
	}
	public void turnLeft() { turnLeft(90); } // Default turns are 90 degree
	public void turnRight(double angle) { turnLeft(-angle); } // right turn is a negative left turn
	public void turnRight() { turnLeft(-90); }



	//elevator commands
	public void raiseElevator(double amount) {
		//TODO create method //addSequential();
	}
	public void raiseElevator() { raiseElevator(1.0); }
	public void lowerElevator(double amount) { raiseElevator(-amount); }
	public void lowerElevator() { raiseElevator(-1.0); }

	// rolllllller control commands
	public void dropCube() {

		addSequential(new Robot.SUB_ROLLER.spit);
	}
	public void grabCube() {

		addSequential(new SpinIntake(Direction.IN, false));
	}

	public void sleep(double seconds) {
		addSequential(new TimedCommand(seconds));
	}
}