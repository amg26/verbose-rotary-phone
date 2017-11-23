import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public  class Main {
    public static void main(String[] args){
        Map m = new Map(1000, 1000);
        System.out.println(m.getMap().length);
        System.out.println(m.getElevation(3.5, 2.5));

        Position p = new Position(7.3, 8.2);
        System.out.println(m.getElevation(p));


        JFrame window = new JFrame("SIMULATION");

        window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));

        window.setSize(500, 500);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);




        Simulation simulation = new Simulation();

        SimulationRenderer simulationRenderer = new SimulationRenderer(simulation);
        // window.add(simulationRenderer);
        JScrollPane scrollPane = new JScrollPane(simulationRenderer);
        window.add(scrollPane);

        JPanel controls = new JPanel();
        JButton nextBtn = new JButton("Next Tick");
        nextBtn.setSize(new Dimension(50, 20));
        nextBtn.addActionListener(simulationRenderer);
        nextBtn.setActionCommand("nextTick");
        controls.add(nextBtn);

        JButton playBtn = new JButton("Play/Pause");
        playBtn.setSize(new Dimension(50, 20));
        playBtn.addActionListener(simulationRenderer);
        playBtn.setActionCommand("playPause");
        controls.add(playBtn);

        JButton zoomInBtn = new JButton("Zoom In");
        zoomInBtn.setSize(new Dimension(50, 20));
        zoomInBtn.addActionListener(simulationRenderer);
        zoomInBtn.setActionCommand("zoomIn");
        controls.add(zoomInBtn);

        JButton zoomOutBtn = new JButton("Zoom Out");
        zoomOutBtn.setSize(new Dimension(50, 20));
        zoomOutBtn.addActionListener(simulationRenderer);
        zoomOutBtn.setActionCommand("zoomOut");
        controls.add(zoomOutBtn);

        window.add(controls);

        window.setVisible(true);
    }
}
