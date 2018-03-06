package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
//import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
import org.usfirst.frc.team6530.robot.auto.components.AutoTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *If in left position, given 'R L _' for gameData, go to put cube in left-hand balance
 */
public class GoBalance extends CommandGroup {

    public GoBalance(String side) {
    		if(side == "left") {
    			addSequential(new AutoForward(Constants.GB_FORWARD1) );
    			addSequential(new AutoTurn(Constants.GB_ANGLE1) );
    			addSequential(new AutoForward(Constants.GB_FORWARD2) );
				//addSequential(new AutoElevator("balance");
				addSequential(new AutoForward(Constants.GB_FORWARD3) );
    			//addSequential(new AutoRoller("spit") );
    		}
    		else {
    			addSequential(new AutoForward(Constants.GB_FORWARD1) );
    			addSequential(new AutoTurn(-Constants.GB_ANGLE1) );
    			addSequential(new AutoForward(Constants.GB_FORWARD2) );
				//addSequential(new AutoElevator("balance");
				addSequential(new AutoForward(Constants.GB_FORWARD3) );
    			//addSequential(new AutoRoller("spit") );
    		}

    }
}
