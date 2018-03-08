package org.usfirst.frc.team6530.robot.commands.autonomousCommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6530.robot.Robot;
import org.usfirst.frc.team6530.robot.subsystems.Limelight;

import static org.usfirst.frc.team6530.robot.Robot.LIMELIGHT;

/**
 * Auton program to chase Power Cubes (or whatever is in pipeline two.)
 */
public class DriveToCube extends Command {
    private double lastTX;

    public DriveToCube() {
        requires(Robot.AUTO_DRIVE);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.AUTO_DRIVE.enable();
        LIMELIGHT.setLEDs(Limelight.LIMELIGHT_LED_OFF);
        LIMELIGHT.setPipeline(0);
    }

    @Override
    protected void execute() {
        if (LIMELIGHT.hasValidTarget()) {
            Robot.AUTO_DRIVE.ArcadeDrive(-0.65, Robot.AUTO_DRIVE.pidTune, true);
            double tx = LIMELIGHT.getXDistance();
            Robot.AUTO_DRIVE.setSetpoint(Robot.SUB_GYRO.getYaw() + tx);
            SmartDashboard.putNumber("Drive PID setpoint", Robot.AUTO_DRIVE.getSetpoint());
            lastTX = tx;
        } else {
            try {
                if (lastTX > 0) {
                    Robot.AUTO_DRIVE.TankDrive(-.25, .25);
                } else if (lastTX < 0) {
                    Robot.AUTO_DRIVE.TankDrive(.25, -.25);
                }
            } catch (NullPointerException e) {
                Robot.AUTO_DRIVE.TankDrive(0, 0);
                System.out.println("oh no");
                e.printStackTrace();
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}