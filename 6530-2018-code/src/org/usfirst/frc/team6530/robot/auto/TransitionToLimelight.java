package org.usfirst.frc.team6530.robot.auto;

import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
import org.usfirst.frc.team6530.robot.auto.components.AutoTurn;
import org.usfirst.frc.team6530.robot.commands.autonomousCommands.DriveToCube;
import org.usfirst.frc.team6530.robot.commands.autonomousCommands.Actions.LiftElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *Not used; would have backed robot up after scoring 1st cube to make cubes
 *visible for the limelight camera
 */
public class TransitionToLimelight extends CommandGroup {

    public TransitionToLimelight(String position) {
        switch(position) {
	        case "switch left":
	        	addSequential(new AutoForward(-30) );
	        	addSequential(new AutoTurn(-90) );
	        	addSequential(new LiftElevator(0, "down") );
	        	addSequential(new AutoForward(40) );
	        	addSequential(new DriveToCube() );
	        	
	        case "switch right":
	        	addSequential(new AutoForward(-30) );
	        	addSequential(new AutoTurn(90) );
	        	addSequential(new LiftElevator(0, "down") );
	        	addSequential(new AutoForward(40) );
	        	addSequential(new DriveToCube() );
	        	
	        case "long switch":
	        	addSequential(new AutoForward(-30) );
	        	addSequential(new LiftElevator(0, "down") );
	        	addSequential(new DriveToCube() );
	        	
	        case "balance":
	        	addSequential(new AutoForward(-30) );
	        	addSequential(new LiftElevator(0, "down") );
	        	addSequential(new DriveToCube() );
	        	
        }
    }
}
