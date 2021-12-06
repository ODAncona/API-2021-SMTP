import java.util.ArrayList;

public class Group {

    private final Victim sender;
    private final ArrayList<Victim> recipients;

    public Victim getSender() {
        return sender;
    }

    public ArrayList<Victim> getRecipients() {
        return recipients;
    }

    public Group(Victim sender, ArrayList<Victim> recipients) {
        this.sender = sender;
        this.recipients = recipients;
    }

}