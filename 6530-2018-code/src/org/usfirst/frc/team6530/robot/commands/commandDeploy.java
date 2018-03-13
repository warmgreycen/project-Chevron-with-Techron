
package org.usfirst.frc.team6530.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;


/**
 * Controls the ramp
 */
public class commandDeploy {
	private Servo ramp;

		public commandDeploy (Servo ramping) {
			ramp = ramping;
	}
		public void move(Joystick joy) {
			if ((joy.getRawButtonPressed(5)) && (joy.getRawButtonPressed(6))) {
				ramp.setPosition(1);
			}else if (joy.getRawButtonPressed(4))  {
				ramp.setPosition(0);
			}else {
//				ramp.setPosition(0);
			}
		}
}
		
