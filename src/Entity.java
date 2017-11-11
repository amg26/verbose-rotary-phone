import java.util.ArrayList;

public class Entity {
    protected Position pos;
    protected int speed;
    protected int needs;
    protected boolean gender;
    protected int sightradius;
    public Entity(){

    }
    public Entity(Position pos, int speed , boolean gender, int sightradius) {
        this.pos = pos;
        this.speed = speed;
        this.gender = gender;
        this.sightradius = sightradius;
    }
    public void consume(){

    }
    public void reproduce(){

    }
    //should return position vv
    public void planMove(){

    }
    public void move(){

    }
    public void move(Position target){

    }
    //replace void with ArrayList<Entity> vv
    public void see(int sightradius){

    }
}
