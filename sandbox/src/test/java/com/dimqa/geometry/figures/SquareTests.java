package com.dimqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea() {
        Square s = new Square(5.0);
        double result = s.area();
        Assertions.assertEquals(25.0, result);
    }

    @Test
    void canCaltulatePerimeter() {
        Assertions.assertEquals(20.0, new Square(5.0).perimeter());
    }

    @Test
    void cannotCreateSquareWithNegativeSide() {
        try {
            new Square(-5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void testEquality() {
        Square s1 = new Square(5.0);
        Square s2 = new Square(5.0);
        Assertions.assertEquals(s1, s2);
    }

    @Test
    void testNonEquality() {
        Square s1 = new Square(5.0);
        Square s2 = new Square(2.0);
        Assertions.assertNotEquals(s1, s2);
    }

    @Test
    void testPass() {
        Square s1 = new Square(5.0);
        Square s2 = new Square(5.0);
        Assertions.assertTrue(s1.equals(s2));
    }
}
