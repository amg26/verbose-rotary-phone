import javafx.geometry.Pos;

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
                Position pos = new Position(i*20+50, j*20+50);
                entities.add(new Todd(pos, 5));
            }
        }
        for(int i=0; i<5; i++){
            Position pos = new Position(i*30, i*30);
            entities.add(new Zebra(pos, 10, false));
        }
    }

    public void tick() {

        for(Entity e : entities) {
            e.tick(entitiesWithinRadius(e,));
            //e.tick(entities);
        }
    }

    public ArrayList<Entity> entitiesWithinRadius(Entity ent) {
        ArrayList<Entity> closeEntities = new ArrayList<>();

        for(Entity other : entities) {
            if(other.getPosition().distanceTo(ent.getPosition()) <= ent.getSightRadius()) {
                closeEntities.add(other);
            }
        }
        closeEntities.remove(ent);
        return closeEntities;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public Map getMap() {
        return map;
    }
}