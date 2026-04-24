public class NumberCell extends Cell   {

    public NumberCell(int x, int y, int number) {
        super(x, y);
        this.symbol = Integer.toString(number);
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        if(revealed) {
            return "revealed number cell at position (" + x + ", " + y + ")";
        } else {
            return "hidden number cell at position (" + x + ", " + y + ")";
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
        return x == ((NumberCell) other).x && y == ((NumberCell) other).y;
    }

}
