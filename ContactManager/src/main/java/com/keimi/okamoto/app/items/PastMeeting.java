/**
 * A meeting that was held in the past.
 * It includes your notes about what happened and what was agreed.
 */
package com.keimi.okamoto.app.items;

public interface PastMeeting extends Meeting {

    /**
     * Returns the notes from the meeting. *
     * If there are no notes, the empty string is returned. *
     *
     * @return the notes from the meeting.
     */
    String getNotes();
}
