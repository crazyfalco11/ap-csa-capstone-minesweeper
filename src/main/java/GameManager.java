import java.util.ArrayList;

public class GameManager {
    
    private Board board;
    private ArrayList<String> moveHistory;
    private String playerName;

    public GameManager(String playerName, int width, int height, int numMines) {
        this.playerName = playerName;
        this.board = new Board(width, height, numMines);
        this.moveHistory = new ArrayList<>();
    }

    public void makeMove(String move) {
        int x;
        int y;
        if (board.isGameOver()) {
            System.out.println("Game over! Please start a new game.");
            return;
        }
        String command = move.substring(0, 1);
        move = move.substring(indexOf(" ") + 1).trim();
        try {
            String[] parts = move.split("");
            x = Integer.parseInt(parts[1].trim());
            String yString = parts[2].trim();
            char yChar = yString.charAt(0);
            y = Character.getNumericValue(yChar) - Character.getNumericValue('A') + 1;
        } catch (Exception e) {
            System.out.println("Invalid input format! Please enter coordinates as 'x,y'.");
            return;
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
        System.out.println("Flags remaining: " + board.getNumFlags());
        System.out.println("Mines remaining: " + board.getNumMines());
        System.out.println("Game status: ");
        if (board.isGameWon()) {
            System.out.println("Congratulations! You've won the game!");
        } else if (board.isGameLost()) {
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
