public class NumberCell extends Cell   {
   
    private int number;
    String symbol;

    public NumberCell(int x, int y, int number) {
        super(x, y);
        this.number = number;
        this.symbol = Integer.toString(number);
    }

    @Override
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

}
