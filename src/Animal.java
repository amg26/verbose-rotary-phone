import com.sun.scenario.animation.shared.AnimationAccessor;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Math.sqrt;

public abstract class Animal extends Entity implements Killable {
    //STATE
    boolean dead;
    double health;
    double hunger;
    double thirst;
    boolean gender;
    protected int reproductionCooldown;
    boolean pregnant;

    //PROPERTY
    int rank;
    double maxHealth;
    double maxHunger;
    double maxThirst;
    double preferredSpace=16;

    //CONSTANTS TO FIDDLE WITH (should be overridden(?) by each specific animal class)
    double enemyFear;// = -1000;
    double foodTastiness;// = 500;
    double preyTastiness;// = 500;
    double kinFriendliness;// = 500;

    //my thinking: pass in things that matter to the individual state, like position, not properties like maxHealth or rank
    //this is probably bad form as well
    public Animal(Map map, Position pos, double direction, boolean gender) {
        super(map, pos, direction);
        this.gender = gender;

    }

    //in case you need a super-powerful zebra(?)
    public Animal(Map map, Position pos, double direction, boolean gender, int rank) {
        this(map, pos, direction, gender);
        this.rank = rank;
    }

    //in case you need a half-dead animal
    public Animal(Map map, Position pos, double direction, boolean gender, double health) {
        this(map, pos, direction, gender);
        this.health = health;
    }

    public void consume(Food food) {
        if (hunger < maxHunger) {
            food.getEaten();
            move(food.getPosition());
            hunger += 0.5;
        }
    }

    //consume an animal
    public void consume(Animal victim) {
        if (hunger < maxHunger) {
            if (getRank() > victim.getRank()) {
                move(victim.getPosition());
                victim.getAttacked(15);
                hunger += 15;
            }
        }
    }

    public void getAttacked(double amount) {
        System.out.println(this+" UNDER ATTACK");
        health -= amount;
    }

    public void die() {
        dead = true;
    }

    public void pursueMate(ArrayList<Animal> kin){
        double min = sightRadius;

        Position locClosestMate = kin.get(0).getPosition();
        Animal mate = kin.get(0);
        for(int i = 0; i < kin.size(); i ++){
            //used to be sqrt((kin.get(i).getX()*kin.get(i).getX())+(kin.get(i).getY()*kin.get(i).getY())) ............................andrew
            double dist = sqrt((Math.pow(kin.get(i).getX()-getX(),2))+Math.pow(kin.get(i).getY()-getY(),2));
            if (dist < min && this.gender != kin.get(i).getGender()){
                locClosestMate = kin.get(i).getPosition();
                min = dist;
                mate = kin.get(i);
            }
        }
        if (min < 10 && this.reproductionCooldown > 100){
            reproduce(mate);
            reproductionCooldown =0;
            mate.reproductionCooldown =0;
            System.out.println(";)");
            //return;
        }
        if (min > 1 && this.reproductionCooldown > 100){
            /**
             * moves towards it
             * TODO: Fix according to how Phil does moveTo
             */
            System.out.println("MOVING TOWARD M8");
            move(locClosestMate);
        }
    }

    public void flee(ArrayList<Animal> danger){
        System.out.println("FLEEEE");
        /**
         * runs from closest threat for now, kind of stupid
         * TODO: calculate best escape route, stop to consume if not at maxSpeed
         */
        double min = sightRadius;
        Position locClosestThreat = danger.get(0).getPosition();
        for(int i = 0; i < danger.size(); i ++){
            //OLD: (sqrt((danger.get(i).pos.getX()*danger.get(i).pos.getX())+(danger.get(i).pos.getY()*danger.get(i).pos.getY())))
            if ((sqrt(Math.pow(danger.get(i).pos.getX()-getX(),2)+Math.pow(danger.get(i).pos.getY()-getY(),2))) < min){
                locClosestThreat = danger.get(i).getPosition();
                //OLD: sqrt((danger.get(i).pos.getX()*danger.get(i).pos.getX())+(danger.get(i).pos.getY()*danger.get(i).pos.getY()))
                min = (sqrt(Math.pow(danger.get(i).pos.getX()-getX(),2)+Math.pow(danger.get(i).pos.getY()-getY(),2)));
            }
        }
        //System.out.println(locClosestThreat);
        moveAway(locClosestThreat);
    }

