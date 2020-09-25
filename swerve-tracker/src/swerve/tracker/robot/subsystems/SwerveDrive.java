package swerve.tracker.robot.subsystems;

import swerve.tracker.robot.RobotConstants;
import swerve.tracker.robot.framework.Subsystem;
import swerve.tracker.robot.util.Vector2d;

/**
 * Created by yoseph on 5/19/2016.
 */
public class SwerveDrive extends Subsystem {

    /**
     * The Swerve unit based on the cartesian quadrant system. unit1 being the unit in quadrant 1 in respect to the
     * robot's front, and so on.
     */
    private SwerveUnit[] units;

    /**
     * Creates and initializes the Swerve Units.
     */
    public SwerveDrive() {
        super();
        units = new SwerveUnit[4];
        units[0] = new SwerveUnit.Build(RobotConstants.SWERVE_DRIVE_PIN_1, RobotConstants.SWERVE_DIRECTION_PIN_1)
                .build();
        //units[1] = new SwerveUnit.Build(RobotConstants.SWERVE_DRIVE_PIN_2, RobotConstants.SWERVE_DIRECTION_PIN_2)
        //       .reverseAngle().build();
        //units[2] = new SwerveUnit.Build(RobotConstants.SWERVE_DRIVE_PIN_3, RobotConstants.SWERVE_DIRECTION_PIN_3)
        //       .build();
        //units[3] = new SwerveUnit.Build(RobotConstants.SWERVE_DRIVE_PIN_4, RobotConstants.SWERVE_DIRECTION_PIN_4)
        //       .reverseAngle().build();
    }

    public void init() {
        if (units[0] != null)
            units[0].init();
        if (units[1] != null)
            units[1].init();
        if (units[2] != null)
            units[2].init();
        if (units[3] != null)
            units[3].init();
    }

    /**
     * The method moves the robot by moving every wheel with the same translation vector.
     *
     * @param vector The direction to move in and the speed based on the magnitude.
     */
    public void translate(Vector2d vector) {
        if (units[0] != null)
            units[0].move(vector);
        if (units[1] != null)
            units[1].move(vector);
        if (units[2] != null)
            units[2].move(vector);
        if (units[3] != null)
            units[3].move(vector);
    }

    /**
     * This method will make the robot rotate around the given point `p`. With the wheels being at speed `speed`.
     * <p>
     * To make a swerve drive rotate around a given point all wheels must be perpendicular to that point. Then the
     * wheels need to be driven at the given speed and the robot will rotate around that point.
     * <p>
     * First it finds the vector for the line from the wheel to the center of the robot by using the width of the
     * robot and the length of the robot (assuming the wheels are at the exact edge of the robot).
     * That vector is added to the vector of the line from the point of rotation to the center.
     * That resultant vector give us the angle that wheel needs to be at to be parallel to the point of rotation.
     * <p>
     * Then that angle is shifted counter clockwise by 90 degrees and used in conjugction with the given speed to
     * drive the unit.
     * <p>
     * The process is repeated for every unit.
     *
     * @param p     The point to rotate across.
     * @param speed The wheel's speed.
     */
    public void rotate(Vector2d p, double speed) {
        /* Finding the vectors to shift for each wheel. */
        //ov stands for Offset Vector.
        //Offset Vector that is added to movement vector to find tangent line to circle around given point.
        Vector2d[] ov = new Vector2d[4];
        ov[0] = new Vector2d(RobotConstants.WIDTH, RobotConstants.LENGTH);
        ov[1] = new Vector2d(RobotConstants.WIDTH, RobotConstants.LENGTH);
        ov[2] = new Vector2d(RobotConstants.WIDTH, RobotConstants.LENGTH);
        ov[3] = new Vector2d(RobotConstants.WIDTH, RobotConstants.LENGTH);

        /* Finding angle wheels need to be at. */
        //na stands for normal angle. It is the angle the wheel needs to be in order to be normal to the line between
        //the point of rotation and the wheel.
        double[] na = new double[4];
        na[0] = ov[0].add(p).getAngle() + 90;
        na[1] = ov[1].add(p).getAngle() + 90;
        na[2] = ov[2].add(p).getAngle() + 90;
        na[3] = ov[3].add(p).getAngle() + 90;

        /* Creating the vector for every wheel for movement. */
        //mv stands for movement vector, it is the translation vector for each independent wheel.
        Vector2d[] mv = new Vector2d[4];
        mv[0] = new Vector2d(Math.cos(speed * Math.toRadians(na[0])), speed * Math.sin(Math.toRadians(na[0])));
        mv[1] = new Vector2d(Math.cos(speed * Math.toRadians(na[1])), speed * Math.sin(Math.toRadians(na[1])));
        mv[2] = new Vector2d(Math.cos(speed * Math.toRadians(na[2])), speed * Math.sin(Math.toRadians(na[2])));
        mv[3] = new Vector2d(Math.cos(speed * Math.toRadians(na[3])), speed * Math.sin(Math.toRadians(na[3])));

        /* move each wheel with it's respective vector. */
        if (units[0] != null)
            units[0].move(mv[0]);
        if (units[1] != null)
            units[1].move(mv[1]);
        if (units[2] != null)
            units[2].move(mv[2]);
        if (units[3] != null)
            units[3].move(mv[3]);
    }

    /**
     * @param trans The translation Vector.
     * @param p     The point to rotate around.
     * @param speed The speed at which to rotate.
     */
    public void move(Vector2d trans, Vector2d p, double speed) {

    }

    @Override
    public void end() {
        if (units[0] != null)
            units[0].move(90, 0);
        if (units[1] != null)
            units[1].move(90, 0);
        if (units[2] != null)
            units[2].move(90, 0);
        if (units[3] != null)
            units[3].move(90, 0);
    }

    /**
     * @param quad the quadrant the swerve unit is on.
     * @return The swerve unit instance at the given quadrant.
     */
    public SwerveUnit getSwerveUnit(int quad) {
        if (quad >= 1 && quad <= 4)
            return units[quad - 1];
        return null;
    }
}
