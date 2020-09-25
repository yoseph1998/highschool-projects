package swerve.tracker.robot.framework;

import org.bulldog.core.util.BulldogUtil;
import swerve.tracker.robot.Robot;
import swerve.tracker.robot.RobotConstants;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by yoseph on 5/10/2016.
 * A class that contains the robot thread, manages all commands, and runs the robot code.
 */
public class Scheduler implements Runnable {

    /* Static Methods */
    private static Scheduler scheduler;
    private volatile static String[] arguments;
    private final Thread thread;
    /**
     * The refresh rate of the program in milli seconds.
     */
    private final long rate = 20;
    private final Client client;
    private final CommandScheduler commandScheduler;
    private Robot robot;
    private ArrayList<Subsystem> subsystems;
    private volatile boolean running = false;

    /**
     * Starts the Scheduler, Client, and Command Scheduler thread.
     */
    public Scheduler() {
        subsystems = new ArrayList<Subsystem>();
        client = new Client(RobotConstants.SERVER_HOST, RobotConstants.SERVER_PORT);
        commandScheduler = new CommandScheduler();
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    /**
     * @param args The arguments on running the program.
     */
    public static void main(String args[]) {
        scheduler = new Scheduler();
        arguments = args;

        String input = "";
        while(!input.equals("stop")) {
            Scanner s = new Scanner(System.in);
            input = s.nextLine();
            input = input.toLowerCase();
            String[] inputArr = input.split(" ");
            scheduler.processInput(inputArr);
        }
    }

    private synchronized void processInput(String[] input) {
        if(input == null)
            return;;
        if(input.length == 1 && input[0].equals("stop"))
            stop();
        robot.processInput(input);
        client.processInput(input);
        commandScheduler.processInput(input);
    }

    /**
     * @return An instance of the running Scheduler.
     */
    public static Scheduler getInstance() {
        return scheduler;
    }

    @Override
    public void run() {
        client.start();
        commandScheduler.start();
        System.out.println("Waiting for socket to initialize...");
        while(!client.isInitialized());
        System.out.println("Starting robot.");
        robot = Robot.getInstance();
        for (int i = 0; i < subsystems.size(); i++) {
            subsystems.get(i).init();
        }
        robot.init(arguments);
        long begin = System.nanoTime();
        long time = 0;
        long wait = 0;
        while (running) {
            time = System.nanoTime();

            wait = rate - (time - begin);

            if (wait < 0)
                wait = 5;

            try {
                thread.wait(wait);
            } catch (Exception e) {
            }
            robot.update();

        }
        System.out.println("Stopping robot components.");
        for (int i = 0; i < subsystems.size(); i++) {
            subsystems.get(i).end();
        }
        robot.end();
        commandScheduler.stop();
        client.stop();
        System.out.println("Waiting for other threads to end...");
        while(!client.isStopped() || !commandScheduler.isStopped());
        System.out.println("Robot stopped");
        //System.exit(0); Commented out to not have the program abandon the terminal while other threads are running.
    }


    /**
     * adds a subsystem to the program to keep track of commands running on them.
     *
     * @param subsystem The added subsystem.
     */
    public void addSubsystem(Subsystem subsystem) {
        subsystems.add(subsystem);
    }


    /**
     * @return The CommandScheduler.
     */
    public synchronized CommandScheduler getCommandScheduler() {
        return commandScheduler;
    }

    /**
     * Peacefully stops the thread by waiting until this current iteration is over then calls the finalization methods
     * and no longer continues to run.
     */
    public void stop() {
        running = false;
    }
}
