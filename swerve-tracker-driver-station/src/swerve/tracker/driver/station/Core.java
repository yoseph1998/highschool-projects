package swerve.tracker.driver.station;

import net.java.games.input.Controller;
import swerve.tracker.driver.station.components.Component;
import swerve.tracker.driver.station.components.JoysticksWindows;
import swerve.tracker.driver.station.io.JInputJoystick;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by yoseph on 5/20/2016.
 */
public class Core implements Component {

    private JInputJoystick joystick;
    private Server server;
    @Override
    public void init(Graphics2D g) {
        server = new Server();
    }

    @Override
    public void update(Graphics2D g) {
        if(!joystick.pollController()) {
            System.out.println("Joystick Disconnected.");
            return;
        }
        server.send(0, joystick);
    }

    @Override
    public void draw(Graphics2D g) {

    }
}
