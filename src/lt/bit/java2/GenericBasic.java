package lt.bit.java2;

public class GenericBasic {


    public static void main(String[] args) {
        System.out.println(min(23.45, 456.78, (o1, o2) -> o1 < o2));
        System.out.println(min(23.45, 1456.78, (o1, o2) -> o1 < o2));

        System.out.println(min("23.45", "456.78",
                (o1, o2) -> o1.compareTo(o2) < 0));
        System.out.println(min("23.45", "1456.78",
                (o1, o2) -> o1.compareTo(o2) < 0));

        // Double vs String
        // Comparable Comparable
//        System.out.println(min(23.45, "1456.78",
//                (o1, o2) -> o1.compareTo(o2) < 0));

        System.out.println(min(new A("antras", 1),
                                new A("nulis", 1),
                (o1, o2) -> o1.getSize() < o2.getSize() ||
                        o1.getSize() == o2.getSize() &&
                        o1.getName().compareTo(o2.getName()) < 0));

        Lygiuoti<Double> doubleLygiuoti = (o1, o2) -> { return o1 > o2; };
        Lygiuoti<String> stringLygiuoti = (o1, o2) -> o1.length() > o2.length();
        Lygiuoti<A> aLygiuoti = (o1, o2) -> o1.getName().length() > o1.getName().length();
        Lygiuoti lygiuoti = (o1, o2) -> true;
    }


//    static Comparable min(Comparable a, Comparable b) {
//        if (a.compareTo(b) < 0) return a;
//        else return b;
//    }

//    static <T extends Comparable> T min(T a, T b) {
//        if (a.compareTo(b) < 0) return a;
//        else return b;
//    }

    static <T> T min(T a, T b, Lygiuoti<T> lygiavimas) {
        if (lygiavimas.arMaziau(a, b)) return a;
        else return b;
    }
}

@FunctionalInterface
interface Lygiuoti<E> {

    boolean arMaziau(E o1, E o2);

}

