package org.usfirst.frc.team6530.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Shea told me to monitor PDP usage but not sure if it's possible
 */
public class subsystemPDP extends Subsystem {

	static PowerDistributionPanel _PDP;
	public subsystemPDP() {
		_PDP = new PowerDistributionPanel(0);
	}
	public double portCurrent(int port) {
//		return _PDP.getCurrent(port);
		return 0;
	}
    public void initDefaultCommand() {
    }
}
