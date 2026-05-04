import java.util.Scanner;

/**
 * Main entry point for the Minesweeper game.
 *
 * This is your starting point. Build your game by adding classes
 * and implementing the game logic as described in your project handout.
 */
public class Runner {


    /**
     * Main method to start the Minesweeper game, which prompts the user for their name and game settings (board width, height, and number of mines), initializes the GameManager with the provided settings, and handles the game loop to process player moves until the game is over, allowing players to interact with the game and providing feedback on their actions during gameplay
     * 
     * precondition: the main method is called, which prompts the user for their name and game settings (board width, height, and number of mines), initializes the GameManager with the provided settings, and handles the game loop to process player moves until the game is over, allowing players to interact with the game and providing feedback on their actions during gameplay
     * postcondition: the main method will start the Minesweeper game by prompting the user for their name and game settings, initializing the GameManager with the provided settings, and handling the game loop to process player moves until the game is over, allowing players to interact with the game and providing feedback on their actions during gameplay, which is essential for the functionality of the Minesweeper game and enhances the player's experience by providing a structured way to start and play the game
     * 
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Minesweeper!"); // Greet the player and introduce the game
        System.out.print("Enter your name: "); // Prompt the player to enter their name for a personalized gaming experience
        String playerName = scanner.nextLine();
        System.out.print("Enter board width: "); // Prompt the player to enter the width of the game board, allowing them to customize the size of the game board for a more personalized gaming experience
        int width = scanner.nextInt();
        System.out.print("Enter board height: "); // Prompt the player to enter the height of the game board, allowing them to customize the size of the game board for a more personalized gaming experience
        int height = scanner.nextInt();
        System.out.print("Enter number of mines: "); // Prompt the player to enter the number of mines on the game board, allowing them to customize the difficulty level of the game for a more personalized gaming experience
        int numMines = scanner.nextInt();
        GameManager gameManager = new GameManager(playerName, width, height, numMines); // Initialize the GameManager with the player's name and game settings, allowing it to manage the overall game state and handle player interactions during gameplay
        System.out.print("Enter your move (e.g., 'R A3' to reveal or 'F B5' to flag): \n"); // Prompt the player to enter their move, providing an example of the expected input format for revealing or flagging a cell, allowing the player to interact with the game and make informed decisions during gameplay
        scanner.nextLine(); // Consume the newline character after nextInt()
        while (!gameManager.getBoard().isGameOver()) { // Game loop to process player moves until the game is over, allowing players to interact with the game and providing feedback on their actions during gameplay
            String move = scanner.nextLine();
            gameManager.makeMove(move); // Process the player's move by validating the input, updating the game board accordingly (reveal or flag a cell), and tracking the move history, allowing the player to interact with the game and providing feedback on their actions during gameplay
        }
        scanner.close();
    }
}
