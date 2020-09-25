package swerve.tracker.driver.station;

import swerve.tracker.driver.station.components.Component;
import swerve.tracker.driver.station.components.JoysticksWindows;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by yoseph on 5/20/2016.
 */
public class GUI implements Component {

    private JoysticksWindows joysticksWindow;
    @Override
    public void init(Graphics2D g) {
        joysticksWindow = new JoysticksWindows(10,10, 400, 400);
    }

    @Override
    public void update(Graphics2D g) {
        joysticksWindow.update(g);
    }

    @Override
    public void draw(Graphics2D g) {
        joysticksWindow.draw(g);
    }
    public JoysticksWindows getJoysticksWindow() {
        return joysticksWindow;
    }
}
