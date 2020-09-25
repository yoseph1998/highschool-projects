package swerve.tracker.robot.framework;

/**
 * Created by yoseph on 5/11/2016.
 * This class stores the state of all the buttons and axis of this joystick and allows for other classes to access them.
 */
public class Joystick {
    private final int port;
    private double[] rawAxis;
    private boolean[] rawButtons;

    public Joystick(int port, Type type) {
        this.port = port;
        if (type == Type.ATTACK3) {
            rawButtons = new boolean[11];
            rawAxis = new double[3];
        } else {
            rawButtons = new boolean[11];
            rawAxis = new double[3];
        }
        Joysticks.add(this);
    }

    /**
     * Will initialize to the ATTACK3 joystick at the given port.
     * @param port
     */
    public Joystick(int port) {
        this(port, Type.ATTACK3);
    }

    /**
     * @return The port this joystick is on.
     */
    public int getPort() {
        return port;
    }

    /**
     * @param but The button number.
     * @return The state of the button. 0 being released, 1 being pressed, and -1 being not found button.
     */
    public boolean getRawButton(int but) {
        but--;
        if (but > rawButtons.length || but <= 0)
            return false;
        return rawButtons[but];
    }

    /**
     * @param axis The button number.
     * @return The state of the axis. between -1 and 1.
     */
    public double getRawAxis(int axis) {
        axis--;
        if (axis > rawButtons.length || axis <= 0)
            return 0;
        return rawAxis[axis];
    }

    /**
     * Updates the RawAxis.
     */
    public boolean updateAxis(int axis, double value) {
        axis--;
        if (axis < rawAxis.length && axis >= 0) {
            rawAxis[axis] = value;
            return true;
        }
        return false;
    }

    /**
     * Updates the button.
     */
    public boolean updateButton(int but, boolean value) {
        but--;
        if (but < rawButtons.length && but >= 0) {
            rawButtons[but] = value;
            return true;
        }
        return false;
    }

    /**
     * @return The number of axis on this joystick.
     */
    public int getNumAxis() {
        return rawAxis.length;
    }

    /**
     * @return The number of buttons on this joystick.
     */
    public int getNumButtons() {
        return rawButtons.length;
    }

    @Override
    public String toString() {
        String str = " P" + port;
        for (int i = 0; i < rawAxis.length; i++) {
            str += " A" + rawAxis[i];
        }

        for (int i = 0; i < rawButtons.length; i++) {
            str += " B" + rawButtons[i];
        }
        return str;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Joystick && ((Joystick)o).getPort() == getPort();
    }

    @Override
    public int hashCode() {
        return port;
    }

    public enum Type {
        ATTACK3;
    }
}
