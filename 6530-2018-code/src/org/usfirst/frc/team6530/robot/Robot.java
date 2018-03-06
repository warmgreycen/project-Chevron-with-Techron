/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6530.robot;

import org.usfirst.frc.team6530.robot.auto.CommandGroupAuto;
import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.commands.autonomousCommands.DriveToCube;
import org.usfirst.frc.team6530.robot.enumeration.Autonomous;
import org.usfirst.frc.team6530.robot.subsystems.*;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {

	/** choosers */
		SendableChooser<Autonomous> autoChooser;
		// add choosers as needed, these put drop down options in the smart dash
		
		
	/** subsystems */
		public static subsystemEncoders SUB_ENCODERS;
		public static subsystemRoller SUB_ROLLER;
		public static subsystemDrive SUB_DRIVE;
		public static subsystemGyro SUB_GYRO;
		public static subsystemClimber SUB_CLIMBER;
		public static subsystemElevator SUB_ELEVATOR;
		public static subsystemPID SUB_PID;
		public static subsystemPitch SUB_PITCH;
		public static autoDriveTrain AUTO_DRIVE;
		public static OI oi;
		public static Vision vision;
		public static Limelight LIMELIGHT;

		
	/** autonomous */
		CommandGroup m_autoCommand;
		Command autoMove;
		Command autoVision;

		SendableChooser<CommandGroup> m_chooser = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		
	/** instantiate subsystems */
		SUB_ENCODERS = new subsystemEncoders();
		SUB_ROLLER = new subsystemRoller();
		SUB_DRIVE = new subsystemDrive();
		SUB_GYRO = new subsystemGyro();
		SUB_CLIMBER = new subsystemClimber();
		SUB_ELEVATOR = new subsystemElevator();
		SUB_PITCH = new subsystemPitch();
		AUTO_DRIVE = new autoDriveTrain();
//		vision = new Vision();
		LIMELIGHT = new Limelight();

	/** instantiate operator interface */
		oi = new OI();
	
	/** instantiate autonomous chooser */
		autoChooser = new SendableChooser<>();
		
//		m_chooser.addDefault("Starting on Right", new AutoRight());
//		m_chooser.addObject("Starting on Left", new AutoLeft());
//		m_chooser.addObject("Starting in middle ", new AutoCenter());
//		m_chooser.addObject("Drive over the Line", new DriveOverLine());
//		m_chooser.addObject("Limelight To Cube", new DriveToCube());
		
		SmartDashboard.putData("Auto mode", m_chooser);
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
	m_autoCommand = (CommandGroup) m_chooser.getSelected();
	if(m_autoCommand != null) {
		m_autoCommand.start(); 
	}
}


/** runs at 50hz when in autonomous */
public void autonomousPeriodic() {
	Scheduler.getInstance().run(); 
}


/** runs when teleop starts*/
public void teleopInit() {
//	if (auto != null)
//		auto.cancel(); 
	LIMELIGHT.setLEDs(Limelight.LIMELIGHT_LED_ON);
    LIMELIGHT.setPipeline(0);
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
