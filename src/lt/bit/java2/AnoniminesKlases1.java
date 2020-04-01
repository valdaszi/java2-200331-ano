package lt.bit.java2;

import java.util.ArrayList;
import java.util.List;

class AnoniminesKlases1 {

    public static void main(String[] args) {

        List<Double> skaiciukai = new ArrayList<>();
        skaiciukai.add(1.23);
        skaiciukai.add(3.45);
        skaiciukai.add(6.78);
        System.out.println(skaiciukai);

        class Veiksmas implements Funkcija {
            public Double funkcija(Double x) {
                return x * 2;
            }
        }
        Funkcija f = new Veiksmas();
        skaiciukai = modifikuojame(skaiciukai, f);
        System.out.println(skaiciukai);

        class Veiksmas2 implements Funkcija {
            public Double funkcija(Double x) {
                return x + 1;
            }
        }
        Funkcija f2 = new Veiksmas2();
        skaiciukai = modifikuojame(skaiciukai, f2);
        System.out.println(skaiciukai);


        class Veiksmas3 implements Funkcija {
            public Double funkcija(Double x) {
                return x / 2;
            }
        }
        Funkcija f3 = new Veiksmas3();
        skaiciukai = modifikuojame(skaiciukai, f3);
        System.out.println(skaiciukai);
    }

    // 1.
//    static List<Double> modifikuojame(List<Double> skaiciukai) {
//        List<Double> res = new ArrayList<>();
//        for (Double d : skaiciukai) {
//            res.add(d * 2);
//        }
//        return res;
//    }


    // 2.
//    static List<Double> modifikuojame(List<Double> skaiciukai, Veiksmas veiksmas) {
//        List<Double> res = new ArrayList<>();
//        for (Double d : skaiciukai) {
//            res.add(veiksmas.funkcija(d));
//        }
//        return res;
//    }
//
//    static List<Double> modifikuojame(List<Double> skaiciukai, Veiksmas3 veiksmas) {
//        List<Double> res = new ArrayList<>();
//        for (Double d : skaiciukai) {
//            res.add(veiksmas.funkcija(d));
//        }
//        return res;
//    }

    // 3.
    static List<Double> modifikuojame(List<Double> skaiciukai, Funkcija veiksmas) {
        List<Double> res = new ArrayList<>();
        for (Double d : skaiciukai) {
            res.add(veiksmas.funkcija(d));
        }
        return res;
    }
}

// FunctionalInterface - tai reiskia kad interfeisas tuti tik viena (!!!) abstraktu metoda
@FunctionalInterface
interface Funkcija {
    Double funkcija(Double x);

    // default int a() { return 10; }  // gali buti default metodai

    // void m(); // - negali buti keli abstraktus metodai
}





