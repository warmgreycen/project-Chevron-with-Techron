//package org.usfirst.frc.team6530.robot.commands;
//
//import edu.wpi.first.wpilibj.command.Command;
//
//import org.usfirst.frc.team6530.robot.OI;
//import org.usfirst.frc.team6530.robot.Robot;
//import edu.wpi.first.wpilibj.Joystick;
//
///**
// * An example command.  You can replace me with your own command.
// */
//public class ManualCommandDrive extends Command {
//	public ManualCommandDrive() {
//		requires(Robot.SUB_DRIVE);
//	}
//	protected void initialize() {
//		Robot.SUB_DRIVE.setDriveValue(0, 0);
//	}
//	protected void execute() {
//		/** ------- Current drive modes -------
//		 *  	@param DriveWithJoystick    | Right joy = right side motor <>
//		 *  		@param driveForza       | Drives like a car, turning is dependent on driving forward
//		 *  			@param driveRLTank  | Drives like rocket league mixed with a tank, 
//		 *  									forward and backward with triggers,
//		 *  									turn in place with left joystick
//		**/
//		
//		Robot.SUB_DRIVE.DriveWithJoystick(OI.DRIVER);  		
//	}
//	protected boolean isFinished() {
//		return false;
//	}
//	protected void end() {
//		exit();
//	}
//	protected void interrupted() {
//		exit();
//	}
//	private void exit() {
//		//set motors to off when robot isn't enabled
//		Robot.SUB_DRIVE.setDriveValue(0, 0);
//	}
//}
