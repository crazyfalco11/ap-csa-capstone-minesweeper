public class MineCell extends Cell {
   
    String symbol = "💣";

    public MineCell(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isMine() {
        return true;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
    
}
