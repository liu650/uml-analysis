public class Position {
    public int x;
    public int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj){
        Position objPosition = (Position) obj;
        return objPosition.x == this.x && objPosition .y == this.y;
    }
}
