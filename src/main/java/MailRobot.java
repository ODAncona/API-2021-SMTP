public class MailRobot {
    /**
     * @param args Usage:
     *             MailRobot <victimConfig>
     */
    public static void main(String[] args) {
        /*
        if (args.length < 2) {
            System.out.println("Usage: MailRobot <victimConfig>");
            //return;
        }
        String filePath = args[1];
        */

        //To put in parameter of main
        String filePath = "src/main/resources/target.txt";
        String configPath = "src/main/resources/configuration.txt";

        // Get Configuration
        ConfigurationService confService = new ConfigurationService(configPath);
        String host = confService.getConf("smtp_server");
        int port = Integer.parseInt(confService.getConf("smtp_port"));
        int groupSize = Integer.parseInt(confService.getConf("group_size"));

        // Prepare Victim
        GroupManager groupManager = new GroupManager(filePath);
        groupManager.constituteVictimGroup(groupSize);

        // Prepare Joke
        PrankFactory prankFactory = new PrankFactory();
        String joke = prankFactory.getAJoke();

        // Mail Service
        MailService mailService = new MailService(host, port);
        mailService.setContent(joke);

        // Act
        mailService.sendMailToGroup(groupManager.getTargetGroup(), groupManager.getSender().getEmail());
    }
}
