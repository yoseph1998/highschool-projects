package org.rivierarobotics.scouting.form;

/**
 * Created by Yoseph Alabdulwahab on 4/22/2016.
 */
public class TeamEntry extends Entry {

    private String teamName;
    private int memberCount;
    private String pitOrganization;
    private String toolsComment;
    private DefenseAbility lowbar;
    private DefenseAbility rockWall;
    private DefenseAbility roughTerrian;
    private DefenseAbility sallyPort;
    private DefenseAbility drawbridge;
    private DefenseAbility ramparts;
    private DefenseAbility moat;
    private DefenseAbility portcullis;
    private DefenseAbility cheval;
    private DefenseAbility spybox;
    private Goal autoGoal;
    private int autoNumGoals;
    private String autoComment;
    private String autoAim;
    private int driveTrainSimCount;
    private String collectorInfo;
    private String shooterDesign;
    private String comment;

    private TeamEntry(Builder b) {
        super(b.id);
        teamName = b.teamName;
        memberCount = b.memberCount;
        pitOrganization = b.pitOrganization;
        toolsComment = b.toolsComment;
        lowbar = b.lowbar;
        rockWall = b.rockWall;
        roughTerrian = b.roughTerrian;
        sallyPort = b.sallyPort;
        drawbridge = b.drawbridge;
        ramparts = b.ramparts;
        moat = b.moat;
        portcullis = b.portcullis;
        cheval = b.cheval;
        spybox = b.spybox;
        autoGoal = b.autoGoal;
        autoNumGoals = b.autoNumGoals;
        autoComment = b.autoComment;
        autoAim = b.autoAim;
        driveTrainSimCount = b.driveTrainSimCount;
        collectorInfo = b.collectorInfo;
        shooterDesign = b.shooterDesign;
        comment = b.comment;
    }

    public int getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public String getPitOrganization() {
        return pitOrganization;
    }

    public void setPitOrganization(String pitOrganization) {
        this.pitOrganization = pitOrganization;
    }

    public String getToolsComment() {
        return toolsComment;
    }

    public void setToolsComment(String toolsComment) {
        this.toolsComment = toolsComment;
    }

    public DefenseAbility getLowbar() {
        return lowbar;
    }

    public void setLowbar(DefenseAbility lowbar) {
        this.lowbar = lowbar;
    }

    public DefenseAbility getRockWall() {
        return rockWall;
    }

    public void setRockWall(DefenseAbility rockWall) {
        this.rockWall = rockWall;
    }

    public DefenseAbility getRoughTerrian() {
        return roughTerrian;
    }

    public void setRoughTerrian(DefenseAbility roughTerrian) {
        this.roughTerrian = roughTerrian;
    }

    public DefenseAbility getSallyPort() {
        return sallyPort;
    }

    public void setSallyPort(DefenseAbility sallyPort) {
        this.sallyPort = sallyPort;
    }

    public DefenseAbility getDrawbridge() {
        return drawbridge;
    }

    public void setDrawbridge(DefenseAbility drawbridge) {
        this.drawbridge = drawbridge;
    }

    public DefenseAbility getRamparts() {
        return ramparts;
    }

    public void setRamparts(DefenseAbility ramparts) {
        this.ramparts = ramparts;
    }

    public DefenseAbility getMoat() {
        return moat;
    }

    public void setMoat(DefenseAbility moat) {
        this.moat = moat;
    }

    public DefenseAbility getPortcullis() {
        return portcullis;
    }

    public void setPortcullis(DefenseAbility portcullis) {
        this.portcullis = portcullis;
    }

    public DefenseAbility getCheval() {
        return cheval;
    }

    public void setCheval(DefenseAbility cheval) {
        this.cheval = cheval;
    }

    public DefenseAbility getSpybox() {
        return spybox;
    }

    public void setSpybox(DefenseAbility spybox) {
        this.spybox = spybox;
    }

    public Goal getAutoGoal() {
        return autoGoal;
    }

    public void setAutoGoal(Goal autoGoal) {
        this.autoGoal = autoGoal;
    }

    public int getAutoNumGoals() {
        return autoNumGoals;
    }

    public void setAutoNumGoals(int autoNumGoals) {
        this.autoNumGoals = autoNumGoals;
    }

    public String getAutoComment() {
        return autoComment;
    }

