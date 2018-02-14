package org.usfirst.frc.team6530.robot.commands;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.auto.components.AutoTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SidesGoForward extends CommandGroup {

    public SidesGoForward(String side) {
    		addSequential(new AutoForward(Constants.SGF_LENGTH) );
    		
    		if(side == "left") {
    			addSequential(new AutoTurn(Constants.SGF_ANGLE) );
    		}
    		else {
    			addSequential(new AutoTurn(Constants.SGF_ANGLE) );
    		}
    }
}
