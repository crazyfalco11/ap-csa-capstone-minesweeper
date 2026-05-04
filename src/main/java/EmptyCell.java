public class EmptyCell extends Cell {

    private String symbol;

    @Override
    public String getSymbol() {
        return symbol;
    }


    /**
     * Constructor for the EmptyCell class, which initializes the empty cell with its coordinates (x, y) and sets the symbol for an empty cell to a space, allowing it to be properly represented on the game board as an empty cell and interact with the player's actions during gameplay
     * 
     * precondition: the EmptyCell constructor is called with valid coordinates (x, y) for the empty cell, which initializes the empty cell's position on the game board and sets its symbol to a space, allowing it to be properly represented on the game board as an empty cell and interact with the player's actions during gameplay
     * postcondition: the EmptyCell object will be created with the specified coordinates (x, y) and will have its symbol set to a space, allowing it to be properly represented on the game board as an empty cell and interact with the player's actions during gameplay, which is essential for the functionality of the Minesweeper game and enhances the player's experience by providing a clear representation of empty cells on the game board
     * 
     * @param x
     * @param y
     */
    public EmptyCell(int x, int y) {
        super(x, y);
        symbol = " "; // Set the symbol for an empty cell to a space
    }


    /**
     * Implementation for the toString method to provide a string representation of the empty cell, which includes its coordinates (x, y) and whether it is revealed or hidden, allowing developers and players to easily understand the state of the empty cell and its position on the game board for debugging purposes or to display information about the cell during gameplay
     * 
     * precondition: the toString method is called for an empty cell, which returns a string representation of the empty cell that includes its coordinates (x, y) and whether it is revealed or hidden, allowing developers and players to easily understand the state of the empty cell and its position on the game board for debugging purposes or to display information about the cell during gameplay
     * postcondition: the toString method will return a string representation of the empty cell that includes its coordinates (x, y) and whether it is revealed or hidden, allowing developers and players to easily understand the state of the empty cell and its position on the game board for debugging purposes or to display information about the cell during gameplay, which enhances the player's experience by providing clear information about the empty cell's location and state on the game board and can assist in troubleshooting or understanding the game state during development and gameplay
     * 
     * @return
     */
    @Override
    public String toString() {
        if(revealed) {
            return "revealed empty cell at position (" + x + ", " + y + ")";
        } else {
            return "hidden empty cell at position (" + x + ", " + y + ")";
        }
    }


    /**
     * Implementation for the equals method to compare two EmptyCell objects based on their coordinates (x, y), which allows for proper comparison of empty cells in the game board and can be used to determine if two empty cells are the same based on their position, enhancing the functionality of the game and allowing for accurate comparisons between empty cells during gameplay
     * 
     * precondition: the equals method is called to compare two EmptyCell objects, which checks if the other object is an instance of the EmptyCell class and then compares their coordinates (x, y) to determine if they are equal, allowing for proper comparison of empty cells in the game board and can be used to determine if two empty cells are the same based on their position during gameplay
     * postcondition: the equals method will return true if the other object is an instance of the EmptyCell class and has the same coordinates (x, y) as the current empty cell, indicating that they are equal based on their position, and will return false otherwise, allowing for proper comparison of empty cells in the game board and can be used to determine if two empty cells are the same based on their position during gameplay, which enhances the functionality of the game and allows for accurate comparisons between empty cells during gameplay
     * 
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        return x == ((EmptyCell) other).x && y == ((EmptyCell) other).y;
    }

}
