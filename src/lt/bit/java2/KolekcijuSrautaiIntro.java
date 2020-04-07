package lt.bit.java2;

import java.util.ArrayList;
import java.util.List;

public class KolekcijuSrautaiIntro {

    public static void main(String[] args) {
        List<Mokinys> mokiniai = new ArrayList<>();
        mokiniai.add(new Mokinys("Jonas", 10));
        mokiniai.add(new Mokinys("Ona", 9));
        mokiniai.add(new Mokinys("Petras", 12));
        mokiniai.add(new Mokinys("Maryte", 6));

        double avg = calc(mokiniai);
        System.out.println(avg);

        // Mokinys -> Mokinys -> Mokinys -> [map] -> Integer -> [mapToInt] -> int -> int> [avg]
        avg = mokiniai.stream()
                .map(x -> x.getAge())
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
