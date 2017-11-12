import javafx.geometry.Pos;

import java.util.ArrayList;

public class Simulation {
    private Map map;
    private ArrayList<Entity> entities;

    public Simulation() {
        map = new Map(1000, 1000);
        entities = new ArrayList<>();

        for(int i = 0; i < 10000; i++) {
            Position pos = new Position((int) ((Math.random() * map.getMap()[0].length)), (int) ((Math.random() * map.getMap().length)));
            if(!map.isUnderwater(pos)) {
                System.out.println(map.getElevation(pos) - Map.WATER_DEPTH);
                entities.add(new Todd(map, pos, 5));
            } else {
                i--;
            }
        }

        for(int i=0; i<5; i++){
            Position pos = new Position(i*30, i*30);
            entities.add(new Zebra(map, pos, 10, false));
        }
    }

    public void tick() {

        for(Entity e : entities) {
            e.tick(entitiesWithinRadius(e));
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