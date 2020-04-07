package lt.bit.java2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class A {
    public static double CONST = 1.23;

    private String name = "A";
    private int size = -5;

    static {
        try {
            InputStream is = new FileInputStream("asasas");
            //
            CONST = 3.45;
            // ...
        } catch (FileNotFoundException e) {
            CONST = 9.99;
        }
        System.out.println("A static { ... }");
    }

    public A() {
        // -> {}
    }

    public A(String name, int size) {
        // --> {}
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    {
        size = size + 100;
        // ...
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
