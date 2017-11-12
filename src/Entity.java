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
    public Entity(){

    }
    public Entity(Position pos, int speed , boolean gender, int sightradius, int rank, int health, int thirst, int hunger) {
        this.pos = pos;
        this.speed = speed;
        this.gender = gender;
        this.sightradius = sightradius;
        this.rank = rank;
    }
    //based on needs + want to reproduce + not die
    public void tick() {
        if (hunger == 0 && thirst == 0){
            //sit nerd u ded
            health = 0;
            return;
        }
        if (hunger == 0) {
            speed = (int) (0.5 * speed);
            if (health < (int) (0.2 * maxhealth)) {
                health = 0;
            }
            health = (int) (0.8 * health);
        }
        if (thirst == 0) {
            speed = (int) (0.5 * speed);
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
            //runs from closest threat for now, kind of stupid

        }
    }
    public void consume(Entity consumable){
        Entity f = consumable;
        Entity w = consumable; //for when there's a river
        if (this.hunger < 20) {
            if (this.getRank() > f.getRank()) {
                if (f instanceof Food) {
                    this.move(f.getPosition());
                    f.health--;
                    this.hunger = this.hunger + 15;
                /*if (this.health < this.maxhealth){
                    //can be any number. I just put 10 for now
                    if (this.getRank() > 10) {
                        this is also just a random increase rate for health as the animal eats food
                        this.health = this.health + (int)(this.health* 0.2);
                    }*/
                }
                else if (f instanceof Entity) {
                    this.move(f.getPosition());
                    f.health = f.health - 15;
                    this.hunger = this.hunger + 15;
                }
            }
        }
        if (this.thirst < 20){
            this.move(w.getPosition());
            this.thirst = this.thirst + 15;
            }
        }
    public void reproduce(Entity e){

    }
    //should return position vv
    public void planMove(ArrayList<Entity> closeentities){

    }
    public void move(){

    }
    public void move(Position target){

    }
    //replace void with ArrayList<Entity> vv
    public ArrayList<Entity> scan(int sightradius){
        entitiesWithinRadius(sightradius);
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
}
