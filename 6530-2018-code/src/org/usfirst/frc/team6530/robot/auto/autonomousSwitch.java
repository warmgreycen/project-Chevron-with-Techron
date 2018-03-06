//package org.usfirst.frc.team6530.robot.auto;
//
//import org.usfirst.frc.team6530.robot.commands.DriveToSwitch;
//import org.usfirst.frc.team6530.robot.subsystems.subsystemElevator;
//
//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.command.CommandGroup;
//
///**
// *
// */
//public class autonomousSwitch extends CommandGroup {
//
//    public autonomousSwitch() {
//        // Add Commands here:
//    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
//    	
//    	if (gameData.charAt(0) == 'L') {
//    	 addParallel(new LiftElevator(false)); //switch left
//    	addSequential(new DriveToSwitch(1));
//        addSequential(new Rotate(true)); 
//        addSequential(new DriveToSwitch(.5)); //drives the rest of the way to the switch
//    	addSequential(new outtake());
//    	
//    	}
//    	
//    	
//    	if (gameData.charAt(0) == 'R'){
//    	addParallel(new subsystemElevator(false)); 
//    	addSequential(new DriveToSwitch(1));
//        addSequential(new Rotate(false)); 
//        addSequential(new DriveToScale(.5)); //drives the rest of the way to the switch 
//    	addSequential(new outtake());
//    	
//    	}
//        //      addSequential(new Command2());
//        // these will run in order.
//
//        // To run multiple commands at the same time,
//        // use addParallel()
//        // e.g. addParallel(new Command1());
//        //      addSequential(new Command2());
//        // Command1 and Command2 will run in parallel.
//
//        // A command group will require all of the subsystems that each member
//        // would require.
//        // e.g. if Command1 requires chassis, and Command2 requires arm,
//        // a CommandGroup containing them would require both the chassis and the
//        // arm.
//    }
//}