import java.util.ArrayList;

public class GameManager {
    
    private GameBoard board;
    private ArrayList<String> moveHistory;
    private String playerName;
    boolean firstMoveMade;


    /**
     * Constructor for the GameManager class, which initializes the game manager with the player's name, creates a new game board with the specified width, height, and number of mines, and sets up the move history and first move flag, allowing it to manage the overall game state and handle player interactions during gameplay
     * 
     * precondition: the GameManager constructor is called with valid parameters for the player's name, width, height, and number of mines, which initializes the game manager's state and sets up the game board and move history for gameplay
     * postcondition: the GameManager object will be created with the specified player's name, a new game board with the specified width, height, and number of mines, an empty move history, and the first move flag set to false, allowing it to manage the overall game state and handle player interactions during gameplay, which is essential for the functionality of the Minesweeper game and enhances the player's experience by providing a structured way to manage the game state and track player actions during gameplay
     * 
     * @param playerName
     * @param width
     * @param height
     * @param numMines
     */
    public GameManager(String playerName, int width, int height, int numMines) {
        this.playerName = playerName;
        this.board = new GameBoard(width, height, numMines);
        this.moveHistory = new ArrayList<>();
        this.firstMoveMade = false;
        System.out.println("Game started! Good luck, " + playerName + "!");
        System.out.println(board.toString());
    }

    public GameBoard getBoard() {
        return board;
    }


    /**
     * Implementation for the makeMove method to process a player's move, which takes a string input representing the player's command and coordinates, validates the input, updates the game board accordingly (reveal or flag a cell), and tracks the move history, allowing the player to interact with the game and providing feedback on their actions during gameplay
     * 
     * precondition: the makeMove method is called with a valid move string that represents a player's command and coordinates, which processes the move by validating the input, updating the game board accordingly (reveal or flag a cell), and tracking the move history, allowing the player to interact with the game and providing feedback on their actions during gameplay
     * postcondition: the makeMove method will process the player's move by validating the input, updating the game board accordingly (reveal or flag a cell), and tracking the move history, allowing the player to interact with the game and providing feedback on their actions during gameplay, which is essential for the functionality of the Minesweeper game and enhances the player's experience by allowing them to make informed decisions and see the results of their actions on the game board during gameplay
     * 
     * @param move
     */
    public void makeMove(String move) {
        int x = -1;
        int y = -1;
        if (board.isGameOver()) {
            System.out.println("Game over! Please start a new game.");
            return;
        }
        if (move == null || move.length() < 1) {
            System.out.println("Invalid input! Please enter a valid move.");
            return;
        }
        String command = move.substring(0, 1);
        if (move.length() > 1) {
            move = move.substring(move.indexOf(" ") + 1); // Extract the coordinates part of the move
            if (move.length() == 2) {
                y = Integer.parseInt(move.substring( 1))-1; // Convert the y coordinate from string to integer and adjust for 0-based index
                String xString = move.substring(0,1);
                char xChar = xString.charAt(0);
                x = xChar - 'A';
                if (x < 0 || x > board.getWidth()-1 || y < 0 || y > board.getHeight()-1) {
                    System.out.println("Coordinates out of bounds! Please enter valid coordinates.");
                    return;
                }
            } else {
                System.out.println("Invalid input format! Please enter coordinates as 'A1'.");
                return;
            }
        } else {
            x = -1;
            y = -1;
        }
        if (command.equalsIgnoreCase("R")) {
            if (!firstMoveMade) {
                board.firstMove(x, y);
                firstMoveMade = true;
            }
            board.revealCell(x, y);
            moveHistory.add("Revealed cell at (" + (char)('A' + x) + (y + 1) + ")");
            if (!(board.isGameOver())){
                System.out.println(board.toString());
            }
        } else if (command.equalsIgnoreCase("F")) {
            board.flagCell(x, y);
            moveHistory.add("Flagged cell at (" + (char)('A' + x) + (y + 1) + ")");
            if (!(board.isGameOver())){
            System.out.println(board.toString());
            }
        } else if (command.equalsIgnoreCase("Q")) {
            board.setGameOver(true);
            System.out.println("Game quit! Thanks for playing.");
        } else if (command.equalsIgnoreCase("S")) {
            displayStatus();
        } else if (command.equalsIgnoreCase("?")) {
            displayHelp();
        } else {       
            System.out.println("Invalid command! Use 'R' to reveal and 'F' to flag.");
            return;
        }
     }


     /**
      * Implementation for the displayStatus method to show the current game status, including the player's name, the number of mines remaining, and the game's progress, allowing players to monitor their performance and make informed decisions during gameplay
      * 
      * precondition: the displayStatus method is called, which displays the current game status including the player's name, the number of mines remaining, and the game's progress, allowing players to monitor their performance and make informed decisions during gameplay
      * postcondition: the displayStatus method will print the current game status to the console, providing players with information about their performance and the game's progress, which enhances the player's experience by allowing them to track their progress and make informed decisions during gameplay
      */
    public void displayStatus() {
        System.out.println("Player: " + playerName);
        System.out.println("Mines remaining: " + board.minesRemaining());
        System.out.println("Game status: ");
        System.out.println("Game in progress...");
        System.out.println("Move history:");
        for (String move : moveHistory) {
            System.out.println(move);
        }
    }


    /**
     * Implementation for the displayHelp method to show the available commands and their usage, allowing players to understand how to interact with the game and make informed decisions during gameplay
     * 
     * precondition: the displayHelp method is called, which displays the available commands and their usage, allowing players to understand how to interact with the game and make informed decisions during gameplay
     * postcondition: the displayHelp method will print the available commands and their usage to the console, providing players with information about how to interact with the game and enhancing the player's experience by making it easier for them to learn and play the game
     */
    public void displayHelp() {
        System.out.println("Commands:");
        System.out.println("R RowCol ie. A1- Reveal the cell at coordinates (A1)");
        System.out.println("F RowCol ie. A1 - Flag the cell at coordinates (A1)");
        System.out.println("S - Display current game status");
        System.out.println("Q - Quit the game");
        System.out.println("? - Display this help message");
    }

}
