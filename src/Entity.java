import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class Entity {
    protected Position pos;
    protected int speed;
    protected int thirst;
    protected int hunger;
    protected boolean gender;
    protected int sightradius;
    protected int rank;
    protected int health;
    protected int maxhealth;
    protected int maxspeed;
    public Entity(){

    }
    public Entity(Position pos, int maxspeed , boolean gender, int sightradius, int rank, int health, int thirst, int hunger, int speed) {
        this.pos = pos;
        this.maxspeed = maxspeed;
        this.gender = gender;
        this.sightradius = sightradius;
        this.rank = rank;
        this.speed = speed;
    }
    //based on needs + want to reproduce + not die
    public void tick() {
        if (hunger == 0 && thirst == 0){
            //sit nerd u ded
            health = 0;
            return;
        }
        if (hunger == 0) {
            speed = (int) (0.5 * maxspeed);
            if (health < (int) (0.2 * maxhealth)) {
                health = 0;
            }
            health = (int) (0.8 * health);
        }
        if (thirst == 0) {
            speed = (int) (0.5 * maxspeed);
            if (health < (int) (0.2 * maxhealth)) {
                health = 0;
            }
            health = (int) (0.5 * health);
        }
        ArrayList<Entity> kin = new ArrayList<>();
        ArrayList<Entity> danger = new ArrayList<>();
        ArrayList<Entity> food = new ArrayList<>();
        for (int i = 0; i < scan(sightradius).size(); i++) {
            if (scan(sightradius).get(i).getRank() < rank) {
                food.add(scan(sightradius).get(i));
            }
            if (scan(sightradius).get(i).getRank() > rank) {
                danger.add(scan(sightradius).get(i));
            }
            if (scan(sightradius).get(i).getRank() == rank) {
                kin.add(scan(sightradius).get(i));
            }
        }
        if(hunger > 70 && thirst > 70 && danger.size() == 0){
            double min = sightradius;
            Position locclosestmate;
            for(int i = 0; i < kin.size(); i ++){
                if ((sqrt((kin.get(i).pos.getX()*kin.get(i).pos.getX())+(kin.get(i).pos.getY()*kin.get(i).pos.getY())))< min && this.gender != kin.get(i).getGender()){
                    locclosestmate = kin.get(i).getPosition();
                    min = (sqrt((kin.get(i).pos.getX()*kin.get(i).pos.getX())+(kin.get(i).pos.getY()*kin.get(i).pos.getY())));
                    if (min < 1){
                        this.reproduce(kin.get(i));
                        return;
                    }
                    if (min > 1){
                        //moves towards it
                    }
                }
            }

        }
        if( danger.size() != 0 ){
            /**
             * runs from closest threat for now, kind of stupid
             * TODO: calculate best escape route, stop to consume if not at maxspeed
             */
            double min = sightradius;
            Position locclosestthreat = null;
            for(int i = 0; i < danger.size(); i ++){
                if ((sqrt((danger.get(i).pos.getX()*danger.get(i).pos.getX())+(danger.get(i).pos.getY()*danger.get(i).pos.getY())))< min){
                    locclosestthreat = danger.get(i).getPosition();
                    min = (sqrt((danger.get(i).pos.getX()*danger.get(i).pos.getX())+(danger.get(i).pos.getY()*danger.get(i).pos.getY())));
                }
            }
            double x = (pos.getX() - locclosestthreat.getX())/(sqrt((locclosestthreat.getX()*locclosestthreat.getX())+(locclosestthreat.getY()*locclosestthreat.getY())))*speed;
            double y = (pos.getY() - locclosestthreat.getY())/(sqrt((locclosestthreat.getX()*locclosestthreat.getX())+(locclosestthreat.getY()*locclosestthreat.getY())))*speed;
            Position escape = new Position(x+pos.getX(), y+pos.getY());
            moveTo(escape);
            return;
        }
    }
    public void consume(){

    }
    public void reproduce(Entity e){

    }
    //should return position vv
    public void planMove(ArrayList<Entity> closeentities){

    }
    public void move(Position target){

    }
    public void moveTo(Position target){

    }
    //replace void with ArrayList<Entity> vv
    public ArrayList<Entity> scan(int sightradius){
        entitiesWithinRadius(sightradius);
        return null;

    }
    public ArrayList<Entity> entitiesWithinRadius(int test){
        return null;
    }
    public int getRank(){
        return rank;
    }
    public Position getPosition(){
        return pos;
    }
    public boolean getGender(){
        return gender;
    }
    public int getSightRadius(){
        return sightradius;
    }
    public double getX(){
        return getPosition().getX();
    }
    public double getY(){
        return getPosition().getY();
    }
}
