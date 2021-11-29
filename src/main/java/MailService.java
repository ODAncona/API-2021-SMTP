import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailService {
    private final int PORT;
    private final String HOST;
    private static final Logger LOG = Logger.getLogger(MailService.class.getName());

    public MailService(String host, int port) {
        this.HOST = host;
        this.PORT = port;
    }

    public void sendMail(String rcpt_to, String mail_from, String content) {
    }

    public void sendMailToGroup(ArrayList<Victim> group, Victim sender, String content) {
        for (Victim v : group) {
            sendMail(v.getEmail(), sender.getEmail(), content);
        }
    }

    private void connect() {
        Socket clientSocket = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            clientSocket = new Socket(HOST, PORT);
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String fromServer;

            while ((fromServer = reader.readLine()) != null) {
                switch (fromServer){
                    case "EHLO":
                        break;
                    case "OK":
                        break;
                    default:
                        break;
                }
            }

        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.toString(), ex);
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }
            try {
                if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }
        }
    }
}
