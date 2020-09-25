package org.usfirst.frc.team5818.robot.states;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * A state class for state objects to follow. This is how we change what state the robot is
 * running. For example on the Autonomous period of the match the main code loop should be
 * referencing the Autonomous state.
 * @author Yoseph Alabdulwahab
 *
 */
public abstract class State {
	
	/**
	 * The name of this state
	 */
	private final String stateName;
	
	/**
	 * Sets the name to the given string.
	 * @param name The name of this state.
	 */
	public State(String name)
	{
		stateName = name;
	}
	
	/**
	 * Returns the name of this state.
	 * @return The name of this state.
	 */
	public String getName()
	{
		return stateName;
	}
	
	/**
	 * Initializes this state
	 */
	public abstract void init();
	
	/**
	 * does all data calculation and updating.
	 * @param stick The joystick.
	 */
	public abstract void update(Joystick stickDrive);
	
	public abstract void transmit(RobotDrive robot);

}
