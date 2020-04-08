package lt.bit.java2;

import java.util.List;

public class Studentas {
    private String name;
    private String email;
    private String miestas;
    private List<Pazymys> pazymiai;

    public Studentas(String name, String email, String miestas, List<Pazymys> pazymiai) {
        this.name = name;
        this.email = email;
        this.miestas = miestas;
        this.pazymiai = pazymiai;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMiestas() {
        return miestas;
    }

    public void setMiestas(String miestas) {
        this.miestas = miestas;
    }

    public List<Pazymys> getPazymiai() {
        return pazymiai;
    }

    public void setPazymiai(List<Pazymys> pazymiai) {
        this.pazymiai = pazymiai;
    }
}
