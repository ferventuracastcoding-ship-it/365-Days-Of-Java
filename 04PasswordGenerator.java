import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {

    private static final SecureRandom random = new SecureRandom();

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()_-+=<>?/";

    private static final String ALL =
            UPPERCASE + LOWERCASE + NUMBERS + SYMBOLS;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("     Secure Password Generator");
        System.out.println("=================================");

        System.out.print("Enter password length (8-64): ");
        int length = scanner.nextInt();

        if (length < 8) {
            System.out.println("Password length must be at least 8.");
            return;
        }

        String password = generatePassword(length);

        System.out.println("\nGenerated Password:");
        System.out.println(password);

        System.out.println("\nPassword Strength: "
                + passwordStrength(password));

        System.out.println("\nGenerate 5 More Passwords:");
        for (int i = 1; i <= 5; i++) {
            System.out.println(i + ". " + generatePassword(length));
        }

        scanner.close();
    }

    public static String generatePassword(int length) {

        StringBuilder password = new StringBuilder();

        // Required characters
        password.append(randomChar(UPPERCASE));
        password.append(randomChar(LOWERCASE));
        password.append(randomChar(NUMBERS));
        password.append(randomChar(SYMBOLS));

        // Fill remaining characters
        while (password.length() < length) {
            password.append(randomChar(ALL));
        }

        // Shuffle characters
        char[] chars = password.toString().toCharArray();

        for (int i = chars.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        return new String(chars);
    }

    private static char randomChar(String characters) {
        return characters.charAt(random.nextInt(characters.length()));
    }

    public static String passwordStrength(String password) {

        int score = 0;

        if (password.length() >= 8) score++;
        if (password.length() >= 12) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*\\d.*")) score++;
        if (password.matches(".*[!@#$%^&*()_\\-+=<>?/].*")) score++;

        if (score <= 2)
            return "Weak";
        else if (score <= 4)
            return "Medium";
        else
            return "Strong";
    }
}
