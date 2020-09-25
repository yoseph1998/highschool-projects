package org.rivierarobotics.scouting.form;

/**
 * Created by Yoseph Alabdulwahab on 4/23/2016.
 */
public abstract class Entry {

    protected final int id;

    public Entry(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
