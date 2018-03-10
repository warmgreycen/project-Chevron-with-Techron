package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.auto.components.AutoPitch;
import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
//import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
//import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
import org.usfirst.frc.team6530.robot.auto.components.AutoTurn;
//import org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions.LiftElevator;
import org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions.LiftElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *If in left position, given 'R L _' for gameData, go to put cube 
 *in left-hand balance
 */
public class GoBalance extends CommandGroup {

    public GoBalance(String side) {
    	//addSequential(new AutoForward(Constants.GB_FORWARD1) );
    	addSequential(new AutoForward(106) );
    	addSequential(new LiftElevator(Constants.ELEVATOR_HEIGHT_BAL, "up") );
    	
		if(side == "left") {
			
			addSequential(new AutoTurn(80) );
		}
		else {
			//addSequential(new AutoForward(Constants.GB_FORWARD1) );
			addSequential(new AutoTurn(-80) );
		}
		//addSequential(new AutoForward(Constants.GB_FORWARD2) );
		addSequential(new AutoPitch() );
		addSequential(new AutoRoller("spit") );
		
		//addSequential(new TransitionToLimelight("balance") );
    }
}
