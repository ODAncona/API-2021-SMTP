import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class GroupManager {

    private final ArrayList<Group> groups = new ArrayList<>();

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public GroupManager(String path, int nbGroups) {
        ArrayList<Victim> victims = new ArrayList<>();
        BufferedReader fis = null;

        try {
            fis = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8));
            try {
                String line;
                while ((line = fis.readLine()) != null) {
                    String[] victim = line.split(" ");
                    if (victim[0].matches("^(.+)@(.+)$"))
                        victims.add(new Victim(victim[0], victim[1]));
                    else throw new RuntimeException("Erreur email invalide: " + victim[0]);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        if (3 * nbGroups > victims.size())
            throw new RuntimeException("Pas assez de victimes pour " + nbGroups);

        Collections.shuffle(victims);

        for (int i = 0; i < nbGroups; ++i) {
            ArrayList<Victim> victimsInGroup = new ArrayList<>();

            int j = nbGroups + i;
            while (j < victims.size()) {
                victimsInGroup.add(victims.get(j));
                j += nbGroups;
            }

            groups.add(new Group(victims.get(i), victimsInGroup));
        }
    }
}