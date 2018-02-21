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
// *
// */
//public class MiddleGoSwitch extends CommandGroup {
//
//    public MiddleGoSwitch(String side) {
//    	if(side == "left") {
//    		addSequential(new OldAutoTurn(Constants.M_LEFTANGLE) );
//    		addSequential(new OldAutoForward(Constants.M_LEFTDIAGONAL) );
//    		addSequential(new OldAutoTurn(Constants.M_LEFTANGLE + 90) );
//    		addSequential(new AutoRoller() );
//    	}
//    	else {
//    		addSequential(new OldAutoTurn(Constants.M_LEFTANGLE) );
//    		addSequential(new OldAutoForward(Constants.M_LEFTDIAGONAL) );
//    		addSequential(new OldAutoTurn(Constants.M_LEFTANGLE + 90) );
//    		addSequential(new AutoRoller() );
//    	}
//    }
//}
