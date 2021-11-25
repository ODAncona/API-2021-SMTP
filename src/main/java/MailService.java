public class MailService {
    private String PORT;

    public MailService(String PORT) {
        this.PORT = PORT;
    }

    public MailService() {

    }

    public void sendMail(Group rcpt_to, String mail_from, String content) {
    }
}
