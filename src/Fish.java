import java.util.ArrayList;

public class Fish extends Entity {
    public Fish (Map map, Position pos, int maxspeed, boolean gender) {
        super(map, pos, maxspeed, gender, 5, 2, 100, 1000, 1000, maxspeed);
    }

    @Override
    public void tick(ArrayList<Entity> closeEntities) {
        super.tick(closeEntities);
    }
}
