package src.meetingItem;

public class ContactImpl implements Contact {
    private int id;

    public ContactImpl(int id) {
        this.id = id;
    }

    /**
    * Returns the ID of the contact.
    * @return the ID of the contact.
    */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the contact.
     * @return the name of the contact.
     */
    public String getName() {
        return null;
    }

    /**
     * Returns our notes about the contact, if any.
     * If we have not written anything about the contact, the empty
     * string is returned. *
     * @return a string with notes about the contact, maybe empty.
     */
    public String getNotes() {
        return null;
    }

    /**
     * Add notes about the contact.
     * @param note the notes to be added
     */
    public void addNotes(String note) {

    }
}
