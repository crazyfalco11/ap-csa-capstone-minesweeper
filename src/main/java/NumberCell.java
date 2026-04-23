public class NumberCell extends Cell   {
   
    private int number;

    public NumberCell(int x, int y, int number) {
        super(x, y);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
