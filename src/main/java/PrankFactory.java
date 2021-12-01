import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PrankFactory {
    private final ArrayList<String> person = new ArrayList<>();
    private final ArrayList<String> verb = new ArrayList<>();
    private final ArrayList<String> number = new ArrayList<>();
    private final ArrayList<String> object = new ArrayList<>();

    public String getAJoke() {
        final String s = person.get((int) (Math.random() * person.size())) +
                verb.get((int) (Math.random() * verb.size())) +
                number.get((int) (Math.random() * number.size())) +
                object.get((int) (Math.random() * object.size()));
        return s;
    }

    public PrankFactory(String jokesPath) {
        BufferedReader fis = null;
        try {
            fis = new BufferedReader(new FileReader(jokesPath, StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            String line;
            while ((line = fis.readLine()) != null) {
                String[] joke = line.split(" ");
                person.add(joke[0]);
                verb.add(joke[1]);
                number.add(joke[2]);
                object.add(joke[3]);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
