package org.usfirst.frc.team6530.robot.commands;

import org.usfirst.frc.team6530.robot.Robot;

//import org.usfirst.frc.team6530.robot.subsystems.SUB_DRIVE;
import edu.wpi.first.wpilibj.command.Command;

/**
This command reads the operator's joystick, using that input to set the motors to the
correct speed through the SUB_DRIVE subsystem.
*/
public class ManualCommandDrive extends Command {
	boolean hasAdjustedSpeed = false;

   public ManualCommandDrive() {
   }

   // Called just before this Command runs the first time
   protected void initialize() {
   }

   // Called repeatedly when this Command is scheduled to run
   protected void execute() {
   		Robot.SUB_DRIVE.setTankDrive(Robot.oi.getLeftInput(), Robot.oi.getRightInput() );
   		hasAdjustedSpeed = true;
   }

   // Make this return true when this Command no longer needs to run execute()
   protected boolean isFinished() {
       //return hasAdjustedSpeed;
   		return false;
   }

   // Called once after isFinished returns true
   protected void end() {
   		Robot.SUB_DRIVE.setTankDrive(0, 0);
   }

   // Called when another command which requires one or more of the same
   // subsystems is scheduled to run
   protected void interrupted() {
   		Robot.SUB_DRIVE.setTankDrive(0, 0);
   }
}