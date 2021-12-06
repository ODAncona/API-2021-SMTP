import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class PrankFactory {
    private final String path;
    private final String mode;
    private final ArrayList<String> person = new ArrayList<>();
    private final ArrayList<String> verb = new ArrayList<>();
    private final ArrayList<String> number = new ArrayList<>();
    private final ArrayList<String> object = new ArrayList<>();
    private final ArrayList<String> customJokes = new ArrayList<>();

    public String getAJoke() {
        // Factory Mode
        if (mode.equals("Factory")) {
            return person.get((int) (Math.random() * person.size())) + " " +
                    verb.get((int) (Math.random() * verb.size())) + " " +
                    number.get((int) (Math.random() * number.size())) + " " +
                    object.get((int) (Math.random() * object.size()));
        }

        // Custom Mode
        if (mode.equals("Custom")) {
            return customJokes.get((int) (Math.random() * customJokes.size()));
        }

        return "THIS AINT FUNNY";
    }

    public PrankFactory(String path, String mode) {
        this.path = path;
        this.mode = mode;
        parseJokeFile();
    }

    private void parseJokeFile() {
        BufferedReader fis = null;
        try {
            fis = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8));

            // Factory Mode
            if (mode.equals("Factory")) {
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

            // Custom Joke Mode
            else if (mode.equals("Custom")) {
                try {
                    String joke;
                    while ((joke = fis.readLine()) != null) {
                        customJokes.add(joke);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
