import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimulationRenderer extends JPanel {
    private Simulation simulation;
    private double[][] map;
    private ArrayList<Entity> entities;

    public SimulationRenderer(Simulation simulation){
        this.simulation = simulation;

        map = simulation.getMap().getMap(); // lol
        entities = simulation.getEntities();

        this.setPreferredSize(new Dimension(500, 500));
    }

    public void tick(){
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        for(int j = 0; j < map.length; j++) {
            for(int i = 0; i < map[0].length; i++) {
                Color c = new Color((int) map[j][i], (int) map[j][i], (int) map[j][i]);
                g.setColor(c);
                g.fillRect(i * 5, j * 5, 5, 5);
            }
        }
        g.setColor(Color.CYAN);
        for(Entity e : entities){
            g.fillOval((int)e.getX(), (int)e.getY(), 10, 10);

        }
    }
}
