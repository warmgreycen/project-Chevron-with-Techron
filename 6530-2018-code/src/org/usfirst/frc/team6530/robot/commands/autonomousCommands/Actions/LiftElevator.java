package org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions;

import org.usfirst.frc.team6530.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class LiftElevator extends Command {

    public LiftElevator(double timeOut) {

      requires(Robot.SUB_ELEVATOR);
      setTimeout(timeOut); 
    }
    protected void initialize() {
    }
    protected void execute() {
    	Robot.SUB_ELEVATOR.up();
    }
    protected boolean isFinished() {
        return isTimedOut();
    }
    protected void end() {
    	Robot.SUB_ELEVATOR.elevatorMotor.set(-1);
    }
    protected void interrupted() {
    }
}