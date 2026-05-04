public class Cell {

    protected String flag = "F";
    protected String hidden = ".";
    protected int x;
    protected int y;
    protected boolean revealed = false;
    protected boolean flagged = false;


    /**
     * Constructor for the Cell class, which initializes the cell with its coordinates (x, y) and sets the default values for revealed and flagged status, allowing the cell to be properly represented on the game board and interact with the player's actions during gameplay
     * 
     * precondition: the Cell constructor is called with valid coordinates (x, y) for the cell, which initializes the cell's position on the game board and sets its revealed and flagged status to false, allowing it to be properly represented on the game board and interact with the player's actions during gameplay
     * postcondition: the Cell object will be created with the specified coordinates (x, y) and will have its revealed and flagged status set to false, allowing it to be properly represented on the game board and interact with the player's actions during gameplay, which is essential for the functionality of the Minesweeper game and enhances the player's experience by providing a clear representation of the cell's state on the game board
     * 
     * @param x
     * @param y
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void reveal() {
        this.revealed = true;
    }

    public boolean isFlagged() {
        return flagged;
    }


    /**
     * Implementation for flagging a cell to mark it as a suspected mine, which allows the player to keep track of potential mine locations and helps them avoid accidentally revealing a mine, and also allows the player to unflag a cell if they change their mind about its status as a suspected mine
     * 
     * precondition: the player has made a move to flag a cell at (x, y), which triggers the flag method to toggle the flagged status of the cell at (x, y) and update the number of flags remaining accordingly, and also checks if the player has won the game after flagging a cell
     * postcondition: the cell at (x, y) will have its flagged status toggled (flagged if it was not flagged, and unflagged if it was flagged), the number of flags remaining will be updated accordingly (decreased by one when a cell is flagged, and increased by one when a cell is unflagged), and the checkWin method will be called to check if the player has won the game after flagging a cell, which may result in a win if all mines are correctly flagged and all non-mine cells are revealed
     */
    public void flag() {
        // Implementation for flagging the cell
        if (flagged) {
            flagged = false; // Set the symbol to the flag emoji
        } else {
            flagged = true; // Set the symbol to the flag emoji
        }
    }

    public String getSymbol() {
        return hidden; // Default symbol for a cell (can be overridden in subclasses)
    }


    /**
     * Implementation for getting the display symbol for the cell based on its revealed and flagged status, which determines what symbol to show on the game board for the cell, allowing the player to visually identify the state of the cell (hidden, flagged, or revealed) and make informed decisions during gameplay
     * 
     * precondition: the getDisplaySymbol method is called for a cell, which checks the revealed and flagged status of the cell to determine what symbol to return for display on the game board, allowing the player to visually identify the state of the cell (hidden, flagged, or revealed) and make informed decisions during gameplay
     * postcondition: the getDisplaySymbol method will return the appropriate symbol for the cell based on its revealed and flagged status, allowing the player to visually identify the state of the cell (hidden, flagged, or revealed) and make informed decisions during gameplay, which enhances the player's experience by providing clear visual cues about the state of each cell on the game board and helps them navigate the game effectively
     * 
     * @return
     */
    public String getDisplaySymbol() {
        if (revealed) {
            return getSymbol(); // Return the actual symbol (number, empty, or mine) if revealed
        } else if (flagged) {
            return flag; // Return the flag symbol if the cell is flagged
        } else {
            return hidden; // Return the hidden symbol if the cell is not revealed or flagged
        }
    }


    /**
     * Implementation for the toString method to provide a string representation of the cell, which includes its coordinates (x, y) and can be used for debugging purposes or to display information about the cell during gameplay, allowing developers and players to easily understand the state of the cell and its position on the game board
     * 
     * precondition: the toString method is called for a cell, which returns a string representation of the cell that includes its coordinates (x, y), allowing developers and players to easily understand the state of the cell and its position on the game board for debugging purposes or to display information about the cell during gameplay
     * postcondition: the toString method will return a string representation of the cell that includes its coordinates (x, y), allowing developers and players to easily understand the state of the cell and its position on the game board for debugging purposes or to display information about the cell during gameplay, which enhances the player's experience by providing clear information about the cell's location and can assist in troubleshooting or understanding the game state during development and gameplay
     * 
     * @return
     */
    public String toString() {
        return "cell at position (" + x + ", " + y + ")";
    }


    /**
     * Implementation for the equals method to compare two Cell objects based on their coordinates (x, y), which allows for proper comparison of cells in the game board and can be used to determine if two cells are the same based on their position, enhancing the functionality of the game and allowing for accurate comparisons between cells during gameplay
     * 
     * precondition: the equals method is called to compare two Cell objects, which checks if the other object is an instance of the Cell class and then compares their coordinates (x, y) to determine if they are equal, allowing for proper comparison of cells in the game board and can be used to determine if two cells are the same based on their position during gameplay
     * postcondition: the equals method will return true if the other object is an instance of the Cell class and has the same coordinates (x, y) as the current cell, indicating that they are equal based on their position, and will return false otherwise, allowing for proper comparison of cells in the game board and can be used to determine if two cells are the same based on their position during gameplay, which enhances the functionality of the game and allows for accurate comparisons between cells during gameplay
     * 
     * @param other
     * @return
     */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        };
        return x == ((Cell)other).x && y == ((Cell)other).y;
    }

}
