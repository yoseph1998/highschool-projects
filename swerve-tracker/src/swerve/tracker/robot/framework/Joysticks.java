package swerve.tracker.robot.framework;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yoseph on 5/16/2016.
 * This class stores all the currently existing joysticks to allow the Client class to update the state of the buttons
 * and axis of a joystick.
 */
public class Joysticks {
    private final static HashMap<Integer, Joystick> joysticks = new HashMap<Integer, Joystick>();

    /**
     * If a joystick at a given port already exists, it will be replaced with the new Joystick.
     * @param stick The joystick to add.
     * @return The added joystick.
     */
    public synchronized static Joystick add(Joystick stick) {
        joysticks.put(stick.getPort(), stick);
        return stick;
    }

    /**
     * @param port The port the joystick desired is on.
     * @return The joystick at the given port.
     */
    public synchronized static Joystick getJoystick(int port) {
        return joysticks.get(port);
    }

    /**
     * Updates the values of the Joystick.
     *
     * @param data The unparsed data for a joystick.
     *             port,a1,a2,a3,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11
     */
    public synchronized static void update(String data) {
        List<String> dataList = Arrays.asList(data.split(";"));
        int port;
        Joystick stick = null;
        for (int i = 0; i < dataList.size(); i++) {
            String curDat = dataList.get(i);
            if (i == 0) {
                port = Integer.parseInt(curDat);
                stick = getJoystick(port);
                if (stick == null)
                    stick = new Joystick(port);
            } else if (i <= stick.getNumAxis()) {
                double value = Double.parseDouble(curDat);
                if (stick != null)
                    stick.updateAxis(i, value);
            } else if (i <= stick.getNumAxis() + stick.getNumButtons()) {
                double value = Integer.parseInt(curDat);
                boolean state = false;
                if (value == 1)
                    state = true;
                if (stick != null)
                    stick.updateButton(i - 3, state);
            }
        }
    }
}
