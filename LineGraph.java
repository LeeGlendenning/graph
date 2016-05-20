/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/*
To Do:
    1. figure out how to get width of string in particular font and size. then use that to centre titles
    2. figure out how to draw strings vertically. for y axis title
    3. draw ticks - figure out how to decide what increments to use
    4. add ability to show multiple plots in same graph

Consider:
    1. if jpanel gets too small (where titles are overlapping axes), don't draw axes titles
    2. draw light gray mesh to mark ticks
*/


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.BorderFactory;

/**
 *
 * LineGraph class should extend Graph which should extend JComponent but for
 * now we will skip that
 */
public class LineGraph extends Graph {

    ArrayList<Point> points;
    
    private int maxX, maxY, minX, minY; // min and max values used for creating axes and scaling their size
    private int borderOffset;

    /*
     * LineGraph constructor creates a LineGraph object to be drawn to the screen
     * 
     * @param points        ArrayList of Point objects. Data to display on the line graph
     * @param title         String title for line graph
     * @param xAxis         String label for the x axis of the line graph
     * @param yAxis         String label for the y axis of the line graph
     * @param colour        Color object to use when drawing the line graph
     */
    public LineGraph(ArrayList<Point> ps, String title, String xAxis, String yAxis, Color colour) {
        this.points = new ArrayList();
        // points actually added in init
        this.title = title;
        this.xAxisLabel = xAxis;
        this.yAxisLabel = yAxis;
        this.colour = colour;
        init(ps); // set man/min xy and border for this JComponent
        
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
    
    private void init(ArrayList<Point> ps){
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addPoints(ps);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        borderOffset = (int) (0.1*Math.min(this.getBounds().width, this.getBounds().height)); // number of pixels from border to draw axes of graph. 10% of min(width, height)
        
        updateGraph(g);
        
        //System.out.println("width: "+this.getBounds().width + ", height: "+this.getBounds().height+". border offset: " + borderOffset);
    }
    
    @Override
    protected void updateGraph(Graphics g){
        //drawAxes(g);
        drawTitles(g);
        drawGraph(g);
    }
    
    /*private void updateMinMax(){
        this.maxX = getMaxXY().x;
        this.maxY = getMaxXY().y;
        this.minX = getMinXY().x;
        this.minY = getMinXY().y;
    }*/
    
    @Override
    protected void drawTitles(Graphics g){
        g.setColor(Color.BLACK);
        
        // draw title for line graph
        g.drawString(title, borderOffset + 50, borderOffset); // +50 is just so it looks centered for now
        
        // draw x axis label
        g.drawString(xAxisLabel, borderOffset, this.getBounds().height - borderOffset/4);
        
        // draw y axis label
        //g.drawString(yAxisLabel, , );
    }
    
    @Override
    protected void drawGraph(Graphics g){
        sortPointsByX(); // drawing line between points assumes points are sorted by x value
        //updateMinMax();
        
        int xBuff = Math.round((float)0.1*(maxX - minX)); // 10% of difference between maxX and minX
        int yBuff = Math.round((float)0.1*(maxY - minY)); // 10% of difference between maxY and minY
        
        int ptWidth = (maxX - minX) + 2*xBuff; // divide graph into ptWidth number of intervals along x axis
        int ptHeight = (maxY - minY) + 2*yBuff; // divide graph into ptHeight number of intervals along y axis
        
        float xSpacing = ((float)this.getBounds().width / ptWidth); // size of intervals along x axis
        float ySpacing = ((float)this.getBounds().height / ptHeight); // size of intervals along y axis
        
        drawPoints(g, xBuff, yBuff, ptWidth, ptHeight, xSpacing, ySpacing);
        drawAxes(g, xBuff, yBuff, ptWidth, ptHeight, xSpacing, ySpacing);
    }
    
    private void drawPoints(Graphics g, int xBuff, int yBuff, int ptWidth, int ptHeight, float xSpacing, float ySpacing){
        g.setColor(colour);
        
        for (int i = 0; i < points.size(); i ++){
            int xDiff = points.get(i).x - minX + xBuff;
            int yDiff = points.get(i).y - minY + yBuff;
            
            //draw the point
            g.fillOval(Math.round(xDiff * xSpacing)-2, this.getBounds().height - Math.round(yDiff * ySpacing)-2, 4, 4);
            
            if (i != points.size()-1){ // is last point, don't need to connect anymore points
                int xDiffNext = points.get(i+1).x - minX + xBuff;
                int yDiffNext = points.get(i+1).y - minY + yBuff;
                // draw line connecting 2 points
                g.drawLine(Math.round(xDiff * xSpacing), this.getBounds().height - Math.round(yDiff * ySpacing), Math.round(xDiffNext * xSpacing), this.getBounds().height - Math.round(yDiffNext * ySpacing));
            }
        }
    }
    
    private void drawAxes(Graphics g, int xBuff, int yBuff, int ptWidth, int ptHeight, float xSpacing, float ySpacing){
        g.setColor(Color.BLACK);
        //System.out.println("minX = " + minX + ", maxX = " + maxX);
        
        int xDiff, yDiff;
        
        // draw x axis
        if (minX < 0 && maxX > 0){ // x=0 is in our graph so draw y-axis there
            xDiff = -minX + xBuff;
            g.drawLine(Math.round(xDiff * xSpacing), Math.round(yBuff * ySpacing), Math.round(xDiff * xSpacing), this.getBounds().height - Math.round(yBuff * ySpacing));
        }else if (minX <= 0){ // maxX is negative so draw y-axis on right side of graph
            xDiff = maxX - minX + xBuff;
            g.drawLine(Math.round(xDiff * xSpacing), Math.round(yBuff * ySpacing), Math.round(xDiff * xSpacing), this.getBounds().height - Math.round(yBuff * ySpacing));
        }else if (maxX >= 0){ // minX is positive so draw y-axis on left side of graph
            g.drawLine(Math.round(xBuff * xSpacing), Math.round(yBuff * ySpacing), Math.round(xBuff * xSpacing), this.getBounds().height - Math.round(yBuff * ySpacing));
        }
        
        // draw y axis
        if (minY < 0 && maxY > 0){ // y=0 is in our graph so draw x-axis there
            yDiff = -minY + yBuff;
            g.drawLine(Math.round(xBuff * xSpacing), this.getBounds().height - Math.round(yDiff * ySpacing), this.getBounds().width - Math.round(xBuff * xSpacing), this.getBounds().height - Math.round(yDiff * ySpacing));
        }else if (minY <= 0){ // maxY is negative so draw x-axis at top of graph
            yDiff = maxY - minY + yBuff;
            g.drawLine(Math.round(xBuff * xSpacing), this.getBounds().height - Math.round(yDiff * ySpacing), this.getBounds().width - Math.round(xBuff * xSpacing), this.getBounds().height - Math.round(yDiff * ySpacing));
        }else if (maxY >= 0){ // minY is positive so draw x-axis at bottom of graph
            g.drawLine(Math.round(xBuff * xSpacing), this.getBounds().height - Math.round(yBuff * ySpacing), this.getBounds().width - Math.round(xBuff * xSpacing), this.getBounds().height - Math.round(yBuff * ySpacing));
        }
    }
    
    private void sortPointsByX(){
        Collections.sort(points, new PointCompare());
    }
    
    public class PointCompare implements Comparator<Point> {

        @Override
        public int compare(Point a, Point b) {
            if (a.x < b.x) {
                return -1;
            }else if (a.x > b.x) {
                return 1;
            }else {
                return 0;
            }
        }
    }
    
    public void addPoints(ArrayList<Point> ps){
        for (int i = 0; i < ps.size(); i ++){
            addPoint(ps.get(i));
        }
    }
    
    public void addPoint(Point p){
        if (pointIsAllowed(p)){
            points.add(new Point(p.x, p.y));
            if (p.x > maxX){
                this.maxX = p.x;
            }else if (p.x < minX){
                this.minX = p.x;
            }
            if (p.y > maxY){
                this.maxY = p.y;
            }else if (p.y < minY){
                this.minY = p.y;
            }
        }
    }
    
    // returns false if a point with the given x value already exists in points ArrayList
    private boolean pointIsAllowed(Point p){
        for (int i = 0; i < points.size(); i ++){
            if (points.get(i).x == p.x){
                return false;
            }
        }
        return true;
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
