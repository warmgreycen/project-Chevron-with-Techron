package org.usfirst.frc.team6530.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;

public class MultiSpeedController implements SpeedController {
    private SpeedController[] speedControllers;
    private double speed;

    public MultiSpeedController(TalonSRX talonSRX, TalonSRX talonSRX2, TalonSRX talonSRX3) {
        this.speedControllers = speedControllers;
        this.set(0.0);
    }

    @Override
    public double get() {
        return this.speed;
    }

    @Override
    public void set(double speed) {
        this.speed = speed;

        for (SpeedController speedController : this.speedControllers) {
            speedController.set(speed);
        }
    
   
    }

    @Override
    public void pidWrite(double output) {
        this.set(output);
    }

    @Override
    public void disable() {
        for (SpeedController speedController : this.speedControllers) {
            speedController.disable();
        }
    }

	@Override
	public void setInverted(boolean isInverted) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getInverted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void stopMotor() {
		// TODO Auto-generated method stub
		
	}
}
