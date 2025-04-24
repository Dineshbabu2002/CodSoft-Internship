import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;
        int round = 0;

        System.out.println("🎮 Welcome to the Number Guessing Game!");

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; // Generates number between 1 and 100
            int attempts = 0;
            int maxAttempts = 7;
            boolean guessedCorrectly = false;
            round++;

            System.out.println("\n🔁 Round " + round + ": I have picked a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts. Good luck!");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    int scoreThisRound = (maxAttempts - attempts + 1) * 10;
                    totalScore += scoreThisRound;
                    System.out.println("🎉 Correct! You guessed it in " + attempts + " attempts.");
                    System.out.println("🏆 You earned " + scoreThisRound + " points this round.");
                    guessedCorrectly = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("📉 Too low! Try again.");
                } else {
                    System.out.println("📈 Too high! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("❌ Out of attempts! The number was: " + numberToGuess);
            }

            System.out.println("📊 Total Score: " + totalScore);
            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes");
        }

        System.out.println("\n🎮 Game Over! Thanks for playing.");
        System.out.println("🎯 Final Score: " + totalScore);
        scanner.close();
    }
}
