package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.auto.components.OldAutoForward;
import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
import org.usfirst.frc.team6530.robot.auto.components.OldAutoTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SidesGoSwitch extends CommandGroup {

    public SidesGoSwitch(String side) {
    	if(side == "left") {
    		addSequential(new OldAutoTurn(-Constants.SGS_ANGLE1) );
    		addSequential(new OldAutoForward(Constants.SGS_DIAGONAL) );
    		addSequential(new OldAutoTurn(Constants.SGS_ANGLE1 + 90) );
    		addSequential(new OldAutoForward(Constants.SGS_SIDEWAYS) );
    		addSequential(new OldAutoTurn(Constants.SGS_ANGLE2) );
    		addSequential(new AutoRoller() );
    	}
    	else {
    		
    	}
    }
}
