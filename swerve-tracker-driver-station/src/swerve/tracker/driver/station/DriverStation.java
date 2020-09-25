package swerve.tracker.driver.station;

import javax.swing.*;

/**
 * Created by yoseph on 5/13/2016.
 */
public class DriverStation {
    public static JFrame frame;
    public static Tread tread;
    public static void main(String[] args) {
        frame = new JFrame("Driver Station");
        frame.setContentPane((tread = new Tread(frame)));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                tread.endProgram();
            }
        });
    }
    public static Tread getTread() {
        return tread;
    }
}
