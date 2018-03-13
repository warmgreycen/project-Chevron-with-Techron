package org.usfirst.frc.team6530.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import org.usfirst.frc.team6530.robot.Robot;
import org.usfirst.frc.team6530.robot.Constants;

/**Some more of Greycen's auton code for driving. Not used in final code, but
 * maybe would have worked better. Uses PID control
 */


/**
 * The drivetrain subsystem. Contains methods for controlling the robot drivetrain.
 * Also includes PID angle control via the
 * {@link com.kauailabs.navx.frc.AHRS AHRS} mounted to the RoboRIO and {@link PIDSubsystem}.
 */
public class autoDriveTrain extends PIDSubsystem { //
   
    private TalonSRX driveLeftMaster;  
//    private TalonSRX driveLeftFollowOne; 
//    private TalonSRX driveLeftFollowTwo;
    private TalonSRX driveRightMaster;
//    private TalonSRX driveRightFollowOne;
//    private TalonSRX driveRightFollowTwo;
    private boolean teleop;
    public double pidTune;

    public autoDriveTrain() {
        super("Drivetrain", 0.025, 0, 0);

        /** As of now, my limelight autonomous 
         *  doesn't work with more than one motor on each side.
         *  Working on a fix but there isn't much time for it.
         */
        this.driveLeftMaster = new TalonSRX(Constants.LEFT_MASTER);
//        this.driveLeftFollowOne = new TalonSRX(Constants.LEFT_SLAVE1);
//        this.driveLeftFollowOne.set(ControlMode.Follower, Constants.LEFT_MASTER);
//        this.driveLeftFollowTwo = new TalonSRX(Constants.LEFT_SLAVE2);
//        this.driveLeftFollowTwo.set(ControlMode.Follower, Constants.LEFT_MASTER);

        this.driveRightMaster = new TalonSRX(Constants.RIGHT_MASTER);
//        this.driveRightFollowOne = new TalonSRX(Constants.RIGHT_SLAVE1);
//        this.driveRightFollowOne.set(ControlMode.Follower, Constants.RIGHT_MASTER);
//        this.driveRightFollowTwo = new TalonSRX(Constants.RIGHT_SLAVE2);
//        this.driveRightFollowTwo.set(ControlMode.Follower, Constants.RIGHT_MASTER);

        teleop = false;
        // configure pid loop
        pidTune = 0;
        double outRange = 0.9;
        setInputRange(-180, 180);
        setOutputRange(-outRange, outRange);
        getPIDController().setContinuous(true);
        setPercentTolerance(1);
    }
    protected void initDefaultCommand() {
    	/**we will never beusing the autoDriveTrain to drive,
    	 * so we will not have a default command for it.
    	 * implement Subsystem will yell at me if i don't 
    	 * have the void in the class though.
    	 */
    }

    /**
     * Drives the driveTrain tank drive style. The driveTrain will continue to
     * run until stopped with a method like {@link SUB_AUTODRIVE.StopDrive()}.
     * @param left  The power to drive the left side. Should be a {@code double}
     *              between 0 and 1.
     * @param right The power to drive the right side. Should be a {@code double}
     *              between 0 and 1.
     */
    public void TankDrive(double left, double right) {
        this.driveLeftMaster.set(ControlMode.PercentOutput, applyDeadband(left, 0.05));
        this.driveRightMaster.set(ControlMode.PercentOutput, applyDeadband(right, 0.05));
    }

    /**
     * Arcade drive method for differential drive platform.
     *
     * @param xSpeed        The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
     * @param zRotation     The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is
     *                      positive.
     * @param squaredInputs If set, decreases the input sensitivity at low speeds.
     * @author Worcester Polytechnic Institute
     */
    public void ArcadeDrive(double xSpeed, double zRotation, boolean squaredInputs) {
        xSpeed = limit(xSpeed);
        xSpeed = applyDeadband(xSpeed, 0.02);

        zRotation = limit(zRotation);
        zRotation = applyDeadband(zRotation, 0.02);

        // this stuff makes lower joystick inputs = fine control of motors
        if (squaredInputs) {
            xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
            zRotation = Math.copySign(zRotation * zRotation, zRotation);
        }
        double leftMotorOutput;
        double rightMotorOutput;

        double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

        if (xSpeed >= 0.0) {
            // First quadrant, else second quadrant
            if (zRotation >= 0.0) {
                leftMotorOutput = maxInput;
                rightMotorOutput = xSpeed - zRotation;
            } else {
                leftMotorOutput = xSpeed + zRotation;
                rightMotorOutput = maxInput;
            }
        } else {
            // Third quadrant, else fourth quadrant
            if (zRotation >= 0.0) {
                leftMotorOutput = xSpeed + zRotation;
                rightMotorOutput = maxInput;
            } else {
                leftMotorOutput = maxInput;
                rightMotorOutput = xSpeed - zRotation;
            }
        }

        driveLeftMaster.set(ControlMode.PercentOutput, limit(leftMotorOutput));
        driveRightMaster.set(ControlMode.PercentOutput, -limit(rightMotorOutput));
    }
  

    /**
     * Stops all drive motors.
     */
    public void StopDrive() {
        this.driveLeftMaster.set(ControlMode.PercentOutput, 0);
        this.driveRightMaster.set(ControlMode.PercentOutput, 0);
    }

    /**
     * Limit motor values to the -1.0 to +1.0 range.
     *
     * @param num The number to limit to [-1, 1].
     * @return The limited value.
     */
    private static double limit(double num) {
        if (num > 1.0) {
            return 1.0;
        }
        if (num < -1.0) {
            return -1.0;
        }
        return num;
    }

    protected double applyDeadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }

    protected double returnPIDInput() {
        if (!teleop) {
            return Robot.SUB_GYRO.getYaw();
        } else {
            return Robot.SUB_GYRO.getAngle();
        }
    }

    protected void usePIDOutput(double output) {
        this.pidTune = output;
    }

    public double getPidTune() {
        return pidTune;
    }
}