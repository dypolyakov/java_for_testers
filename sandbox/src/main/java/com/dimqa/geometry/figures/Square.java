package com.dimqa.geometry.figures;

public record Square(double side) {

    public Square {
        if (side < 0) {
            throw new IllegalArgumentException("Square side should be non-negative");
        }
    }

    public static void printSquareArea(Square s) {
        System.out.printf("Площадь квадрата со стороной %f = %f%n", s.side, s.area());
    }

    public double area() {
        return side * side;
    }

    public double perimeter() {
        return 4 * side;
    }
}
