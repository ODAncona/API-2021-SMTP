public class Main {
    /**
     *
     * @param args
     *
     * Usage:
     * pranckManager <victimConfig>
     */
    public static void main(String[] args){
        if(args.length < 2){
            System.out.println("Usage: pranckManager <victimConfig>");
            return;
        }
        PrankManager evil = new PrankManager();
    }
}
