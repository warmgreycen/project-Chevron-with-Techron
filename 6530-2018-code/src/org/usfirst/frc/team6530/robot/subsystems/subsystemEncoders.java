package org.usfirst.frc.team6530.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import org.usfirst.frc.team6530.robot.subsystems.common.IDisplay;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class subsystemEncoders implements org.usfirst.frc.team6530.robot.subsystems.common.IDisplay {
	static final int LEFT_A = 3;
	static final int LEFT_B = 4;
	static final int RIGHT_A = 1;
	static final int RIGHT_B = 2;
	
	//126 per rotation
	Encoder left;
	Encoder right;
	public subsystemEncoders() {
		left = new Encoder(LEFT_A, LEFT_B, false, EncodingType.k1X);
		right = new Encoder(RIGHT_A, RIGHT_B, true, EncodingType.k1X);
		left.reset();
		right.reset();
	}
	
	public int GetLeft() {
		return left.get();
	}
	
	public int GetRight() {
		return right.get();
	}
	
	public void ResetAll() {
		right.reset();
		left.reset();
	}
	
	public void ResetLeft() {
		left.reset();
	}
	
	public void ResetRight() {
		right.reset();
	}

	@Override
	public void Display() {
		SmartDashboard.putString("Left", "Left:" + Integer.toString(left.get()));
		SmartDashboard.putString("Right", "Right:" + Integer.toString(right.get()));
	}
}
