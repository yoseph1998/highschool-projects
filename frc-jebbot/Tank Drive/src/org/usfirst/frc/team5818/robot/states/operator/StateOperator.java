package org.usfirst.frc.team5818.robot.states.operator;

import org.usfirst.frc.team5818.robot.states.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class StateOperator extends State 
{

	/**
	 * Sets the state name to "Operator"
	 */
	public StateOperator() 
	{
		super("Operator");
	}
	
	/**
	 * Sets the name of the current state for future reference
	 * @param name Name of this state
	 */
	public StateOperator(String name) 
	{
		super(name);
	}
	

	public void init()
	{
		
	}
	
	public void update(Joystick stickDrive)
	{
		
	}
	
	public void transmit(RobotDrive robot)
	{
		
	}
}
