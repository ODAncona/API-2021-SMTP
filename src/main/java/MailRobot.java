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
        String filePath = "src/main/resources/target.txt";
        String configPath = "src/main/resources/configuration.txt";
        // Ressources creation
        ConfigurationService confService = new ConfigurationService(configPath);
        String host = confService.getConf("smtp_server");
        int port = Integer.parseInt(confService.getConf("smtp_port"));
        int groupSize = Integer.parseInt(confService.getConf("group_size"));
        System.out.println(host);
        System.out.println(port);
        System.out.println(groupSize);
        MailService mailService = new MailService(host, port);
        GroupManager groupManager = new GroupManager(filePath);
        groupManager.constituteVictimGroup(3);
        //PrankFactory prankFactory = new PrankFactory();
        /*
        groupManager.getVictim();
        groupManager.constituteVictimGroup(5);
        mailService.sendMailToGroup(groupManager.getGroup(), groupManager.getAVictimAmongGroup(), prankFactory.getAJoke());
         */
    }
}
