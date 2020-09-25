package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVOutput {
    private File file;
    private FileWriter writer;
    public CSVOutput(File f) {
        file = f;
        try {
            writer = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(TeamReport this) {

        try {
            writer.write(report.getMatchNumber() + ",");
            writer.write(report.getTeam() + ",");
            writer.write(report.crossedBaseline() ? "TRUE," : "FALSE,");
            writer.write(report.hopperWasActivated() ? "TRUE," : "FALSE,");
            writer.write("\"" + report.getAutoGearPlacement() + "\",");
            writer.write(report.getAutoGearSidePeg() + ",");
            writer.write(report.getAutoHighShots() + ",");
            writer.write(report.getAutoLowShots() + ",");
            writer.write(report.getTeleopHighShots() + ",");
            writer.write(report.getTeleopLowShots() + ",");
            writer.write(report.getClimbed() + ",");
            writer.write(report.getGearsAttempted() + ",");
            writer.write(report.getGearsPlaced() + ",");
            writer.write(report.getDefense() + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
