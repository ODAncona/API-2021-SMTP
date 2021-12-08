package pranker;

public class MailSender {

    private static void showUsage() {
        System.out.println("Usage: java -jar app <victims> <configuration> <jokes> <mode>");
        System.out.println("Modes: \"Custom\" -> send some custom jokes");
        System.out.println("Modes: \"Factory\" -> forge some customized jokes");
    }

    public static void main(String[] args) {
        // Check Arguments
        if (args.length != 4) {
            showUsage();
            return;
        } else if (!args[3].equals("Custom") && !args[3].equals("Factory")) {
            System.out.println("Unknow mode !");
            showUsage();
            return;
        }

        String victimsPath = args[0];
        String configPath = args[1];
        String jokesPath = args[2];
        String mode = args[3];

        // Get Configuration
        ConfigurationService confService = new ConfigurationService(configPath);
        String host = confService.getConf("smtp_server");
        int port = Integer.parseInt(confService.getConf("smtp_port"));
        int nbGroups = Integer.parseInt(confService.getConf("nb_groups"));

        // Prepare victims
        GroupManager groupManager = new GroupManager(victimsPath, nbGroups);

        // Prepare Jokes
        PrankFactory prankFactory = new PrankFactory(jokesPath, mode);

        // Mail Service
        MailService mailService = new MailService(host, port);

        // Act
        mailService.sendMail(groupManager, prankFactory);
    }
}
