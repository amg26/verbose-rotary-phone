import javax.swing.*;
import java.awt.*;

public  class Main {
    public static void main(String[] args){
        Map m = new Map(100, 100);
        System.out.println(m.getElevation(3.5, 2.5));

        Position p = new Position(7.3, 8.2);
        System.out.println(m.getElevation(p));


        JFrame window = new JFrame("SIMULATION");

        window.setSize(500, 500);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        Simulation simulation = new Simulation();

        SimulationRenderer simulationRenderer = new SimulationRenderer(simulation);
        window.add(simulationRenderer);

        simulationRenderer.tick();




        //tick simulation
        //tick simulationRendereerererrerer
    }
}
