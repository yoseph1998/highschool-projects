package swerve.tracker.driver.station.components;

import java.awt.*;

/**
 * Created by yoseph on 5/20/2016.
 */
public interface Component {
    public void init(Graphics2D g);
    public void update(Graphics2D g);

    public void draw(Graphics2D g);
}
