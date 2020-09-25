package swerve.tracker.robot.commands;

import org.bulldog.core.gpio.Pwm;
import org.bulldog.devices.servo.Servo;
import swerve.tracker.robot.Robot;
import swerve.tracker.robot.framework.Command;
import swerve.tracker.robot.hardware.ContinuousServo;
import swerve.tracker.robot.hardware.MicroServo;

/**
 * Created by yoseph on 5/20/2016.
 * This test will run the servo in a way that will make it easy to debug a problem.
 */
public class TestServo extends Command {
    private final Pwm pwm;
    private int dir;
    private Class type;
    private Servo servo;
    private long lastTime;
    private double i = 0;
    private boolean finished = false;

    /**
     * @param c   The class of the servo that wants to be tested.
     * @param pin The pin to test the servo on.
     */
    public TestServo(Class c, String pin) {
        pwm = Robot.getInstance().getBoard().getPin(pin).as(Pwm.class);
        type = c;
    }

    /**
     * Will run the servo from one extreme to another, then reverse it and run the same pattern.
     */
    @Override
    protected void init() {
        if (type == ContinuousServo.class) {
            servo = new ContinuousServo(pwm);
            i = -1;
        } else if (type == MicroServo.class) {
            servo = new MicroServo(pwm);
            i = 0;
        }
        else
            System.out.println("Test for " + type.getName() + " does not exist.");
    }

    @Override
    protected void update() {
        if(type == MicroServo.class) {
            if (System.nanoTime() - lastTime >= 400000000L && i <= 360) {
                System.out.println("Angle = " + i);
                servo.setAngle(i);
                if (i >= 360)
                    finished = true;
                i += 10;
                lastTime = System.nanoTime();
            }
        } else if (type == ContinuousServo.class) {
            if (System.nanoTime() - lastTime >= 400000000L && i <= 1) {
                System.out.println("Speed = " + i);
                ((ContinuousServo)servo).setSpeed(i);
                if (i >= 0.95)
                    finished = true;
                i += 0.1;
                lastTime = System.nanoTime();
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

    @Override
    protected void end() {
        if(type == MicroServo.class)
            ((MicroServo)servo).setAngle(0);
        else if(type == ContinuousServo.class)
            ((ContinuousServo)servo).setSpeed(0);
        System.out.println("Test complete.");
    }

    @Override
    protected void interrupt() {
        if(type == MicroServo.class)
            ((MicroServo)servo).setAngle(0);
        else if(type == ContinuousServo.class)
            ((ContinuousServo)servo).setSpeed(0);
    }
}
