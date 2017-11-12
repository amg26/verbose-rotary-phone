import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    private Map map;
    private ArrayList<Entity> entities;
    private Random rand;
    private ArrayList<Food> food;

    public Simulation() {
        map = new Map(300, 300);
        entities = new ArrayList<>();
        rand = new Random();
        food = new ArrayList<Food>();
        for(int i = 0; i < 100; i++) {
            Position pos = new Position((int) ((Math.random() * map.getMap()[0].length)), (int) ((Math.random() * map.getMap().length)));
            System.out.println(pos);
            if(!map.isUnderwater(pos)) {
                System.out.println(map.getElevation(pos) - Map.WATER_DEPTH);
                Food pieceOfFood = new Food(map ,pos, 5, 0 );
                entities.add(pieceOfFood);
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
        for (Entity e : entities){
            if( e.pregnant){
                Position baby = new Position(e.getPosition().getX(), e.getPosition().getY());
                if(e.getClass() == Zebra.class){
                    entities.add(new Zebra( map, baby, e.maxspeed, rand.nextBoolean()));
                    e.pregnant = false;
                }
            }
        }

        for (int i = 0; i < food.size(); i ++){
            if (food.get(i).getSize() == 0){
                food.remove(food.get(i));
            }
        }
        for (int i = 0; i < entities.size(); i ++){
            if( entities.get(i).health == 0){
                entities.remove(i);
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