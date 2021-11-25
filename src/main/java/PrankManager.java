import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;

public class PrankManager {
    private int sender;
    private MailService mailService;
    private final String targetFilePath;

    public PrankManager(String targetFilePath) {
        this.targetFilePath = targetFilePath;
    }

    public void getVictim() {
        BufferedReader fis = null;
        try {
            fis = new BufferedReader(new FileReader(targetFilePath), "UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }

        try{
            String line;
            while ((line = fis.readLine()) != null) {
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void selectSenderAmongVictim() {

    }


}
