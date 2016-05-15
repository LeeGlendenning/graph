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
    5. draw light gray mesh to mark ticks

Note:
    1. 20 pixels seems like a nice distance between points. Consider setting this as minimum or at least scale spacing to it (i.e. when window resizes)

*/


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.BorderFactory;

/**
 *
 * LineGraph class should extend Graph which should extend JComponent but for
 * now we will skip that
 */
public class LineGraph extends Graph {

    ArrayList<Point> points;
    
    private int maxX, maxY, minX, minY; // min and max values used for creating axes and scaling their size
    private int BORDER_OFFSET;

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
        this.xAxisLabel = xAxis;
        this.yAxisLabel = yAxis;
        this.colour = colour;
        init(); // set man/min xy and border for this JComponent
        
    }

    /*
     * LineGraph empty constructor creates a blank LineGraph object with default values
     */
    public LineGraph() {
        this.points = new ArrayList();
        this.xAxisLabel = "";
        this.yAxisLabel = "";
        this.maxX = 0;
        this.maxY = 0;
        this.minX = 0;
        this.minY = 0;
        this.colour = Color.BLACK;
    }
    
    private void init(){
        this.maxX = getMaxXY().x;
        this.maxY = getMaxXY().y;
        this.minX = getMinXY().x;
        this.minY = getMinXY().y;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        BORDER_OFFSET = (int) (0.1*Math.min(this.getBounds().width, this.getBounds().height)); // number of pixels from border to draw axes of graph. 10% of min(width, height)
        
        drawAxes(g);
        drawTitles(g);
        drawPoints(g);
        
        
        System.out.println("width: "+this.getBounds().width + ", height: "+this.getBounds().height+". border offset: " + BORDER_OFFSET);
    }
    
    @Override
    protected void drawAxes(Graphics g){
        g.setColor(Color.BLACK);
        
        // draw x axis for line graph
        g.drawLine(BORDER_OFFSET, this.getBounds().height - BORDER_OFFSET, this.getBounds().width - BORDER_OFFSET, this.getBounds().height - BORDER_OFFSET); // x1, y1, x2, y2
        
        // draw y axis for line graph
        g.drawLine(BORDER_OFFSET, BORDER_OFFSET, BORDER_OFFSET, this.getBounds().height - BORDER_OFFSET); // x1, y1, x2, y2
    }
    
    @Override
    protected void drawTitles(Graphics g){
        g.setColor(Color.BLACK);
        
        // draw title for line graph
        g.drawString(title, BORDER_OFFSET + 50, BORDER_OFFSET); // +50 is just so it looks centered for now
        
        // draw x axis label
        g.drawString(xAxisLabel, BORDER_OFFSET, this.getBounds().height - BORDER_OFFSET/4);
        
        // draw y axis label
        //g.drawString(yAxisLabel, , );
    }
    
    protected void drawPoints(Graphics g){
        g.setColor(colour);
        
        for (int i = 0; i < points.size(); i ++) {
            // draw just the points
            //g.drawLine(BORDER_OFFSET + points.get(i).x, 100 - points.get(i).y, BORDER_OFFSET + points.get(i).x, 100 - points.get(i).y);
            
            
            if (i < points.size() - 1){ // don't want to draw a line from the last point to nothing. Will make sense when points are drawn bigger (commented line above)
                // draw line connected 2 points
                g.drawLine(BORDER_OFFSET + points.get(i).x, (this.getBounds().height - BORDER_OFFSET) - points.get(i).y, BORDER_OFFSET + points.get(i+1).x, (this.getBounds().height - BORDER_OFFSET) - points.get(i+1).y);
            }
        }
    }
    
    public void addPoint(int x, int y){
        points.add(new Point(x, y));
        if (x > maxX){
            maxX = x;
        }
        if (y > maxY){
            maxY = y;
        }
    }
    
    @Override
    protected Point getMaxXY(){
        if (points.isEmpty()){
            return new Point(0, 0);
        }
        Point max = new Point(points.get(0).x, points.get(0).y);
        for (int i = 1; i < points.size(); i ++){
            if (points.get(i).x > max.x){
                max.x = points.get(i).x;
            }
            if (points.get(i).y > max.y){
                max.y = points.get(i).y;
            }
        }
        return max;
    }
    
    @Override
    protected Point getMinXY(){
        if (points.isEmpty()){
            return new Point(0, 0);
        }
        Point min = new Point(points.get(0).x, points.get(0).y);
        for (int i = 1; i < points.size(); i ++){
            if (points.get(i).x < min.x){
                min.x = points.get(i).x;
            }
            if (points.get(i).y < min.y){
                min.y = points.get(i).y;
            }
        }
        return min;
    }
    
    
    
}
