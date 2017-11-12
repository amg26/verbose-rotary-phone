import java.util.ArrayList;

public class Giraffe extends Entity{
    public Giraffe(Map map, Position pos, double maxspeed, boolean gender){
        super(map, pos, 2.7, gender, 30, 2, 100, 100, 100, 2.7);
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
