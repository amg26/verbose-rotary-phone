import java.util.ArrayList;

public class Simulation {
    private Map map;
    private ArrayList<Entity> entities;

    public Simulation() {
        map = new Map(100, 100);
        entities = new ArrayList<>();

        //TEMPORARY
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                Position pos = new Position(i*20, j*20);
                entities.add(new Todd(pos, 5));
            }
        }
    }

    public void tick() {

        for(Entity e : entities) {
            //e.tick(entitiesWithinRadius(e, e.getSightRadius()));
            e.tick();
        }
    }

    public ArrayList<Entity> entitiesWithinRadius(Entity ent, double radius) {
        ArrayList<Entity> closeEntities = new ArrayList<>();

        for(Entity other : entities) {
            if(other.getPosition().distanceTo(ent.getPosition()) <= radius) {
                closeEntities.add(other);
            }
        }

        return closeEntities;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public Map getMap() {
        return map;
    }
}