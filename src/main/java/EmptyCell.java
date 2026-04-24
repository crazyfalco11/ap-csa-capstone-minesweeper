public class EmptyCell extends Cell {

    public EmptyCell(int x, int y) {
        super(x, y);
        symbol = "⬛";
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        if(revealed) {
            return "revealed empty cell at position (" + x + ", " + y + ")";
        } else {
            return "hidden empty cell at position (" + x + ", " + y + ")";
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
        return x == ((EmptyCell) other).x && y == ((EmptyCell) other).y;
    }

}
