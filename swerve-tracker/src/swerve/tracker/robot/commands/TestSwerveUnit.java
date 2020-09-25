package swerve.tracker.robot.commands;

import org.bulldog.core.util.BulldogUtil;
import swerve.tracker.robot.Robot;
import swerve.tracker.robot.framework.Command;
import swerve.tracker.robot.subsystems.SwerveUnit;

/**
 * Created by yoseph on 5/20/2016.
 * This runs the unit in a way to allow for easy debugging to narrow down problems.
 */
public class TestSwerveUnit extends Command {
    SwerveUnit unit;
    private int unitQuad;
    private long lastTime = 0;
    private double i = 0;
    private boolean finished = false;

    /**
     * @param quad The SwerveUnit num or quadrant where it is located.
     */
    public TestSwerveUnit(int quad) {
        unitQuad = quad;
    }

    /**
     * Will run the unit from 0 degrees to 360. This will show weather the unit spins properly and weather the drive is
     * corresponding with the spin.
     */
    @Override
    protected void init() {
        unit = Robot.getInstance().getSwerveDrive().getSwerveUnit(unitQuad);
        unit.setSpeed(0.5);
    }

    @Override
    protected void update() {
        if(System.nanoTime() - lastTime >= 400000000L && i <= 360) {
            System.out.println("Angle = " + i);
            unit.setAngle(i);
            if(i >= 360)
                finished = true;
            i+= 10;
            lastTime = System.nanoTime();
        }
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

    @Override
    protected void end() {
        unit.setAngle(0);
        unit.setSpeed(0);
        System.out.println("Test Complete!");
    }

    @Override
    protected void interrupt() {
        unit.setAngle(0);
        unit.setSpeed(0);
    }
}
