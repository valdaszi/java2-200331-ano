package lt.bit.java2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AnoniminesKlases3 {
    public static void main(String[] args) {

        List<Double> skaiciukai = new ArrayList<>();
        skaiciukai.add(1.23);
        skaiciukai.add(3.45);
        skaiciukai.add(6.78);
        System.out.println(skaiciukai);

        Funkcija f = (Double x) -> {
            return x * 2;
        };
        skaiciukai = modifikuojame(skaiciukai, f);
        System.out.println(skaiciukai);

        Funkcija f2 = (x) -> {
            return x + 1;
        };
        skaiciukai = modifikuojame(skaiciukai, f2);
        System.out.println(skaiciukai);


        Funkcija f3 = x -> x / 2;
        skaiciukai = modifikuojame(skaiciukai, f3);
        System.out.println(skaiciukai);

        skaiciukai = modifikuojame(skaiciukai, x -> x + 2);
        System.out.println(skaiciukai);

        final List<Double> listas = new ArrayList<>();
        Consumer<Double> consumer = d -> {};


        // listas = new ArrayList<>();

    }

    static List<Double> modifikuojame(List<Double> skaiciukai, Funkcija veiksmas) {
        List<Double> res = new ArrayList<>();
        for (Double d : skaiciukai) {
            res.add(veiksmas.funkcija(d));
        }
        return res;
    }
}
