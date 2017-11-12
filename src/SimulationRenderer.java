import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

public class SimulationRenderer extends JPanel implements ActionListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private Simulation simulation;
    private double[][] map;
    private ArrayList<Entity> entities;
    private BufferedImage bg;

    //IMAGE FILES
    private Image zebraImage;

    //which one to generate when clicking
    private int currentAnimal;

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("CLICK");
        //5 is map scale
        Position mousePos = new Position(e.getX()/5, e.getY()/5);
        simulation.generateAnimal(1, mousePos);
        if(e.getButton()== InputEvent.BUTTON1_MASK){
            simulation.generateAnimal(1, mousePos);
        }else if(e.getButton()==InputEvent.BUTTON2_MASK){
            simulation.generateAnimal(2, mousePos);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("PRESS");
        if(e.getButton() == MouseEvent.BUTTON2){
            if(currentAnimal<3){
                currentAnimal++;
            }else{
                currentAnimal=0;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private Image toddImage;
    private Image lionImage;
    private Image giraffeImage;
    //TIMING
    private Timer t;

    public static final int TILE_SIZE = 5;

    public SimulationRenderer(Simulation simulation) {
        this.simulation = simulation;
        map = simulation.getMap().getMap(); // lol

        this.setPreferredSize(new Dimension(10000, 10000));

        entities = simulation.getEntities();
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        bg = new BufferedImage(map[0].length * TILE_SIZE, map.length * TILE_SIZE, BufferedImage.TYPE_INT_RGB);

        try{
            zebraImage = ImageIO.read(new File("src/zebra.png"));
            toddImage = ImageIO.read(new File("src/todd.png"));
            lionImage = ImageIO.read(new File("src/lion.png"));
            giraffeImage = ImageIO.read(new File("src/giraffe.png"));
        } catch(IOException e){System.out.println("hmm...");}
        //toddImage = toolkit.createImage("src/zebra.png");


        generateBackground();

        t = new Timer(20, this);
        t.setActionCommand("nextTick");
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
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
                g.drawImage(zebraImage, (int)(e.getX() * 5), (int)(e.getY() * 5), null);
            }else if(e.getClass() == Todd.class){
                g.drawImage(toddImage, (int)(e.getX()*5), (int)(e.getY()*5), null);
            }else if(e.getClass() == Food.class){
                Food f = (Food)e;
                g.setColor(Color.RED);
                g.fillOval((int)(f.getX()*5),(int)(f.getY()*5), f.getSize()*3, f.getSize()*3);
            }else if(e.getClass() == Lion.class){
                g.drawImage(lionImage, (int)(e.getX()*5), (int)(e.getY()*5), null);
            }
            else if(e.getClass() == Giraffe.class){
                g.drawImage(giraffeImage, (int)(e.getX()*5), (int)(e.getY()*5), null);
            }
            //g.fillOval((int)e.getX() * 5, (int)e.getY() * 5, 16, 16);

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
        } else if(e.getActionCommand().equals("Zebra")){
            currentAnimal = 0;
        } else if(e.getActionCommand().equals("Lion")){
            currentAnimal = 1;
        } else if(e.getActionCommand().equals("Giraffe")){
            currentAnimal = 2;
        } else if(e.getActionCommand().equals("Todd")){
            currentAnimal = 3;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Position mousePos = new Position(e.getX()/5, e.getY()/5);
        simulation.generateAnimal(currentAnimal, mousePos);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

            currentAnimal=e.getWheelRotation()%4;
            System.out.println(currentAnimal);

    }
}
