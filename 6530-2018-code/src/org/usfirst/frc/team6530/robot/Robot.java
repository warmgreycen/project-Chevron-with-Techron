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

import org.usfirst.frc.team6530.robot.auto.CommandGroupAuto;
import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
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
		public static subsystemRoller SUB_ROLLER;
		public static subsystemDrive SUB_DRIVE;
		public static subsystemGyro SUB_GYRO;

		public static subsystemElevator SUB_ELEVATOR;
		public static subsystemPID SUB_PID;
		public static OI oi;
		public static Vision vision;

		
	/** autonomous */
		private CommandGroupAuto auto;
		Command autoMove;

	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
//		SUB_ARDUINO = new SubsystemArduino();
		
	/** instantiate subsystems */
		SUB_ENCODERS = new subsystemEncoders();
//		SUB_ROLLER = new subsystemRoller();
		SUB_DRIVE = new subsystemDrive();
		SUB_GYRO = new subsystemGyro();

		SUB_ELEVATOR = new subsystemElevator();
		vision = new Vision();

	/** instantiate operator interface */
		oi = new OI();
	
	/** instantiate autonomous chooser */
		autoChooser = new SendableChooser<>();
		autoChooser.addDefault(Autonomous.NOTHING.toString(), Autonomous.NOTHING); // set default to nothing
		for(int i = 1; i < Autonomous.values().length; i++) { 
			autoChooser.addObject(Autonomous.values()[i].toString(), Autonomous.values()[i]); } // add each autonomous enum value to chooser
		SmartDashboard.putData("Auto Mode", autoChooser); //display the chooser on the dash
		autoMove = new AutoForward(60);

	/** instantiate cameras */
		 vision.startCameraThread();
}


/** runs when robot gets disabled */
public void disabledInit() { }


/** runs at 50hz when bot is disabled */
public void disabledPeriodic() {
	Scheduler.getInstance().run(); 
}


/** runs when autonomous start */
public void autonomousInit() {
	//if(autoChooser.getSelected() != null) {
	//	auto = new CommandGroupAuto(autoChooser.getSelected());
	//	auto.start(); 
	//} 
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
	LiveWindow.run(); 
	}
}
