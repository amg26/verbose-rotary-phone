import java.util.ArrayList;

public class Giraffe extends Animal{
    static int defaultRank = 79;
    static double defaultSightRadius = 30;
    static double defaultHealth = 100;
    static double defaultHunger = 100;
    static double defaultThirst = 100;
    static double defaultMaxSpeed = 7.2;

    public Giraffe(Map map, Position pos, double direction, boolean gender){
        super(map, pos, direction, gender);
        hunger = maxHunger = defaultHunger;
        thirst = maxThirst = defaultThirst;
        sightRadius = defaultSightRadius;
        rank = defaultRank;
        maxSpeed = defaultMaxSpeed;
        speed = maxSpeed;
        health = defaultHealth;

        enemyFear = -1000;
        foodTastiness = 7500;
        preyTastiness = 0;
        kinFriendliness = 500;
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
