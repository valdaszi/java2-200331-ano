package lt.bit.java2;

public class AnoniminesKlasesBendrai {

    public static void main(String[] args) {

        // Anonimine klase pagal interfeisa
        // - as privalau implementuoti abstrakcius metodus
        I1 i1 = new I1() {

            @Override
            public int m1(int x) {
                return x * 100;
            }

            @Override
            public int m2(int x, int y) {
                return m1(x + y);
            }
        };
        System.out.println(i1.getClass().getName() + " " + i1.m2(10, 20));

        // Anonimine klase pagal abstrakcia klase
        // - as privalau implementuoti abstrakcius metodus
        Abs abs = new Abs() {
            @Override
            int m1(int x) {
                return 1 + x;
            }
        };
        System.out.println(abs.getClass().getName() + " " + abs.m2(10, 11));

        // Anonimine klase pagal paprasta klase
        // - as neprivalau nieko implementuoti
        Norm norm = new Norm() {
            @Override
            int m1(int x) {
                return 1 + x;
            }
        };
        System.out.println(norm.getClass().getName() + " " +norm.m2(10, 11));
    }


}

interface I1 {
    int m1(int x);
    int m2(int x, int y);
    default int m3(int x) {
        return m1(x);
    }
}

abstract class Abs {
    abstract int m1(int x);
    int m2(int x, int y) {
        return m1(x + y);
    }
}

class Norm {
    int m1(int x) {
        return 1 + x;
    }
    int m2(int x, int y) {
        return m1(x + y);
    }
}