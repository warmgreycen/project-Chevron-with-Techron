package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.auto.components.AutoPitch;
import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
//import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
//import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
import org.usfirst.frc.team6530.robot.auto.components.AutoTurn;
//import org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions.LiftElevator;
import org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions.LiftElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *In middle position.
 *If left side of switch is ours, turn left, go forward, and drop off cube there
 *If right side of switch is ours, turn right, go forward, and drop off cube there
 */
public class MiddleGoSwitch extends CommandGroup {

    public MiddleGoSwitch(String side) {
    	addSequential(new LiftElevator(Constants.ELEVATOR_HEIGHT_SWITCH, "up") );
    	
    	if(side == "left") {
    		addSequential(new AutoTurn(-Constants.M_ANGLE1) );
    		addSequential(new AutoForward(Constants.M_FORWARD) );
    		addSequential(new AutoTurn(-Constants.M_ANGLE2) );
    	}
    	else {
    		addSequential(new AutoTurn(Constants.M_ANGLE1) );
    		addSequential(new AutoForward(Constants.M_FORWARD) );
    		addSequential(new AutoTurn(Constants.M_ANGLE2) );
    	}
    	addSequential(new AutoPitch() );
    	addSequential(new AutoRoller("spit") );
    }
}
