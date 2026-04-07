package com.turkcell;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Primitive types
        int a = 0;
        int b = a;
        a = 10;
        System.out.println(a);
        System.out.println(b);

        System.out.println("------------------");

        // Reference types
        int[] c = {0, 1, 2, 3};
        int[] d = c;
        d[3] = 30;
        System.out.println(c[3]);
        System.out.println(d[3]);

        System.out.println("------------------");
        
        System.out.println(a == b);
        System.out.println(c == d);

        System.out.println("------------------");

        int[] x = {0, 1, 2, 3};
        int[] y = {0, 1, 2, 3};
        System.out.println(x);
        System.out.println(x == y);
        System.out.println(Arrays.equals(x, y));

        System.out.println("------------------");

        String s1 = "Merhaba";
        String s2 = "Merhaba";

        System.out.println(s1 == s2); // String pool
        System.out.println(s1.equals(s2)); // Daha güvenli

        System.out.println("------------------");

        String s3 = "Turkcell";
        String s4 = s3.intern();
        // intern() metodu, string'i string pool'a ekler ve referansını döner

        System.out.println(s3 == s4);

        System.out.println("------------------");

        String s5 = "Turkcell";
        String s6 = new String("Turkcell"); // yeni bir String nesnesi oluşturur, string pool'dan değil heap'ten referans alır

        System.out.println(s5 == s6);
        
        System.out.println("------------------");

        // Döngüler
        for(int i = 0; i < 5; i++) {
            System.out.println(i);
        }

        System.out.println("------------------");

        String[] students = {"Orhan", "Elif", "Yaren"};
        
        for(int i = 0; i < students.length; i++) {
            System.out.println(students[i]);
        }

        for(String student : students) {
            System.out.println(student);
        }

    }
}