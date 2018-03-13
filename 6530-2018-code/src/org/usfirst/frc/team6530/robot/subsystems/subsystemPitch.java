package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.commands.commandPitch;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem containing methods for using the climber
 */
public class subsystemPitch extends Subsystem {

    Spark Motor = new Spark(Constants.PWM_PITCH);
  
    
    public void intake(double speed) {

    	Motor.set(speed);
    }
    
    public void spit(double speed) {

    	Motor.set(speed);
    }
    
    public void initDefaultCommand() {
    		setDefaultCommand(new commandPitch() );
    	}
    }
