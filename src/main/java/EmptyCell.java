public class EmptyCell extends Cell {
  
    String symbol = "⬛";

    public EmptyCell(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isMine() {
        return false;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

}
