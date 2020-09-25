package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class TextOutput {
    private File file;
    private FileWriter writer;
    public TextOutput(File file) {
       this.file = file;
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(TeamReport report) {

        try {
            writer.write("TEAM #  " + report.getTeam() + "\n");
            writer.write("MATCH # " + report.getMatchNumber() + "\n");
            writer.write("AUTONOMOUS\n");
            writer.write("==========\n");
            writer.write(report.crossedBaseline() ? "Crossed baseline\n" : "Did not cross baseline\n");
            writer.write(report.hopperWasActivated() ? "Activated hopper\n" : "Did not activate hopper\n");
            writer.write("Gear placement " + report.getAutoGearPlacement() + "\n");
            writer.write("Gear placement on side peg " + report.getAutoGearSidePeg() + "\n");
            writer.write(report.getAutoHighShots() + " fuel in high goal\n");
            writer.write(report.getAutoLowShots() + " fuel in low goal\n");
            writer.write("TELOPERATED\n");
            writer.write("===========\n");
            writer.write(report.getTeleopHighShots() + " fuel in high goal\n");
            writer.write(report.getTeleopLowShots() + " fuel in low goal\n");
            writer.write("climb " + report.getClimbed() + "\n");
            writer.write(report.getGearsAttempted() + " gears attempted\n");
            writer.write(report.getGearsPlaced() + " gears placed\n");
            writer.write("defense: " + report.getDefense() + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}