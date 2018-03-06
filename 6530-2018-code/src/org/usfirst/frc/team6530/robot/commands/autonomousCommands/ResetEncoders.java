package org.usfirst.frc.team6530.robot.commands.autonomousCommands;

import org.usfirst.frc.team6530.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;


public class ResetEncoders extends InstantCommand {

    public ResetEncoders() {
        super();
     
        requires(Robot.SUB_ENCODERS);
    }
    protected void initialize() {
    	Robot.SUB_ENCODERS.encoderReset();

    }

}