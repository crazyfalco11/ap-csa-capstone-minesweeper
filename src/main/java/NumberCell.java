public class NumberCell extends Cell   {

    private String symbol;

    @Override
    public String getSymbol() {
        return symbol;
    }


    /**
     * Constructor for the NumberCell class, which initializes the number cell with its coordinates (x, y) and the number of adjacent mines, allowing it to be properly represented on the game board as a number cell and interact with the player's actions during gameplay
     * 
     * precondition: the NumberCell constructor is called with valid coordinates (x, y) for the number cell and a valid number of adjacent mines, which initializes the number cell's position on the game board and sets its symbol to the string representation of the number of adjacent mines, allowing it to be properly represented on the game board as a number cell and interact with the player's actions during gameplay
     * postcondition: the NumberCell object will be created with the specified coordinates (x, y) and will have its symbol set to the string representation of the number of adjacent mines, allowing it to be properly represented on the game board as a number cell and interact with the player's actions during gameplay, which is essential for the functionality of the Minesweeper game and enhances the player's experience by providing a clear representation of number cells on the game board and allowing players to make informed decisions based on the number of adjacent mines during gameplay
     * 
     * @param x
     * @param y
     * @param number
     */
    public NumberCell(int x, int y, int number) {
        super(x, y);
        this.symbol = Integer.toString(number);
    }


    /**
     * Returns a string representation of the number cell, indicating whether it is revealed or hidden
     * 
     * precondition: the toString method is called for a number cell, which returns a string representation of the number cell that includes its coordinates (x, y) and whether it is revealed or hidden, allowing developers and players to easily understand the state of the number cell and its position on the game board for debugging purposes or to display information about the cell during gameplay
     * postcondition: the toString method will return a string representation of the number cell that includes its coordinates (x, y) and whether it is revealed or hidden, allowing developers and players to easily understand the state of the number cell and its position on the game board for debugging purposes or to display information about the cell during gameplay, which enhances the player's experience by providing clear information about the number cell's location and state on the game board and can assist in troubleshooting or understanding the game state during development and gameplay
     * 
     * @return a string representation of the number cell
     */
    @Override
    public String toString() {
        if(revealed) {
            return "revealed number cell at position (" + x + ", " + y + ")";
        } else {
            return "hidden number cell at position (" + x + ", " + y + ")";
        }
    }


    /**
     * Checks if this number cell is equal to another object
     * 
     * precondition: the equals method is called with a valid object to compare with, which checks if the object is an instance of NumberCell and has the same coordinates (x, y) as this number cell, allowing developers to determine if two number cells are the same based on their position on the game board
     * postcondition: the equals method will return true if the objects are equal (same coordinates), false otherwise, allowing developers to compare number cells based on their position on the game board and ensuring accurate comparisons during gameplay, which enhances the functionality of the game and allows for proper handling of number cells during gameplay
     * 
     * @param other the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        return x == ((NumberCell) other).x && y == ((NumberCell) other).y;
    }

}
