import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    private Map map;
    private ArrayList<Entity> entities;
    private ArrayList<Animal> animals;
    private ArrayList<Food> food;

    public Simulation() {
        map = new Map(1000, 1000);

        entities = new ArrayList<>();
        animals = new ArrayList<>();
        food = new ArrayList<>();

        for(int i = 0; i < 1; i++) {
            Position pos = new Position((int) ((Math.random() * map.getMap()[0].length)), (int) ((Math.random() * map.getMap().length)));
            //System.out.println(pos);
            if(!map.isUnderwater(pos)) {
                //System.out.println(map.getElevation(pos) - Map.WATER_DEPTH);
                //Food pieceOfFood = new Food(map ,pos, 5, 0 );
                //entities.add(pieceOfFood);
                //food.add(pieceOfFood);
                Lion lion = new Lion(map, pos, 1, RNG.getInstance().nextBoolean());
                entities.add(lion);
                animals.add(lion);
            } else {
                i--;
            }
        }
        for(int i = 0; i < 1; i++) {
            Position pos = new Position((int) ((Math.random() * map.getMap()[0].length)), (int) ((Math.random() * map.getMap().length)));
            //System.out.println(pos);
            if(!map.isUnderwater(pos)) {
                //System.out.println(map.getElevation(pos) - Map.WATER_DEPTH);
                //Food pieceOfFood = new Food(map ,pos, 5, 0 );
                //entities.add(pieceOfFood);
                //food.add(pieceOfFood);
                Zebra zebra = new Zebra(map, pos, 3.3, RNG.getInstance().nextBoolean());
                entities.add(zebra);
                animals.add(zebra);
            } else {
                i--;
            }
        }
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                Todd todd = new Todd(map, new Position(i*16, j*16), 0);
                entities.add(todd);
                animals.add(todd);
            }
        }
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                Giraffe giraffe = new Giraffe(map, new Position(i*14+50, j*14+50), 2.5, RNG.getInstance().nextBoolean());
                entities.add(giraffe);
                animals.add(giraffe);
            }
        }

        /*
        for(int i=0; i<2; i++){
            Position pos = new Position(i*30+100, i*30+100);
            entities.add(new Zebra(map, pos, 5, false));
        }
        */
    }

    public void generateAnimal(int type, Position pos){

        Giraffe g = new Giraffe(map, pos, 0, RNG.getInstance().nextBoolean());
        entities.add(g);
        animals.add(g);
        /*
        switch(type){
            case 0:
                entities.add(new Zebra(map, pos,2, rand.nextBoolean()));
                break;
            case 1:
                entities.add(new Lion(map, pos, 2, rand.nextBoolean()));
                break;
            case 2:
                entities.add(new Giraffe(map, pos, 2, rand.nextBoolean()));
                break;
            case 3:
                entities.add(new Todd(map, pos, 2));
                break;
            default:
                entities.add(new Zebra(map, pos, 2, rand.nextBoolean()));

        }
        */
    }

    public void tick() {

        for(Entity e : entities) {
            e.tick(entitiesWithinRadius(e));
            //e.tick(entities);
        }
        /*
        for (Entity e : entities){
            if( e.pregnant){
                Position baby = new Position(e.getPosition().getX(), e.getPosition().getY());
                if(e.getClass() == Zebra.class){
                    entities.add(new Zebra( map, baby, e.maxspeed, rand.nextBoolean()));
                    e.pregnant = false;
                }
            }
        }
        */

        for (int i = food.size()-1; i > 0; i --){
            if (food.get(i).getSize() <= 1){
                food.remove(i);
                entities.remove(food.get(i));
            }
        }
        for (int i = animals.size()-1; i > 0; i --){
            //System.out.println(entities.get(i).health);
            if( animals.get(i).isDead() ){
                animals.remove(i);
                //hmmm
                entities.remove(animals.get(i));
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