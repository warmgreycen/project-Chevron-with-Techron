package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.auto.components.AutoTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleGoSwitch extends CommandGroup {

    public MiddleGoSwitch(String side) {
    	if(side == "left") {
    		addSequential(new AutoTurn(Constants.M_LEFTANGLE) );
    		addSequential(new AutoForward(Constants.M_LEFTDIAGONAL) );
    		addSequential(new AutoTurn(Constants.M_LEFTANGLE + 90) );
    		//addSequential(new dump cube);
    	}
    	else {
    		addSequential(new AutoTurn(Constants.M_LEFTANGLE) );
    		addSequential(new AutoForward(Constants.M_LEFTDIAGONAL) );
    		addSequential(new AutoTurn(Constants.M_LEFTANGLE + 90) );
    		//addSequential(new dump cube);
    	}
    }
}
