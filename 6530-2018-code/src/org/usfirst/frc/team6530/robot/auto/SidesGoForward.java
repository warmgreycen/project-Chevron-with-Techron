//package org.usfirst.frc.team6530.robot.auto;
//
//import org.usfirst.frc.team6530.robot.Constants;
//import org.usfirst.frc.team6530.robot.auto.components.OldAutoForward;
//import org.usfirst.frc.team6530.robot.auto.components.AutoRoller;
//import org.usfirst.frc.team6530.robot.auto.components.OldAutoTurn;
//
//import edu.wpi.first.wpilibj.command.CommandGroup;
//
///**
// *If in left position, given 'L _ _' for gameData, go to put cube in left-hand side of switch
// */
//public class SidesGoForward extends CommandGroup {
//
//    public SidesGoForward(String side) {
//    		addSequential(new OldAutoForward(Constants.SGF_LENGTH) );
//    		
//    		if(side == "left") {
//    			addSequential(new OldAutoTurn(Constants.SGF_ANGLE) );
//    			addSequential(new AutoRoller() );
//    		}
//    		else {
//    			addSequential(new OldAutoTurn(Constants.SGF_ANGLE) );
//    			addSequential(new AutoRoller() );
//    		}
//    }
//}
