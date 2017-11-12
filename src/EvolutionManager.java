import java.util.ArrayList;
import java.util.PriorityQueue;

public class EvolutionManager {
    private int initialCount;
    private ArrayList<EvolutionaryEntity> agents;

    public EvolutionManager(int initialCount) {
        this.initialCount = initialCount;

        agents = new ArrayList<>();

        for(int i = 0; i < initialCount; i++) {
            double[] parameters = new double[2];
            parameters[0] = 10.0;
            parameters[1] = -10.0;
            agents.add(new EvolutionaryEntity(parameters));
        }
    }

    private void evolveAgents() {
        PriorityQueue<EvolutionaryEntity> pq = new PriorityQueue<>();

        pq.addAll(agents);

        agents.clear();
        while(agents.size() <= initialCount/2) {
            agents.add(pq.remove());
        }
        while(agents.size() < initialCount) {
            agents.add(generateEntity());
        }
    }

    private static EvolutionaryEntity generateEntity() {
        double[] parameters = new double[2];
        parameters[0] = Math.random() * 100 - 50;
        parameters[1] = Math.random() * 100 - 50;

        return new EvolutionaryEntity(parameters);
    }

    public EvolutionaryEntity evolveEntity(EvolutionaryEntity basis) {
        double[] oldParameters = basis.getParameters();
        double[] modifiedParameters = new double[oldParameters.length];

        for(int i = 0; i < oldParameters.length; i++) {
            modifiedParameters[i] = oldParameters[i] + ((double) (Math.random() * 10) - 5);
        }

        return new EvolutionaryEntity(modifiedParameters);
    }
}
