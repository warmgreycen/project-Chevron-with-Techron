package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.auto.components.AutoPitch;
import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
//import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
import org.usfirst.frc.team6530.robot.auto.components.AutoTurn;
//import org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions.LiftElevator;
import org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions.LiftElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *If in left position, drive forward, turn right, go to other side of switch,
 *put cube on switch.
 *If in right position, drive forward, turn left, put cube on balancego to other side of switch,
 *put cube on switch.
 */
public class SidesGoSwitch extends CommandGroup {

    public SidesGoSwitch(String side) {
    	addSequential(new AutoForward(Constants.SGS_FORWARD1) );
    	
    	if(side == "left") {
    		addSequential(new AutoTurn(Constants.SGS_ANGLE) );
    		addSequential(new AutoForward(Constants.SGS_FORWARD2) );
    		addSequential(new LiftElevator(Constants.ELEVATOR_HEIGHT_SWITCH, "up") );
    		addSequential(new AutoTurn(Constants.SGS_ANGLE) );
    	}
    	else {
    		addSequential(new AutoTurn(-Constants.SGS_ANGLE) );
    		addSequential(new AutoForward(Constants.SGS_FORWARD2) );
    		addSequential(new LiftElevator(Constants.ELEVATOR_HEIGHT_SWITCH, "up") );
    		addSequential(new AutoTurn(-Constants.SGS_ANGLE) );
    	}
    	
    	addSequential(new AutoForward(Constants.SGS_FORWARD3) );
    	addSequential(new AutoPitch() );
    	addSequential(new AutoRoller("spit") );
    	addSequential(new TransitionToLimelight("long switch") );
    }
}
