package org.usfirst.frc.team6530.robot.auto.components;

import org.usfirst.frc.team6530.robot.Constants;
import org.usfirst.frc.team6530.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**Pitches the roller claw down about 75% of the way. Just turns on motor
 * for a short period, turns it off.
 *
 */

public class AutoPitch extends Command {

    public AutoPitch() {
      requires(Robot.SUB_PITCH);
      setTimeout(Constants.PITCH_TIMEOUT);
    }
    protected void initialize() {
    }
    protected void execute() {
    	Robot.SUB_PITCH.spit(0.6);
    }
    protected boolean isFinished() {
        return isTimedOut();
    }
    protected void end() {
    	Robot.SUB_PITCH.spit(0);
    }
    protected void interrupted() {
    	Robot.SUB_PITCH.spit(0);
    }
}