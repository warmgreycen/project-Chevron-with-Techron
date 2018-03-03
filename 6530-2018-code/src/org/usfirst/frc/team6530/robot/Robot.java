/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6530.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;

import org.usfirst.frc.team6530.robot.auto.CommandGroupAuto;
import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.auto.components.OldAutoForward;
import org.usfirst.frc.team6530.robot.enumeration.Autonomous;

import org.usfirst.frc.team6530.robot.subsystems.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	/** choosers */
		SendableChooser<Autonomous> autoChooser;
		// add choosers as needed, these put drop down options in the smart dash
		
		
	/** subsystems */
		public static subsystemEncoders SUB_ENCODERS;
		//public static subsystemRoller SUB_ROLLER;
		public static subsystemDrive SUB_DRIVE;
		public static subsystemGyro SUB_GYRO;

		public static subsystemElevator SUB_ELEVATOR;
		//public static subsystemPID SUB_PID;
		public static OI oi;
		//public static Vision vision;

		
	/** autonomous */
		private CommandGroupAuto auto;
		Command autoMove;
		String gameData;
		Character startPosition = 'l';

	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
	/** instantiate subsystems */
		SUB_ENCODERS = new subsystemEncoders();
		//SUB_ROLLER = new subsystemRoller();
		SUB_DRIVE = new subsystemDrive();
		SUB_GYRO = new subsystemGyro();

		SUB_ELEVATOR = new subsystemElevator();
		//vision = new Vision();

	/** instantiate operator interface */
		oi = new OI();
	
	/** instantiate autonomous chooser */
		autoChooser = new SendableChooser<>();
		autoChooser.addDefault(Autonomous.NOTHING.toString(), Autonomous.NOTHING); // set default to nothing
		for(int i = 1; i < Autonomous.values().length; i++) { 
			autoChooser.addObject(Autonomous.values()[i].toString(), Autonomous.values()[i]); } // add each autonomous enum value to chooser
		SmartDashboard.putData("Auto Mode", autoChooser); //display the chooser on the dash
		autoMove = new AutoForward(80);

	/** instantiate cameras */
		 //vision.startCameraThread();
		 
		 SUB_ENCODERS.encoderReset();
}


/** runs when robot gets disabled */
public void disabledInit() { }


/** runs at 50hz when bot is disabled */
public void disabledPeriodic() {
//	gameData = DriverStation.getInstance().getGameSpecificMessage(); //Scan the field management system for game data
//	
//	if(startPosition == 'l') {//If robot starts on left side
//		if(gameData.charAt(0) == 'l') {//If left of switch is ours, go there
//			auto = SidesGoForward("left");
//		}
//		else {
//			if(gameData.charAt(1) == 'l') {//If left of balance is ours, go there
//				auto = GoBalance("left");
//			}
//			else {//Else, go to right of switch
//				auto = SidesGoSwitch("left");
//			}
//		}
//	}
//	
//	else if(startPosition == 'm') {//If robot starts in middle
//		if(gameData.charAt(0) == 'l') {//If left of switch is ours, go there
//			auto = MiddleGoSwitch("left");
//		}
//		else {//Else, go to right of switch
//			auto = MiddleGoSwitch("right");
//		}
//	}
//	
//	else {//If robot starts on right side
//		if(gameData.charAt(0) == 'r') {
//			auto = SidesGoForward("right");
//		}
//		else {
//			if(gameData.charAt(1) == 'r') {
//				auto = GoBalance("right");
//			}
//			else {
//				auto = SidesGoSwitch("right");
//			}
//		}
//	}
	//Scheduler.getInstance().run();
}


/** runs when autonomous start */
public void autonomousInit() {
	//if(autoChooser.getSelected() != null) {
	//	auto = new CommandGroupAuto(autoChooser.getSelected());
	//	auto.start(); 
	//}
	//auto.start();
	autoMove.start();
}


/** runs at 50hz when in autonomous */
public void autonomousPeriodic() {
	Scheduler.getInstance().run(); 
}


/** runs when teleop starts*/
public void teleopInit() {
	if (auto != null)
		auto.cancel(); 
}


/** runs at ~50hz when in teleop mode */
public void teleopPeriodic() {
	Scheduler.getInstance().run(); 
}


/** runs at ~50hz when in test mode */
@SuppressWarnings("deprecation")
public void testPeriodic() {
		SUB_DRIVE.setDriveValue(.5, .5);
		System.out.println("Right Encoder Distance:"+SUB_ENCODERS.getRightEncoderDistance() );
		System.out.println("Left Encoder Distance:"+SUB_ENCODERS.getLeftEncoderDistance() );
	}
}
