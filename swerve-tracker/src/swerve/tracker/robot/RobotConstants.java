package swerve.tracker.robot;

import org.bulldog.beagleboneblack.BBBNames;

/**
 * Stores all the pin layout for different aspects of the code to use so that they can be changed easily and also to
 * make sure that no two objects try to talk to the same pin.
 */
public class RobotConstants {
    public static final String SERVER_HOST = "192.168.0.2";
    public static final int SERVER_PORT = 5818;
    public static final double LENGTH = 10;
    public static final double WIDTH = 10;
    public static final String SWERVE_DRIVE_PIN_1 = BBBNames.PWM_P8_13;
    public static final String SWERVE_DRIVE_PIN_2 = BBBNames.PWM_P8_19;
    public static final String SWERVE_DRIVE_PIN_3 = BBBNames.PWM_P8_34;
    public static final String SWERVE_DRIVE_PIN_4 = BBBNames.PWM_P8_36;
    public static final String SWERVE_DIRECTION_PIN_1 = BBBNames.PWM_P9_14;
    public static final String SWERVE_DIRECTION_PIN_2 = BBBNames.PWM_P9_16;
    public static final String SWERVE_DIRECTION_PIN_3 = BBBNames.PWM_P9_21;
    public static final String SWERVE_DIRECTION_PIN_4 = BBBNames.PWM_P9_22;


}
