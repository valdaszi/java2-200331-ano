package lt.bit.java2;

public class DinaminesKlases {

    static int i = 100;
    int y = 200;

    class A {
        String name;
        A(String name) {
            this.name = name + i + y;
        }
    }
}

class Dex extends DinaminesKlases {}

class DemoDinamines {

    public static void main(String[] args) {
        System.out.println(StatinesKlases.i);

        Dex master = new Dex();
        System.out.println(master.y);

        Dex.A a = master.new A("Abc");
        System.out.println(a.name);
    }
}
