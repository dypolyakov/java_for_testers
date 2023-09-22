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

    @Test
    void cannotCreateTriangleWithNegativeSideA() {
        try {
            new Triangle(-1.0, 2.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void cannotCreateTriangleWithNegativeSideB() {
        try {
            new Triangle(1.0, -2.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void cannotCreateTriangleWithNegativeSideC() {
        try {
            new Triangle(1.0, 2.0, -3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void cannotCreateTriangleWithSidesAandBLessThenC() {
        try {
            new Triangle(5.0,6.0,12.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void cannotCreateTriangleWithSidesAandCLessThenB() {
        try {
            new Triangle(5.0,18.0,12.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void cannotCreateTriangleWithSidesBandCLessThenA() {
        try {
            new Triangle(31.0,18.0,12.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void testEquality() {
        Triangle t1 = new Triangle(3, 4, 5);
        Triangle t2 = new Triangle(3, 4, 5);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testNonEquality() {
        Triangle t1 = new Triangle(3, 4, 5);
        Triangle t2 = new Triangle(4, 5, 6);
        Assertions.assertNotEquals(t1, t2);
    }

    @Test
    void testEquality2() {
        Triangle t1 = new Triangle(3, 4, 5);
        Triangle t2 = new Triangle(4, 5, 3);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality3() {
        Triangle t1 = new Triangle(3, 4, 5);
        Triangle t2 = new Triangle(5, 3, 4);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality4() {
        Triangle t1 = new Triangle(3, 4, 5);
        Triangle t2 = new Triangle(3, 5, 4);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality5() {
        Triangle t1 = new Triangle(3, 4, 5);
        Triangle t2 = new Triangle(5, 4, 3);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality6() {
        Triangle t1 = new Triangle(3, 4, 5);
        Triangle t2 = new Triangle(4, 3, 5);
        Assertions.assertEquals(t1, t2);
    }
}
