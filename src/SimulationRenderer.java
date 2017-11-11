import javax.swing.*;
import java.awt.Graphics2D;

public class SimulationRenderer extends JPanel{
    private Simulation simulation;

    public SimulationRenderer(Simulation simulation){
        this.simulation = simulation;

    }

    public void tick(){
        repaint();


    }

    public void paintComponent(Graphics2D g2){
        g2.drawString("heyheyhey", 0 ,0);
    }

}
