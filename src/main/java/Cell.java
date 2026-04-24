public class Cell {

    String flag = "🚩";
    String hidden = "⬜";
    String symbol;
    int x;
    int y;
    boolean revealed = false;
    boolean isMine;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        symbol = hidden;
    }

    public void reveal() {
        this.revealed = true;
    }

    public void flag() {
        // Implementation for flagging the cell
        if (symbol.equals(hidden)) {
            symbol = flag; // Set the symbol to the flag emoji
        } else if (symbol.equals(flag)) {
            symbol = hidden; // Unflag the cell
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDisplaySymbol() {
        if (revealed) {
            return getSymbol(); // Return the actual symbol (number or mine) if revealed
        } else {
            return symbol;
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
