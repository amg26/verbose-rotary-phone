import java.util.ArrayList;
import java.util.PriorityQueue;

public class EvolutionManager {
    private int initialCount;
    private ArrayList<EvolutionaryEntity> agents;
    private Simulation sim;

    public EvolutionManager(int initialCount) {
        sim = new Simulation();

        this.initialCount = initialCount;

        agents = new ArrayList<>();

        for(int i = 0; i < initialCount; i++) {
            double[] parameters = new double[2];
            parameters[0] = 10.0;
            parameters[1] = -10.0;
            agents.add(generateEntity());
        }

        addAgentsToSim();
    }

    public void train(int iterations) {
        for(int i = 0; i < iterations; i++) {
            sim = new Simulation();
            addAgentsToSim();
            for(int j = 0; j < 1000; j++) {
                sim.tick();
            }
            evolveAgents();
        }
    }

    private void addAgentsToSim() {
        ArrayList<Entity> toAdd = new ArrayList<>();
        for(EvolutionaryEntity e : agents) {
            toAdd.add((Entity) e);
        }
        sim.setEntities(toAdd);
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

    private EvolutionaryEntity generateEntity() {
        Position pos = new Position(Math.random() * 100, Math.random() * 100);
        double[] parameters = new double[2];
        parameters[0] = Math.random() * 100 - 50;
        parameters[1] = Math.random() * 100 - 50;

        return new EvolutionaryEntity(sim.getMap(), pos, parameters);
    }

    public EvolutionaryEntity evolveEntity(EvolutionaryEntity basis) {
        double[] oldParameters = basis.getParameters();
        double[] modifiedParameters = new double[oldParameters.length];

        for(int i = 0; i < oldParameters.length; i++) {
            modifiedParameters[i] = oldParameters[i] + ((double) (Math.random() * 10) - 5);
        }

        Position pos = new Position(Math.random() * 100, Math.random() * 100);
        return new EvolutionaryEntity(sim.getMap(), pos, modifiedParameters);
    }

    public Simulation getSim() {
        return sim;
    }
}
