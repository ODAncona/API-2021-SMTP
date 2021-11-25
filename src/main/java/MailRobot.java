public class MailRobot {
    /**
     * @param args Usage:
     *             MailRobot <victimConfig>
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: MailRobot <victimConfig>");
            return;
        }
        // Ressources creation
        MailService mailService = new MailService();
        GroupManager groupManager = new GroupManager();
        PrankFactory prankFactory = new PrankFactory();

        groupManager.getVictim();
        groupManager.constituteVictimGroup(5);
        groupManager.selectSenderAmongVictim();
        mailService.sendMail(groupManager.getGroup(),joke);
    }
}
