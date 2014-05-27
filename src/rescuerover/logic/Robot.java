package rescuerover.logic;

public class Robot extends MapObject implements Movable {

    private boolean alive;

    public Robot(int x, int y, int direction) {
        super(x, y, direction);
        alive = true;
    }

    @Override
    public boolean move(int direction) {
        return false;
    }
}