import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class GroupManager {
    private int sender;
    private final String targetFilePath;

    public GroupManager(String targetFilePath) {
        this.targetFilePath = targetFilePath;
    }

    public void getVictim() {
        BufferedReader fis = null;
        try {
            fis = new BufferedReader(new FileReader(targetFilePath, StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            String line;
            while ((line = fis.readLine()) != null) {
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public ArrayList<Victim> getGroup() {
        return new ArrayList<Victim>();
    }

    public void selectSenderAmongVictim() {

    }

    public void constituteVictimGroup() {

    }


}
