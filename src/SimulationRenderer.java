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

    private double zoomx=1, zoomy=1;

    //IMAGE FILES
    private Image zebraImage;

    //which one to generate when clicking
    private int currentAnimal;



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
        //THis in no way indicates any sort of larger structural problem
        revalidate();
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
        //super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;

        super.paintComponent(g);

        g2.scale(zoomx, zoomy);


        g.drawImage(bg, 0, 0, null);

        g.drawString(""+currentAnimal,10,10);

        for(Entity e : entities){
            if(e.getClass() == Zebra.class){
                g.setColor(Color.GREEN);
                g.fillOval((int)e.getX(), (int)e.getY(), 16, 16);
                g.drawImage(zebraImage, (int)(e.getX()), (int)(e.getY()), null);
            }else if(e.getClass() == Todd.class){
                g.drawImage(toddImage, (int)(e.getX()), (int)(e.getY()), null);
            }else if(e.getClass() == Food.class){
                Food f = (Food)e;
                g.setColor(Color.RED);
                g.fillOval((int)(f.getX()),(int)(f.getY()), f.getSize()*1, f.getSize()*1);
            }else if(e.getClass() == Lion.class){
                g.setColor(Color.RED);
                g.fillOval((int)e.getX(), (int)e.getY(), 16, 16);
                g.drawImage(lionImage, (int)(e.getX()), (int)(e.getY()), null);

            }
            else if(e.getClass() == Giraffe.class){
                g.drawImage(giraffeImage, (int)(e.getX()), (int)(e.getY()), null);
            }

            //DRAW BARS FOR DEBUGGING PORPOISES:
            if(e instanceof Animal){
                g.setColor(Color.red);
                g.fillRect((int)e.getX(), (int)e.getY()-8, (int)((Animal) e).getHealth()/6, 2);
                g.setColor(Color.YELLOW);
                g.fillRect((int)e.getX(), (int)e.getY()-11, (int)((Animal) e).hunger/6, 2);
                //g.setColor(Color.CYAN);
                //g.fillRect((int)e.getX(), (int)e.getY()-22, (int)((Animal) e).thirst/6, 5);
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
        }
        else if(e.getActionCommand().equals("zoomIn")) {
            System.out.println("ZOOMIN");
            zoomx+=0.2;
            zoomy+=0.2;
        }
        else if(e.getActionCommand().equals("zoomOut")) {
            System.out.println("ZOOMOUT");
            zoomx-=0.1;
            zoomy-=0.1;
            if(zoomx<=0.05)
                zoomx=0.1;
            if(zoomy<=0.05)
                zoomy=0.1;
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Position mousePos = new Position(e.getX()/zoomx, e.getY()/zoomy);
        if(SwingUtilities.isLeftMouseButton(e)) {
            simulation.generateAnimal(currentAnimal, mousePos);
            repaint();
        }
        if(SwingUtilities.isRightMouseButton(e)) {
            simulation.generateFood(mousePos, 10);
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Position mousePos = new Position(e.getX() / zoomx, e.getY() / zoomy);
        if(e.getButton() == MouseEvent.BUTTON1) {
            simulation.generateAnimal(currentAnimal, mousePos);
            repaint();
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            simulation.generateFood(mousePos, 10);
            repaint();
        }
        if(e.getButton() == MouseEvent.BUTTON2){
            System.out.println("SWITCHING TO NEXT ANIMAL");
            if(currentAnimal<3){
                currentAnimal++;
            }else{
                currentAnimal=0;
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
}
