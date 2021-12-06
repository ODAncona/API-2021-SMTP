import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailService {

    private final int PORT;
    private final String HOST;

    public static final String CRLF = "\r\n";

    private static final Logger LOG = Logger.getLogger(MailService.class.getName());

    public MailService(String host, int port) {
        this.HOST = host;
        this.PORT = port;
    }

    public void readFromServer(BufferedReader reader) {
        String fromServer;
        try {
            do {
                fromServer = reader.readLine();
            } while (!fromServer.equals("250 Ok"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendMail(GroupManager groupManager, PrankFactory prankFactory) {
        Socket clientSocket = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            clientSocket = new Socket(HOST, PORT);
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Connexion
            reader.readLine();

            writer.write("EHLO localhost\r\n");
            writer.flush();

            readFromServer(reader);

            for (Group group : groupManager.getGroups()) {
                writer.write("MAIL FROM: " + group.getSender().getEmail() + CRLF);
                writer.flush();
                readFromServer(reader);
                for (int i = 0; i < group.getRecipients().size(); ++i) {
                    writer.write("RCPT TO: " + group.getRecipients().get(i).getEmail() + CRLF);
                    writer.flush();
                    readFromServer(reader);
                }
                writer.write("DATA" + CRLF);
                writer.write("Content-Type: text/plain: charset=\"utf-8\"" + CRLF);
                writer.flush();
                reader.readLine();
                writer.write("From: " + group.getSender().getEmail() + CRLF);
                writer.write("To: ");
                for (int i = 0; i < group.getRecipients().size(); ++i) {
                    writer.write(group.getRecipients().get(i).getEmail() + ",");
                }
                writer.write(CRLF + "Subject: Blague" + CRLF + CRLF);
                writer.write(prankFactory.getAJoke() + CRLF + "." + CRLF);
                writer.flush();
                readFromServer(reader);
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
            System.out.println("Méfait accompli! ;)");
        }
    }
}