    public void pursueFood(ArrayList<Food> food){
        //System.out.println("2");
        double min = sightRadius;
        Food closestFood = food.get(0);
        Position locClosestFood = closestFood.pos;
        for(int i = 0; i < food.size(); i ++){
            if(this.getPosition().distanceTo(food.get(i).getPosition()) < min){
                min = this.getPosition().distanceTo(food.get(i).getPosition());
                locClosestFood = food.get(i).getPosition();
                closestFood = food.get(i);
            }
        }

        move(locClosestFood);
        consume(closestFood);
    }

    public void pursuePrey(ArrayList<Animal> prey){
        //System.out.println("2");

        double min = sightRadius;
        Animal closestPrey = prey.get(0);
        Position locClosestPrey = closestPrey.pos;
        for(int i = 0; i < prey.size(); i ++){
            if(this.getPosition().distanceTo(prey.get(i).getPosition()) < min){
                min = this.getPosition().distanceTo(prey.get(i).getPosition());
                locClosestPrey = prey.get(i).getPosition();
                closestPrey = prey.get(i);
            }
        }

        move(locClosestPrey);
        consume(closestPrey);
    }

    public void chillWithHerd(ArrayList<Animal> kin){
        //System.out.println("3");
        double min = sightRadius;
        Position locClosestFriend = null;
        for(int i = 0; i < kin.size(); i ++){
            double dist = kin.get(i).getPosition().distanceTo(this.getPosition());
            if(dist < min){
                //used to be kin.get(i).getPosition().distanceTo(food.get(i).getPosition) and idk wot that did
                min = kin.get(i).getPosition().distanceTo(this.getPosition());
                locClosestFriend = kin.get(i).getPosition();
            }
        }
        if(locClosestFriend!=null) {
            double dist = pos.distanceTo(locClosestFriend);
            if (dist > preferredSpace) {
                this.move(locClosestFriend);
            } else {
                this.moveAway(locClosestFriend);
            }
        }
    }

    @Override
    public void tick(ArrayList<Entity> closeEntities){

        // CATEGORIZE SURROUNDING ENTITIES
        ArrayList<Animal> kin = new ArrayList<>();
        ArrayList<Animal> danger = new ArrayList<>();
        ArrayList<Animal> prey = new ArrayList<>();
        ArrayList<Food> food = new ArrayList<>();
        for (int i = 0; i < closeEntities.size(); i++) {
            if(closeEntities.get(i) instanceof Animal) {
                Animal a = (Animal) closeEntities.get(i);
                if (a.getRank() < rank) {
                    prey.add(a);
                }
                if (a.getRank() > rank) {
                    danger.add(a);
                }
                if (a.getRank() == rank) {
                    kin.add(a);
                }
            }else if(closeEntities.get(i) instanceof Food){
                Food f = (Food) closeEntities.get(i);
                food.add(f);
            }
        }

        brainV1(kin, danger, prey, food);

        /*  ANDREW'S FIXED METHODS
        // PURSUE A MATE
        if(hunger > 40 && thirst > 40 && danger.size() == 0 && kin.size() != 0){
            pursueMate(kin);
        }
        //FLEE
        if( danger.size() != 0 ){
            System.out.println("FLEE");
            flee(danger);
        }
        // ATTEMPT TO EAT NEARBY FOOD
        else if (food.size() != 0) {
            pursueFood(food);
        }
        // ATTEMPT TO HUNT NEARBY PREY
        else if (prey.size() != 0) {
            System.out.println("HUNT");
            pursuePrey(prey);
        }
        // CHILL WITH THE HERD
        else if (prey.size() ??!=?? == 0 && kin.size() != 0){
            //System.out.println("CHILL");
            chillWithHerd(kin);
        }
        // RANDOMLY WALK FOWARDISH IF ALONE
        else if (prey.size() == 0 && kin.size() == 0){
            //System.out.println("WANDER");
            randomForwardWalk();
        }
        */

        // CLEAR LISTS
        danger.clear();
        kin.clear();
        prey.clear();

        // REST OF TICK BEHAVIOUR
        reproductionCooldown++;
        if (health <= 0)
            die();
        if(getX()<0 || getX()>map.getMap().length*5 || getY()<0 || getY()>map.getMap()[0].length*5)
            die();

        if (hunger <= 0 && thirst <= 0) {
            health = 0;
        }

        if (hunger <= 0) {
            speed = (int) (0.5 * maxSpeed);
            if (health < (int) (0.2 * maxHealth)) {
                health = 0;
            }
            health = (int) (0.8 * health);
        }
        if (thirst <= 0) {
            speed = (int) (0.5 * maxSpeed);
            if (health < (int) (0.2 * maxHealth)) {
                health = 0;
            }
            health = (int) (0.5 * health);
        }
        //thirst-=0.5;
        //TODO: add back hunger
        //hunger-=0.1;
    }


