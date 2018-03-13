package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.auto.components.AutoPitch;
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
 *If in left position, drive forward, turn right, put cube on switch
 *If in right position, drive forward, turn left, put cube on switch
 */
public class SidesGoForward extends CommandGroup {

    public SidesGoForward(String side) {
    		
    	addSequential(new AutoForward(Constants.SGF_FORWARD1) );	
    	addSequential(new LiftElevator(Constants.ELEVATOR_HEIGHT_SWITCH, "up") );
    		
    		if(side == "left") {
    			addSequential(new AutoTurn(80) );
    			addSequential(new AutoForward(Constants.SGF_FORWARD2) );
    			addSequential(new AutoPitch() );
    			addSequential(new AutoRoller("spit") );
    		}
    		else {
    			addSequential(new AutoTurn(-80) );
    			addSequential(new AutoForward(39.5) );
    			addSequential(new AutoPitch() );
    			addSequential(new AutoRoller("spit") );
    		}
    		
    }
}
