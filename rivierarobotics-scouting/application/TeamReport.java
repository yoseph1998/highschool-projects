package application;

import com.sun.xml.internal.bind.v2.model.core.ID;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class TeamReport extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private TextField team;
    private TextField matchNumber;

    private CheckBox crossBL;
    private CheckBox hopperActivated;

    private ToggleGroup gearPlace;
    private RadioButton noGearAttempt;
    private RadioButton gearAttempted;
    private RadioButton gearSuccess;
    private ToggleGroup gearSidePeg;
    private RadioButton sidePegNoAttempt;
    private RadioButton sidePegAttempted;
    private RadioButton sidePegSuccess;

    private ObservableList<String> shootingChoices = FXCollections.observableArrayList(
            "0 - 10", "10 - 50", "50 - 150", "150+");

    private ComboBox<?> autoHighShots;
    private ComboBox<?> autoLowShots;
    private ComboBox<?> teleopHighShots;
    private ComboBox<?> teleopLowShots;

    private ToggleGroup climb;
    private RadioButton climbNoAttempt;
    private RadioButton climbAttempted;
    private RadioButton climbSuccess;

    private Spinner<?> gearsAttempted;
    private Spinner<?> gearsPlaced;

    private ObservableList<String> defenseChoices = FXCollections.observableArrayList(
            "none", "bad", "medium", "good");
    private ComboBox<?> defense;

    private Button upload;
    private Button save;

    public String getMatchNumber() {
        return matchNumber.getCharacters().toString();
    }

    public int getTeam(){
        return Integer.parseInt(team.getCharacters().toString());
    }

    public boolean crossedBaseline() {
        return crossBL.isSelected();
    }
    public boolean hopperWasActivated() {
        return hopperActivated.isSelected();
    }
    public String getAutoGearPlacement() {
        if (gearPlace.getSelectedToggle() == noGearAttempt) return "not attempted";
        else if (gearPlace.getSelectedToggle() == gearAttempted) return "attempted but failed";
        else return "successful";
    }
    public String getAutoGearSidePeg() {
        if (gearSidePeg.getSelectedToggle() == sidePegNoAttempt) return "not attempted";
        else if (gearSidePeg.getSelectedToggle() == sidePegAttempted) return "attempted but failed";
        else return "successful";
    }
    public String getAutoHighShots() {
        if (autoHighShots.getSelectionModel().getSelectedItem() != null)
            return autoHighShots.getSelectionModel().getSelectedItem().toString();
        else
            return "not defined";
    }
    public String getAutoLowShots() {
        if (autoLowShots.getSelectionModel().getSelectedItem() != null)
            return autoLowShots.getSelectionModel().getSelectedItem().toString();
        else
            return "not defined";
    }
    public String getTeleopHighShots() {
        if (teleopHighShots.getSelectionModel().getSelectedItem() != null)
            return teleopHighShots.getSelectionModel().getSelectedItem().toString();
        else
            return "not defined";
    }
    public String getTeleopLowShots() {
        if (teleopLowShots.getSelectionModel().getSelectedItem() != null)
            return teleopLowShots.getSelectionModel().getSelectedItem().toString();
        else
            return "not defined";
    }
    public String getClimbed() {
        if (climb.getSelectedToggle() == climbNoAttempt) return "not attempted";
        else if (climb.getSelectedToggle() == climbAttempted) return "attempted but failed";
        else return "successful";
    }
    public int getGearsAttempted() {
        return Integer.parseInt(gearsAttempted.getValue().toString());
    }
    public int getGearsPlaced() {
        return Integer.parseInt(gearsPlaced.getValue().toString());
    }
    public String getDefense() {
        if (defense.getSelectionModel().getSelectedItem() != null)
            return defense.getSelectionModel().getSelectedItem().toString();
        else
            return "not defined";
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("team report");
        createForm(primaryStage);
        primaryStage.show();
    }
    private void createForm(Stage stage) {
        GridPane gr = new GridPane();
        gr.setAlignment(Pos.CENTER);
        gr.setHgap(10);
        gr.setVgap(10);
        gr.setPadding(new Insets(10, 10, 10, 10));

        Label teamLbl = new Label("TEAM #: ");
        gr.add(teamLbl, 0, 0);
        team = new TextField();
        gr.add(team, 1, 0);

        Label matchLbl = new Label("MATCH #: ");
        gr.add(matchLbl, 0, 1);
        matchNumber = new TextField();
        gr.add(matchNumber, 1, 1);

        Text auto = new Text("AUTO");
        gr.add(auto, 0, 2, 2, 1);

        crossBL = new CheckBox("Crossed Baseline");
        gr.add(crossBL, 0, 3);

        hopperActivated = new CheckBox("Activated Hopper");
        gr.add(hopperActivated, 1, 3);

        gearPlace = new ToggleGroup();
        gearSidePeg = new ToggleGroup();

        Text gears = new Text("Gear Placement");
        gr.add(gears, 0, 4);
        noGearAttempt = new RadioButton("No attempt at placing gear");
        gearAttempted = new RadioButton("Gear attempted but failed");
        gearSuccess = new RadioButton("Gear placed succesfully");
        noGearAttempt.setToggleGroup(gearPlace);
        gearAttempted.setToggleGroup(gearPlace);
        gearSuccess.setToggleGroup(gearPlace);
        gr.add(noGearAttempt, 0, 5);
        gr.add(gearAttempted, 0, 6);
        gr.add(gearSuccess, 0, 7);

        Text sideGear = new Text("Gear side peg");
        gr.add(sideGear, 1, 4);
        sidePegNoAttempt = new RadioButton("No attempt at placing gear on side peg");
        sidePegAttempted = new RadioButton("Gear on side peg attempted but failed");
        sidePegSuccess = new RadioButton("Gear on side peg placed successfully");
        sidePegNoAttempt.setToggleGroup(gearSidePeg);
        sidePegAttempted.setToggleGroup(gearSidePeg);
        sidePegSuccess.setToggleGroup(gearSidePeg);
        gr.add(sidePegNoAttempt, 1, 5);
        gr.add(sidePegAttempted, 1, 6);
        gr.add(sidePegSuccess, 1, 7);

        autoHighShots = new ComboBox<>(shootingChoices);
        Label ahsLabel = new Label("High shots: ");
        gr.add(ahsLabel, 0, 8);
        gr.add(autoHighShots, 1, 8);
        autoLowShots = new ComboBox<>(shootingChoices);
        Label alsLabel = new Label("Low shots");
        gr.add(alsLabel, 0, 9);
        gr.add(autoLowShots, 1, 9);

        Text teleop = new Text("TELEOP");
        gr.add(teleop, 0, 10, 2, 1);
        Separator sep = new Separator();
        gr.add(sep, 0, 11, 2, 1);

        teleopHighShots = new ComboBox<>(shootingChoices);
        Label thsLabel = new Label("High Shots: ");
        gr.add(thsLabel, 0, 12);
        gr.add(teleopHighShots, 1, 12);
        teleopLowShots = new ComboBox<>(shootingChoices);
        Label tlsLabel = new Label("Low Shots: ");
        gr.add(tlsLabel, 0, 13);
        gr.add(teleopLowShots, 1, 13);

        climb = new ToggleGroup();
        Text climbText = new Text("Climb:");
        gr.add(climbText, 0, 14, 2, 1);
        climbNoAttempt = new RadioButton("No attempt at climb");
        climbAttempted = new RadioButton("Climb attempted but failed");
        climbSuccess = new RadioButton("Climb successful");
        climbNoAttempt.setToggleGroup(climb);
        climbAttempted.setToggleGroup(climb);
        climbSuccess.setToggleGroup(climb);
        gr.add(climbNoAttempt, 0, 15, 2, 1);
        gr.add(climbAttempted, 0, 16, 2, 1);
        gr.add(climbSuccess, 0, 17, 2, 1);

        gearsAttempted = new Spinner<Object>(0, 13, 0, 1);
        gearsPlaced = new Spinner<Object>(0, 13, 0, 1);
        gearsAttempted.setEditable(true);
        gearsPlaced.setEditable(true);
        Label gaLbl = new Label("Gears Attempted: ");
        Label gpLbl = new Label("Gears placed: ");
        gr.add(gaLbl, 0, 18);
        gr.add(gearsAttempted, 1, 18);
        gr.add(gpLbl, 0, 19);
        gr.add(gearsPlaced, 1, 19);

        defense = new ComboBox<>(defenseChoices);
        Label defenseLbl = new Label("Rate defense: ");
        gr.add(defenseLbl, 0, 20);
        gr.add(defense, 1, 20);

        upload = new Button("upload to DB");
        save = new Button("save as txt");
        gr.add(upload, 0, 21);
        gr.add(save, 1, 21);

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File f = chooseFile(stage);
                if (f == null) {
                    return;
                } else {
                    TextOutput op = new TextOutput(f);
                    op.write(this);
                }
            }
        });

        upload.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File f = chooseFile(stage);
                if (f == null) {
                    return;
                } else {
                    CSVOutput op = new CSVOutput(f);
                    op.write(this);
                }


            }
        });

        System.out.println(getGearsAttempted());

        Scene sc = new Scene(gr, 500, 700);
        stage.setScene(sc);
    }
    public File chooseFile(Stage st) {
        FileChooser fc = new FileChooser();
        File f = null;
        f = fc.showOpenDialog(st);
        if (f == null) {
            Alert noFile = new Alert(Alert.AlertType.ERROR);
            noFile.setTitle("no file selected");
            noFile.setContentText("please select a file");
            noFile.showAndWait();
        }
        return f;
    }
}