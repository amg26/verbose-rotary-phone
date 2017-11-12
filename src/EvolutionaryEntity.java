import java.util.ArrayList;

public class EvolutionaryEntity extends AbstractEntity implements Comparable{
    private double[] parameters;
    private double sameSpeciesWeight = 0;
    private double differentSpeciesWeight = 0;
    private int ticksSurvived;

    public EvolutionaryEntity(double[] parameters) {
        this.parameters = parameters;
        sameSpeciesWeight = parameters[0];
        differentSpeciesWeight = parameters[1];

        ticksSurvived = 0;
    }

    public void tick(ArrayList<AbstractEntity> nearby) {
        ticksSurvived++;

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
