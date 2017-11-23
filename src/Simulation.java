import javafx.geometry.Pos;

import java.awt.image.RGBImageFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Simulation {
    private Map map;
    private ArrayList<Entity> entities;
    private ArrayList<Animal> animals;
    private ArrayList<Food> food;
    int counter = 0;

    public Simulation() {
        map = new Map(1000, 1000);

        entities = new ArrayList<>();
        animals = new ArrayList<>();
        food = new ArrayList<>();


        /*
        for(int i = 0; i < 10; i++) {
            Position pos = new Position((int) ((Math.random() * map.getMap()[0].length)), (int) ((Math.random() * map.getMap().length)));
            //System.out.println(pos);
            if(!map.isUnderwater(pos)) {
                //System.out.println(map.getElevation(pos) - Map.WATER_DEPTH);
                Food pieceOfFood = new Food(map ,new Position(pos), 5, 0 );
                entities.add(pieceOfFood);
                food.add(pieceOfFood);
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

*/



        /*
        for(int i=0; i<2; i++){
            Position pos = new Position(i*30+100, i*30+100);
            entities.add(new Zebra(map, pos, 5, false));
        }
        */
    }

    public void generateAnimal(int type, Position pos){

        switch(type){
            case 0:
                Zebra z = new Zebra(map, pos, RNG.getInstance().nextAngle(), RNG.getInstance().nextBoolean());
                entities.add(z);
                animals.add(z);
                break;
            case 1:
                Lion l = new Lion(map, pos, RNG.getInstance().nextAngle(), RNG.getInstance().nextBoolean());
                entities.add(l);
                animals.add(l);
                break;
            case 2:
                Giraffe g = new Giraffe(map, pos, RNG.getInstance().nextAngle(), RNG.getInstance().nextBoolean());
                entities.add(g);
                animals.add(g);
                break;
            case 3:
                Todd t = new Todd(map, pos, RNG.getInstance().nextAngle());
                entities.add(t);
                animals.add(t);
                break;
            default:
                Zebra defz = new Zebra(map, pos, RNG.getInstance().nextAngle(), RNG.getInstance().nextBoolean());
                entities.add(defz);
                animals.add(defz);

        }

    }

    public void generateFood(Position pos, int amount){
        Food f = new Food(map, pos, amount, 0);
        entities.add(f);
        food.add(f);
    }

    public void tick() {
        counter++;
        if(counter%100==0)
            System.out.println("TICK "+counter);

        for(Entity e : entities) {
            e.tick(entitiesWithinRadius(e));
            //e.tick(entities);
        }


        ArrayList<Animal> freshBabies = new ArrayList<>();

        for (Animal a : animals){
            if( a.pregnant){
                Position babyPos = new Position(a.getX(), a.getY());
                if(a.getClass() == Zebra.class){
                    Zebra baby = new Zebra( map, babyPos, RNG.getInstance().nextAngle(), RNG.getInstance().nextBoolean());
                    freshBabies.add(baby);
                    a.pregnant = false;
                }
                if(a.getClass() == Giraffe.class){
                    Giraffe baby = new Giraffe( map, babyPos, RNG.getInstance().nextAngle(), RNG.getInstance().nextBoolean());
                    freshBabies.add(baby);
                    a.pregnant = false;
                }
                if(a.getClass() == Lion.class){
                    Lion baby = new Lion( map, babyPos, RNG.getInstance().nextAngle(), RNG.getInstance().nextBoolean());
                    freshBabies.add(baby);
                    a.pregnant = false;
                }
                if(a.getClass() == Todd.class){
                    Todd baby = new Todd( map, babyPos, RNG.getInstance().nextAngle());
                    freshBabies.add(baby);
                    a.pregnant = false;
                }
            }
        }
        entities.addAll(freshBabies);
        animals.addAll(freshBabies);



        for (int i = food.size()-1; i >= 0; i --){
            if (food.get(i).getSize() <= 1){
                entities.remove(food.get(i));
                food.remove(i);
            }
        }
        for (int i = animals.size()-1; i >= 0; i --){
            //System.out.println(entities.get(i).health);
            if( animals.get(i).isDead() ){
                entities.remove(animals.get(i));
                animals.remove(i);
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

    //TEMPORARY
    public Entity getZebra(){
        return animals.get(0);
    }

    public Map getMap() {
        return map;
    }
}