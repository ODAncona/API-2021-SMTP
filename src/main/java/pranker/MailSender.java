package pranker;

public class MailSender {

    public static void main(String[] args) {

        //To put in parameter of main
        String victimsPath = "src/config/victims.txt";
        String configPath = "src/config/configuration.txt";
        String jokesPath = "src/config/jokes_custom.txt";

        // Get Configuration
        ConfigurationService confService = new ConfigurationService(configPath);
        String host = confService.getConf("smtp_server");
        int port = Integer.parseInt(confService.getConf("smtp_port"));
        int nbGroups = Integer.parseInt(confService.getConf("nb_groups"));

        // Prepare victims
        GroupManager groupManager = new GroupManager(victimsPath, nbGroups);

        // Prepare Jokes
        PrankFactory prankFactory = new PrankFactory(jokesPath, "Custom");

        // Mail Service
        MailService mailService = new MailService(host, port);

        // Act
        mailService.sendMail(groupManager, prankFactory);
    }

}
