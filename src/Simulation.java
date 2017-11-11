import java.util.ArrayList;

public class Simulation {
    private Map map;
    private ArrayList<Entity> entities;

    public Simulation() {
        map = new Map(100, 100);
        entities = new ArrayList<>();
    }

    public void tick() {
        for(Entity e : entities) {
            //e.tick();
        }
    }

    public ArrayList<Entity> entitiesWithinRadius() {
        return null;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public Map getMap() {
        return map;
    }
}