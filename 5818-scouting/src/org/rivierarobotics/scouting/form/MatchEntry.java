package org.rivierarobotics.scouting.form;

public class MatchEntry extends Entry {

    public static final int BLUE = 0;
    public static final int RED = 1;
    private int teams[][];
    private Defense[][] defenses;
    private int score;
    private String comment;

    public MatchEntry(int id, int[][] teams, Defense[][] defenses, String comment) {
        super(id);
        this.teams = teams;
        this.defenses = defenses;
        this.comment = comment;
    }

    public MatchEntry(int id, int[][] teams, Defense[][] defenses) {
        this(id, teams, defenses, "");
    }

    public MatchEntry(int id) {
        this(id, new int[2][3], new Defense[2][5]);
    }

    public boolean containsTeam(int id) {
        for (int y = 0; y < teams.length; y++)
            for (int x = 0; x < teams[y].length; x++)
                if (teams[y][x] == id)
                    return true;
        return false;
    }

    public int getId() {
        return id;
    }

    public int[][] getTeams() {
        return teams;
    }

    public void setTeams(int[][] teams) {
        this.teams = teams;
    }

    public Defense[][] getDefenses() {
        return defenses;
    }

    public void setDefenses(Defense[][] defenses) {
        this.defenses = defenses;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public enum Defense {
        LOWBAR, ROCK_WALL, ROUGH_TERRAIN, SALLY_PORT, DRAWBRIDGE, RAMPARTS, MOAT, PORTCULLIS, CHEVAL;
    }
}
