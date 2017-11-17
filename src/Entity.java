import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.random;
import static java.lang.Math.sqrt;


//TODO- FIX THIS

public class Entity {
    protected int reproductionCooldown;
    protected Position pos;
    protected double speed;
    protected int thirst;
    protected int hunger;
    protected boolean gender;
    protected int sightradius;
    protected int rank;
    protected int health;
    protected int maxhealth;
    protected double maxspeed;
    protected Random rand;
    protected double direction;
    protected boolean pregnant;
    protected Map map;
    protected double alteredSpeed;
    private final double piOver8 = Math.PI/8;


    public Entity(Map map, Position pos, double maxspeed , boolean gender, int sightradius, int rank, int health, int thirst, int hunger, double speed) {
        this.pos = pos;
        this.maxspeed = maxspeed;
        this.gender = gender;
        this.sightradius = sightradius;
        this.rank = rank;
        this.speed = speed;
        this.map = map;
        this.health = health;
        this.hunger = hunger;
        this.thirst = thirst;
        rand = new Random();
        direction = 2*Math.PI*rand.nextDouble();
        reproductionCooldown = 0;
        pregnant = false;
    }
    //based on needs + want to reproduce + not die
    public void tick(ArrayList<Entity> closeEntities) {

        if (hunger <= 0 && thirst <= 0){
            //sit nerd u ded
            health = 0;
            //this.die(); // pls dye
            //System.out.println("DYIEIYING OF THIRST");
        }
        if (hunger <= 0) {
            speed = (int) (0.5 * maxspeed);
            if (health < (int) (0.2 * maxhealth)) {
                health = 0;
            }
            health = (int) (0.8 * health);
        }
        if (thirst <= 0) {
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
        if(hunger > 40 && thirst > 40 && danger.size() == 0 && kin.size() != 0){
            System.out.println("SHOULD BE MATING");
            double min = sightradius;
            Position locClosestMate = kin.get(0).getPosition();
            Entity mate = kin.get(0);
            for(int i = 0; i < kin.size(); i ++){
                if ((sqrt((kin.get(i).pos.getX()*kin.get(i).pos.getX())+(kin.get(i).pos.getY()*kin.get(i).pos.getY())))< min && this.gender != kin.get(i).getGender()){
                    locClosestMate = kin.get(i).getPosition();
                    min = (sqrt((kin.get(i).pos.getX()*kin.get(i).pos.getX())+(kin.get(i).pos.getY()*kin.get(i).pos.getY())));
                    mate = kin.get(i);
                }
            }
            if (min < 10){// && this.reproductionCooldown%20 == 0){
                System.out.println("MATING");
                this.reproduce(mate);
                this.reproductionCooldown ++;
                mate.reproductionCooldown++;
                System.out.println(";)");
                //return;
            }
            if (min > 1){// && this.reproductionCooldown%20 ==0){
                /**
                 * moves towards it
                 * TODO: Fix according to how Phil does moveTo
                 */
                System.out.println("MOVING TOWARD MATE");
                moveTo(locClosestMate);
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
            //System.out.println(escape.getX()+"<esc, pos>"+pos.getX()+", locClosestThreatx:"+locClosestThreat.getX());
            moveTo(escape);
        }
        /*
        else if (thirst >= hunger && food.size() != 0) {
            //System.out.println("2");
            double min = sightradius;
            Entity closestFood = food.get(0);
            Position locClosestFood = closestFood.pos;
            for(int i = 0; i < food.size(); i ++){
                if(this.getPosition().distanceTo(food.get(i).getPosition()) < min){
                    min = this.getPosition().distanceTo(food.get(i).getPosition());
                    locClosestFood = food.get(i).getPosition();
                    closestFood = food.get(i);
                }
            }
            moveTo(locClosestFood);
            consume(closestFood);
        }
        */
        /*
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
        */
        else if (thirst >= hunger && food.size() == 0 && kin.size() == 0){
            //System.out.println("4");
            randomForwardWalk();
        }
        danger.clear();
        kin.clear();
        food.clear();

        //this.hunger = hunger - 1;
        //this.thirst = thirst - 10;
    }

    public void consume(Entity consumable) {
        Entity f = consumable;
        Entity w = consumable; //for when there's a river
        //if (this.hunger < 40) {
            if (this.getRank() > f.getRank()) {
                if (f instanceof Food) {
                    ((Food) f).getEaten();
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
        //}

    }
    public void reproduce(Entity e){
        if (e.gender){
            e.pregnant = true;
        }
        else if (gender){
            gender = true;
        }
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

        double nextElevation = 0;
        //HAHAHAHA IDK
        if((initialPos.getX()<1 || initialPos.getX()>map.getMap().length*5-1) || (initialPos.getY()<1 || initialPos.getY()>map.getMap()[0].length*5-1)) {
            if (theta > piOver8 && theta < 3 * piOver8)
                nextElevation = map.getElevation(initialPos, 1, 1);
            else if (theta > 3 * piOver8 && theta < 5 * piOver8)
                nextElevation = map.getElevation(initialPos, 1, 0);
            else if (theta > 5 * piOver8 && theta < 7 * piOver8)
                nextElevation = map.getElevation(initialPos, 1, -1);
            else if (theta > 7 * piOver8 && theta < 9 * piOver8)
                nextElevation = map.getElevation(initialPos, 0, -1);
            else if (theta > 9 * piOver8 && theta < 11 * piOver8)
                nextElevation = map.getElevation(initialPos, -1, -1);
            else if (theta > 11 * piOver8 && theta < 13 * piOver8)
                nextElevation = map.getElevation(initialPos, -1, 0);
            else if (theta > 13 * piOver8 && theta < 15 * piOver8)
                nextElevation = map.getElevation(initialPos, -1, 1);
            else if ((initialPos.getX() < 5 || initialPos.getX() > map.getMap().length * 5 - 5) || (initialPos.getY() < 5 || initialPos.getY() > map.getMap()[0].length * 5 - 5))
                nextElevation = map.getElevation(initialPos, 0, 1);
        }



        //if(nextElevation<Map.WATER_DEPTH){
        //    theta+=(Math.PI);
        //    direction+=Math.PI;
        //}


        //System.out.println(alteredSpeed  + ",<altered, "+speed*(alteredSpeed/1000)*r*Math.cos(theta) +" Direction:"+direction);
        //if(alteredSpeed == -3.14159){
        //    return;
        //}



        System.out.println("alteredSpeed"+alteredSpeed);
        pos.addX(((speed/5)*r*Math.cos(theta)));//-Math.abs(speed*(alteredSpeed/10))*r*Math.cos(theta));
        pos.addY(((speed/5)*r*Math.sin(theta)));//-Math.abs(speed*(alteredSpeed/10))*r*Math.sin(theta));
        //pos.addX(speed);
        //pos.addY(speed);

        //altered by terrain

    }
    public void move(Position target){

    }
    public void moveTo(Position target){
        double tempx = target.getX() - pos.getX();
        double tempy = target.getY() - pos.getY();
        direction = Math.atan2(tempy,tempx);

        if(direction<0){
            direction+=(2*Math.PI);
        }else if(direction > Math.PI){
            direction-=(2*Math.PI);
        }
        movePolar(speed, direction);
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
