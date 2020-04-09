package lt.bit.java2;

import java.util.function.BiFunction;
import java.util.function.Function;

public class LambdaMethodRef {

    public static void main(String[] args) {

        // 1. (a, b, ...) -> { return Class.staticMethod(a, b, ...); }
        //    (a, b, ...) -> Class.staticMethod(a, b, ...)  =>  Class::staticMethod

        Function<Integer, String> f1 = (Integer i) -> { return String.valueOf(i); };
        f1 = i -> String.valueOf(i);
        f1 = String::valueOf;

        // 2. (a, b, ...) -> new Class(a, b, ...)  =>  Class::new

        BiFunction<Integer, String, IntegerString> f2 = (i, s) -> new IntegerString(i, s);
        f2 = IntegerString::new;

        // 3. (Class a, b, ...) -> a.method(b, ...)  =>  Class::method

        TriFunction<IntegerString, Integer, String, String> f3 = (is, i, s) -> is.calculate(i, s);
        f3 = IntegerString::calculate;

        // 4. final Class x = ....
        // (a, b, ...) -> x.method(a, b, ...)  =>  x::method

        final IntegerString is = new IntegerString(1, "string");
        BiFunction<Integer, String, String> f4 = (i, s) -> is.calculate(i, s);
        f4 = is::calculate;
    }
}

interface TriFunction<P1, P2, P3, R> {
    R apply(P1 p1, P2 p2, P3 p3);
}


class IntegerString {
    Integer a;
    String b;

    public IntegerString(Integer a, String b) {
        this.a = a;
        this.b = b;
    }

    String calculate(Integer x, String y) {
        return b + x + y + a;
    }
}
