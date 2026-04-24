import java.util.ArrayList;

public class GameManager {
    
    private GameBoard board;
    private ArrayList<String> moveHistory;
    private String playerName;

    public GameManager(String playerName, int width, int height, int numMines) {
        this.playerName = playerName;
        this.board = new GameBoard(width, height, numMines);
        this.moveHistory = new ArrayList<>();
        System.out.println("Game started! Good luck, " + playerName + "!");
        System.out.println(board.toString());
    }

    public GameBoard getBoard() {
        return board;
    }

    public void makeMove(String move) {
        int x = -1;
        int y = -1;
        if (board.isGameOver()) {
            System.out.println("Game over! Please start a new game.");
            return;
        }
        String command = move.substring(0, 1);
        if (move.length() > 1) {
            move = move.substring(move.indexOf(" ") + 1); // Extract the coordinates part of the move
            if (move.length() == 2) {
                x = Integer.parseInt(move.substring( 1));
                String yString = move.substring(0,1);
                char yChar = yString.charAt(0);
                y = Character.getNumericValue(yChar) - Character.getNumericValue('A') + 1;
            } else if (x < 0 || x > board.getWidth()-1 || y < 0 || y > board.getHeight()-1) {
                System.out.println("Coordinates out of bounds! Please enter valid coordinates.");
                return;
            } else {
                System.out.println("Invalid input format! Please enter coordinates as 'A1'.");
                return;
            }
        } else {
            x = -1;
            y = -1;
        }
        if (command.equalsIgnoreCase("R")) {
            board.revealCell(x, y);
            moveHistory.add("Revealed cell at (" + x + ", " + y + ")");
            System.out.println(board.toString());
        } else if (command.equalsIgnoreCase("F")) {
            board.flagCell(x, y);
            moveHistory.add("Flagged cell at (" + x + ", " + y + ")");
            System.out.println(board.toString());
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

    public void displayStatus() {
        System.out.println("Player: " + playerName);
        System.out.println("Mines remaining: " + board.minesRemaining());
        System.out.println("Game status: ");
        if (board.isGameWon()) {
            System.out.println("Congratulations! You've won the game!");
        } else if (board.isGameOver()) {
            System.out.println("Game over! You've hit a mine.");
        } else {
            System.out.println("Game in progress...");
        }
        System.out.println("Move history:");
        for (String move : moveHistory) {
            System.out.println(move);
        }
    }

    public void displayHelp() {
        System.out.println("Commands:");
        System.out.println("R x,y - Reveal the cell at coordinates (x,y)");
        System.out.println("F x,y - Flag the cell at coordinates (x,y)");
        System.out.println("S - Display current game status");
        System.out.println("Q - Quit the game");
        System.out.println("? - Display this help message");
    }

}
