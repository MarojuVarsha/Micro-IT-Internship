import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] options = {"rock", "paper", "scissors"};

        System.out.println("=== Rock, Paper, Scissors Game ===");

        while (true) {
            System.out.print("\nEnter your move (rock, paper, scissors). To quit, type 'exit': ");
            String userMove = scanner.nextLine().toLowerCase();

            if (userMove.equals("exit")) {
                System.out.println("Thanks for playing!");
                break;
            }

            if (!userMove.equals("rock") && !userMove.equals("paper") && !userMove.equals("scissors")) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }

            String computerMove = options[random.nextInt(3)];
            System.out.println("Computer chose: " + computerMove);

            if (userMove.equals(computerMove)) {
                System.out.println("It's a tie!");
            } else if (
                (userMove.equals("rock") && computerMove.equals("scissors")) ||
                (userMove.equals("scissors") && computerMove.equals("paper")) ||
                (userMove.equals("paper") && computerMove.equals("rock"))
            ) {
                System.out.println("You win!");
            } else {
                System.out.println("Computer wins!");
            }
        }

        scanner.close();
    }
}
