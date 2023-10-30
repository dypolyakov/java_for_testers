package com.dimqa.geometry;

import com.dimqa.geometry.figures.Square;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {

//        List<Square> squares = List.of(new Square(7.0), new Square(5.0), new Square(3.0));
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100.0));
        var squares = Stream.generate(randomSquare).limit(5);
        squares.forEach(Square::printSquareArea);


//        Rectangle.printRectangleArea(3.0, 5.0);
//        Rectangle.printRectangleArea(7.0, 9.0);
//
//        Triangle triangle = new Triangle(3.0, 5.0, 7.0);
//        System.out.println(triangle.area());
    }

}
