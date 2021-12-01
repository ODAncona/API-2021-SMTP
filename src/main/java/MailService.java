import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailService {
    private final int PORT;
    private final String HOST;
    private static final Logger LOG = Logger.getLogger(MailService.class.getName());
    String content;

    public MailService(String host, int port) {
        this.HOST = host;
        this.PORT = port;
    }

    public void sendMailToGroup(ArrayList<Victim> group, String rcpt_to) {
        Socket clientSocket = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            clientSocket = new Socket(HOST, PORT);
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String fromServer;

            // Connexion
            System.out.println(reader.readLine());
            writer.write("EHLO localhost");
            writer.flush();

            for(Victim victim: group) {
                // Mail From
                while ((fromServer = reader.readLine()) != null) {
                    if (fromServer.contains("250") || fromServer.contains("OK")) {
                        writer.write("MAIL FROM: " + "coco@coco.ch");
                        writer.flush();
                        break;
                    }
                    System.out.println(fromServer);
                }

                // RCPT TO
                while ((fromServer = reader.readLine()) != null) {
                    if (fromServer.contains("250") || fromServer.contains("OK")) {
                        writer.write("RCPT TO: " + rcpt_to);
                        writer.flush();
                        break;
                    }
                    System.out.println(fromServer);
                }

                // Content
                while ((fromServer = reader.readLine()) != null) {
                    if (fromServer.contains("250") || fromServer.contains("OK")) {
                        writer.write("DATA: " + content);
                        writer.flush();
                        break;
                    }
                    System.out.println(fromServer);
                }

                // Send
                while ((fromServer = reader.readLine()) != null) {
                    if (fromServer.contains("354") || fromServer.contains("<CR><LF>.<CR><LF>")) {
                        writer.write("\n.\n");
                        writer.flush();
                        break;
                    }
                    System.out.println(fromServer);
                }
                System.out.println("HAHAHA");
                break;
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

    public void setContent(String content) {
        this.content = content;
    }
}
