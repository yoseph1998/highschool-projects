package swerve.tracker.driver.station.components;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by yoseph on 5/20/2016.
 */
public class JoysticksWindows implements Component {

    private int x, y, width, height;
    
    private BufferedImage i;
    private Graphics2D graphics;

    public JoysticksWindows(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        i = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics = (Graphics2D) i.getGraphics();
    }

    @Override
    public void init(Graphics2D g) {

    }

    @Override
    public void update(Graphics2D g) {

    }

    @Override
    public void draw(Graphics2D g) {
        drawContainer(graphics);
        g.drawImage(i, x, y, null);
    }

    private void drawContainer(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0, width, height);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Default", 20, 30));
        g.drawString("Joysticks", width/2-70, 35);
        g.drawLine(width/20, height / 8, width * 19 / 20 - 1, height / 8);
        g.drawRect(0,0, width-1, height-1);
    }
}
