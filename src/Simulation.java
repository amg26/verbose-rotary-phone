import java.util.ArrayList;

public class Simulation {
    private Map map;
    private ArrayList<Entity> entities;

    public Simulation() {
        map = new Map(100, 100);
        entities = new ArrayList<>();
    }

    public void nextTick() {
        for(Entity e : entities) {
            //e.move();
        }
    }

    public ArrayList<Entity> entitiesWithinRadius(double radius) {
        return null;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}