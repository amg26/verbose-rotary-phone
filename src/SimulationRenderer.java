import javax.swing.*;
import java.awt.*;

public class SimulationRenderer extends JPanel {
    private Simulation simulation;
    private double[][] map;

    public SimulationRenderer(Simulation simulation){
        this.simulation = simulation;

        map = simulation.getMap().getMap(); // lol

        this.setPreferredSize(new Dimension(500, 500));
    }

    public void tick(){
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawString("HELLO", 10, 10);
        for(int j = 0; j < map.length; j++) {
            for(int i = 0; i < map[0].length; i++) {
                Color c = new Color(0, (int) map[j][i], 0);
                g.setColor(c);
                g.fillRect(i * 5, j * 5, 5, 5);
            }
        }
    }
}
