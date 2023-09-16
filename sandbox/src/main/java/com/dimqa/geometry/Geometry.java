package com.dimqa.geometry;

import com.dimqa.geometry.figures.Rectangle;
import com.dimqa.geometry.figures.Square;
import com.dimqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(7.0));
        Square.printSquareArea(new Square(5.0));
        Square.printSquareArea(new Square(3.0));

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7.0, 9.0);

        Triangle triangle = new Triangle(3.0, 5.0, 7.0);
        System.out.println(triangle.area());
    }

}
