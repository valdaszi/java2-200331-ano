package lt.bit.java2;

import java.time.LocalDate;

public class Pazymys {
    private LocalDate data;
    private int pazymys;

    public Pazymys(LocalDate data, int pazymys) {
        this.data = data;
        this.pazymys = pazymys;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getPazymys() {
        return pazymys;
    }

    public void setPazymys(int pazymys) {
        this.pazymys = pazymys;
    }
}
