package lt.bit.java2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import lt.bit.java2.A;

public class GenerizuotiTipai {

    public static void main(String[] args) {

        System.out.println(A.CONST);

        List<Integer> integerList = new ArrayList<>();
        integerList.add(123);
        integerList.add(456);
        integerList.add(null);

        System.out.println(integerList);
    }
}


abstract class G1<E, V, K, T, U, S1, N extends Number> {

    V value;
    K key;
    N number;

    List<?> listasSuNeaiskuKokiaisElementais;
    List<Object> listasSuNeaiskuKokiaisElementais2;
    List listasSuNeaiskuKokiaisElementais3;

    List<? extends Number> listasSuNeaiskuKokiaisNumberElementais;

    {
        // listasSuNeaiskuKokiaisElementais.add("aaa");
        // listasSuNeaiskuKokiaisElementais.add(new Object());

        listasSuNeaiskuKokiaisElementais2.add("aaa");
        listasSuNeaiskuKokiaisElementais2.add(new Object());

        listasSuNeaiskuKokiaisElementais3.add("aaa");
        listasSuNeaiskuKokiaisElementais3.add(new Object());

        // listasSuNeaiskuKokiaisNumberElementais.add(123);
        Number n = listasSuNeaiskuKokiaisNumberElementais.get(0);
    }


    abstract Map<K,V> calculate(E p1, V p2, K p3, T p4, U p5, S1 s1);
    abstract void calculate2(E p1);
}