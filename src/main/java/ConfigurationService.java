import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class ConfigurationService {

    private final String path;
    private final HashMap<String, String> configuration = new HashMap<>();

    /**
     * Constructor
     *
     * @param path : path to config file
     */
    public ConfigurationService(String path) {
        this.path = path;
        getConfiguration();
    }

    /**
     * Parse config file and put each config entry in a HashMap
     */
    private void getConfiguration() {
        BufferedReader fis = null;
        try {
            fis = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8));
            String line;
            while ((line = fis.readLine()) != null) {
                String[] lineConfig = line.split(" ");
                configuration.put(lineConfig[0], lineConfig[1]);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @param key : configuration parameter
     * @return a string value of configuration
     */
    public String getConf(String key) {
        return configuration.get(key);
    }

}
