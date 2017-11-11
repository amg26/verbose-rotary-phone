import javax.swing.*;
import java.awt.*;

public  class Main {
    public static void main(String[] args){
        Map m = new Map(50, 100);
        System.out.println(m.getElevation(3.5, 2.5));

        Position p = new Position(7.3, 8.2);
        System.out.println(m.getElevation(p));
        Zebra z1 = new Zebra(p, 5, false);

        System.out.println(m);

        JFrame window = new JFrame("SIMULATION");
        window.setVisible(true);

        Simulation simulation = new Simulation();

        SimulationRenderer simulationRenderer = new SimulationRenderer(simulation);
        window.add(simulationRenderer);





        //tick simulation
        //tick simulationRendereerererrerer
    }
}
