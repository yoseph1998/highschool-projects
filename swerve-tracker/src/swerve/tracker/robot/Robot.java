package swerve.tracker.robot;

import org.bulldog.beagleboneblack.BBBNames;
import org.bulldog.core.platform.Board;
import org.bulldog.core.platform.Platform;
import swerve.tracker.robot.commands.TestServo;
import swerve.tracker.robot.commands.TestSwerveUnit;
import swerve.tracker.robot.hardware.ContinuousServo;
import swerve.tracker.robot.hardware.MicroServo;
import swerve.tracker.robot.subsystems.SwerveDrive;
import swerve.tracker.robot.subsystems.SwerveUnit;
import swerve.tracker.robot.utils.Utils;

/**
 * The class where the high level code begins.
 */
public class Robot {
    private static Robot robot;
    private final Board board;
    private final SwerveDrive swerveDrive;


    private final RobotDriver driver;

    private Robot() {
        board = Platform.createBoard();
        swerveDrive = new SwerveDrive();
        driver = new RobotDriver();
    }

    /**
     * If an instance does not exits it will create a new one.
     * This method should never be called in any constructor except the Scheduler's constructor, or a program infinite
     * could occur freezing the program.
     *
     * @return An instance of the Robot.
     */
    public static Robot getInstance() {
        if (robot == null)
            robot = new Robot();
        return robot;
    }

    /**
     * Will run tests if the arguments call to do so.
     *
     * @param args The arguments received by the program.
     */
    public void init(String[] args) {
        if (driver != null)
            driver.init();
    }

    public void processInput(String[] input) {
        if (input == null)
            return;
        if (input.length >= 1 && input[0].equals("test")) {
            switch (input[1]) {
                case "microservo":
                    new TestServo(MicroServo.class, BBBNames.PWM_P9_14).start();
                    break;
                case "continuousservo":
                    new TestServo(ContinuousServo.class, BBBNames.PWM_P8_13).start();
                    break;
                case "swerveunit":
                    if (Utils.isNumeric(input[2]))
                        new TestSwerveUnit(Integer.parseInt(input[2])).start();
                    break;
            }
        }
        if (input.length >= 5 && input[0].equals("write")) {
            if (input[1].equals("swerveunit")) {
                SwerveUnit unit = null;
                if (Utils.isNumeric(input[2]))
                    unit = swerveDrive.getSwerveUnit((int)Double.parseDouble(input[2]));
                if (unit != null)
                    if (input[3].equals("speed") && Utils.isNumeric(input[4]))
                        unit.setSpeed(Double.parseDouble(input[4]));
                    else if (input[3].equals("angle") && Utils.isNumeric(input[4]))
                        unit.setAngle(Double.parseDouble(input[4]));


            }
        }
    }

    /**
     * The robot's iterative method.
     * Called every loop.
     */
    public void update() {
        if (driver != null)
            driver.update();
    }

    /**
     * The last method that is called in the program. Before the robot stops.
     */
    public void end() {
        if (driver != null)
            driver.end();
    }

    /**
     * @return The instance of the board object.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @return The instance of the SwerveDrive
     */
    public SwerveDrive getSwerveDrive() {
        return swerveDrive;
    }

}

