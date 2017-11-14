package fr.supinternet.maledictiondeguinoclelu;

/**
 * Created by clementineferreol on 13/11/2017.
 */
public class User {

    protected String name;
    protected String email;

    public User() {
    }

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
