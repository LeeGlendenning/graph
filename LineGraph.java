/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/*
To Do:
    1. consider: if jpanel gets too small (where titles are overlapping axes), don't draw axes titles
    2. figure out how to get width of string in particular font and size. then use that to centre titles
    3. figure out how to draw strings vertically. for y axis title
    4. draw more noticeable point (bigger) in drawPoints()

Note:
    1. 20 pixels seems like a nice distance between points. Consider setting this as minimum or at least scale spacing to it (i.e. when window resizes)

*/


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
    String title;
    String xAxis;
    String yAxis;
    Color colour;
    int BORDER_OFFSET;

    /*
     * LineGraph constructor creates a LineGraph object to be drawn to the screen
     * 
     * @param points        ArrayList of Point objects. Data to display on the line graph
     * @param title         String title for line graph
     * @param xAxis         String label for the x axis of the line graph
     * @param yAxis         String label for the y axis of the line graph
     * @param colour        Color object to use when drawing the line graph
     */
    public LineGraph(ArrayList<Point> points, String title, String xAxis, String yAxis, Color colour) {
        this.points = points;
        this.title = title;
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
        
        BORDER_OFFSET = (int) (0.1*Math.min(this.getBounds().width, this.getBounds().height)); // number of pixels from border to draw axes of graph. 10% of min(width, height)
        
        g.setColor(Color.BLACK);
        
        //draw title for line graph
        g.drawString(title, BORDER_OFFSET + 50, BORDER_OFFSET); // +50 is just so it looks centered for now
        
        // draw x axis for line graph
        g.drawLine(BORDER_OFFSET, this.getBounds().height - BORDER_OFFSET, this.getBounds().width - BORDER_OFFSET, this.getBounds().height - BORDER_OFFSET); // x1, y1, x2, y2
        g.drawString(xAxis, BORDER_OFFSET, this.getBounds().height - BORDER_OFFSET/4);
        
        // draw y axis for line graph
        g.drawLine(BORDER_OFFSET, BORDER_OFFSET, BORDER_OFFSET, this.getBounds().height - BORDER_OFFSET); // x1, y1, x2, y2
        //g.drawString(yAxis, , );
        
        
        drawPoints(g);
        
        
        System.out.println("width: "+this.getBounds().width + ", height: "+this.getBounds().height+". border offset: " + BORDER_OFFSET);
    }
    
    private void drawPoints(Graphics g){
        for (int i = 0; i < points.size(); i ++) {
            // draw points
            //g.drawLine(BORDER_OFFSET + points.get(i).x, 100 - points.get(i).y, BORDER_OFFSET + points.get(i).x, 100 - points.get(i).y);
            
            if (i < points.size() - 1){ // so only a point gets drawn in the last iteration
                g.drawLine(BORDER_OFFSET + points.get(i).x, (this.getBounds().height - BORDER_OFFSET) - points.get(i).y, BORDER_OFFSET + points.get(i+1).x, (this.getBounds().height - BORDER_OFFSET) - points.get(i+1).y);
            }
        }
    }
    
}
