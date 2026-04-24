import java.util.Scanner;

/**
 * Main entry point for the Minesweeper game.
 *
 * This is your starting point. Build your game by adding classes
 * and implementing the game logic as described in your project handout.
 */
public class Runner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Minesweeper!");
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        System.out.print("Enter board width: ");
        int width = scanner.nextInt();
        System.out.print("Enter board height: ");
        int height = scanner.nextInt();
        System.out.print("Enter number of mines: ");
        int numMines = scanner.nextInt();
        GameManager gameManager = new GameManager(playerName, width, height, numMines);
        System.out.print("Enter your move (e.g., 'R A3' to reveal or 'F B5' to flag): \n");
        scanner.nextLine(); // Consume the newline character after nextInt()
        while (!gameManager.getBoard().isGameOver()) {
            String move = scanner.nextLine();
            gameManager.makeMove(move);
        }
        scanner.close();
    }
}
