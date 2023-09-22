public class Hello {
    public static void main(String[] args) {
        int x = 1;
        int y = 0;
        if (y == 0) {
            System.out.println("Division by zero is not allowed");
        } else {
            int z = divide(x, y);
            System.out.printf("%d / %d = %d", x, y, z);
        }
    }

    private static int divide(int x, int y) {
        int z = x / y;
        return z;
    }
}
