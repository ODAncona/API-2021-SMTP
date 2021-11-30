import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class ConfigurationService {
    private final String path;
    private final HashMap<String, String> configuration = new HashMap<>();

    public ConfigurationService(String path) {
        this.path = path;
        getConfiguration();
    }

    private void getConfiguration() {
        BufferedReader fis = null;
        try {
            fis = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            String line;
            while ((line = fis.readLine()) != null) {
                String[] lineConfig = line.split(" ");
                configuration.put(lineConfig[0], lineConfig[1]);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getConf(String key) {
        return configuration.get(key);
    }

}
