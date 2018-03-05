package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.auto.components.AutoTurn;
//import org.usfirst.frc.team6530.robot.auto.components.OldAutoForward;
//import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
//import org.usfirst.frc.team6530.robot.auto.components.OldAutoTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *If in left position, given 'L _ _' for gameData, go to put cube in left-hand side of switch
 */
public class SidesGoForward extends CommandGroup {

    public SidesGoForward(String side) {
    		addSequential(new AutoForward(Constants.SGF_LENGTH) );
    		
    		if(side == "left") {
    			addSequential(new AutoTurn(Constants.SGF_ANGLE) );
    		}
    		else {
    			addSequential(new AutoTurn(-Constants.SGF_ANGLE) );
    		}
    		
    		//addSequential(new AutoElevator("switch") );
			addSequential(new AutoForward(5) );
			//addSequential(new AutoRoller("spit") );
    }
}
