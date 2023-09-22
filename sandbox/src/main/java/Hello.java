import java.io.File;

public class Hello {
    public static void main(String[] args) {
        try {
            int z = calculate();
            System.out.println(z);
        } catch (ArithmeticException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static int calculate() {
        int x = 1;
        int y = 0;
        int z = divide(x, y);
        return z;
    }

    private static int divide(int x, int y) {
        int z = x / y;
        return z;
    }
}
