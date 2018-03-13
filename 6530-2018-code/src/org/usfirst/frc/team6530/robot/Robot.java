package org.usfirst.frc.team6530.robot;

import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.commands.commandDeploy;
import org.usfirst.frc.team6530.robot.enumeration.Autonomous;
import org.usfirst.frc.team6530.robot.subsystems.*;
import org.usfirst.frc.team6530.robot.OI;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc.team6530.robot.auto.GoBalance;
import org.usfirst.frc.team6530.robot.auto.SidesGoForward;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Robot extends IterativeRobot {

	/** choosers */
		SendableChooser<Autonomous> autoChooser;
		// add choosers as needed, these put drop down options in the smart dash
	
	/** Important starting variables */
		String gameData;
		Character startPosition;
		
		
	/** subsystems */
		public static subsystemEncoders SUB_ENCODERS;
		public static subsystemRoller SUB_ROLLER;
		public static subsystemDrive SUB_DRIVE;
		public static subsystemGyro SUB_GYRO;
		public static subsystemClimber SUB_CLIMBER;
		public static subsystemElevator SUB_ELEVATOR;
		public static subsystemPitch SUB_PITCH;
		public static autoDriveTrain AUTO_DRIVE;
		commandDeploy ramp = new commandDeploy(Constants.ramp);
		public static OI oi;
		public static Limelight LIMELIGHT;

		public static PowerDistributionPanel pdp;
		
	/** autonomous */
		CommandGroup m_autoCommand;
		Command auto;

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

		LIMELIGHT = new Limelight();
		pdp = new PowerDistributionPanel();
		

	/** instantiate operator interface */
		oi = new OI();
		
	/** instantiate SmartDashboard*/
		SmartDashboard.setDefaultString("Starting Position (l, m, or r):", "m");
	
	/** instantiate autonomous chooser */
		autoChooser = new SendableChooser<>();
		
		SmartDashboard.putData("Auto mode", m_chooser);
		 
		 SUB_ENCODERS.encoderReset();
}


/** runs when robot gets disabled */
public void disabledInit() { }


/** runs at 50hz when bot is disabled */
public void disabledPeriodic() {
	
}


/** runs when autonomous start */
public void autonomousInit() {
	
	startPosition = SmartDashboard.getString("Starting Position (l, m, or r):", "r").charAt(0);
	gameData = DriverStation.getInstance().getGameSpecificMessage(); //Scan the field management system for game data

	if(startPosition == 'l') {//If robot starts on left side
		if(gameData.charAt(1) == 'L') {
			auto = new GoBalance("left");
		}
		else {
			if(gameData.charAt(0) == 'L') {
				auto = new SidesGoForward("left");
			}
			else {//Else, go to right of switch
				auto = new AutoForward(37);
			}
		}
	}
//	
//	else if(startPosition == 'm') {//If robot starts in middle
//		if(gameData.charAt(0) == 'L') {//If left of switch is ours, go there
//			auto = new MiddleGoSwitch("left");
//		}
//		else {//Else, go to right of switch
//			auto = new MiddleGoSwitch("right");
//		}
//	}
//	
	else {//If robot starts on right side
		if(gameData.charAt(1) == 'R') {
			auto = new GoBalance("right");
		}
		else {
			if(gameData.charAt(0) == 'R') {
				auto = new SidesGoForward("right");
			}
			else {
				auto = new AutoForward(37);
			}
		}
	}
	auto.start(); 
}


/** runs at 50hz when in autonomous */
public void autonomousPeriodic() {
	Scheduler.getInstance().run(); 
}


/** runs when teleop starts*/
public void teleopInit() {
	if (auto != null) {
		auto.cancel(); 
	}
	LIMELIGHT.setLEDs(Limelight.LIMELIGHT_LED_OFF);
    LIMELIGHT.setPipeline(0);

}


/** runs at ~50hz when in teleop mode */
public void teleopPeriodic() {
	Scheduler.getInstance().run(); 
	SmartDashboard.putNumber("Motor current draw 11", pdp.getCurrent(11));
	SmartDashboard.putNumber("Motor current draw 10", pdp.getCurrent(10));
	SmartDashboard.putNumber("Motor current draw 9", pdp.getCurrent(9));
	SmartDashboard.putNumber("Motor current draw 8", pdp.getCurrent(8));
	SmartDashboard.putNumber("Motor current draw 7", pdp.getCurrent(7));
	SmartDashboard.putNumber("Motor current draw 6", pdp.getCurrent(6));
	SmartDashboard.putNumber("Motor current draw 5", pdp.getCurrent(5));
	SmartDashboard.putNumber("Motor current draw 4", pdp.getCurrent(4));
	SmartDashboard.putNumber("Motor current draw 3", pdp.getCurrent(3));
	SmartDashboard.putNumber("Motor current draw 2", pdp.getCurrent(2));
	SmartDashboard.putNumber("Motor current draw 1", pdp.getCurrent(1));
	SmartDashboard.putNumber("Motor current draw 12", pdp.getCurrent(12));
	SmartDashboard.putNumber("Motor current draw 13", pdp.getCurrent(13));
	SmartDashboard.putNumber("Motor current draw 14", pdp.getCurrent(14));
	SmartDashboard.putNumber("Motor current draw 15", pdp.getCurrent(15));
		ramp.move(OI.OPERATOR);
}


/** runs at ~50hz when in test mode */
//@SuppressWarnings("deprecation")
public void testPeriodic() {}
}
