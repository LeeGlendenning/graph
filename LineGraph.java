/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * LineGraph class should extend Graph which should extend JComponent but for
 * now we will skip that
 */
public class LineGraph extends JPanel {

    ArrayList<Point> points;
    String xAxis;
    String yAxis;
    Color colour;

    /*
     * LineGraph constructor creates a LineGraph object to be drawn to the screen
     * 
     * @param points        ArrayList of Point objects. Data to display on the line graph
     * @param xAxis         String label for the x axis of the line graph
     * @param yAxis         String label for the y axis of the line graph
     * @param colour        Color object to use when drawing the line graph
     */
    public LineGraph(ArrayList<Point> points, String xAxis, String yAxis, Color colour) {
        this.points = points;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.colour = colour;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /*
     * LineGraph empty constructor creates a blank LineGraph object with default values
     */
    public LineGraph() {
        this.points = new ArrayList();
        this.xAxis = "";
        this.yAxis = "";
        this.colour = Color.BLACK;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int BORDER_OFFSET = 15; // number of pixels from border to draw axes of graph
        
        g.setColor(Color.BLACK);
        
        // draw x axis for line graph
        g.drawLine(BORDER_OFFSET, this.getBounds().height - BORDER_OFFSET, this.getBounds().width - BORDER_OFFSET, this.getBounds().height - BORDER_OFFSET); // x1, y1, x2, y2
        
        // draw y axis for line graph
        g.drawLine(BORDER_OFFSET, BORDER_OFFSET, BORDER_OFFSET, this.getBounds().height - BORDER_OFFSET); // x1, y1, x2, y2
        
        System.out.println("width: "+this.getBounds().width + ", height: "+this.getBounds().height);

    }

}
