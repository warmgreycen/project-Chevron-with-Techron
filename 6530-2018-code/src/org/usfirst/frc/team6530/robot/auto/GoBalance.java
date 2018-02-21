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
// *If in left position, given 'R L _' for gameData, go to put cube in left-hand balance
// */
//public class GoBalance extends CommandGroup {
//
//    public GoBalance(String side) {
//        // To run multiple commands at the same time,
//        // use addParallel()
//        // e.g. addParallel(new Command1());
//        //      addSequential(new Command2());
//        // Command1 and Command2 will run in parallel.
//    		
//    		if(side == "left") {
//    			addSequential(new OldAutoTurn(-Constants.GB_ANGLE1) );
//    			addSequential(new OldAutoForward(Constants.GB_DIAGONAL) );
//    			addSequential(new OldAutoTurn(Constants.GB_ANGLE1) );
//    			addSequential(new OldAutoForward(Constants.GB_FORWARD) );
//    			addSequential(new OldAutoTurn(Constants.GB_ANGLE1 + 90) );
//    			addSequential(new AutoRoller() );
//    		}
//    		else {
//    			addSequential(new OldAutoTurn(Constants.GB_ANGLE1) );
//    			addSequential(new OldAutoForward(Constants.GB_DIAGONAL) );
//    			addSequential(new OldAutoTurn(-Constants.GB_ANGLE1) );
//    			addSequential(new OldAutoForward(Constants.GB_FORWARD) );
//    			addSequential(new OldAutoTurn(-Constants.GB_ANGLE2 - 90) );
//    			addSequential(new AutoRoller() );
//    		}
//
//    }
//}
