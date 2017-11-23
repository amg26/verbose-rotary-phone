import java.util.ArrayList;

public class Todd extends Animal{
    static int defaultRank = 30;
    static double defaultSightRadius = 30;
    static double defaultHealth = 100;
    static double defaultHunger = 100;
    static double defaultThirst = 100;
    static double defaultMaxSpeed = 3.5;

    static final boolean toddsGender = false;


    public Todd(Map map, Position pos, double direction){
        super(map, pos, direction, toddsGender);
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
