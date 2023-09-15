package com.dimqa.geometry.figures;

public class Rectangle {
    public static void printRectangleArea(double a, double b) {
        System.out.printf("Площадь прямоугольника со сторонами %f и %f = %f%n", a, b, rectangleArea(a, b));
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }
}
