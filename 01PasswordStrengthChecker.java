import java.util.Scanner;

public class PasswordStrengthChecker {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================");
        System.out.println("   Password Strength Checker");
        System.out.println("==================================");

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        int score = 0;

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        // Check password length
        if (password.length() >= 8) {
            score++;
        }

        if (password.length() >= 12) {
            score++;
        }

        // Check each character
        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }

        if (hasUpper) score++;
        if (hasLower) score++;
        if (hasDigit) score++;
        if (hasSpecial) score++;

        System.out.println("\nPassword Analysis");
        System.out.println("------------------------");
        System.out.println("Length: " + password.length());
        System.out.println("Uppercase: " + hasUpper);
        System.out.println("Lowercase: " + hasLower);
        System.out.println("Numbers: " + hasDigit);
        System.out.println("Special Characters: " + hasSpecial);

        System.out.println("\nStrength:");

        if (score <= 2) {
            System.out.println("Weak");
        } else if (score <= 4) {
            System.out.println("Medium");
        } else if (score <= 6) {
            System.out.println("Strong");
        } else {
            System.out.println("Very Strong");
        }

        System.out.println("\nSuggestions:");

        if (password.length() < 8)
            System.out.println("- Use at least 8 characters.");

        if (!hasUpper)
            System.out.println("- Add an uppercase letter.");

        if (!hasLower)
            System.out.println("- Add a lowercase letter.");

        if (!hasDigit)
            System.out.println("- Add a number.");

        if (!hasSpecial)
            System.out.println("- Add a special character (!,@,#,$,%,&, etc.).");

        if (score >= 6) {
            System.out.println("- Excellent password!");
        }

        scanner.close();
    }
}
