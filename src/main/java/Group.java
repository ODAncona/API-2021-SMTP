import java.util.ArrayList;

public class Group {

    private final Victim sender;
    private final ArrayList<Victim> recipients;

    /**
     * Constructor
     *
     * @param sender : the fake sender
     * @param recipients : list of victims
     */
    public Group(Victim sender, ArrayList<Victim> recipients) {
        this.sender = sender;
        this.recipients = recipients;
    }

    /**
     * @return the sender of the group among victims
     */
    public Victim getSender() {
        return sender;
    }

    /**
     * @return the list of victim
     */
    public ArrayList<Victim> getRecipients() {
        return recipients;
    }

}