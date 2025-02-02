package pranker;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailService {

    private final int port;
    private final String host;

    private static final Logger LOG = Logger.getLogger(MailService.class.getName());

    /**
     * Constructor
     *
     * @param host : smtp server's ip
     * @param port : port
     */
    public MailService(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Read until the server answer "250 OK"
     *
     * @param reader : socket buffered reader
     */
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

    /**
     * Connect to smtp server, and send email to the group's victim.
     *
     * @param groupManager : list of all groups
     * @param prankFactory : joke builder
     */
    public void sendMail(GroupManager groupManager, PrankFactory prankFactory) {
        Socket clientSocket = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            clientSocket = new Socket(host, port);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8));

            // Connexion
            reader.readLine();

            writer.write("EHLO localhost\r\n");
            writer.flush();

            readFromServer(reader);

            for (Group group : groupManager.getGroups()) {
                String CRLF = "\r\n";
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
                String subject = "Vous êtes concerné!";
                writer.write(CRLF + "Subject: =?utf-8?B?" + Base64.getEncoder().encodeToString(subject.getBytes(StandardCharsets.UTF_8)) + "?=" + CRLF + CRLF);
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