package org.usfirst.frc.team6530.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Contains functions for use with the Logitech F310 controller.
 * @author Warm
 */
public class Logitech extends Joystick {
  // Logitech axis ports
  public static final int AXIS_LEFT_X = 1;
  public static final int AXIS_LEFT_Y = 2;
  public static final int AXIS_SHOULDER = 3;
  public static final int AXIS_RIGHT_X = 4;
  public static final int AXIS_RIGHT_Y = 5;
  public static final int AXIS_DPAD = 6;

  // Logitech buttons
  public static final int BUTTON_A = 2;
  public static final int BUTTON_B = 3;
  public static final int BUTTON_X = 1;
  public static final int BUTTON_Y = 4;
  public static final int BUTTON_SHOULDER_LEFT = 5;
  public static final int BUTTON_SHOULDER_RIGHT = 6;
  public static final int BUTTON_TRIGGER_LEFT = 7;
  public static final int BUTTON_TRIGGER_RIGHT = 8;
  public static final int BUTTON_BACK = 9;
  public static final int BUTTON_START = 10;
  public static final int BUTTON_LEFT_STICK = 11;
  public static final int BUTTON_RIGHT_STICK = 12;
  public static final int BUTTON_MODE = -1;
  public static final int BUTTON_LOGITECH = -1;


  public Logitech(int LogitechPort) {
    super(LogitechPort);
  }
  
  public double getLeftX() {
    return getRawAxis(AXIS_LEFT_X);
  }
  public double getRightX() {
    return getRawAxis(AXIS_RIGHT_X);
  }
  public double getLeftY() {
    return getRawAxis(AXIS_LEFT_Y);
  }
  public static double RIGHT_Y(Joystick joy) {return (joy.getRawAxis(5));}
  public boolean getButtonStateA() {
    return getRawButton(BUTTON_A);
  }
  public boolean getButtonStateB() {
    return getRawButton(BUTTON_B);
  }
  public boolean getButtonStateX() {
    return getRawButton(BUTTON_X);
  }
  public boolean getButtonStateY() {
    return getRawButton(BUTTON_Y);
  }
  public JoystickButton getButtonA() {
    return new JoystickButton(this, BUTTON_A);
  }
  public JoystickButton getButtonB() {
    return new JoystickButton(this, BUTTON_B);
  }
  public JoystickButton getButtonX() {
    return new JoystickButton(this, BUTTON_X);
  }
  public JoystickButton getButtonY() {
    return new JoystickButton(this, BUTTON_Y);
  }
  /**
   * DPad Left and Right only
   * WPILIB cannot access the vertical axis of the Logitech Game Controller Dpad
   */
  public double getDPadX() {
    return getRawAxis(AXIS_DPAD);
  }
  public boolean getDPadLeft() {
    double x = getDPadX();
    return (x < -0.5);
  }
  public boolean getDPadRight() {
    double x = getDPadX();
    return (x > 0.5);
  }
  public JoystickButton getStartButton(){
    return new JoystickButton(this, BUTTON_START);
  }
  public JoystickButton getBackButton() {
    return new JoystickButton(this, BUTTON_BACK);
  }
  public JoystickButton getLeftShoulder() {
    return new JoystickButton(this, BUTTON_SHOULDER_LEFT);
  }
  public JoystickButton getRightShoulder() {
    return new JoystickButton(this, BUTTON_SHOULDER_RIGHT);
  }
  public JoystickButton getLeftStickClick() {
    return new JoystickButton(this, BUTTON_LEFT_STICK);
  }
  public JoystickButton getRightStickClick() {
    return new JoystickButton(this, BUTTON_RIGHT_STICK);
  }
  public JoystickButton getLeftTriggerClick() {
    return new JoystickButton(this, BUTTON_TRIGGER_LEFT);
  }
  public JoystickButton getRightTriggerClick() {
    return new JoystickButton(this, BUTTON_TRIGGER_RIGHT);
  }
}