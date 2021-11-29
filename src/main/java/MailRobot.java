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
        String filePath = "/home/olivier/Documents/heig/s3/api/labo/API-2021-SMTP/src/main/resources/target.txt";
        // Ressources creation
        String host = "localhost";
        int port = 3101;
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
