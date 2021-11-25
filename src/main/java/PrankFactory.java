import java.util.ArrayList;

public class PrankFactory {
    ArrayList<String> verb;
    ArrayList<String> objet;
    ArrayList<String> raison;

    public String getAJoke(Victim v) {
        StringBuilder sb = new StringBuilder();
        sb.append(v.getName()).append(verb.get(2)).append(objet.get(4)).append(raison.get(3));
        return sb.toString();
    }

    public PrankFactory() {
        this.verb.add("mange");
        this.objet.add("ta mère");
        this.raison.add("parce qu'il est nain");
    }
}
