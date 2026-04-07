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

        int whileDongusu = 0;
        while(whileDongusu < 5) {
            System.out.println("While: " +whileDongusu);
            whileDongusu++;
        }

        System.out.println("------------------");

        String name = "Orhan";
        System.out.println(name);
        String name2 = name.concat("abc");
        System.out.println(name2);
        System.out.println(name);
        //String immutable'dir, yani değiştirilemez. concat() metodu yeni bir String oluşturur ve eski String'i değiştirmez.

        System.out.println("------------------");

        String result1 = calculateGrade(85); 
        String result2 = calculateGrade(70, "Elif"); 
        String result3 = calculateGrade(60); 
        String result4 = calculateGrade(50, "Orhan");
        String result5 = calculateGrade(30, "İrfan");

        System.out.println(result1);
        System.out.println(result2);

        System.out.println("------------------");

        double sum1 = sum(2.5, 3.3);
        double sum2 = sum(1, 2, 3, 4);
        System.out.println("Sum 1: " + sum1);
        System.out.println("Sum 2: " + sum2);

    }

    public static String calculateGrade(int grade, String name) // required parameter
    {
        if(grade >= 85) 
        {
            String result = name + " Notunuz: A";
            return result;
        } 
        else if(grade >= 70) 
        {
            String result = name + " Notunuz: B";
            return name + " Notunuz: B";
        }
        else if(grade >= 50) 
        {
            String result = name + " Notunuz: C";
            return name + " Notunuz: C";
        }
        else 
        {
            String result = name + " Notunuz: F";
            return name + " Notunuz: F";
        }
    }

    // Overloading
    public static String calculateGrade(int grade) // optional parameter
    {
        return calculateGrade(grade, "Öğrenci");
    }

    public static double sum(double a, double b) {
        return a + b;
    }

    public static double sum(double... numbers) { // varargs (Variable Arguments) - istediğimiz kadar parametre gönderebiliriz
        double total = 0;
        for(double number : numbers) {
            total += number;
        }
        return total;
    }
}