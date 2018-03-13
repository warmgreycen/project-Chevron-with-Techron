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
 *If in left position, drive forward, turn right, put cube on balance
 *If in right position, drive forward, turn left, put cube on balance
 */
public class GoBalance extends CommandGroup {

    public GoBalance(String side) {
    	addSequential(new AutoForward(106) );
    	addSequential(new LiftElevator(Constants.ELEVATOR_HEIGHT_BAL, "up") );
    	
		if(side == "left") {
			
			addSequential(new AutoTurn(72) );
		}
		else {
			addSequential(new AutoTurn(-72) );
		}
		addSequential(new AutoPitch() );
		addSequential(new AutoRoller("spit") );
    }
}
