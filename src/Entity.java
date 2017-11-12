import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.random;
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
    protected Random rand;
    protected double direction;
    protected Map map;
    protected double alteredSpeed;
    private final double piOver8 = Math.PI/8;

    public Entity(){

    }
    public Entity(Map map, Position pos, int maxspeed , boolean gender, int sightradius, int rank, int health, int thirst, int hunger, int speed) {
        this.pos = pos;
        this.maxspeed = maxspeed;
        this.gender = gender;
        this.sightradius = sightradius;
        this.rank = rank;
        this.speed = speed;
        this.map = map;
        rand = new Random();
        direction = 2*Math.PI*rand.nextDouble();
    }
    //based on needs + want to reproduce + not die
    public void tick(ArrayList<Entity> closeEntities) {

        if (hunger == 0 && thirst == 0){
            //sit nerd u ded
            health = 0;
            //this.die(); // pls dye
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
        for (int i = 0; i < closeEntities.size(); i++) {
            if (closeEntities.get(i).getRank() < rank) {
                food.add(closeEntities.get(i));
            }
            if (closeEntities.get(i).getRank() > rank) {
                    danger.add(closeEntities.get(i));
            }
            if (closeEntities.get(i).getRank() == rank) {
                kin.add(closeEntities.get(i));
            }
        }
        if(hunger > 70 && thirst > 70 && danger.size() == 0 && kin.size() != 0){
            double min = sightradius;
            Position locClosestMate;
            for(int i = 0; i < kin.size(); i ++){
                if ((sqrt((kin.get(i).pos.getX()*kin.get(i).pos.getX())+(kin.get(i).pos.getY()*kin.get(i).pos.getY())))< min && this.gender != kin.get(i).getGender()){
                    locClosestMate = kin.get(i).getPosition();
                    min = (sqrt((kin.get(i).pos.getX()*kin.get(i).pos.getX())+(kin.get(i).pos.getY()*kin.get(i).pos.getY())));
                    if (min < 1){
                        this.reproduce(kin.get(i));
                        //return;
                    }
                    if (min > 1){
                        /**
                         * moves towards it
                         * TODO: Fix according to how Phil does moveTo
                         */
                        moveTo(locClosestMate);
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
            Position locClosestThreat = danger.get(0).getPosition();
            for(int i = 0; i < danger.size(); i ++){
                if ((sqrt((danger.get(i).pos.getX()*danger.get(i).pos.getX())+(danger.get(i).pos.getY()*danger.get(i).pos.getY())))< min){
                    locClosestThreat = danger.get(i).getPosition();
                    min = (sqrt((danger.get(i).pos.getX()*danger.get(i).pos.getX())+(danger.get(i).pos.getY()*danger.get(i).pos.getY())));
                }
            }
            //System.out.println(locClosestThreat);
            double x = (pos.getX() - locClosestThreat.getX())/(sqrt((locClosestThreat.getX()*locClosestThreat.getX())+(locClosestThreat.getY()*locClosestThreat.getY())))*speed;
            double y = (pos.getY() - locClosestThreat.getY())/(sqrt((locClosestThreat.getX()*locClosestThreat.getX())+(locClosestThreat.getY()*locClosestThreat.getY())))*speed;
            Position escape = new Position(x+pos.getX(), y+pos.getY());
            moveTo(escape);
        }
        else if (thirst >= hunger && food.size() != 0) {
            //System.out.println("2");
            double min = sightradius;
            Position locClosestFood = null;
            for(int i = 0; i < food.size(); i ++){
                if(this.getPosition().distanceTo(food.get(i).getPosition()) < min){
                    min = this.getPosition().distanceTo(food.get(i).getPosition());
                    locClosestFood = food.get(i).getPosition();
                }
            }
            moveTo(locClosestFood);
        }
        else if (thirst >= hunger && food.size()!= 0 && kin.size() != 0){
            //System.out.println("3");
            double min = sightradius;
            Position locClosestFriend = null;
            for(int i = 0; i < kin.size(); i ++){
                if(kin.get(i).getPosition().distanceTo(this.getPosition()) < min){
                    min = kin.get(i).getPosition().distanceTo(food.get(i).getPosition());
                    locClosestFriend = kin.get(i).getPosition();
                }
            }
            this.moveTo(locClosestFriend);
        }
        else if (thirst >= hunger && food.size() == 0 && kin.size() == 0){
            //System.out.println("4");
            randomForwardWalk();
        }
        danger.clear();
        kin.clear();
        food.clear();

        //this.hunger = hunger - 5;
        //this.thirst = thirst - 10;
    }

    public void consume(Entity consumable) {
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
                } else if (f instanceof Entity) {
                    this.move(f.getPosition());
                    f.health = f.health - 15;
                    this.hunger = this.hunger + 15;
                }
            }
        }
        if (this.thirst < 20) {
            this.move(w.getPosition());
            this.thirst = this.thirst + 15;
        }
    }
    public void reproduce(Entity e){

    }
    //should return position vv
    public void planMove(ArrayList<Entity> closeentities){

    }

    public void movePolar(double r, double theta){
        Position initialPos = new Position(pos);

        //NE CCW to E
        if(theta>piOver8 && theta < 3*piOver8)
            alteredSpeed = map.slope(initialPos, 1, 1);
        else if(theta>3*piOver8 && theta < 5*piOver8)
            alteredSpeed = map.slope(initialPos, 1, 0);
        else if(theta>5*piOver8 && theta < 7*piOver8)
            alteredSpeed = map.slope(initialPos, 1, -1);
        else if(theta>7*piOver8 && theta < 9*piOver8)
            alteredSpeed = map.slope(initialPos, 0, -1);
        else if(theta>9*piOver8 && theta < 11*piOver8)
            alteredSpeed = map.slope(initialPos, -1, -1);
        else if(theta>11*piOver8 && theta < 13*piOver8)
            alteredSpeed = map.slope(initialPos, -1, 0);
        else if(theta>13*piOver8 && theta < 15*piOver8)
            alteredSpeed = map.slope(initialPos, -1, 1);
        else
            alteredSpeed = map.slope(initialPos, 0, 1);

        if(alteredSpeed == -3.14159){
            return;
        }

        System.out.println(alteredSpeed  + ",<altered, "+speed*alteredSpeed*r*Math.cos(theta));
        pos.addX((speed)-speed*(alteredSpeed/10)*r*Math.cos(theta));
        pos.addY((speed)-speed*(alteredSpeed/10)*r*Math.sin(theta));
        //altered by terrain

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
    public void randomForwardWalk(){

        movePolar(rand.nextDouble(), ((rand.nextDouble()-.5)+direction));
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
    public int getHealth(){
        return health;
    }
}
