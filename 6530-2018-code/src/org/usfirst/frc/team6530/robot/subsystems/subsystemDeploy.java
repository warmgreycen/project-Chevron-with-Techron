package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.commands.commandDeploy;
import org.usfirst.frc.team6530.robot.commands.commandPitch;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class subsystemDeploy extends Subsystem {

    Spark Motor = new Spark(Constants.PWM_DEPLOY);
  
    
    public void shut(double speed) {

    	Motor.set(speed);
    }
    
    public void open(double speed) {

    	Motor.set(-speed);
    }
    
    public void initDefaultCommand() {

//    	if(DriverStation.getInstance().isAutonomous() ) {
//    		setDefaultCommand(new AutoPitch() );
//    	}
//    	else if(DriverStation.getInstance().isOperatorControl() ){
    		setDefaultCommand(new commandDeploy() );
    	}
    }



