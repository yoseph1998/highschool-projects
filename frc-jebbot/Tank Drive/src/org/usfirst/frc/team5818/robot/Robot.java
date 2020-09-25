package org.usfirst.frc.team5818.robot;


import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it 
 * contains the code necessary to operate a robot with tank drive.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
	
	/**
	 * The left motor channel on our robot.
	 */
	public static final int MOTOR_CHANNEL_LEFT = 0;
	
	/**
	 * The right motor channel on our robot.
	 */
	public static final int MOTOR_CHANNEL_RIGHT = 1;
	
	/**
	 * The port of which our driving joystick is hooked
	 * to on the FRC driver station.
	 */
	public static final int JOYSTICK_DRIVE_PORT = 0;
	
	/**
	 * The class that handles the basic driving operations.
	 */
    private RobotDrive myRobot;
    
    /**
     * The joystick that deals with the driving operations of the robot.
     */
    private Joystick driveStick;
    
    /**
     * Initializes the RobotDrive object and sets the motor channels
     * for the tank drive setup.
     * 
     * Initializes the Joystick for driving to run on the driving port.
     */
    public Robot() 
    {
    	//Setting the motor channels on the RobotDrive object for the tank drive setup.
        myRobot = new RobotDrive(MOTOR_CHANNEL_LEFT, MOTOR_CHANNEL_RIGHT);
        myRobot.setExpiration(0.1);
        
        //Setting the joystick port to the first joystick drive port
        driveStick = new Joystick(JOYSTICK_DRIVE_PORT);
    }

    
    /**
     * Runs the motors with tank steering.
     */
    public void operatorControl() {
    	
        myRobot.setSafetyEnabled(true);
        
        //Operator Control loop.
        while (isOperatorControl() && isEnabled()) 
        {
        	
        	double forwardPower = driveStick.getY() * (-1);
        	double turnPower = driveStick.getX() * (-1);
        	
        	//TODO format numbers to be 0.00 only, instead of the entire double.
        	//Print the joystick values to the SmartDashboard
        	SmartDashboard.putString("DB/String 0", "Stick Y = " + forwardPower);
        	SmartDashboard.putString("DB/String 1", "Stick X = " + turnPower);
        	
        	/*
        	 * See http://home.kendra.com/mauser/Joystick.html for explanation on
        	 * how to convert joystick control to tank drive control.
        	 */
        	double v = forwardPower * (1 - Math.abs(turnPower)) + forwardPower;
        	double w = turnPower * (1 - Math.abs(forwardPower)) + turnPower;
        	double leftPower = (v-w)/2;
        	double rightPower = (v+w)/2;
        	
        	//Write the drive speed to each motor channel (left_channel, right_channel)
        	myRobot.tankDrive(leftPower, rightPower);
    		
        	// Wait for a motor update time. To not send more information than the motor can process.
            Timer.delay(0.005);
        }
    }
}
