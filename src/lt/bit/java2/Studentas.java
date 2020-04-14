package lt.bit.java2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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


    private static List<Studentas> studentai;
    static {
        studentai = new ArrayList<>();
        studentai.add(new Studentas("Ona", "ona@gmail.com", "Kaunas",
                Arrays.asList(
                        new Pazymys(LocalDate.of(2019, 12, 31), 9),
                        new Pazymys(LocalDate.of(2019, 12, 31), 8),
                        new Pazymys(LocalDate.of(2020, 1, 15), 9)
                )));
        studentai.add(new Studentas("Jonas", "jonas@gmail.com", "Babtai",
                Arrays.asList(
                        new Pazymys(LocalDate.of(2020, 1, 30), 5),
                        new Pazymys(LocalDate.of(2020, 1, 31), 10),
                        new Pazymys(LocalDate.of(2020, 2, 1), 8),
                        new Pazymys(LocalDate.of(2020, 2, 2), 7)
                )));
        studentai.add(new Studentas("Petras", "petras@takas.lt", "Kaunas",
                Arrays.asList(
                        new Pazymys(LocalDate.of(2020, 1, 30), 8),
                        new Pazymys(LocalDate.of(2020, 2, 1), 9),
                        new Pazymys(LocalDate.of(2020, 2, 2), 2)
                )));
    }

    public static List<Studentas> generuotiStudentus() {
        return studentai;
    }
}
