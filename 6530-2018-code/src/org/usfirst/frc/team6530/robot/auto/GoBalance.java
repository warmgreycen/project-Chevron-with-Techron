package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.auto.components.AutoTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *If in left position, given 'R L _' for gameData, go to put cube in left-hand balance
 */
public class GoBalance extends CommandGroup {

    public GoBalance(String side) {
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    		double turnAngle1 = 30;
    		double distance1 = 60;
    		double distance2 = 15;
    		double turnAngle2 = 90;
    		
    		if(side == "left") {
    			addSequential(new AutoTurn(-turnAngle1) );
    			addSequential(new AutoForward(distance1) );
    			addSequential(new AutoTurn(-turnAngle1) );
    			addSequential(new AutoForward(distance2) );
    			addSequential(new AutoTurn(turnAngle2) );
    			//addSequential(new dump cube);
    		}
    		else {
    			addSequential(new AutoTurn(turnAngle1) );
    			addSequential(new AutoForward(distance1) );
    			addSequential(new AutoTurn(turnAngle1) );
    			addSequential(new AutoForward(distance2) );
    			addSequential(new AutoTurn(-turnAngle2) );
    			//addSequential(new dump cube);
    		}

    }
}