    public void randomForwardWalk(){
        movePolar(RNG.getInstance().nextDouble(), ((RNG.getInstance().nextDouble()-.5)+direction));
    }

    public void reproduce(Animal a){
        if (a.gender){
            a.pregnant = true;
        }
        else if (gender){
            gender = true;
        }
    }

    // PHIL'S EXPERIMENTAL TRADITIONAL AI ATTEMPT
    public void brainV1(ArrayList<Animal> kin, ArrayList<Animal> danger, ArrayList<Animal> prey, ArrayList<Food> food){
        /* THE IDEA
        *  some sort of weighted system
        *  - loop through all entities in surroundings
        *  - apply a weight to each one, more positive = want to go toward, negative = run away
        *          based on:
        *           -distance
        *           -severity of enemy / tastiness of food / urge to mate
        */

        //TODO: change from inverse square force law - type movement to more decision-based for some behaviors
            //i.e. don't run slowly toward the zebra if it is further, either run full speed @ it or don't
        //ALSO TODO: might need some other sort of modifier for priorities, not sure if enemyFear et al. can
            // handle that while keeping behavior reasonable

        ArrayList<Vector2D> weightVectors = new ArrayList<>();


        //dangers
        for(Animal a : danger){
            //diff. in rank *constant / health
            double weight = enemyFear*(a.getRank()-rank)/((health+1)*(Math.pow(pos.distanceTo(a.getPosition()),2)));
            Vector2D weightVector = new Vector2D(weight, pos, a.getPosition());
            weightVectors.add(weightVector);
        }
        //food
        for(Food f : food){
            double weight = foodTastiness*(f.size)/((maxHunger-hunger+1)*Math.pow(pos.distanceTo(f.getPosition()),2));
            Vector2D weightVector = new Vector2D(weight, pos, f.getPosition());
            weightVectors.add(weightVector);
        }
        //prey
        for(Animal p : prey){
            double weight = preyTastiness*(rank - p.rank)/((maxHunger-hunger+1)*Math.pow(pos.distanceTo(p.getPosition()),2));
            Vector2D weightVector = new Vector2D(weight, pos, p.getPosition());
            weightVectors.add(weightVector);
        }
        //kin NOT an inverse square law, more like this:
        // (5|x|-250)/(x^2 + 1)
        //quick mafs ^
        for(Animal k : kin){
            double dist = pos.distanceTo(k.getPosition());
            double weight = kinFriendliness*(5*dist-250)/((Math.pow(dist,2)+1));
            Vector2D weightVector = new Vector2D(weight, pos, k.getPosition());
            weightVectors.add(weightVector);
        }


        //SUM ALL
        Vector2D sum = Vector2D.sumAll(weightVectors);

        if(!sum.isZero())
            move(sum);
        else
            randomForwardWalk();


    }

    public int getRank(){
        return rank;
    }

    public boolean isDead(){
        return dead;
    }

    public boolean getGender(){
        return gender;
    }

    public double getHealth(){
        return health;
    }

}