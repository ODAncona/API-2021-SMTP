package pranker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class GroupManager {

    private final ArrayList<Group> groups = new ArrayList<>();

    /**
     * Constructor
     *
     * @param path     : list of victims file path
     * @param nbGroups : number of groups
     */
    public GroupManager(String path, int nbGroups) {
        ArrayList<Victim> victims = new ArrayList<>();
        BufferedReader fis = null;

        // Parse file
        try {
            fis = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8));
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

        // Handle exception
        if (3 * nbGroups > victims.size())
            throw new RuntimeException("Pas assez de victimes pour " + nbGroups);

        // Constitute groups
        Collections.shuffle(victims);

        // Interlacement
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

    public ArrayList<Group> getGroups() {
        return groups;
    }
}