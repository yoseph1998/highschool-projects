package swerve.tracker.driver.station;

import swerve.tracker.driver.station.io.JInputJoystick;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by yoseph on 5/20/2016.
 */
public class Server {
    private String types = "SDSignal, Joystick";
    private ServerSocket server;
    private BufferedWriter output;

    public Server() {
        try {
            server = new ServerSocket(5818);
            Socket socket = server.accept();
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {

        }
    }

    public void send(int sds, JInputJoystick... joysticks) {
        if (server == null)
            return;
        if (Arrays.asList(types.split(",")).size() != 1 + joysticks.length) {
            types = "SDSignal";
            for (int i = 0; i < joysticks.length; i++)
                types += ", Joystick";
            try {
                output.write("T:" + types);
            } catch (Exception e) {

            }
        }

        String data = "0";

        for(int i = 0; i < joysticks.length; i++) {
            data +=",0,";
            data+="," + joysticks[i].getXAxisValue();
            data+="," + joysticks[i].getYAxisValue();
            data+="," + joysticks[i].getZAxisValue();

            for(int j = 0; j < joysticks[i].getNumberOfButtons(); j++) {
                data+=","+joysticks[i].getButtonValue(j);
            }
        }
        try {
            output.write("D:"+data);
        } catch (Exception e) {

        }
    }
}
