public class Cell {

    String flag = "🚩";
    String hidden = "⬜";
    String symbol;
    int x;
    int y;
    boolean revealed = false;

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
        symbol = flag;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDisplaySymbol() {
        if (revealed) {
            getSymbol(); // Return the actual symbol (number or mine) if revealed
        } else {
            return symbol;
        }
    }

    public boolean isMine() {
        return false; // Default implementation, overridden in MineCell
    }

    public int getNumber() {
        return 0; // Default implementation, overridden in NumberCell
    }



}
