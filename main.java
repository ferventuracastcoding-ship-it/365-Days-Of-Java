import java.util.Scanner;
import java.util.Random;

public class GuessGame {
    public static void main(String[] args) {
        Random r = new Random();
        int secret = r.nextInt(50) + 1;
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Guess (1 - 50): ");
        int guess = sc.nextInt();
        
        System.out.println(
            guess == secret ? "You Win" : "You Lose! Number was " + secret);
    }
}
