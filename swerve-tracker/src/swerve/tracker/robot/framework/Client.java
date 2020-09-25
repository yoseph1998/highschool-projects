package swerve.tracker.robot.framework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by yoseph on 5/11/2016.
 * A class designed to read data from a socket server and update the respective objects with the received data.
 * This runs on its own thread to not slow the program if socket delays are experienced.
 */
public class Client implements Runnable {

    private final String host;
    private final int port;
    private final long rate = 20;
    private volatile Thread thread;
    private volatile boolean running = false;
    private Socket socket;
    private BufferedReader input;
    private String[] dataType = {"SDSignal", "Joystick"};
    private String[] data = new String[dataType.length];
    private boolean hasReportedDataFailure = false;
    private volatile boolean stopped = false;
    private volatile boolean initialized = false;


    public Client(String host, int port) {
        this.port = port;
        this.host = host;
    }

    /**
     * Starts the thread.
     */
    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            running = true;
            thread.start();
        }
    }

    /**
     * Tells the thread to stop after it's current loop is over.
     */
    public void stop() {
        running = false;
    }

    /**
     * Initializes the socket and input stream.
     */
    private void init() {
        try {
            socket = new Socket(host, port);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.out.println("Could not create socket.");
        }
        initialized = true;
    }

    /**
     * Data comes in like D:
     */
    private void update() {
        String result;
        /* Receive input from stream. */
        try {
            result = input.readLine();
            if (hasReportedDataFailure)
                System.out.println("Socket data received.");
            hasReportedDataFailure = false;
        } catch (Exception e) {
            if (!hasReportedDataFailure)
                System.out.println("Failed to retrieve socket data.");
            hasReportedDataFailure = true;
            return;
        }
        /* Parse data into types and raw data. */
        try {
            if (result.substring(0, 2).equals("D:")) {
                //Remove header from data string.
                String dat = result.substring(2);
                String tempDat = "";

                //Split data by commas.
                ArrayList<String> datsArr = (ArrayList<String>) Arrays.asList(dat.split(","));

                //Save data to data array.
                for (int i = 0; i < data.length; i++) {
                    data[i] = datsArr.get(i);
                }
            } else if (result.substring(0, 3).equals("T:")) {
                //Remove the header of the data string.
                String dat = result.substring(3);

                //Split data by commas.
                ArrayList<String> types = (ArrayList<String>) Arrays.asList(dat.split(","));

                //Save types to types array.
                dataType = (String[]) types.toArray();

                //readjust the size of the data array.
                data = new String[dataType.length];
            } else {
                throw new Exception("String did not begin correctly, could not parse.");
            }
        } catch (Exception e) {
            System.out.println("Could not parse received data.");
        }

        /* Assign data to respective locations */
        try {
            for (int i = 0; i < data.length; i++) {
                if (dataType[i].equals("SDSignal")) {
                    if (Integer.parseInt(data[i]) == -1)
                        Scheduler.getInstance().stop();
                } else if (dataType[i].equals("Joystick")) {
                    Joysticks.update(data[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("Could not save or execute based on received data.");
        }
    }

    /**
     * Tries to end the socket.
     */
    private void end() {
        try {
            socket.close();
        } catch (Exception e) {
            System.out.println("Could not close Socket.");
        }
    }

    @Override
    public void run() {
        init();
        long begin = System.nanoTime();
        long time = 0;
        long wait = 0;
        while (running) {
            time = System.nanoTime();

            wait = rate - (time - begin);

            if (wait <= 0)
                wait = 5;

            try {
                thread.wait(wait);
            } catch (Exception e) {

            }
            update();
        }
        end();
        stopped = true;
    }

    /**
     * @return If the thread has stopped.
     */
    public boolean isStopped() {
        return stopped;
    }

    /**
     * @return If the socket has finished initializing.
     */
    public boolean isInitialized() {
        return initialized;
    }

    public void processInput(String[] input) {
        if(input == null)
            return;
        if(input.length == 2 && input[0].equals("stop") && input[1].equals("client"))
            stop();
    }
}
