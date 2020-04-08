package lt.bit.java2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class KolekcijuSrautaiIntro {

    public static void main(String[] args) {
        List<Mokinys> mokiniai = new ArrayList<>();
        mokiniai.add(new Mokinys("Jonas", 10));
        mokiniai.add(new Mokinys("Ona", 9));
        mokiniai.add(new Mokinys("Petras", 12));
        mokiniai.add(new Mokinys("Maryte", 6));

        double avg = calc(mokiniai);
        System.out.println(avg);

        // Mokinys -> [map] -> Integer -> [mapToInt] -> int -> [avg]
        avg = mokiniai.stream()
                .map(Mokinys::getAge)
                .map(x -> {
                    System.out.println(x);
                    return x;
                })
                .mapToInt(x -> x)
                .average()
                .orElse(0);
        System.out.println(avg);
    }

    static double calc(List<Mokinys> mok) {
        int suma = 0;
        for (Mokinys m : mok) {
            suma += m.getAge();
        }
        return (double)suma / mok.size();
    }

}


class Mokinys {
    private String name;
    private int age;

    public Mokinys(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


/*

    y = f(x)

    a = f2(x1, x2)

    z = f(x), kur x = f2(x1, x2), z = f(f2(x1, x2))

    y = f(x), z = y(x2),   z = f(x)(x2)

 */

class FF {

    Function<Integer, Function<Double, Double>> ff = x -> d -> d * x;
    double rezult = ff.apply(123).apply(1.23);

    Function<Function<Integer, Integer>, Double> ff2 = f -> f.apply(1) * f.apply(1) * 1.0;
    double rezult2 = ff2.apply(i -> i * 2);

}

// Figura -> Keturkampis -> Kvadratas
class Figura {}
class Keturkampis extends Figura {}
class Staciakampis extends Keturkampis {}
class Kvadratas extends Keturkampis {}

// PECS
class K {
    {
        List<? extends Keturkampis> keturkampiai = new ArrayList<>();
        // List<Keturkampis> arba List<Staciakampis> arba List<Kvadratas>

        // 1. Ka galiu ideti
//        keturkampiai.add(new Keturkampis());
//        keturkampiai.add(new Kvadratas());
//        keturkampiai.add(new Figura());

        // 2. Ka mes galim isimti is to listo
        Keturkampis k = keturkampiai.get(0);
        Figura f = keturkampiai.get(0);
        Object o = keturkampiai.get(0);
    }
    {
        List<? super Keturkampis> keturkampiai = new ArrayList<>();
        // List<Keturkampis> arba List<Figura> arba List<Object>

        // 1. Ka galiu ideti
        keturkampiai.add(new Keturkampis());
        // keturkampiai.add(new Figura());
        keturkampiai.add(new Kvadratas());

        // 2. Ka mes galim isimti is to listo
        // Keturkampis k = keturkampiai.get(0);
        Object o = keturkampiai.get(0);
    }
}
