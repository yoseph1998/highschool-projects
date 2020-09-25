package swerve.tracker.robot.hardware;

import org.bulldog.core.gpio.Pwm;
import org.bulldog.devices.servo.Servo;

/**
 * Created by yoseph on 5/20/2016.
 */
public class MicroServo extends Servo {

    private static final double MIN_ANGLE_DEFAULT = 0.033D;
    private static final double MAX_ANGLE_DEFAULT = 0.124D;
    private static final double INITIAL_ANGLE_DEFAULT = 0.0D;
    private static final int TIME_PER_DEGREE = 1;

    private double maxAngle, angle;
    private boolean reversed;

    /**
     * Initializes the servo to not have reversed motion and to have a 200 degree max turn capability.
     * @param pwm The pwm pin this servo is on.
     */
    public MicroServo(Pwm pwm) {
        this(pwm, false);
    }

    /**
     * Initializes the servo to not have reversed motion.
     * @param pwm The pwm pin this servo is on.
     * @param max The maximum angle this servo can possibly go to.
     */
    public MicroServo(Pwm pwm, double max) {
        this(pwm, false, max);
    }

    /**
     * Initializes the servo to have a 200 degree turn capability.
     * @param pwm The pwm pin this servo is running on.
     * @param reversed Weather the servo is reversed in movement.
     */
    public MicroServo(Pwm pwm, boolean reversed) {
        this(pwm, reversed, 180);
    }

    public MicroServo(Pwm pwm, boolean reversed, double max) {
        super(pwm, INITIAL_ANGLE_DEFAULT, MIN_ANGLE_DEFAULT, MAX_ANGLE_DEFAULT, TIME_PER_DEGREE);
        this.reversed = reversed;
        maxAngle = max;
    }

    @Override
    public double getAngle() {
        return angle;
    }

    /**
     * Will set the angle of the servo ranging from 0deg to the max angle (200deg by default).
     *
     * @param angle The desired angle.
     */
    @Override
    public void setAngle(double angle) {
        this.angle = angle;
        if (reversed) {
            angle = maxAngle - angle;
        }
        angle = angle * 180 / maxAngle;
        super.setAngle(angle);
    }

    /**
     * @return The max angle of the servo.
     */
    public double getMaxAngle() {
        return maxAngle;
    }

    /**
     * @param max The maximum angle of the servo
     */
    public void setMaxAngle(double max) {
        maxAngle = max;
    }

    /**
     * @return The reversal state of the motor.
     */
    public boolean isReversed() {
        return reversed;
    }

    /**
     * Sets weather the servo moves in the opposite way.
     *
     * @param rev The state of the reversal.
     */
    public void setReversed(boolean rev) {
        reversed = rev;
    }
}
