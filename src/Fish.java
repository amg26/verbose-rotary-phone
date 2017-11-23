import java.util.ArrayList;

public class Fish extends Animal{
    static int defaultRank = 5;
    static double defaultSightRadius = 30;
    static double defaultHealth = 100;
    static double defaultHunger = 100;
    static double defaultThirst = 100;
    static double defaultMaxSpeed = 7.2;

    //OLDENTITY values by Anh:
    //5, 2, 100, 1000, 1000


    public Fish(Map map, Position pos, double direction, boolean gender){
        super(map, pos, direction, gender);
        //these should probably be in the constructor, i guess that's the point
        hunger = maxHunger = defaultHunger;
        thirst = maxThirst = defaultThirst;
        sightRadius = defaultSightRadius;
        rank = defaultRank;
        maxSpeed = defaultMaxSpeed;
        speed = maxSpeed;
        health = defaultHealth;


    }
    public ArrayList<Entity> see(int sightradius){
        // entitiesWithinRadius(sightradius);
        return null;
    }
    public void tick(ArrayList<Entity> closeEntities){
        super.tick(closeEntities);
        //System.out.println("hunger:"+hunger);
        //System.out.println("closeEntities: "+closeEntities);

    }
    public void dance(double vigour){

    }
    public void reproduce(Entity e){

    }
}
