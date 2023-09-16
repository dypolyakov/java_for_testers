package com.dimqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculatePerimeter() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        Assertions.assertEquals(12.0, triangle.perimeter());
    }

    @Test
    void canCalculateArea() {
        Triangle triangle = new Triangle(3.0, 5.0, 7.0);
        Assertions.assertEquals(6.49519052838329, triangle.area());
    }
}
