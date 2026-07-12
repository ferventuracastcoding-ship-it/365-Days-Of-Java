import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+";

    public static String generatePassword(int length) {

        String characters = LOWERCASE + UPPERCASE + NUMBERS + SYMBOLS;

        SecureRandom random = new SecureRandom();

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {

            int index = random.nextInt(characters.length());

            password.append(characters.charAt(index));
        }

        return password.toString();
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================");
        System.out.println("   Secure Password Generator");
        System.out.println("==============================");

        System.out.print("Password length: ");

        int length = scanner.nextInt();


        if (length < 8) {

            System.out.println("Password should be at least 8 characters.");

        } else {

            String password = generatePassword(length);

            System.out.println("\nGenerated Password:");
            System.out.println(password);

            System.out.println("\nPassword Features:");
            System.out.println("✓ Uppercase letters");
            System.out.println("✓ Lowercase letters");
            System.out.println("✓ Numbers");
            System.out.println("✓ Special characters");
        }


        scanner.close();
    }
}
