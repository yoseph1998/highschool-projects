package org.rivierarobotics.scouting.form;

import java.util.LinkedList;

/**
 * Created by Yoseph Alabdulwahab on 4/23/2016.
 */
public class Entries {

    private LinkedList<Entry> teamEntries;
    private LinkedList<Entry> matchEntries;

    public Entries() {
        teamEntries = new LinkedList<Entry>();
        matchEntries = new LinkedList<Entry>();
    }

    public void addTeamEntry(TeamEntry entry) {
        int loc = findClosest(teamEntries, entry.getId());
        teamEntries.add(loc, entry);
    }

    public void addMatchEntry(MatchEntry entry) {
        int loc = findClosest(matchEntries, entry.getId());
        teamEntries.add(loc, entry);
    }

    public TeamEntry getTeamEntry(int id) {
        int loc = find(teamEntries, id);
        if (loc == -1)
            return null;
        return (TeamEntry) teamEntries.get(loc);
    }

    public MatchEntry getMatchEntry(int id) {
        int loc = find(matchEntries, id);
        if (loc == -1)
            return null;
        return (MatchEntry) teamEntries.get(loc);
    }

    /**
     * @param entries the <LinkedList> of entries
     * @param key     the entry id.
     * @return The location of that entry if it exists, or -1 if not.
     */
    private int find(LinkedList<Entry> entries, int key) {
        int imin = 0, imax = entries.size() - 1;
        int imid = imin + (imax - imin) / 2;
        while (imin <= imax) {
            imid = imin + (imax - imin) / 2;
            if (entries.get(imid).getId() == key)
                return imid;
            else if (entries.get(imid).getId() < key)
                imin = imid + 1;
            else
                imax = imid - 1;
        }

        return -1;
    }

    /**
     * @param entries the <LinkedList> of entries
     * @param key     the entry id.
     * @return The location of that entry or the next biggest entry.
     */
    private int findClosest(LinkedList<Entry> entries, int key) {
        int imin = 0, imax = entries.size() - 1;
        int imid = imin + (imax - imin) / 2;
        while (imin <= imax) {
            imid = imin + (imax - imin) / 2;
            if (entries.get(imid).getId() == key)
                return imid;
            else if (entries.get(imid).getId() < key)
                imin = imid + 1;
            else
                imax = imid - 1;
        }
        if (imin > entries.size() - 1)
            imin = entries.size() - 1;
        else if (imin < 0)
            imin = 0;

        return imin;
    }
}
