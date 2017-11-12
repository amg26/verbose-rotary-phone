import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    private Map map;
    private ArrayList<Entity> entities;
    private Random rand;

    public Simulation() {
        map = new Map(1000, 1000);
        entities = new ArrayList<>();
        rand = new Random();

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
            e.tick(entitiesWithinRadius(e));
            //e.tick(entities);
        }
        for (Entity e : entities){
            if( e.pregnant){
                Position baby = new Position(e.getPosition().getX(), e.getPosition().getY());
                if(e.getClass() == Zebra.class){
                    Zebra babyzebra = new Zebra(baby, e.maxspeed, rand.nextBoolean());
                }
                else{
                    return;
                }
            }
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