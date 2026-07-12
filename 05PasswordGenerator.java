import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+";

    private static final String ALL = UPPER + LOWER + NUMBERS + SYMBOLS;

    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALL.length());
            password.append(ALL.charAt(index));
        }

        return password.toString();
    }

    public static void main(String[] args) {
        int length = 16;

        System.out.println("=== Java Password Generator ===");

        for (int i = 1; i <= 5; i++) {
            System.out.println("Password " + i + ": " +
                    generatePassword(length));
        }
    }
}
