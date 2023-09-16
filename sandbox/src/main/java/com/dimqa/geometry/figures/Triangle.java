package com.dimqa.geometry.figures;

public record Triangle(double a, double b, double c) {
    public double perimeter() {
        return a + b + c;
    }

    public double area() {
        double p = perimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
