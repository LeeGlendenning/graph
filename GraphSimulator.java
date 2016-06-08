package graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author The Boss
 */
public class GraphSimulator {
    
    private final JFrame frame;
    private final LineGraph graph;
    private final JButton startButton = new JButton("Start");
    private final JButton stopButton = new JButton("Stop");
    private final JButton addPointButton = new JButton("Add Point");
    private final JTextField delayField = new JTextField(10);
    private final JTextField boundsField = new JTextField(10);
    private final JTextField xField = new JTextField(10);
    private final JTextField yField = new JTextField(10);
    
    public GraphSimulator(String simName){
        frame = new JFrame(simName);
        ArrayList<Point> p = new ArrayList();
        p.add(new Point(0,0));
        /*p.add(new Point(-25,5));
        p.add(new Point(-20,10));
        p.add(new Point(50,90));
        p.add(new Point(160,-120));
        p.add(new Point(70,40));
        p.add(new Point(90,150));
        p.add(new Point(110,140));
        p.add(new Point(130,110));
        p.add(new Point(150,110));
        p.add(new Point(-30,50));
        p.add(new Point(-100, 0));*/
        graph = new LineGraph(p, "Sim Graph", "X", "Y", Color.BLUE);
        initFrame();
        createSimulator();
        
        registerListeners();
    }
    
    private void initFrame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void createSimulator(){
        Container window = frame.getContentPane();
        window.setLayout(new BorderLayout());
        
        // Pad the graph border and add graph to window
        JPanel graphPanel = new JPanel(new BorderLayout());
        graphPanel.add(graph, BorderLayout.CENTER);
        graphPanel.setBorder(BorderFactory.createMatteBorder(15, 15, 15, 15, graphPanel.getBackground()));
        window.add(graphPanel, BorderLayout.CENTER);
        
        // Create simPanel to hold simulation controls
        JPanel simPanel = new JPanel();
        simPanel.setLayout(new BoxLayout(simPanel, BoxLayout.PAGE_AXIS));
        
        // Add simulation title to simulation panel
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Random Point Generator"));
        simPanel.add(titlePanel);
        
        // Add bounds field to simulation panel
        JPanel boundsPanel = new JPanel(new BorderLayout());
        boundsPanel.add(new JLabel("Bounds: "), BorderLayout.WEST);
        boundsPanel.add(boundsField, BorderLayout.EAST);
        simPanel.add(Box.createVerticalStrut(20));
        simPanel.add(boundsPanel);
        
        // Add time delay field to simulation panel
        JPanel delayPanel = new JPanel(new BorderLayout());
        delayPanel.add(new JLabel("Delay (ms): "), BorderLayout.WEST);
        delayPanel.add(delayField, BorderLayout.EAST);
        simPanel.add(Box.createVerticalStrut(10));
        simPanel.add(delayPanel);
        
        // Add start and stop buttons to simPanel
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(startButton, BorderLayout.WEST);
        buttonPanel.add(stopButton, BorderLayout.EAST);
        buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 10, buttonPanel.getBackground()));
        simPanel.add(Box.createVerticalStrut(10));
        simPanel.add(buttonPanel);
        
        
        
        
        
        // Add bounds field to simulation panel
        JPanel xPanel = new JPanel(new BorderLayout());
        xPanel.add(new JLabel("X: "), BorderLayout.WEST);
        xPanel.add(xField, BorderLayout.EAST);
        simPanel.add(Box.createVerticalStrut(20));
        simPanel.add(xPanel);
        
        // Add time delay field to simulation panel
        JPanel yPanel = new JPanel(new BorderLayout());
        yPanel.add(new JLabel("Y: "), BorderLayout.WEST);
        yPanel.add(yField, BorderLayout.EAST);
        simPanel.add(Box.createVerticalStrut(10));
        simPanel.add(yPanel);
        
        simPanel.add(addPointButton);
        
        
        
        
        JPanel simContainer = new JPanel();
        simContainer.add(simPanel);
        
        // Create border for simulation container panel and add to window
        simContainer.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
        window.add(simContainer, BorderLayout.WEST);
        
        // Prepare and show the JFrame
        frame.setPreferredSize(new Dimension(600, 400));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void registerListeners(){
        addPointButton.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Adding point: " + xField.getText() + ", " + yField.getText());
                graph.addPoint(new Point(Integer.parseInt(xField.getText()), Integer.parseInt(yField.getText())));
                
            }
        });  
    }
    
}
