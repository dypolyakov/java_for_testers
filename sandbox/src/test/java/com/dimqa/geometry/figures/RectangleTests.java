package com.dimqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {

    @Test
    void cannotCreateRectangleWithNegativeSide() {
        try {
            new Rectangle(-5.0, -4.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void testEquality() {
        Rectangle r1 = new Rectangle(5.0, 4.0);
        Rectangle r2 = new Rectangle(5.0, 4.0);
        Assertions.assertEquals(r1, r2);
    }

    @Test
    void testNonEquality() {
        Rectangle r1 = new Rectangle(5.0, 4.0);
        Rectangle r2 = new Rectangle(3.0, 2.0);
        Assertions.assertNotEquals(r1, r2);
    }

    @Test
    void testEquality2() {
        Rectangle r1 = new Rectangle(5.0, 4.0);
        Rectangle r2 = new Rectangle(4.0, 5.0);
        Assertions.assertEquals(r1, r2);
    }
}
