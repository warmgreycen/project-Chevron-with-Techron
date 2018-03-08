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
 *If robot is in L or R position, and switch on our side is ours on the right,
 *drive forward past switch, turn right, drive to right side, turn to face switch, then
 *lift elevator, drive forward, and spit out cube
 */
public class SidesGoSwitch extends CommandGroup {

    public SidesGoSwitch(String side) {
    	addSequential(new AutoForward(Constants.SGS_FORWARD1) );
    	
    	if(side == "left") {
    		addSequential(new AutoTurn(Constants.SGS_ANGLE) );
    		addSequential(new AutoForward(Constants.SGS_FORWARD2) );
    		addSequential(new LiftElevator(Constants.ELEVATOR_TIMEOUT_SWITCH, "up") );
    		addSequential(new AutoTurn(Constants.SGS_ANGLE) );
    	}
    	else {
    		addSequential(new AutoTurn(-Constants.SGS_ANGLE) );
    		addSequential(new AutoForward(Constants.SGS_FORWARD2) );
    		addSequential(new LiftElevator(Constants.ELEVATOR_TIMEOUT_SWITCH, "up") );
    		addSequential(new AutoTurn(-Constants.SGS_ANGLE) );
    	}
    	
    	addSequential(new AutoForward(Constants.SGS_FORWARD3) );
    	addSequential(new AutoPitch() );
    	addSequential(new AutoRoller("spit") );
    	addSequential(new TransitionToLimelight("long switch") );
    }
}
