package pranker;

public class Victim {

    final private String email;
    final private String name;

    /**
     * @return email of victim
     */
    public String getEmail() {
        return email;
    }

    /**
     * Constructor
     *
     * @param email : email of victim
     * @param name  : name of victim
     */
    public Victim(String email, String name) {
        this.email = email;
        this.name = name;
    }

}
