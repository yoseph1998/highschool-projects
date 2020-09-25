package swerve.tracker.robot.hardware;

import org.bulldog.core.gpio.Pwm;
import org.bulldog.devices.servo.Servo;

/**
 * Created by yoseph on 5/20/2016.
 */
public class ContinuousServo extends Servo {

    private double speed;
    private boolean reversed;

    /**
     * @param pwm The pwm port this servo is on.
     */
    public ContinuousServo(Pwm pwm) {
        super(pwm);
        reversed = false;
    }

    /**
     * @param pwm The pwm pin the Servo is connected to.
     * @param reversed Weather the servo is to be reversed in movement.
     */
    public ContinuousServo(Pwm pwm, boolean reversed) {
        super(pwm);
        this.reversed = reversed;
    }

    /**
     * @return The servos last set speed.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets the percentage of speed. Ranges from -1 to 1.
     *
     * @param speed the desired speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
        if (speed > 1)
            speed = 1;
        else if (speed < -1)
            speed = -1;
        if (reversed) {
            speed *= -1;
        }
        speed *= 40;
        speed += 91.8;
        setAngle(speed);
    }

    /**
     * @return If the Servo direction is reversed.
     */
    public boolean isReversed() {
        return reversed;
    }

    /**
     * @param rev The state of reversal on the servo.
     */
    public void setReversed(boolean rev) {
        reversed = rev;
    }
}
