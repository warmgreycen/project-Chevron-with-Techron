package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
import org.usfirst.frc.team6530.robot.auto.components.AutoTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SidesGoSwitch extends CommandGroup {

    public SidesGoSwitch(String side) {
    	if(side == "left") {
    		addSequential(new AutoTurn(-Constants.SGS_ANGLE1) );
    		addSequential(new AutoForward(Constants.SGS_DIAGONAL) );
    		addSequential(new AutoTurn(Constants.SGS_ANGLE1 + 90) );
    		addSequential(new AutoForward(Constants.SGS_SIDEWAYS) );
    		addSequential(new AutoTurn(Constants.SGS_ANGLE2) );
    		addSequential(new AutoRoller() );
    	}
    	else {
    		
    	}
    }
}
