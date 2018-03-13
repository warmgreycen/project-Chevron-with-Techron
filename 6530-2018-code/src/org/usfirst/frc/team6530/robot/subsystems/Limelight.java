package org.usfirst.frc.team6530.robot.subsystems;

import org.usfirst.frc.team6530.robot.commands.LimelightLEDOFF;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Class to represent a Limelight vision system.
 * Automatically retrieves values from {@link NetworkTable Network Tables} and contains wrapper methods
 * for reading the data.
 */
public class Limelight extends Subsystem{
	public void initDefaultCommand() {
		setDefaultCommand(new LimelightLEDOFF());
	}
	/**
	 * Integers for use with setLED
	 */
    public static final int LIMELIGHT_LED_ON = 0;
    public static final int LIMELIGHT_LED_OFF = 1;
    public static final int LIMELIGHT_LED_BLINK = 2;

    /**
     * The {@link NetworkTable} that contains the Limelight vision data.
     */
    private NetworkTable table;

    /**
     * Constructor for a Limelight with the name {@code limelight}.
     */
    public Limelight() {
        this("limelight");
    }
    /**
     * @param name The name of the network table to use. By Limelight's default settings, this is {@code limelight}.
     */
    public Limelight(String name) {
        table = NetworkTableInstance.getDefault().getTable(name);
    }

    /**
     * Sets the pipeline to be used for processing.
     * @param pipeline | The pipeline number (0-9) to be used.
     */
    public void setPipeline(int pipeline) {
        this.table.getEntry("pipeline").setNumber(pipeline);
    }

    /**
     * Returns the number of the pipeline currently in use.
     * @return An int (0-9) id of the pipeline currently in use.
     */
    public int getPipeline() {
        return this.table.getEntry("pipeline").getNumber(0).intValue();
    }

    /**
     * Sets the LED mode on the Limelight.
     * @param state | The LED state to set to.
     */
    public void setLEDs(int state) {
        this.table.getEntry("ledMode").setNumber(state);
    }

    /**
     * Gets the horizontal distance from the center of the image
     * to the center of the detected object, in degrees.
     * @return The horizontal distance from image center to object center in degrees.
     */
    public double getXDistance() {
        return this.table.getEntry("tx").getDouble(0);
    }

    /**
     * Gets the vertical distance from the center of the image
     * to the center of the detected object, in degrees.
     * @return The vertical distance from image center to object center in degrees.
     */
    public double getYDistance() {
        return this.table.getEntry("ty").getDouble(0);
    }
    
    /**
     * Tells the code that it sees a power cuboid
     */
    public boolean hasValidTarget() {
        return this.table.getEntry("tv").getDouble(0) == 1;
    }

    /**
     * Returns the {@link NetworkTable} for more direct access.
     * @return The {@link NetworkTable} for the Limelight.
     */
    public NetworkTable getTable() {
        return table;
    }

    /**
     * Method to refresh the network table. You shouldn't really ever need this
     */
    public void tableRefresh() {
        table = NetworkTableInstance.getDefault().getTable(table.getPath());
    }
}