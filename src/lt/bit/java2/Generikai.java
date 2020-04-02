package lt.bit.java2;

import java.util.ArrayList;
import java.util.List;

public class Generikai {

    public static void main(String[] args) {
        List<Double> skaiciukai = new ArrayList<>();
        skaiciukai.add(6.78);
        skaiciukai.add(1.23);
        skaiciukai.add(4.23);
        skaiciukai.add(3.45);
        System.out.println(skaiciukai);

        sulygiuoti(skaiciukai); // T = Double
        System.out.println(skaiciukai);

        List<String> vardai = new ArrayList<>();
        vardai.add("Z");
        vardai.add("A");
        vardai.add("C");
        vardai.add("B");
        System.out.println(vardai);

        sulygiuoti(vardai); // T = String
        System.out.println(vardai);
    }


    // noriu sulygiuoti didejimo tvarka
    static void sulygiuotiDouble(List<Double> listas) {
        for (int i = 0; i < listas.size() - 1; i++) {
            Double min = listas.get(i);
            for (int j = i + 1; j < listas.size(); j++) {
                if (min.compareTo(listas.get(j)) > 0) {
                    min = listas.get(j);
                    listas.set(j, listas.get(i));
                    listas.set(i, min);
                }
            }
        }
    }

    static void sulygiuotiString(List<String> listas) {
        for (int i = 0; i < listas.size() - 1; i++) {
            String min = listas.get(i);
            for (int j = i + 1; j < listas.size(); j++) {
                if (min.compareTo(listas.get(j)) > 0) {
                    min = listas.get(j);
                    listas.set(j, listas.get(i));
                    listas.set(i, min);
                }
            }
        }
    }


    // ? extends Number - Integer, Double, BigDecimal
    // List<? extends Number> listas
    // listas.add(new Integer(12))
    static <T extends Comparable> void sulygiuoti(List<T> listas) {
        for (int i = 0; i < listas.size() - 1; i++) {
            T min = listas.get(i);
            for (int j = i + 1; j < listas.size(); j++) {
                if (min.compareTo(listas.get(j)) > 0) {
                    min = listas.get(j);
                    listas.set(j, listas.get(i));
                    listas.set(i, min);
                }
            }
        }
    }
}


interface KazkaPadaryti {
    Double kazkaDarom(Double x);
}
