public class GameBoard {
    
    private Cell[][] board;
    private int numFlags;
    private boolean gameOver;
    private int numMines;
    private boolean gameLost;


    /**
     * Constructor for the GameBoard class, which initializes the game board with the specified width, height, and number of mines, and also sets up the initial state of the game including the number of flags and game over status
     * 
     * precondition: the GameBoard constructor is called with valid width, height, and number of mines parameters, which initializes the game board with empty cells and prepares it for the placement of mines and numbers, allowing the game to start with a valid board configuration for the player to interact with
     * postcondition: the GameBoard constructor will initialize the game board with empty cells, set the number of mines, and set the game over status to false, allowing the game to start with a valid board configuration for the player to interact with, and the player will be able to make their first move to reveal a cell and trigger the placement of mines and calculation of numbers on the board
     * 
     * @param width
     * @param height
     * @param numMines
     */
    public GameBoard(int width, int height, int numMines) {
        board = new Cell[height][width];
        createBoard();
        this.numMines = numMines;
        gameOver = false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameLost() {
        return gameLost;
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


    /**
     * Implementation for creating the game board with the specified number of mines, which initializes the board with empty cells and prepares it for the placement of mines and numbers, allowing the game to start with a valid board configuration for the player to interact with
     * 
     * precondition: the GameBoard constructor has been called with the specified width, height, and number of mines, which triggers the createBoard method to initialize the board with empty cells and prepare it for the placement of mines and numbers, allowing the game to start with a valid board configuration for the player to interact with
     * postcondition: the createBoard method will initialize the board with empty cells, allowing the game to start with a valid board configuration for the player to interact with, and the board will be ready for the placement of mines and numbers when the first move is made by the player, which enhances the player's experience and allows them to enjoy the game from the very beginning
     */
    public void createBoard() {
        // Implementation for creating the game board with the specified number of mines
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new EmptyCell(j, i); // Initialize all cells as empty
            }
        }
    }


    /**
     * Implementation for handling the first move of the game to ensure the first revealed cell is not a mine, which involves placing mines on the board after the first move is made to guarantee that the first revealed cell is always safe, and also calculates the numbers for each cell based on adjacent mines after placing mines, allowing the player to have a valid board configuration for their first move and providing them with crucial information about adjacent mines from the start of the game
     * 
     * precondition: the player has made their first move by revealing a cell at (x, y), which triggers the firstMove method to place mines on the board while ensuring that the first revealed cell is not a mine, and also calculates the numbers for each cell based on adjacent mines after placing mines, allowing the player to have a valid board configuration for their first move and providing them with crucial information about adjacent mines from the start of the game
     * postcondition: the first revealed cell at (x, y) will not be a mine, mines will be placed on the board while ensuring that the first revealed cell is not a mine, and the numbers for each cell will be calculated based on adjacent mines, allowing the player to have a valid board configuration for their first move and providing them with crucial information about adjacent mines from the start of the game, which enhances the player's experience and allows them to make informed decisions from the very beginning of the game
     * 
     * @param x
     * @param y
     */
    public void firstMove(int x, int y) {
        // Implementation for handling the first move of the game to ensure the first revealed cell is not a mine
        placeMines(numMines, x, y); // Place mines on the board after the first move to ensure the first revealed cell is not a mine
        calculateNumbers(); // Calculate the numbers for each cell based on adjacent mines after placing mines
        revealCell(x, y);
        this.numFlags = numMines; // Set the number of flags equal to the number of mines
    }


    /**
     * Implementation for placing mines randomly on the board, which ensures that the first revealed cell and its adjacent cells are not mines, and also ensures that the specified number of mines are placed on the board without overlapping
     * 
     * precondition: the first move has been made by the player, which triggers the placeMines method to randomly place the specified number of mines on the board while ensuring that the first revealed cell and its adjacent cells are not mines, and also ensures that mines are not placed on top of each other
     * postcondition: the specified number of mines will be placed randomly on the board, while ensuring that the first revealed cell and its adjacent cells are not mines, and also ensuring that mines are not placed on top of each other, which allows the game to proceed with a valid board configuration for the player to interact with
     * 
     * @param numMines
     * @param firstX
     * @param firstY
     */
    public void placeMines(int numMines, int firstX, int firstY) {
        // Implementation for placing mines randomly on the board
        while (numMines > 0) {
            int x = (int) (Math.random() * board[0].length);
            int y = (int) (Math.random() * board.length);
            if (!(board[y][x] instanceof MineCell) && !(x == firstX && y == firstY) && !(x == firstX+1 && y == firstY) && !(x == firstX-1 && y == firstY) && !(x == firstX && y == firstY+1) && !(x == firstX && y == firstY-1) && !(x == firstX+1 && y == firstY+1) && !(x == firstX-1 && y == firstY-1) && !(x == firstX+1 && y == firstY-1) && !(x == firstX-1 && y == firstY+1)) {
                board[y][x] = new MineCell(x, y); // Place a mine at the random position
                numMines--;
            }
        }
    }


