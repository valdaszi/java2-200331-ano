package lt.bit.java2;

public class LambdaBendrai {

    public static void main(String[] args) {

        Lambda1 l1 = (p1, p2) -> p1 + p2;
        Double result = l1.op(100.0, 200.2);
        Double res2 = operacija(10.0, 20.0, l1);
        Double res3 = operacija(10.0, 20.0, (a, b) -> a + 2 * b);

        Abc abc = new Abc();
        Double res4 = abc.kazkas(6., 8.0);

        // lambda kaip nuoroda i paprasta metoda
        Double res5 = operacija(10.0, 20.0, (a, b) -> abc.kazkas(a, b));
        Double res51 = operacija(10.0, 20.0, abc::kazkas);

        // lambda kaip nuoroda i statini metoda
        Double res6 = operacija(10.0, 20.0, (a, b) -> Abc.statinisMetodas(a, b));
        Double res61 = operacija(10.0, 20.0, Abc::statinisMetodas);

        // lambda kaip nuoroda i konstruktoriu
        Double res7 = operacija(10, a -> new Abc(a));
        Double res71 = operacija(10, Abc::new);
    }

    static Double operacija(Double x, Double y, Lambda1 o) {
        return o.op(x, y) * o.op(x, y);
    }

    static Double operacija(int x, Lambda2 o) {
        Abc a = o.m(x);
        return a.kazkas(10.0, 20.);
    }
}

@FunctionalInterface
interface Lambda1 {
    Double op(Double p1, Double p2);
}

@FunctionalInterface
interface Lambda2 {
    Abc m(int x);
}

class Abc {
    long x;

    public Abc(long x) {
        this.x = x;
    }

    Abc() {}

    static Double statinisMetodas(Double a1, Double a2) {
        return a1 + a2;
    }
    // ...
    Double kazkas(Double a, Double b) {
        return (a + b) / 2.0;
    }
    // ...
}
