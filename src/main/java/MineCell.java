public class MineCell extends Cell {
   
    public MineCell(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isMine() {
        return true;
    }

}
