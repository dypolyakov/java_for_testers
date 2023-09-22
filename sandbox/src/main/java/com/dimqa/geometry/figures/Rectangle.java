package com.dimqa.geometry.figures;

import java.util.Objects;

public record Rectangle(double a, double b) {

    public Rectangle {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Rectangle side should be non-negative");
        }
    }
    public static void printRectangleArea(double a, double b) {
        System.out.printf("Площадь прямоугольника со сторонами %.1f и %.1f = %.1f%n", a, b, rectangleArea(a, b));
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return (Double.compare(a, rectangle.a) == 0 && Double.compare(b, rectangle.b) == 0)
                || (Double.compare(a, rectangle.b) == 0 && Double.compare(b, rectangle.a) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
