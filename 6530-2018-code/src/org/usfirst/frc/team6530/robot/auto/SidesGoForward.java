package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.auto.components.AutoTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *If in left position, given 'L _ _' for gameData, go to put cube in left-hand side of switch
 */
public class SidesGoForward extends CommandGroup {

    public SidesGoForward(String side) {
    		addSequential(new AutoForward(Constants.SGF_LENGTH) );
    		
    		if(side == "left") {
    			addSequential(new AutoTurn(Constants.SGF_ANGLE) );
    			//addSequential(new dump cube);
    		}
    		else {
    			addSequential(new AutoTurn(Constants.SGF_ANGLE) );
    			//addSequential(new dump cube);
    		}
    }
}
