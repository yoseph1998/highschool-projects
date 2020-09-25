package swerve.tracker.robot.commands;

import swerve.tracker.robot.framework.Command;

/**
 * Created by yoseph on 5/10/2016.
 */
public class TestCommand extends Command {
    public String name;
    private long time0;

    public TestCommand(String name) {
        this(name, 3);
    }

    public TestCommand(String name, double t) {
        this.name = name;
        this.setTimeout(t);

    }

    @Override
    protected void init() {

        System.out.println(name + " initialized.");
        time0 = System.nanoTime();
    }

    @Override
    protected void update() {
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        System.out.println(name + " ended.");
    }

    @Override
    protected void interrupt() {
        System.out.println("    " + name + " interrupted.");
    }
}
