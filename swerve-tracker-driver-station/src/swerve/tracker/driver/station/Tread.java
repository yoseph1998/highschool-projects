package swerve.tracker.driver.station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Created by yoseph on 5/13/2016.
 */
public class Tread extends JPanel implements Runnable, KeyListener, MouseListener{

    /**
     * Frame rate for the program.
     */
    private final int FPS = 60;
    /**
     * Tick time for the program.
     */
    private final long target = 1000 / FPS;

    /**
     * The width of the image.
     */
    public static final int WIDTH = 1080;
    /**
     * The Height of the image.
     */
    public static final int HEIGHT = (int)(WIDTH*9.0/16.0);
    /**
     * A Scalar in relation to the images quality and the frames size.
     */
    private static double wScale, hScale;
    private final GUI gui;
    private final Core core;
    /**
     * The identifier for the thread's state. If set to false thread will safely stop.
     */
    private volatile boolean running = false;

    /**
     * The JFrame this JPanel is on.
     */
    private static JFrame frame;

    /**
     * The thread this program is running on.
     */
    private Thread thread;
    /**
     * The buffered image all the content is on.
     */
    private BufferedImage image;
    /**
     * The graphics of the image on the screen.
     */
    private Graphics2D imageGraphics;

    public Tread(JFrame frame) {
        super();
        this.frame = frame;
        frame.setSize((int)WIDTH, (int)HEIGHT);
        this.setFocusable(true);
        this.requestFocus();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        imageGraphics = (Graphics2D) image.getGraphics();
        core = new Core();
        gui = new GUI();
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    @Override
    public void addNotify() {

    }

    private void init(Graphics2D g) {

        core.init(g);
        gui.init(g);
    }

    private void update(Graphics2D g) {
        wScale = (double) frame.getWidth() / WIDTH;
        hScale = (double) frame.getHeight() / HEIGHT;
        core.update(g);
        gui.update(g);
    }

    private void draw(Graphics2D g) {
        core.draw(g);
        gui.draw(g);
        repaint();
    }

    private void end(Graphics2D g) {

    }

    public void endProgram() {
        running = false;
        frame.dispose();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, (int)(WIDTH*wScale), (int)(HEIGHT*hScale), this);
        g.dispose();
    }

    @Override
    public void run() {
        init(imageGraphics);
        long bTime = System.nanoTime();
        long fTime = System.nanoTime();
        long wTime = target;
        long dTime = 0;
        while(running) {
            fTime = System.nanoTime();
            wTime = fTime - bTime;
            dTime = target - wTime;
            if(dTime <= 5)
                dTime = 5;
            try {
                //Thread.sleep(dTime);
            } catch (Exception e) {
                System.out.println("Could not sleep thread");
            }
            bTime = System.nanoTime();
            this.update(imageGraphics);
            this.draw(imageGraphics);
        }
        end(imageGraphics);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public GUI getGui() {
        return gui;
    }

    public Core getCore() {
        return core;
    }
}
