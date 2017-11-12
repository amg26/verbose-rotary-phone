import java.util.ArrayList;

public class EvolutionaryEntity extends Entity implements Comparable{
    private double[] parameters;
    private double sameSpeciesWeight = 0;
    private double differentSpeciesWeight = 0;
    private int ticksSurvived;

    public EvolutionaryEntity(Map m, Position pos, double[] parameters) {
        super(m, pos, 2, true, 5, 50, 100, 100, 100, 1);
        this.parameters = parameters;
        sameSpeciesWeight = parameters[0];
        differentSpeciesWeight = parameters[1];

        ticksSurvived = 0;
    }

    public void tick(ArrayList<Entity> nearby) {
        ticksSurvived++;
        double totalang = 0;
        double weightAmount = 0;
        for(Entity e : nearby) {
            double angle = getPosition().getDirectionTo(e.getPosition());
            if(e.getRank() == getRank()) {
                // we are the same!!
                totalang += sameSpeciesWeight * angle;
                weightAmount += sameSpeciesWeight;
            } else {
                totalang += differentSpeciesWeight * angle;
                weightAmount += differentSpeciesWeight;
            }
        }

        Position pos = getPosition();
        pos.addX(Math.cos(totalang/weightAmount) * speed);
        pos.addY(Math.sin(totalang/weightAmount) * speed);
    }

    public double[] getParameters() {
        return parameters;
    }

    public int getTicksSurvived() {
        return ticksSurvived;
    }

    @Override
    public int compareTo(Object other) {
        return getTicksSurvived() - ((EvolutionaryEntity)other).getTicksSurvived();
    }
}
