package org.usfirst.frc.team5818.robot.states;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * This is the State Controller class. It is what chooses the state to run the robot on. For example
 * if the main loop decides to move from the autonomous state to the teleop state, it should go
 * through this class.
 * 
 * All methods and fields should be static, this class should be 100%
 * static to allow other classes and objects in the code to access it.
 * 
 * @author Yoseph Alabdulwahab
 *
 */
public class StateC {
	
	public static final int STATE_AUTONOMOUS = 0;
	public static final int STATE_OPERATOR = 1;
	public static final int STATE_TEST = 2;
	
	private State states[] = {};
	
	public void init()
	{
		
	}
	
	public void update(Joystick stickDrive)
	{
		
	}
	
	public void transmit(RobotDrive drive)
	{
		
	}

	
}
