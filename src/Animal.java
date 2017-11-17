public class Animal extends Entity{
    public Animal(Map map, Position pos, double maxspeed , boolean gender, int sightradius, int rank, int health, int thirst, int hunger, double speed){
        super(map, pos, maxspeed, gender, sightradius, rank, health, thirst, hunger, speed);
        System.out.println("GENERATING AN ANIMAL");
    }
}