    /**
     * Implementation for calculating the numbers for each cell based on adjacent mines, which is essential for the gameplay as it provides the player with information about how many mines are adjacent to each cell, allowing them to make informed decisions about which cells to reveal or flag next, and also helps the player strategize their moves based on the information provided by the number cells
     * 
     * precondition: the mines have been placed on the board, and the calculateNumbers method will be called to iterate through each cell on the board and calculate the number of adjacent mines for non-mine cells, which will be used to determine the symbol to display on number cells and provide crucial information to the player for making informed decisions during gameplay
     * postcondition: the calculateNumbers method will update the board by setting non-mine cells to number cells with the count of adjacent mines, allowing the player to see how many mines are adjacent to each cell and strategize their moves accordingly, which is a fundamental aspect of the Minesweeper game and enhances the player's experience by providing them with valuable information to navigate the game board effectively
     */
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


    /**
     * Implementation for counting the number of adjacent mines around a given cell, which is used to determine the number to display on a number cell and also helps the player strategize their moves based on the information provided by the number cells
     * 
     * precondition: the cell at (x, y) is a non-mine cell, and the countAdjacentMines method will be called to count the number of adjacent mines around the cell at (x, y) by checking all eight surrounding cells (including diagonals) for the presence of mine cells
     * postcondition: the countAdjacentMines method will return the total count of adjacent mines around the cell at (x, y), which can be used to determine the number to display on a number cell and also helps the player strategize their moves based on the information provided by the number cells, allowing them to make informed decisions about which cells to reveal or flag next
     * 
     * @param x
     * @param y
     * @return
     */
    public int countAdjacentMines(int x, int y) {
        // Implementation for counting the number of adjacent mines around a given cell
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x + j >= 0 && x + j < board[0].length && y + i >= 0 && y + i < board.length && board[y + i][x + j] instanceof MineCell){
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * Implementation for revealing a cell on the game board, which also handles the logic for revealing adjacent cells if the revealed cell is an empty cell, and also checks for game over conditions if a mine cell is revealed
     * 
     * precondition: the player has made a move to reveal a cell at (x, y), which triggers the revealCell method to reveal the cell at (x, y) and also reveals adjacent cells if it's an empty cell, and checks for game over conditions if it's a mine cell
     * postcondition: the cell at (x, y) will be revealed, and if it's an empty cell, adjacent cells will also be revealed recursively until non-empty cells are reached; if it's a mine cell, all mines will be revealed and the game will be over with a game over message displayed to the player, and the player will not be able to make any more moves after this method is called
     * 
     * @param x
     * @param y
     */
    public void revealCell(int x, int y) {
        // Implementation for revealing a cell and its adjacent cells if it's an empty cell
            if (board[y][x].isFlagged()) {
                System.out.println("Cell is flagged, cannot reveal."); // If the cell is flagged, do not reveal it
            } else if (board[y][x] instanceof MineCell) {
                revealMines(); // If it's a mine cell, reveal all mines and end the game
            } else if (board[y][x].isRevealed()) {
                System.out.println("Cell is already revealed."); // If the cell is already revealed, do nothing
            } else if (board[y][x] instanceof EmptyCell) {
                floodFill(x, y); // If it's an empty cell, perform flood fill to reveal adjacent cells
            } else {
                board[y][x].reveal(); // Reveal the cell if it's not an empty cell
            }
        checkWin(); // Check if the player has won after revealing a cell
    }


    /**
     * Implementation for flagging a cell to mark it as a suspected mine, which allows the player to keep track of potential mine locations and helps them avoid accidentally revealing a mine, and also allows the player to unflag a cell if they change their mind about its status as a suspected mine
     * 
     * precondition: the player has made a move to flag a cell at (x, y), which triggers the flagCell method to toggle the flagged status of the cell at (x, y) and update the number of flags remaining accordingly, and also checks if the player has won the game after flagging a cell
     * postcondition: the cell at (x, y) will have its flagged status toggled (flagged if it was not flagged, and unflagged if it was flagged), the number of flags remaining will be updated accordingly (decreased by one when a cell is flagged, and increased by one when a cell is unflagged), and the checkWin method will be called to check if the player has won the game after flagging a cell, which may result in a win if all mines are correctly flagged and all non-mine cells are revealed
     * 
     * @param x
     * @param y
     */
    public void flagCell(int x, int y) {
        // Implementation for flagging a cell
        board[y][x].flag();
        if (board[y][x].isFlagged()) {
            numFlags--; // Decrease the number of flags remaining when a cell is flagged
        } else {
            numFlags++; // Increase the number of flags remaining when a cell is unflagged
        }
        checkWin(); // Check if the player has won after flagging a cell
    }


    /**
     * Implementation for converting the game board to a string representation
     * 
     * precondition: the game board has been initialized with cells, and the toString method will be called to generate a string representation of the current state of the game board, which includes the symbols for revealed cells, flagged cells, and hidden cells, as well as row and column labels for display purposes
     * postcondition: the toString method will return a string representation of the game board that accurately reflects the current state of the game, allowing the player to see which cells are revealed, flagged, or hidden, and providing a visual representation of the game board for the player to interact with
     * 
     * @return a string representing the game board
     */
    public String toString() {
        // Implementation for converting the game board to a string representation
        StringBuilder sb = new StringBuilder();
            sb.append("   "); // Add initial spacing for column numbers
        for (int i = 0; i < board[0].length; i++) {
            char letter = (char)('A' + i); // Convert column index to letter (A, B, C, etc.)
            String columnLabel = Character.toString(letter);
            sb.append(columnLabel + " "); // Add column letters for display
        }
        for (int i = 0; i < board.length; i++) {
            if (i < 9) {
                sb.append("\n " + (i + 1) + " "); // Add row numbers for display with extra space for single-digit numbers
            } else {
            sb.append("\n" + (i + 1) + " "); // Add row numbers for display
            }
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j].getDisplaySymbol()).append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * Implementation for flood fill algorithm to reveal adjacent empty cells when an empty cell is revealed
     * 
     * precondition: the cell at (x, y) is an empty cell that has just been revealed, and the flood fill algorithm will recursively reveal all adjacent empty cells and their adjacent number cells until it reaches cells that are not empty or are flagged
     * postcondition: all adjacent empty cells and their adjacent number cells will be revealed, but flagged cells will not be revealed, and the algorithm will stop when it reaches cells that are not empty or are flagged
     * 
     * @param x
     * @param y
     */
    public void floodFill(int x, int y) {
        // Implementation for flood fill algorithm to reveal adjacent empty cells
        if (x < 0 || x >= board[0].length || y < 0 || y >= board.length  || board[y][x].isRevealed() || board[y][x] instanceof MineCell) {
            return; // Base case: out of bounds or already revealed
        }
        if (!board[y][x].isFlagged()) {
            board[y][x].reveal(); // Reveal the current cell but not flagged cells
        }
        if (board[y][x] instanceof EmptyCell) {
            // Recursively flood fill adjacent cells if the current cell is empty
            floodFill(x + 1, y); // Right
            floodFill(x - 1, y); // Left
            floodFill(x, y + 1); // Down
            floodFill(x, y - 1); // Up
            floodFill(x + 1, y + 1); // Down-Right
            floodFill(x - 1, y - 1); // Up-Left
            floodFill(x + 1, y - 1); // Up-Right
            floodFill(x - 1, y + 1); // Down-Left
        }
    }


    /**
     * Implementation for revealing all mines on the board when a mine cell is revealed, which ends the game
     * 
     * precondition: the player has revealed a mine cell, which triggers the revealMines method to reveal all mines on the board and end the game
     * postcondition: all mines on the board will be revealed, and the game will be over with a game over message displayed to the player, and the player will not be able to make any more moves after this method is called
     */
    public void revealMines() {
        // Implementation for revealing all mines on the board
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (cell instanceof MineCell) {
                    cell.reveal();
                }
            }
        }
        gameOver = true; // Set the game over flag to true when a mine is revealed
        gameLost = true; // Set the game lost flag to true when a mine is revealed
    }

    public int minesRemaining() {
        return numFlags; // Return the number of flags remaining, which is equal to the number of mines not yet flagged
    }


    /**
     * Implementation for checking if the player has won the game by verifying that all mines are flagged and all non-mine cells are revealed
     * 
     * precondition: the player has made a move (either revealing a cell or flagging a cell), which triggers the checkWin method to check if the player has won the game by verifying that all mines are flagged and all non-mine cells are revealed
     * postcondition: if the player has won the game, a congratulatory message will be displayed, and the game will be over with the gameOver flag set to true, preventing any further moves; if the player has not won the game, the method will simply return without changing the game state, allowing the player to continue making moves
     * 
     */
    public void checkWin() {
        if (gameOver) {
            return; // If the game is already over, do not check for a win
        }
        boolean gameWon = true; // Assume the player has won until we find a condition that proves otherwise
        // Implementation for checking if the player has won the game
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (!(cell instanceof MineCell) && !cell.isRevealed()) {
                    gameWon = false; // If there is a non-mine cell that is not revealed, the player has not won
                }
            }
        }
            gameOver = gameWon; // Set the game over flag to true when the player wins
    }

}
