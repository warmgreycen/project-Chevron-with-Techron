package org.usfirst.frc.team6530.robot.commands.autonomousCommands;


import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6530.robot.Robot;

/**
 * Auton program to chase strips on sides of switch and scale
 *  (or whatever is in pipeline one.)using
 * limelight camera (not used in final code).
 */
public class ReflectivePursuit extends Command {
    private double lastTX;

    public ReflectivePursuit(double Tx) {
        requires(Robot.AUTO_DRIVE);
        this.lastTX = Tx;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.AUTO_DRIVE.enable();
        Robot.LIMELIGHT.setLEDs(org.usfirst.frc.team6530.robot.subsystems.Limelight.LIMELIGHT_LED_ON);
        Robot.LIMELIGHT.setPipeline(1);
    }

    @Override
    protected void execute() {
        if (Robot.LIMELIGHT.hasValidTarget()) {
            Robot.AUTO_DRIVE.ArcadeDrive(0.65, Robot.AUTO_DRIVE.pidTune, true);
            double tx = Robot.LIMELIGHT.getXDistance();
            Robot.AUTO_DRIVE.setSetpoint(Robot.SUB_GYRO.getYaw() + tx);
            SmartDashboard.putNumber("Drive PID setpoint", Robot.AUTO_DRIVE.getSetpoint());
            lastTX = tx;
        } else {
            try {
                if (lastTX > 0) {
                    Robot.AUTO_DRIVE.TankDrive(.25, -.25);
                } else if (lastTX < 0) {
                    Robot.AUTO_DRIVE.TankDrive(-.25, .25);
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