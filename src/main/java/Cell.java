public class Cell {

    protected String flag = "F";
    protected String hidden = ".";
    protected int x;
    protected int y;
    protected boolean revealed = false;
    protected boolean flagged = false;

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

    public String getDisplaySymbol() {
        if (revealed) {
            return getSymbol(); // Return the actual symbol (number, empty, or mine) if revealed
        } else if (flagged) {
            return flag; // Return the flag symbol if the cell is flagged
        } else {
            return hidden; // Return the hidden symbol if the cell is not revealed or flagged
        }
    }

    public String toString() {
        return "cell at position (" + x + ", " + y + ")";
    }

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
