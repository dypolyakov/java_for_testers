package com.dimqa.geometry.figures;

public class Square {
    public static void printSquareArea(double side) {
        System.out.printf("Площадь квадрата со стороной %f = %f%n", side, squareArea(side));
    }

    private static double squareArea(double side) {
        return side * side;
    }
}
