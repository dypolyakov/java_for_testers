package com.dimqa.geometry.figures;

import java.util.Objects;

public record Triangle(double a, double b, double c) {

    public Triangle {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if (a + b < c || a + c < b || b + c < a) {
            throw new IllegalArgumentException("The sum of any two sides of a triangle " +
                    "must be at least as large as the third side of the triangle");
        }
    }
    public double perimeter() {
        return a + b + c;
    }

    public double area() {
        double p = perimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(a, triangle.a) == 0 && Double.compare(b, triangle.b) == 0 && Double.compare(c, triangle.c) == 0)
                || (Double.compare(b, triangle.a) == 0 && Double.compare(c, triangle.b) == 0 && Double.compare(a, triangle.c) == 0)
                || (Double.compare(c, triangle.a) == 0 && Double.compare(a, triangle.b) == 0 && Double.compare(b, triangle.c) == 0)
                || (Double.compare(a, triangle.a) == 0 && Double.compare(c, triangle.b) == 0 && Double.compare(b, triangle.c) == 0)
                || (Double.compare(c, triangle.a) == 0 && Double.compare(b, triangle.b) == 0 && Double.compare(a, triangle.c) == 0)
                || (Double.compare(b, triangle.a) == 0 && Double.compare(a, triangle.b) == 0 && Double.compare(c, triangle.c) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
