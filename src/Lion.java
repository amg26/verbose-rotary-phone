import java.util.ArrayList;

public class Lion extends Animal{
    static int defaultRank = 50;
    static double defaultSightRadius = 120;
    static double defaultHealth = 100;
    static double defaultHunger = 100;
    static double defaultThirst = 100;
    static double defaultMaxSpeed = 6.7;


    public Lion(Map map, Position pos, double direction, boolean gender){
        super(map, pos, direction, gender);
        hunger = maxHunger = defaultHunger;
        thirst = maxThirst = defaultThirst;
        sightRadius = defaultSightRadius;
        rank = defaultRank;
        maxSpeed = defaultMaxSpeed;
        speed = maxSpeed;
        health = defaultHealth;

        enemyFear = -500;
        foodTastiness = 500;
        preyTastiness = 1000;
        kinFriendliness = 5;
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
