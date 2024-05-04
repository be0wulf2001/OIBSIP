import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    static int playRound(Scanner sc) {
        Random rand = new Random();
        int rand_int = rand.nextInt(100) + 1; // Generate a random number between 1 and 100
        int maxTries = 10;
        int tries = maxTries;

        System.out.println("Guess a number between 1 and 100.");

        while (tries > 0) {
            System.out.println("You have " + tries + " attempt(s) left.");
            System.out.print("Enter your guess: ");
            if (sc.hasNextInt()) {
                int input = sc.nextInt();
                if (input == rand_int) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    return tries; // Return the number of tries left
                } else if (input < rand_int) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }
                tries--;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.next(); // Consume invalid input
            }
        }

        System.out.println("Sorry, you're out of attempts. The correct number was " + rand_int + ".");
        return 0; // Return 0 if the user runs out of attempts
    }

    public static void main(String[] args) {
        int numRounds = 3;
        Scanner sc = new Scanner(System.in);
        int totalScore = 0;

        for (int i = 1; i <= numRounds; i++) {
            System.out.println("Round " + i);
            int score = playRound(sc);
            System.out.println("Score for current round: " + score + "/10.");
            totalScore += score;
        }

        sc.close(); // Close the scanner after finishing all rounds
        System.out.println("Total score across all rounds: " + totalScore + "/30.");
    }
}
