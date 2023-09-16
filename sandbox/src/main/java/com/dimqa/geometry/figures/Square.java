package com.dimqa.geometry.figures;

public record Square(double side) {

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
