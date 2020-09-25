package swerve.tracker.robot.subsystems;

import org.bulldog.core.gpio.Pwm;
import swerve.tracker.robot.Robot;
import swerve.tracker.robot.hardware.ContinuousServo;
import swerve.tracker.robot.hardware.MicroServo;
import swerve.tracker.robot.util.Vector2d;

/**
 * A SwerveUnit is a Swerve Unit that has a 360 continuous rotation servo for the drive and a 180 degree servo for the
 * angling.
 * This class offers a way to control the wheel in a high level manner.
 */
public class SwerveUnit {
    private final String directionPin, drivePin;
    private final Build build;
    private ContinuousServo driveMotor;
    private MicroServo directionMotor;
    private boolean flippedAngle = false;
    private double speed, angle;

    /**
     * @param build The builder object for this unit.
     * @param drivePin The pin the drive servo is connected to.
     * @param directionPin The pin the turn servo is connected to.
     */
    private SwerveUnit(Build build, String drivePin, String directionPin) {
        this.drivePin = drivePin;
        this.directionPin = directionPin;
        this.build = build;
    }
    
    public void init() {
        driveMotor = new ContinuousServo(Robot.getInstance().getBoard().getPin(drivePin).as(Pwm.class), build.driveReversed);
        directionMotor = new MicroServo(Robot.getInstance().getBoard().getPin(directionPin).as(Pwm.class), build.angleReversed);
        driveMotor.setSpeed(0);
        directionMotor.setAngle(90);
    }
    /**
     * Moves the wheel with the given vector.
     *
     * @param vector The vector to move the wheel by.
     */
    public void move(Vector2d vector) {
        move(vector.getAngle(), vector.getMagnitude());
    }

    /**
     * Sets the wheel at the given speed and angle.
     *
     * @param angle The angle to put the swerve unit in.
     * @param speed The speed to put the unit's drive motor in.
     */
    public void move(double angle, double speed) {
        setAngle(angle);
        setSpeed(speed);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
        //Flip the speed's direction if told to do so from when we set the angle.
        if (flippedAngle == true)
            speed *= -1;
        driveMotor.setSpeed(speed);
    }

    /**
     * @return the angle of the unit. from 0(including) to 360(excluding).
     */
    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        // Normalize angle to range 0 to 360.
        angle = angle % 360;
        angle = (angle + 360) % 360;

        this.angle = angle;

        //Since the servo does not turn 360, if we go over the max angle than go back to the beginning and flip
        //motor direction to still move in the direction desired.
        if (angle > directionMotor.getMaxAngle()) {
            angle -= 180;
            if(flippedAngle == false) {
                flippedAngle = true;
                setSpeed(getSpeed());
            }
        } else {
            if(flippedAngle == true) {
                flippedAngle = false;
                setSpeed(getSpeed());
            }
        }
        directionMotor.setAngle(angle);
    }

    /**
     * A class to build the servo object based on the desired settings.
     */
    public static class Build {
        private String drivePin, directionPin;
        private boolean driveReversed = false, angleReversed = false;

        public Build(String drivePin, String directionPin) {
            this.drivePin = drivePin;
            this.directionPin = directionPin;
        }

        public Build reverseAngle() {
            angleReversed = true;
            return this;
        }

        public Build reverseDrive() {
            driveReversed = true;
            return this;
        }

        public SwerveUnit build() {
            return new SwerveUnit(this, drivePin, directionPin);
        }
    }
}
