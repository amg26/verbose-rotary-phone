import java.util.Random;

//this is the 'singleton' pattern, so we can easily use the same instance of the RNG instead of creating a new one for each class
//no idea if this is a good way to do this but it works and we dont need a new instance each time

public class RNG {
    private static RNG ourInstance = new RNG();

    public static RNG getInstance() {
        return ourInstance;
    }


    Random rand = new Random();
    private RNG() {
        rand = new Random();
    }

    public int nextInt(int bound){
        return rand.nextInt(bound);
    }

    public double nextDouble(){
        return rand.nextDouble();
    }

    public boolean nextBoolean(){
        return rand.nextBoolean();
    }

    public double nextAngle() {return rand.nextDouble()*2*Math.PI;}


}
