//package org.usfirst.frc.team6530.robot.auto;
//
//import org.usfirst.frc.team6530.robot.auto.components.AutoForward;
//import org.usfirst.frc.team6530.robot.auto.components.AutoTurn;
//
//import edu.wpi.first.wpilibj.command.CommandGroup;
//
///**
// *
// */
//public class TransitionToLimelight extends CommandGroup {
//
//    public TransitionToLimelight(String position) {
//        switch(position) {
//	        case "switch left":
//	        	addSequential(new AutoForward(-30) );
//	        	addSequential(new AutoTurn(-90) );
//	        	addSequential(new AutoForward(40) );
//	        	addSequential(new AutoTurn(90) );
//	        	
//	        case "switch right":
//	        	
//	        case "upper switch left":
//	        	
//	        case "upper switch right":
//	        	
//	        case "balance left":
//	        	
//	        case "balance right":
//	        	
//        }
//    }
//}
