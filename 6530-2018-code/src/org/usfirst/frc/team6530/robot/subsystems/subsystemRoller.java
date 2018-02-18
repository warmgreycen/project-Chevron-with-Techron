package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
import org.usfirst.frc.team6530.robot.commands.commandRoller;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class subsystemRoller extends Subsystem {

    Spark leftRollMotor = new Spark(Constants.PWM_INTAKE_LEFT);
    Spark rightRollMotor = new Spark(Constants.PWM_INTAKE_RIGHT);
    
    public void intake(double speed) {
    	rightRollMotor.set(-speed);
    	leftRollMotor.set(speed);
    }
    
    public void spit(double speed) {
    	rightRollMotor.set(-speed);
    	leftRollMotor.set(speed);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	if(DriverStation.getInstance().isAutonomous() ) {
    		setDefaultCommand(new AutoRoller() );
    	}
    	else if(DriverStation.getInstance().isOperatorControl() ){
    		setDefaultCommand(new commandRoller() );
    	}
    }
}

