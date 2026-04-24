public class MineCell extends Cell {

    public MineCell(int x, int y) {
        super(x, y);
        symbol = "💣";
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        if(revealed) {
            return "revealed mine cell at position (" + x + ", " + y + ")";
        } else {
            return "hidden mine cell at position (" + x + ", " + y + ")";
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        return x == ((MineCell) other).x && y == ((MineCell) other).y;
    }
    
}
