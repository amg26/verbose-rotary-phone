import java.util.ArrayList;

public class Simulation {
    private Map map;
    private ArrayList<Entity> entities;

    public Simulation() {
        map = new Map(100, 100);
        entities = new ArrayList<>();
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                Position pos = new Position(i*20, j*20);
                entities.add(new Zebra(pos, 5, true));
            }
        }
    }

    public void tick() {
        for(Entity e : entities) {
            //e.tick();
        }
    }

    public ArrayList<Entity> entitiesWithinRadius(double radius) {
        return null;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public Map getMap() {
        return map;
    }
}