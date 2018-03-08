package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
//import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
import org.usfirst.frc.team6530.robot.auto.components.AutoTurn;
import org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions.LiftElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *If in middle start position, check to see which side of switch is ours. Lift
 *the elevator up slightly. If right side is ours, turn left slightly, go forward for 
 *a distance, then turn right slightly to be perpendicular to the switch wall.
 */
public class MiddleGoSwitch extends CommandGroup {

    public MiddleGoSwitch(String side) {
    	addSequential(new LiftElevator(Constants.ELEVATOR_TIMEOUT_SWITCH) );
    	
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
    	addSequential(new AutoRoller("spit") );
    }
}
