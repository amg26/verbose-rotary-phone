import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SimulationRenderer extends JPanel implements ActionListener {
    private Simulation simulation;
    private double[][] map;
    private ArrayList<Entity> entities;
    private BufferedImage bg;
    //TIMING
    private Timer t;

    public static final int TILE_SIZE = 5;

    public SimulationRenderer(Simulation simulation){
        this.simulation = simulation;
        map = simulation.getMap().getMap(); // lol

        entities = simulation.getEntities();

        this.setPreferredSize(new Dimension(1000, 1000));

        bg = new BufferedImage(map[0].length * TILE_SIZE, map.length * TILE_SIZE, BufferedImage.TYPE_INT_RGB);
        generateBackground();

        t = new Timer(100, this);
        t.setActionCommand("nextTick");
        t.start();
    }

    public void tick(){
        simulation.tick();
        repaint();
    }

    private void generateBackground() {
        Graphics g = bg.getGraphics();

        for(int j = 0; j < map.length; j++) {
            for(int i = 0; i < map[0].length; i++) {
                int colorVal = (int) map[j][i];
                if(colorVal > 255) {
                    colorVal = 255;
                } else if(colorVal < 0) {
                    colorVal = 0;
                }

                if(colorVal <= Map.WATER_DEPTH) {
                    g.setColor(new Color(0, 0, 100 + (int) (100/Map.WATER_DEPTH * colorVal)));
                } else {
                    g.setColor(new Color(0, colorVal, 0));
                }
                g.fillRect(i * 5, j * 5, 5, 5);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        g.drawImage(bg, 0, 0, null);

        for(Entity e : entities){
            if(e.getClass() == Zebra.class){
                g.setColor(Color.MAGENTA);
            }else if(e.getClass() == Todd.class){
                g.setColor(Color.CYAN);
            }else{
                g.setColor(Color.RED);
            }
            g.fillOval((int)e.getX(), (int)e.getY(), 10, 10);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("nextTick")) {
            tick();
        } else if(e.getActionCommand().equals("playPause")) {
            if(t.isRunning()) {
                t.stop();
            } else {
                t.start();
            }
        }
    }
}
