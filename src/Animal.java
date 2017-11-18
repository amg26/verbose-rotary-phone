import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Animal extends Entity implements Killable {
    //STATE
    boolean dead;
    double health;
    double hunger;
    double thirst;
    boolean gender;
    //boolean pregnant

    //PROPERTY
    int rank;
    double maxHealth;
    double maxHunger;
    double maxThirst;

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
            hunger += 1;
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
        health -= amount;
        if (health < 0)
            die();
        health = 0;
    }

    public void die() {
        dead = true;
    }

    @Override
    public void tick(ArrayList<Entity> closeEntities){
        movePolar(1,0);
    }


    public void randomForwardWalk(){
        movePolar(RNG.getInstance().nextDouble(), ((RNG.getInstance().nextDouble()-.5)+direction));
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
