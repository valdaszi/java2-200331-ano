package lt.bit.java2;

public class StatinesKlases {

    static int i = 100;
    int y = 200;

    static class A {
        String name;

        public A(String name) {
            this.name = name + i;
        }
    }

}

class DemoStatines {

    public static void main(String[] args) {
        System.out.println(StatinesKlases.i);

        StatinesKlases master = new StatinesKlases();
        System.out.println(master.y);

        StatinesKlases.A a = new StatinesKlases.A("Abc");
        System.out.println(a.name);
    }
}
