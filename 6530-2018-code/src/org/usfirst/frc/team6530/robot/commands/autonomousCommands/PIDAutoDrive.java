//package org.usfirst.frc.team6530.robot.commands.autonomousCommands;
//
//import org.usfirst.frc.team6530.robot.RandomPIDController2;
//import org.usfirst.frc.team6530.robot.Robot;
//
//import edu.wpi.first.wpilibj.PIDSource;
//import edu.wpi.first.wpilibj.PIDSourceType;
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// * 
// */
//public class PIDAutoDrive extends Command {
//	
//	
//	private final PIDSource m_source = new PIDSource() {
//
//		@Override
//		public void setPIDSourceType(PIDSourceType pidSource) {
//		}
//
//		@Override
//		public PIDSourceType getPIDSourceType() {
//			return PIDSourceType.kDisplacement;
//		}
//
//		@Override
//		public double pidGet() {
//			return Robot.gyro.getAngle() - startAngle;
//		}
//	};
//	// divide Ki and multiply Kd by 0.05 to emulate the behavior of a normal PIDController which uses a fixed 0.05 second period.
//	private final RandomPIDController2 controller = new RandomPIDController2(.05, 0.00, 0, m_source, this::usePIDOutput);
//	private final double forwardSpeed;
//	private final double distance;
//	private double stopAt;
//	private final double maxSpeed;
//	private double startPosition;
//	private double startAngle; // which may or may not be zero degrees.
//	
//	private static final double ACCELERATION = 60;//inches
//	private static final double DECELERATION = 60;//inches
//	
//    public PIDAutoDrive(double forwardSpeed, double distance) {	// What are the units of distance?
//        requires(Robot.SUB_DRIVE);
//        controller.setInputRange(0, 360);
//        System.out.println("SetInputRange");
//        controller.setContinuous();
//        System.out.println("SetContinuous");
//        controller.setOutputRange(-1, 1);
//        System.out.println("SetOutputRange");
//        this.forwardSpeed=forwardSpeed;
//        this.distance = distance;
//        maxSpeed = Math.abs(forwardSpeed);
//    }
//    
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	// Encoder 2 spins the opposite direction of Encoder 1.  Encoder 1 has a postive sense, Encoder 2 will therefore have a negative sense.
//    	// In order to add the two values correctly, you should add Encoder 1 to the negative of Encoder 2, or "Encoder 1 - Encoder 2"
//    	// This will, counter intuitively, add the two values, NOT take the difference between the two values
//    	/*
//    	 * A note on Encoders and the sign of distance:
//    	 * Encoders will decrement when the roll backwards.  Therefore, if you want the robot to travel backwards during autonomous,
//    	 * you must set BOTH the speed and the distance to a negative value (multiply by "BACKWARDS"
//    	 */
//    	startPosition = Robot.SUB_ENCODERS.getDistance();
//    	startAngle = Robot.SUB_GYRO.getAngle();
//    	System.out.println("PID AutoDrive");
//    	controller.enable();
//    	System.out.println("PID AutoDrive initilize: Started  stopAt:"+stopAt+" distance:"+distance);
//    }
//    
//    /**
//     * Interpolate: Given two points and the x value of third point, determines
//     * y value of that third point.
//     * 
//     * x is distance and y is speed when used in calcThrottle
//     * 
//     * @param x1 x value of first point
//     * @param y1 y value of first point
//     * @param x2 x value of second point
//     * @param y2 y value of second point
//     * @param x x value of third point
//     * @return y value of third point
//     */
//    
//    private double interpolate(double x1, double y1, double x2, double y2, double x) {
//        double y = y1 + (y2 - y1) / (x2 - x1) * (x - x1);
//        return y;
//    }
//    
//    private double distanceTraveled() {
//    	return Robot.SUB_ENCODERS.getDistance() - startPosition;
//    }
//    private double calcThrottleSteadyState() {
//    	return maxSpeed;
//    }
//    private double calcThrottleDecelerate(double inchesTraveled) {
//    	if (inchesTraveled > distance) {
//    		return 0.2;
//    	}
//    	//Robot.driveTrain.setBrakeCoast(inchesTraveled < distance - DECELERATION)
//    	return interpolate(distance-DECELERATION, 1, distance, 0.2, inchesTraveled);
//    }
//    private double calcThrottleAccelerate(double inchesTraveled) {
//    	//return inchesTraveled/ACCELERATION;
//    	if (inchesTraveled < 0) {
//    		return 0.2;
//    	}
//    	return interpolate(0,0.3,ACCELERATION, 1, inchesTraveled);
//    }
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	//new PrintOutEncoderValues(60,Robot.driveTrain.encoderPort,Robot.driveTrain.encoderStarboard);
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//    	/*
//    	 * isFinished() for PIDAutoDrive
//    	 * Want to stop when we have reached the desired Encoder position
//    	 * The encoder position "stopAt" is recoreded 
//    	 */
//        if (distanceTraveled() >= distance) System.out.println("ISFINISHED");
//    	return Math.abs(distanceTraveled()) >= Math.abs(distance);
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    	System.out.println("Stopping...");
//    	controller.disable();
//    	controller.reset();
//    }
//    
//    private void usePIDOutput(double output) {
//    	/*te their output to zero when disabled.
//    	 * The watchdog will call disable() every 0.1 seconds when
//    	 * the command isn't r
//    	 * PID controllers wriunning.  Every time the output is written
//    	 * we cause the robot to move.  If the watchdog isn't alive,
//    	 * don't accept input.
//    	 */
//    	//System.out.println("Output updated to: "+output);//+", Time since last run: "+controller.getTimeDelta());
//    	double rawDistance = Math.abs(distanceTraveled());
//    	double steadyState = calcThrottleSteadyState();
//    	double accel = calcThrottleAccelerate(rawDistance);
//    	//accel = 1;
//    	double decel = calcThrottleDecelerate(rawDistance);
//    	double potentialThrottle = Math.min(steadyState, decel);
//    	double newThrottle = Math.min(potentialThrottle, accel);
//    	if (this.forwardSpeed <0) {
//    		newThrottle *= -1;
//    	}
//    	System.out.printf("PID: t %.3f; a %.3f; ss %.3f; d %.3f;\nnT %.3f; fS %.3f; dist %.3f; output %.3f\n", distanceTraveled(), accel, steadyState, decel, newThrottle, this.forwardSpeed, distance,output);
//    	    	
//    	Robot.AUTO_DRIVE.ArcadeDrive(newThrottle, -output, false);
//    }
//}