package com.bartoszek.jni;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ScalarTest {
    static {
        System.loadLibrary("scalarLib");
    }

    public Double[] a;
    public Double[] b;
    public Double c;

    public ScalarTest(Double[] a, Double[] b) {
        this.a = a;
        this.b = b;
    }

    public Double[] getA() {
        return a;
    }

    public Double[] getB() {
        return b;
    }

    public Double getC() {
        return c;
    }

    public native Double multi01(Double[] a, Double[] b);

    public native Double multi02(Double[] a);

    public native void multi03();

    public void multi04() {
        System.out.println();
        System.out.println("Aplikacja - iloczyn skalary");
        System.out.println("Wektor a: " + Arrays.toString(a));
        System.out.println("Wektor b: " + Arrays.toString(b));
        System.out.println();
        System.out.println("Wynik wywołania funkcji multi01(): " + multi01(a, b));
        System.out.println("Wynik wywołania funkcji multi02(): " + multi02(a));
        System.out.print("Wynik wywołania funkcji multi03(): ");
        multi03();
        System.out.println(getC().toString());
    }

    public static void main(String[] args) {
        System.out.println("Liczba współrzędnych: ");
        Scanner scan = new Scanner(System.in);
        int size = Integer.parseInt(scan.nextLine());
        List<Double> a = new ArrayList<>();
        List<Double> b = new ArrayList<>();


        System.out.println("Współrzedne wektora a: ");
        for (int i = 0; i < size; i++) {
            double v = scan.nextDouble();
            a.add(v);
        }
        System.out.println("Współrzedne wektora b: ");
        for (int i = 0; i < size; i++) {
            double v = scan.nextDouble();
            b.add(v);
        }

        Double[] arrayA = a.toArray(new Double[0]);
        Double[] arrayB = b.toArray(new Double[0]);

        ScalarTest sc = new ScalarTest(arrayA, arrayB);
        sc.multi04();

    }
}
