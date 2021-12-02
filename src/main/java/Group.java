import java.util.ArrayList;

public class Group {

    private Victim sender;
    private ArrayList<Victim> recipients = new ArrayList<>();

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