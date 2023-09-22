import org.junit.jupiter.api.Test;

public class MathTests {

    @Test
    void testDivideByZero() {
        int x = 1;
        int y = 0;
        int z = x / y;
        System.out.println(z);
    }
}
