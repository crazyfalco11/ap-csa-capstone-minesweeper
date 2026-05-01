public class GameBoard {
    
    private Cell[][] board;
    private int numFlags;
    private boolean gameOver;
    private int numMines;

    public GameBoard(int width, int height, int numMines) {
        board = new Cell[height][width];
        createBoard();
        this.numMines = numMines;
        gameOver = false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getWidth() {
        return board[0].length;
    }

    public int getHeight() {
        return board.length;
    }

    public void createBoard() {
        // Implementation for creating the game board with the specified number of mines
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new EmptyCell(j, i); // Initialize all cells as empty
            }
        }
    }

    public void firstMove(int x, int y) {
        // Implementation for handling the first move of the game to ensure the first revealed cell is not a mine
        placeMines(numMines, x, y); // Place mines on the board after the first move to ensure the first revealed cell is not a mine
        calculateNumbers(); // Calculate the numbers for each cell based on adjacent mines after placing mines
        revealCell(x, y);
        this.numFlags = numMines; // Set the number of flags equal to the number of mines
    }

    public void placeMines(int numMines, int firstX, int firstY) {
        // Implementation for placing mines randomly on the board
        while (numMines > 0) {
            int x = (int) (Math.random() * board[0].length);
            int y = (int) (Math.random() * board.length);
            if (!(board[y][x] instanceof MineCell) && !(x == firstX && y == firstY)) {
                board[y][x] = new MineCell(x, y); // Place a mine at the random position
                numMines--;
            }
        }
    }

    public void calculateNumbers() {
        // Implementation for calculating the numbers for each cell based on adjacent mines
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!(board[i][j] instanceof MineCell)) {
                    int mineCount = countAdjacentMines(j, i);
                    if (mineCount > 0) {
                        board[i][j] = new NumberCell(j, i, mineCount); // Set the cell to a number cell with the count of adjacent mines
                    }
                }
            }
        }
    }

    public int countAdjacentMines(int x, int y) {
        // Implementation for counting the number of adjacent mines around a given cell
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x + j >= 0 && x + j < board[0].length && y + i >= 0 && y + i < board.length) {
                    if (board[y + i][x + j] instanceof MineCell) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public void revealCell(int x, int y) {
        // Implementation for revealing a cell and its adjacent cells if it's an empty cell
            if (board[y][x] instanceof EmptyCell) {
                floodFill(x, y); // If it's an empty cell, perform flood fill to reveal adjacent cells
            } else if (board[y][x] instanceof MineCell) {
                revealMines(); // If it's a mine cell, reveal all mines and end the game
                gameOver = true;
            }
            board[y][x].reveal(); // Reveal the cell if it's not an empty cell
            checkWin(); // Check if the player has won after revealing a cell
    }

    public void flagCell(int x, int y) {
        // Implementation for flagging a cell
        board[y][x].flag();
        if (board[y][x].getSymbol().equals("F")) {
            numFlags--; // Decrease the number of flags remaining when a cell is flagged
        } else {
            numFlags++; // Increase the number of flags remaining when a cell is unflagged
        }
        checkWin(); // Check if the player has won after flagging a cell
    }

    public String toString() {
        // Implementation for converting the game board to a string representation
        StringBuilder sb = new StringBuilder();
            sb.append("  "); // Add initial spacing for column numbers
        for (int i = 0; i < board[0].length; i++) {
            char letter = (char)('A' + i); // Convert column index to letter (A, B, C, etc.)
            String columnLabel = Character.toString(letter);
            sb.append(columnLabel + " "); // Add column letters for display
        }
        for (int i = 0; i < board.length; i++) {
            sb.append("\n" + (i + 1) + " "); // Add row numbers for display
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j].getDisplaySymbol()).append(" ");
            }
        }
        return sb.toString();
    }

    public void floodFill(int x, int y) {
        // Implementation for flood fill algorithm to reveal adjacent empty cells
        if (x < 0 || x >= board[0].length || y < 0 || y >= board.length  || board[y][x].isRevealed() || board[y][x] instanceof MineCell) {
            return; // Base case: out of bounds or already revealed
        }
        board[y][x].reveal(); // Reveal the current cell
        if (board[y][x] instanceof EmptyCell) {
            // Recursively flood fill adjacent cells if the current cell is empty
            floodFill(x + 1, y); // Right
            floodFill(x - 1, y); // Left
            floodFill(x, y + 1); // Down
            floodFill(x, y - 1); // Up
        }
    }

    public void revealMines() {
        // Implementation for revealing all mines on the board
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (cell instanceof MineCell) {
                    cell.reveal();
                }
            }
        }
        System.out.println("Game over! You've hit a mine.");
    }

    public int minesRemaining() {
        return numFlags; // Return the number of flags remaining, which is equal to the number of mines not yet flagged
    }

    public void checkWin() {
        boolean gameWon = true; // Assume the player has won until we find a condition that proves otherwise
        // Implementation for checking if the player has won the game
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (cell instanceof MineCell && !cell.getSymbol().equals("F")) {
                    gameWon = false; // If there is a mine that is not flagged, the player has not won
                }
                if (!(cell instanceof MineCell) && !cell.isRevealed()) {
                    gameWon = false; // If there is a non-mine cell that is not revealed, the player has not won
                }
            }
        }
        if (gameWon) {
            System.out.println("Congratulations! You've won the game!");
            gameOver = true; // Set the game over flag to true when the player wins
        }
    }

}
