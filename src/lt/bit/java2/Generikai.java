package lt.bit.java2;

import javax.script.Compilable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class Generikai {

    static final List<String> listas;

    static {
        listas = new ArrayList<>();
        listas.add("A");
        listas.add("B");
    }
    {
        listas.add("D");
    }

    public static void main(String[] args) {
        List<Double> skaiciukai = new ArrayList<>();
        skaiciukai.add(6.78);
        skaiciukai.add(1.23);
        skaiciukai.add(4.23);
        skaiciukai.add(3.45);
        // skaiciukai.add("aaa");
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

        List<A> listas = new ArrayList<>();
        listas.add(new A("Pirmas", 50));
        listas.add(new A("Antras", 2));
        listas.add(new A("Pirmas", 20));
        sulygiuoti(listas, (x, y) -> {
            int comp = x.getName().compareTo(y.getName());
            if (comp != 0) return comp;
            return x.getSize() - y.getSize();
        });

        List<A> listas2 = new ArrayList<>();
        listas2.add(new A("Pirmas", 50));
        listas2.add(new A("Antras", 2));
        listas2.add(new A("Pirmas", 20));
        Collections.sort(listas2, Comparator.comparing(A::getName)
                    .thenComparing(A::getSize));
        System.out.println(listas2);

        //
        Function<Integer, String> intToString = Object::toString;
        Function<String, String> quote = s -> "'" + s + "'";
        Function<Integer, String> quoteIntToString = quote.compose(intToString);
        System.out.println(quoteIntToString.apply(123));

        BiFunction<Integer, Integer, String> suma = (k1, k2) -> k1 + "+" + k2;
        System.out.println(suma.apply(123, 456));

        Supplier<String> supplier = () -> quote.apply(String.valueOf(Math.random()));
        System.out.println(supplier.get());

        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("Labas rytas");

        UnaryOperator<Integer> neg = t -> t == null ? null : -t;
        System.out.println(neg.apply(-123));

        BinaryOperator<Integer> prod = (a, b) -> a * b;
        System.out.println(prod.apply(-2, 123));

        //
        Predicate<String> predicate = s -> s.length() > 10;
        System.out.println(predicate.test("abc"));
        System.out.println(predicate.test("12345678901"));

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
            // Object min = listas.get(i);
            for (int j = i + 1; j < listas.size(); j++) {
                if (min.compareTo(listas.get(j)) > 0) {
                    min = listas.get(j);
                    listas.set(j, listas.get(i));
                    listas.set(i, min);
                }
            }
        }
    }

    static <T> void sulygiuoti(List<T> listas, Comparator<T> comparator) {
        for (int i = 0; i < listas.size() - 1; i++) {
            T min = listas.get(i);
            // Object min = listas.get(i);
            for (int j = i + 1; j < listas.size(); j++) {
                if (comparator.compare(min, listas.get(j)) > 0) {
                    min = listas.get(j);
                    listas.set(j, listas.get(i));
                    listas.set(i, min);
                }
            }
        }
    }
}