    public void setAutoComment(String autoComment) {
        this.autoComment = autoComment;
    }

    public String getAutoAim() {
        return autoAim;
    }

    public void setAutoAim(String autoAim) {
        this.autoAim = autoAim;
    }

    public int getDriveTrainSimCount() {
        return driveTrainSimCount;
    }

    public void setDriveTrainSimCount(int driveTrainSimCount) {
        this.driveTrainSimCount = driveTrainSimCount;
    }

    public String getCollectorInfo() {
        return collectorInfo;
    }

    public void setCollectorInfo(String collectorInfo) {
        this.collectorInfo = collectorInfo;
    }

    public String getShooterDesign() {
        return shooterDesign;
    }

    public void setShooterDesign(String shooterDesign) {
        this.shooterDesign = shooterDesign;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public enum Goal {
        NONE, LOW, HIGH, BOTH;
    }

    public static class Builder {
        private final int id;
        private String teamName;
        private int memberCount;
        private String pitOrganization;
        private String toolsComment;
        private DefenseAbility lowbar;
        private DefenseAbility rockWall;
        private DefenseAbility roughTerrian;
        private DefenseAbility sallyPort;
        private DefenseAbility drawbridge;
        private DefenseAbility ramparts;
        private DefenseAbility moat;
        private DefenseAbility portcullis;
        private DefenseAbility cheval;
        private DefenseAbility spybox;
        private Goal autoGoal;
        private int autoNumGoals;
        private String autoComment;
        private String autoAim;
        private int driveTrainSimCount;
        private String collectorInfo;
        private String shooterDesign;
        private String comment;

        public Builder(int a) {
            id = a;
        }

        public Builder teamName(String a) {
            teamName = a;
            return this;
        }

        public Builder memberCount(int a) {
            memberCount = a;
            return this;
        }

        public Builder pitOrganization(String a) {
            pitOrganization = a;
            return this;
        }

        public Builder toolsComment(String a) {
            toolsComment = a;
            return this;
        }

        public Builder lowbar(DefenseAbility a) {
            lowbar = a;
            return this;
        }

        public Builder rockWall(DefenseAbility a) {
            rockWall = a;
            return this;
        }

        public Builder roughTerrain(DefenseAbility a) {
            roughTerrian = a;
            return this;
        }

        public Builder sallyPort(DefenseAbility a) {
            sallyPort = a;
            return this;
        }

        public Builder drawbridge(DefenseAbility a) {
            drawbridge = a;
            return this;
        }

        public Builder ramparts(DefenseAbility a) {
            ramparts = a;
            return this;
        }

        public Builder moat(DefenseAbility a) {
            moat = a;
            return this;
        }

        public Builder portcullis(DefenseAbility a) {
            portcullis = a;
            return this;
        }

        public Builder cheval(DefenseAbility a) {
            cheval = a;
            return this;
        }

        public Builder spybox(DefenseAbility a) {
            spybox = a;
            return this;
        }

        public Builder autoGoal(Goal a) {
            autoGoal = a;
            return this;
        }

        public Builder autoNumGoals(int a) {
            autoNumGoals = a;
            return this;
        }

        public Builder autoComment(String a) {
            autoComment = a;
            return this;
        }

        public Builder autoAim(String a) {
            autoAim = a;
            return this;
        }

        public Builder driveTrainSimCount(int a) {
            driveTrainSimCount = a;
            return this;
        }

        public Builder collectorInfo(String a) {
            collectorInfo = a;
            return this;
        }

        public Builder shooterDesign(String a) {
            shooterDesign = a;
            return this;
        }

        public Builder comment(String a) {
            comment = a;
            return this;
        }

        public TeamEntry build() {
            return new TeamEntry(this);
        }
    }

    public static class DefenseAbility {

        private final Defense defense;
        private final boolean auto;
        private final boolean teleop;
        private final String comment;

        public DefenseAbility(Defense defense, boolean auto, boolean teleop, String comment) {
            this.defense = defense;
            this.auto = auto;
            this.teleop = teleop;
            this.comment = comment;
        }

        public Defense getDefense() {
            return defense;
        }

        public boolean auto() {
            return auto;
        }

        public boolean teleop() {
            return teleop;
        }

        public String comment() {
            return comment;
        }
    }
}
