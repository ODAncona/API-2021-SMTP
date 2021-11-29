import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class GroupManager {
    private Victim sender;
    private final String targetFilePath;
    private final ArrayList<Victim> group = new ArrayList<>();
    private final ArrayList<Victim> targetGroup = new ArrayList<>();

    public GroupManager(String targetFilePath) {
        this.targetFilePath = targetFilePath;
    }

    private void getVictim() {
        BufferedReader fis = null;
        try {
            fis = new BufferedReader(new FileReader(targetFilePath, StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            String line;
            while ((line = fis.readLine()) != null) {
                String[] victim = line.split(" ");
                group.add(new Victim(victim[0], victim[1]));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private Victim getAVictimAmongGroup() {
        return group.get((int) (Math.random() * group.size()));
    }

    public void constituteVictimGroup(int size) {
        getVictim();
        Collections.shuffle(group);
        for (int i = 0; i < size; ++i) {
            targetGroup.add(group.get(i));
        }
        sender = getAVictimAmongGroup();
    }

    public ArrayList<Victim> getTargetGroup() {
        return targetGroup;
    }


}
