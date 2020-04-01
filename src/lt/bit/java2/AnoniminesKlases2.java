package lt.bit.java2;

import java.util.ArrayList;
import java.util.List;

public class AnoniminesKlases2 {

    public static void main(String[] args) {

        List<Double> skaiciukai = new ArrayList<>();
        skaiciukai.add(1.23);
        skaiciukai.add(3.45);
        skaiciukai.add(6.78);
        System.out.println(skaiciukai);

        Funkcija f = new Funkcija() {
            public Double funkcija(Double x) {
                return x * 2;
            }
        };
        skaiciukai = modifikuojame(skaiciukai, f);
        System.out.println(skaiciukai);

        Funkcija f2 = new Funkcija() {
            public Double funkcija(Double x) {
                return x + 1;
            }
        };
        skaiciukai = modifikuojame(skaiciukai, f2);
        System.out.println(skaiciukai);


        Funkcija f3 = new Funkcija() {
            public Double funkcija(Double x) {
                return x / 2;
            }
        };
        skaiciukai = modifikuojame(skaiciukai, f3);
        System.out.println(skaiciukai);
    }

    static List<Double> modifikuojame(List<Double> skaiciukai, Funkcija veiksmas) {
        List<Double> res = new ArrayList<>();
        for (Double d : skaiciukai) {
            res.add(veiksmas.funkcija(d));
        }
        return res;
    }
}



