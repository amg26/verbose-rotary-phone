import java.util.ArrayList;

public class Zebra extends Entity{
    public Zebra(Position pos, int speed, boolean gender){

    }
    public ArrayList<Entity> see(int sightradius){
        entitiesWithinRadius(sightradius);
    }

}
