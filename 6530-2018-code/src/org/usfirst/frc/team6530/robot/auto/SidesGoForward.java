package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
//import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
import org.usfirst.frc.team6530.robot.auto.components.AutoTurn;
//import org.usfirst.frc.team6530.robot.auto.components.OldAutoForward;
//import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
//import org.usfirst.frc.team6530.robot.auto.components.OldAutoTurn;
//import org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions.LiftElevator;
import org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions.LiftElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *If in left position, given 'L _ _' for gameData, go put cube in 
 *left-hand side of switch
 */
public class SidesGoForward extends CommandGroup {

    public SidesGoForward(String side) {
    		addSequential(new LiftElevator(Constants.ELEVATOR_HEIGHT_SWITCH, "up") );
    		addSequential(new AutoForward(Constants.SGF_FORWARD1) );
    		
    		if(side == "left") {
    			addSequential(new AutoTurn(Constants.SGF_ANGLE) );
    			addSequential(new AutoForward(Constants.SGF_FORWARD2) );
    			addSequential(new AutoRoller("spit") );
    			addSequential(new LiftElevator(Constants.ELEVATOR_HEIGHT_SWITCH, "down") );
    			addSequential(new TransitionToLimelight("switch left") );
    		}
    		else {
    			addSequential(new AutoTurn(-Constants.SGF_ANGLE) );
    			addSequential(new AutoForward(Constants.SGF_FORWARD2) );
    			addSequential(new AutoRoller("spit") );
    			addSequential(new LiftElevator(Constants.ELEVATOR_HEIGHT_SWITCH, "down") );
    			addSequential(new TransitionToLimelight("switch right") );
    		}
    		
    }
}
