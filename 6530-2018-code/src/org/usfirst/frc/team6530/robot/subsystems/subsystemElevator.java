package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.commands.commandElevator;
import org.usfirst.frc.team6530.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @purpose: Sets base system for raising the robot
 * @author Maddie Lanham
 * @version 1/20/2017
 */
public class subsystemElevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// adds Victor
	public static VictorSP elevatorMotor;
	
	Servo shifter;
	
	public subsystemElevator(){
		elevatorMotor = new VictorSP(Constants.PWM_ELEVATOR);
		
		
		
	}
	
	// moves elevator using left stick
	//forward up, backwards down
	public void elevator(double speed) {
		elevatorMotor.set(speed);
			
	}
	

		
	

		
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new commandElevator());
    	
    }
}
